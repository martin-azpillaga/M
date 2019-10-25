package m.transformation

import java.util.ArrayList
import java.util.HashMap
import java.util.List
import m.CSRuntimeModule
import m.cs.Command
import m.cs.CsFactory
import m.m.Expression
import m.cs.Member
import m.cs.RelationType
import m.m.Access
import m.m.Brackets
import m.m.Call
import m.m.Branch
import m.m.Decrement
import m.m.Divide
import m.m.Game
import m.m.Increment
import m.m.Loop
import m.m.Minus
import m.m.Modulus
import m.m.Plus
import m.m.System
import m.m.Times
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.generator.IFileSystemAccess2

import static m.cs.AssignmentType.*
import static m.cs.TypeEnum.*
import static m.cs.Visibility.*
import static m.transformation.GenericSerializer.*
import m.m.Condition
import m.m.Or
import m.m.And
import m.m.Not
import m.m.Comparison
import m.yaml.YamlFactory
import m.YAMLRuntimeModule
import m.m.Assignment
import m.cs.Block
import java.util.HashSet

class SystemGenerator 
{
	extension CsFactory factory = CsFactory.eINSTANCE
	extension FactoryHelper helper = FactoryHelper.eINSTANCE
	YamlFactory yaml = YamlFactory.eINSTANCE
	
	HashMap<String,Type> types
	HashSet<String> usings
	
	def generate(Game game, HashMap<String,Type> types, IFileSystemAccess2 fsa)
	{
		this.types = types
		
		var csharp = new CSRuntimeModule
		var yamlModule = new YAMLRuntimeModule
		
		for (system : game.systems)
		{
			fsa.generateFile('src-gen/'+system.name+'.done','')
			var s = system as System
			var file = s.convert
			generate(file, csharp, fsa, 'Pure Unity/Assets/Code/Systems/'+s.name+'.cs')
			
			var meta = yaml.createFile
			meta.nodes.add('fileFormatVersion', 2)
			meta.nodes.add('guid', (s.name+'system').uuid)
			
			generate(meta, yamlModule, fsa, 'Pure Unity/Assets/Code/Systems/'+s.name+'.cs.meta')
		}
		
		var contactSystem = contactSystem
		generate(contactSystem, csharp, fsa, 'Pure Unity/Assets/Code/Systems/Contacts.cs')
		var meta = yaml.createFile
		meta.nodes.add('fileFormatVersion', 2)
		meta.nodes.add('guid', ('Contacts'+'system').uuid)
		
		generate(meta, yamlModule, fsa, 'Pure Unity/Assets/Code/Systems/Contacts.cs.meta')
		//var entryPoint = entryPoint(game.systems)
		//generate(entryPoint, csharp, fsa, 'Hybrid Unity/Assets/Code/Systems/Main.cs')
	}
	
	def entryPoint(List<System> systems)
	{
		var file = createFile
		
		file.addUsing('UnityEngine')
		file.addUsing('Unity','Entities')
		
		var member = createMember
		member.visibility = PUBLIC
		var class = createType
		member.value = class
		class.type = CLASS
		class.name = 'Main'.type
		file.types.add(member)
		
		class.members.add(runMethod(systems))
		
		return file
	}
	
	def runMethod(List<System> systems)
	{
		var member = createMember
		member.visibility = PRIVATE
		member.static = true
		
		var runtimeAnnotation = createAnnotation
		runtimeAnnotation.function = 'RuntimeInitializeOnLoadMethod'.type
		runtimeAnnotation.parameters.add(a.label('RuntimeInitializeLoadType').label('BeforeSceneLoad'))
		member.annotations.add(runtimeAnnotation)
		member.static = true
		
		var run = createMethod
		run.type = 'void'.type
		run.name = 'Run'
		member.value=run
		
		var worldAssignment = createAssignment
		worldAssignment.setVar = true
		worldAssignment.access = a.label('world')
		worldAssignment.assignmentType = SET
		var call = a.call('World', string('world'))
		call.setNew = true
		worldAssignment.expression = call
		
		run.commands.add(worldAssignment)
		
		for (system : systems)
		{
			var create = createAssignment
			var newSystem = a.call((system as System).name)
			newSystem.setNew = true
			create.access = a.label('world').call('AddSystem', newSystem)
			
			run.commands.add(create)
		}

		var setWorld = createAssignment
		setWorld.access = a.label('World').label('Active')
		setWorld.assignmentType = SET
		setWorld.expression = a.label('world')
				
		run.commands.add(setWorld)
		
		return member
	}
	
	def convert(System system)
	{
		var file = createFile
		
		usings = new HashSet<String>
		
		file.types.add(systemClass(system))
		
		for (using : usings)
		{
			file.addUsing(using.split('\\.'))
		}
		return file
	}
	
	def systemClass(System system)
	{
		var member = createMember
		member.visibility = PUBLIC
		
		var class = createType
		class.type = CLASS
		class.name = system.name.type
		class.superTypes.add('ComponentSystem'.type)
		
		usings.add('Unity.Entities')
		
		class.members.add(update(system))
		
		member.value = class
		return member
	}
	
	def entityQueries(System system)
	{
		var result = new ArrayList<Member>
		
		var loops = EcoreUtil2.getAllContentsOfType(system, Loop)
		for (loop : loops)
		{
			var member = createMember
			member.visibility = PRIVATE
			
			var field = createField
			field.type = 'EntityQuery'.type
			field.name = loop.entity
			
			member.value = field
			result.add(member)
		}
		
		return result
	}
	
	def onCreate(System system)
	{
		var member = createMember
		member.visibility = PROTECTED
		
		var method = createMethod
		method.override = true
		method.type = 'void'.type
		method.name = 'OnCreate'
		
		var loops = EcoreUtil2.getAllContentsOfType(system,Loop)
		for (loop : loops)
		{
			method.commands.add(getQuery(system, loop))
		}
		
		member.value = method
		return member
	}
	
	def getQuery(System system, Loop loop)
	{
		var command = createAssignment
		command.access = a.label(loop.entity)
		command.assignmentType = SET
		
		var expression = createAccess
		
		var components = new ArrayList<m.cs.Expression>
		
		val writes = EcoreUtil2.getAllContentsOfType(loop, Assignment).filter[it.access.names.size > 1 && it.access.names.head == loop.entity].map[it.access.names.get(1)]
		var reads = EcoreUtil2.getAllContentsOfType(loop, Access).filter[it.names.size > 1 && it.names.head == loop.entity].filter[!writes.toList.contains(it.names.get(1))].map[it.names.get(1)]
		
		for (write : writes)
		{
			var writeAccess = a.label('ComponentType').call('ReadWrite',write.type)
			components.add(writeAccess)
		}
		for (read : reads)
		{
			var readAccess = a.label('ComponentType').call('ReadOnly',read.type)
			components.add(readAccess)
		}
		expression.call('GetEntityQuery', components)
		
		command.expression = expression
		
		return command
	}
	
	def onUpdate(System system)
	{
		var member = createMember
		member.visibility = PROTECTED
		
		var method = createMethod
		method.override = true
		method.type = 'JobHandle'.type
		method.name = 'OnUpdate'
		
		var dependencies = createArgument
		dependencies.type = 'JobHandle'.type
		dependencies.name = 'dependencies'
		method.arguments.add(dependencies)
		
		method.commands.add(scheduleJob(system))
		
		member.value = method
		return member
	}
	
	def update(System system)
	{
		var member = createMember
		member.visibility = PROTECTED
		
		var method = createMethod
		method.override = true
		method.type = 'void'.type
		method.name = 'OnUpdate'
		
		for (command : system.commands)
		{
			method.commands.addAll(commands(system).commands)
		}
		
		member.value = method
		return member
	}
	
	def scheduleJob(System system)
	{
		var command = createReturn
		
		var newJob = createAccess
		newJob.setNew = true
		
		var initializations = new ArrayList<m.cs.InitializeVariable>
		
		var loops = EcoreUtil2.getAllContentsOfType(system, Loop)
		for (loop : loops)
		{
			var initialization = createInitializeVariable
			initialization.name = loop.entity
			initialization.expression = a.label(loop.entity).call('CreateArchetypeChunkArray', a.label('Allocator').label('TempJob'))
			
			initializations.add(initialization)
		}
		
		var types = typesIn(system)
		for (type : types.keySet)
		{
			var sendType = createInitializeVariable
			sendType.name = type
			sendType.expression = a.call('GetArchetypeChunkComponentType', type.type, a.label((types.get(type)).toString))
			initializations.add(sendType)
		}
		
		if (system.makesStructuralChanges)
		{
			var sendBuffer = createInitializeVariable
			sendBuffer.name = 'buffer'
			sendBuffer.expression = a.label('World').label('Active').call('GetExistingSystem','EndSimulationEntityCommandBufferSystem'.type).call('CreateCommandBuffer').call('ToConcurrent')
			initializations.add(sendBuffer)
		}
		newJob.initialize('Job', initializations).call('Schedule',a.label('dependencies'))
		
		
		
		command.expression = newJob
		
		return command
	}
	
	def job(System system)
	{
		var member = createMember
		member.visibility = PRIVATE
		
		var struct = createType
		struct.type = STRUCT
		struct.name = 'Job'.type
		struct.superTypes.add('IJob'.type)
		struct.members.addAll(jobFields(system))
		struct.members.add(execute(system))
		
		member.value = struct
		
		return member
	}
	
	def jobFields(System system)
	{
		var result = new ArrayList<Member>
		
		var loops = EcoreUtil2.getAllContentsOfType(system, Loop)
		for (loop : loops)
		{
			var member = createMember
			member.visibility = PUBLIC
			member.annotations.add(annotation('DeallocateOnJobCompletion'))
			
			var chunk = createField
			chunk.type = type(name('NativeArray'),name('ArchetypeChunk'))
			chunk.name = loop.entity
			member.value = chunk
			
			result.add(member)	
		}
		
		var types = typesIn(system)
		for (type : types.keySet)
		{
			var member = createMember
			member.visibility = PUBLIC
			if (types.get(type))
			{
				member.annotations.add(annotation('ReadOnly'))
			}
			
			var declareType = createField
			declareType.type = type(name('ArchetypeChunkComponentType'), name(type))
			declareType.name = type
			member.value = declareType
			
			result.add(member)
		}
		
		if (system.makesStructuralChanges)
		{
			var member = createMember
			member.visibility = PUBLIC
			
			var field = createField
			field.type = type(name('EntityCommandBuffer','Concurrent'))
			field.name = 'buffer'
			member.value = field
			
			result.add(member)
		}
		
		return result
	}
	
	def makesStructuralChanges(System system)
	{
		var structural = EcoreUtil2.getAllContentsOfType(system, Call).map[name]
		
		return (structural).exists[#['add','remove','create','destroy'].contains(it)]
	}
	
	def makesStructuralChanges(Loop loop)
	{
		var calls = EcoreUtil2.getAllContentsOfType(loop, Call)
		
		for (call : calls)
		{
			if (call.name == 'add' || call.name=='remove')
			{
				var entity = call.parameters.get(1)
				if (entity instanceof Access)
				{
					if (entity.names.head == loop.entity)
					{
						return true
					}
				}
			}
			else if (call.name == 'destroy')
			{
				var entity = call.parameters.get(0)
				if (entity instanceof Access)
				{
					if (entity.names.head == loop.entity)
					{
						return true
					}
				}
			}
		}	
		return false
	}
	
	def typesIn(System system)
	{
		var map = new HashMap<String,Boolean>
		
		val writes = EcoreUtil2.getAllContentsOfType(system, Assignment).filter[it.access.names.size > 1].map[it.access.names.get(1)]
		var reads = EcoreUtil2.getAllContentsOfType(system, Access).filter[it.names.size > 1].filter[!writes.toList.contains(it.names.get(1))].map[it.names.get(1)]
		
		for (write : writes.toSet)
		{
			map.put(write, false)
			if (#['position','rotation','scale'].contains(write))
			{
				usings.add('Unity.Transforms')
			}
			else if (#['mass','velocity','acceleration','force','restitution','friction','extent','radius'].contains(write))
			{
				usings.add('Unity.Physics')
			}
		}
		for (read : reads.toSet)
		{
			map.put(read, true)
			if (#['position','rotation','scale'].contains(read))
			{
				usings.add('Unity.Transforms')
			}
			else if (#['mass','restitution','friction','extent','radius'].contains(read))
			{
				usings.add('Unity.Physics')
			}
		}
		
		
		return map
	}
	
	def execute(System system)
	{
		var member = createMember
		member.visibility = PUBLIC
		
		var method = createMethod
		method.type = 'void'.type
		method.name = 'Execute'
		
		method.commands.addAll(commands(system).commands)
		
		member.value = method
		return member
	}
	
	def commands(System system)
	{
		var result = createBlock
		
		for (command : system.commands)
		{
			result.addCommand(command)
		}
		return result
	}
	
	def void addCommand(Block list, m.m.Command command)
	{
		if (command instanceof Call)
		{
			var function = command.name
			if (function == 'remove')
			{
				var call = createAssignment
				var access = a.label('PostUpdateCommands')
				var methodCall = createMethodCall
				methodCall.name = 'RemoveComponent'
				methodCall.generics.add((command.parameters.get(0) as Access).names.head.type)
				methodCall.expressions.add(command.parameters.get(1).toCS)
				access.portions.add(methodCall)
				call.access = access
				list.commands.add(call)
			}
			else if (function == 'add')
			{
				var call = createAssignment
				call.access = a.label('PostUpdateCommands').call('AddComponent', (command.parameters.get(0) as Access).names.head.type, command.parameters.get(1).toCS)
				list.commands.add(call)
			}
			else if (function == 'destroy')
			{
				var call = createAssignment
				call.access = a.label('PostUpdateCommands').call('DestroyEntity', command.parameters.get(0).toCS)
				list.commands.add(call)
			}
			else if (function == 'create')
			{
				var call = createAssignment
				call.access = a.label('PostUpdateCommands').call('Instantiate', command.parameters.get(0).toCS)
				list.commands.add(call)
			}
		}
		else if (command instanceof Assignment)
		{
			if (command.access.names.size == 2)
			{
				var created = createAssignment
				created.access = a.label(command.access.names.head+'_'+command.access.names.get(1)).label(field(command.access.names.get(1)))
				created.assignmentType = command.type.toCS
				created.expression = command.expression.toCS
				list.commands.add(created)
			}
			else if (command.access.names.size == 1)
			{
				var declaration = createAssignment
				var container = command.eContainer
				while (!(container instanceof Loop || container instanceof System))
				{
					container = EcoreUtil2.getContainerOfType(container, Loop)
				}
				var hash = command.access.names.head+'@'+container.hashCode
				var type = types.get(hash).stringName.type
				declaration.type = type
				declaration.access = a.label(command.access.names.head)
				declaration.assignmentType = SET
				declaration.expression = a.call('default', a.label(type.name.names.head))
				
				var assignment = EcoreUtil2.getContainerOfType(list, m.cs.Assignment)
				var system = assignment.eContainer as m.cs.Block
				system.commands.add(0,declaration)
				
				
				var created = createAssignment
				created.access = a.label(command.access.names.head)
				created.assignmentType = command.type.toCS
				created.expression = command.expression.toCS
				list.commands.add(created)
			}
			else
			{
				var newComponent = createAccess
				newComponent.setNew = true
				newComponent.initialize(command.access.names.last.componentName, initialization(command.access.names.last.field, command.expression.toCS))
				var created = createAssignment
				created.access = a.label('EntityManager').call('SetComponentData', a.label('my_'+command.access.names.head+'_'+command.access.names.get(1)).label('Value'), newComponent)
				list.commands.add(created)
			}
		}
		else if (command instanceof Loop)
		{
			val writes = EcoreUtil2.getAllContentsOfType(command, Assignment).filter[it.access.names.size > 1 && it.access.names.head == command.entity].map[it.access.names.get(1)]
			var reads = EcoreUtil2.getAllContentsOfType(command, Access).filter[it.names.size > 1 && it.names.head == command.entity].filter[!writes.toList.contains(it.names.get(1))].map[it.names.get(1)]
			var lambda = createLambda
			var entity = command.entity
			if (command.makesStructuralChanges)
			{
				lambda.arguments.add(argument('Entity'.type, entity))
			}
			for (w : writes.toSet)
			{
				if (w.isShared)
				{
					lambda.arguments.add(0, argument(w.componentName.type, entity+'_'+w))
					var my = createAssignment
					my.setVar = true
					my.access = a.label('my_'+entity+'_'+w)
					my.expression = a.label(entity+'_'+w)
					lambda.commands.add(my)
				}
				else
				{
					lambda.arguments.add(refArgument(w.componentName.type, entity+'_'+w))
					var my = createAssignment
					my.setVar = true
					my.access = a.label('my_'+entity+'_'+w)
					my.expression = a.label(entity+'_'+w)
					lambda.commands.add(my)
				}
			}
			for (r : reads.toSet)
			{
				if (r.isShared)
				{
					lambda.arguments.add(0, argument(r.componentName.type, entity+'_'+r))
					var my = createAssignment
					my.setVar = true
					my.access = a.label('my_'+entity+'_'+r)
					my.expression = a.label(entity+'_'+r)
					lambda.commands.add(my)
				}
				else
				{
					lambda.arguments.add(refArgument(r.componentName.type, entity+'_'+r))
					var my = createAssignment
					my.setVar = true
					my.access = a.label('my_'+entity+'_'+r)
					my.expression = a.label(entity+'_'+r)
					lambda.commands.add(my)
				}
			}
			var foreach = createAssignment
			var access = a.label('Entities')
			
			for (constraint : command.constraints)
			{
				var methodCall = createMethodCall
				methodCall.name = 'WithAll'
				methodCall.generics.add(constraint.type)
				access.portions.add(methodCall)
			}
			foreach.access = access.call('ForEach',lambda)
			list.commands.add(foreach)
			
			for (c : command.commands)
			{
				lambda.addCommand(c)
			}
			
		}
		else if (command instanceof Branch)
		{
			val branch = createIf
			var ifCondition = command.^if
			list.commands.add(branch)
			
			branch.expression = ifCondition.condition.toCS
			ifCondition.commands.forEach[branch.addCommand(it)]
			
			
			for (elseIf : command.elseIfs)
			{
				val csharpElseIf = createElseIf
				var elseIfCondition = elseIf as Condition
				csharpElseIf.expression = elseIfCondition.condition.toCS
				elseIfCondition.commands.forEach[csharpElseIf.addCommand(it)]
			}
			
			if (command.commands.size > 0)
			{
				val csharpElse = createElse
				command.commands.forEach[csharpElse.addCommand(it)]
			}
		}
	}
	
	def componentName(String name)
	{
		if (name == 'velocity')
		{
			usings.add('Unity.Physics')
			return 'PhysicsVelocity'
		}
		else if (name == 'position')
		{
			usings.add('Unity.Transforms')
			return 'Translation'
		}
		else if (name == 'rotation')
		{
			usings.add('Unity.Transforms')
			return 'Rotation'
		}
		else if (name == 'angularVelocity')
		{
			usings.add('Unity.Physics')
			return 'PhysicsVelocity'
		}
		else
		{
			return name
		}
	}
	
	def isShared(String name)
	{
		return #[Type.input].contains(types.get(name))
	}
	
	def field(String name)
	{
		if (name == 'velocity')
		{
			return 'Linear'
		}
		else if (name == 'angularVelocity')
		{
			return 'Angular'
		}
		else
		{
			return 'Value'
		}
	}
	
	def void addCommandOld(List<Command> list, m.m.Command command)
	{
		if (command instanceof Call)
		{
			
		}
		else if (command instanceof Assignment)
		{
			var created = createAssignment
			created.access = a.label(command.access.names.head+'_'+command.access.names.get(1))
			for (var i = 2; i < command.access.names.size; i++)
			{
				created.access.label(command.access.names.get(i))
			}
			created.assignmentType = command.type.toCS
			created.expression = command.expression.toCS
			list.add(created)
		}
		else if (command instanceof Loop)
		{
			var outer = createFor
			var initialize_out = createAssignment
			initialize_out.setVar = true
			initialize_out.access = a.label(command.entity+'_out')
			initialize_out.expression = number(0)
			outer.initialization = initialize_out
			
			var condition = createComparison
			condition.left = a.label(command.entity+'_out')
			condition.type = RelationType.UNDER
			condition.right = a.label(command.entity).label('Length')
			outer.condition = condition
			
			var increment = createAssignment
			increment.access = a.label(command.entity+'_out')
			increment.increment = true
			outer.increment = increment
			
			outer.commands.add(getChunk(command.entity))
			outer.commands.addAll(getArrays(command))
			
			var inner = createFor
			var initialize_inner = createAssignment
			initialize_inner.setVar = true
			initialize_inner.access = a.label(command.entity+'_in')
			initialize_inner.expression = number(0)
			inner.initialization = initialize_inner
			
			var condition_inner = createComparison
			condition_inner.left = a.label(command.entity+'_in')
			condition_inner.type = RelationType.UNDER
			condition_inner.right = a.label(command.entity+'_chunk').label('Count')
			inner.condition = condition_inner
			
			var increment_inner = createAssignment
			increment_inner.access = a.label(command.entity+'_in')
			increment_inner.increment = true
			inner.increment = increment_inner
			
			
			outer.commands.add(inner)
			inner.commands.addAll(getDatas(command))
			for (c : command.commands)
			{
				inner.addCommand(c)
			}
			
			list.add(outer)
		}
		else if (command instanceof Branch)
		{
			
		}
	}
	
	def getChunk(String name)
	{
		var assignment = createAssignment
		assignment.setVar = true
		assignment.access = a.label(name+'_chunk')
		assignment.assignmentType = SET
		assignment.expression = a.index(name, a.label(name+'_out'))
		return assignment
	}
	
	def getArrays(Loop loop)
	{
		var group = loop.entity
		
		var result = new ArrayList<Command>
		
		val writes = EcoreUtil2.getAllContentsOfType(loop, Assignment).filter[it.access.names.size > 1 && it.access.names.head == loop.entity].map[it.access.names.get(1)]
		var reads = EcoreUtil2.getAllContentsOfType(loop, Access).filter[it.names.size > 1 && it.names.head == loop.entity].filter[!writes.toList.contains(it.names.get(1))].map[it.names.get(1)]
		
		for (type : writes+reads)
		{
			var assignment = createAssignment
			
			assignment.setVar = true
			assignment.access = a.label(group+'_'+type+'_array')
			assignment.assignmentType = SET
			assignment.expression = a.label(group+'_chunk').call('GetNativeArray', a.label(type))
			
			result.add(assignment)
		}
		
		
		return result
	}
	
	def getDatas(Loop loop)
	{
		var group = loop.entity
		
		var result = new ArrayList<Command>
		
		val writes = EcoreUtil2.getAllContentsOfType(loop, Assignment).filter[it.access.names.size > 1 && it.access.names.head == loop.entity].map[it.access.names.get(1)]
		var reads = EcoreUtil2.getAllContentsOfType(loop, Access).filter[it.names.size > 1 && it.names.head == loop.entity].filter[!writes.toList.contains(it.names.get(1))].map[it.names.get(1)]
		
		for (type : writes+reads)
		{
			var assignment = createAssignment
			
			assignment.setVar = true
			assignment.access = a.label(group+'_'+type)
			assignment.assignmentType = SET
			assignment.expression = a.index(group+'_'+type+'_array',a.label(group+'_in'))
			
			result.add(assignment)
		}
		
		return result
	}
	
	def m.cs.Expression toCS(Expression expression)
	{
		if (expression instanceof Or)
		{
			var created = createOr
			created.left = expression.left.toCS
			created.right = expression.right.toCS
			return created
		}
		else if (expression instanceof And)
		{
			var created = createAnd
			created.left = expression.left.toCS
			created.right = expression.right.toCS
			return created
		}
		else if (expression instanceof Not)
		{
			var created = createAnd
			created.left = expression.expression.toCS
			return created
		}
		else if (expression instanceof Brackets)
		{
			var created = createBrackets
			created.expression = expression.expression.toCS
			return created
		}
		else if (expression instanceof Comparison)
		{
			var created = createComparison
			created.left = expression.left.toCS
			created.right = expression.right.toCS
			created.type = expression.type.toCS
			return created
		}
		else if (expression instanceof Plus)
		{
			var created = createPlus
			created.left = expression.left.toCS
			created.right = expression.right.toCS
			return created
		}
		else if (expression instanceof Minus)
		{
			var created = createMinus
			created.left = expression.left.toCS
			created.right = expression.right.toCS
			return created
		}
		else if (expression instanceof Times)
		{
			var created = createTimes
			created.left = expression.left.toCS
			created.right = expression.right.toCS
			return created
		}
		else if (expression instanceof Divide)
		{
			var created = createDivide
			created.left = expression.left.toCS
			created.right = expression.right.toCS
			return created
		}
		else if (expression instanceof Modulus)
		{
			var created = createModulus
			created.left = expression.left.toCS
			created.right = expression.right.toCS
			return created
		}
		else if (expression instanceof Increment)
		{
			var created = createIncrement
			created.left = expression.expression.toCS
			return created
		}
		else if (expression instanceof Decrement)
		{
			var created = createDecrement
			created.left = expression.expression.toCS
			return created
		}
		else if (expression instanceof Access)
		{
			if (expression.names.size == 2)
			{
				var name = expression.names.get(1)
				var access = a.label('my_'+expression.names.head+'_'+name)
				
				return access.label('Value')
			}
			else if (expression.names.size == 1)
			{
				return a.label(expression.names.head)
			}
			else
			{
				return a.label('EntityManager').call('GetComponentData', expression.names.last.type, a.label('my_'+expression.names.head+'_'+expression.names.get(1)).label('Value'))
			}
		}
		else if (expression instanceof Call)
		{
			var function = expression.name
			if (#['sin','cos','tan','log','sqrt'].contains(function))
			{
				return a.label('math').call(function, expression.parameters.map[toCS])
			}
			else if (function == 'join')
			{
				usings.add('Unity.Mathematics')
				var newType = 'float'
				if (expression.parameters.size == 2)
				{
					newType+='2'
				}
				else if (expression.parameters.size == 3)
				{
					newType+='3'
				}
				else
				{
					newType+='4'
				}
				
				var call = a.call(newType, expression.parameters.map[toCS])
				call.setNew = true
				return call
			}
			else if (function == 'random')
			{
				usings.add('Unity.Mathematics')
				var e = expression.parameters.get(0).toCS as m.cs.Access
				return a.label('UnityEngine').label('Random').call('Range', #[(expression.parameters.get(0).toCS as m.cs.Access).label('x'), e.label('y')])
			}
			else if (function == 'has')
			{
				var call = a.label('EntityManager').call('HasComponent', (expression.parameters.get(1) as Access).names.head.type, expression.parameters.get(0).toCS)
				return call
			}
			else
			{
				return a.call(expression.name, expression.parameters.map[toCS])
			}
		}
	}
	
	def toCS(Access access)
	{
		var name = access.names.get(1)
		var result = a.label(access.names.head+'_'+name)
		
		return result.label('Value')
	}
	
	def toCS(m.m.RelationType type)
	{
		switch (type)
		{
			case EQUAL: RelationType.EQUAL
			case NOTEQUAL: RelationType.NOTEQUAL
			case OVER: RelationType.OVER
			case OVEROREQUAL: RelationType.OVEROREQUAL
			case UNDER: RelationType.UNDER
			case UNDEROREQUAL: RelationType.UNDEROREQUAL
		}
	}
	
	def contactSystem()
	{
		var system = createFile
		
		system.addUsing('Unity','Entities')
		system.addUsing('Unity', 'Jobs')
		system.addUsing('Unity', 'Physics')
		system.addUsing('Unity', 'Physics', 'Systems')
		system.addUsing('Unity', 'Mathematics')
		
		system.types.add(contactClass)
		system.types.add(contactA)
		system.types.add(contactB)
		
		return system
	}
	
	def contactClass()
	{
		var member = createMember
		member.visibility = PUBLIC
		member.annotations.add(annotation('AlwaysUpdateSystem'))
		
		var class = createType
		class.name = 'Contacts'.type
		class.superTypes.add('ComponentSystem'.type)
		
		class.members.add(contactUpdate())
		class.members.add(contactCallback())
		class.members.add(contactJob())
		
		member.value = class
		return member
	}
	
	def contactUpdate()
	{
		var member = createMember
		member.visibility = PROTECTED
		
		var method = createMethod
		method.override = true
		method.type = 'void'.type
		method.name = 'OnUpdate'
		
		var lambda = createLambda
		lambda.arguments.add(argument('Entity'.type, 'a'))
		lambda.arguments.add(refArgument('a'.type, 'something'))
		var destroy = createAssignment
		destroy.access = a.label('PostUpdateCommands').call('DestroyEntity',a.label('a'))
		lambda.commands.add(destroy)
		
		var foreach = createAssignment
		foreach.access = a.label('Entities').call('ForEach', lambda)
		
		method.commands.add(foreach)
		
		
		var step = createAssignment
		step.setVar = true
		step.access = a.label('step')
		step.assignmentType = SET
		step.expression = a.label('World').label('Active').call('GetExistingSystem','StepPhysicsWorld'.type)
		
		var enqueue = createAssignment
		enqueue.access = a.label('step').call('EnqueueCallback', a.label('SimulationCallbacks').label('Phase').label('PostCreateContacts'), a.label('callback'))
		
		method.commands.add(step)
		method.commands.add(enqueue)
		
		member.value = method
		return member
	}
	
	def contactCallback()
	{
		var member = createMember
		member.visibility = PRIVATE
		
		var method = createMethod
		method.type = 'JobHandle'.type
		method.name = 'callback'
		method.arguments.add(refArgument('ISimulation'.type, 'simulation'))
		method.arguments.add(refArgument('PhysicsWorld'.type, 'world'))
		method.arguments.add(argument('JobHandle'.type, 'dependencies'))
		
		
		
		var getHandle = createAssignment
		getHandle.setVar = true
		getHandle.access = a.label('handle')
		getHandle.assignmentType = SET
		var expression = createAccess
		expression.setNew = true
		var refWorld = createAccess
		refWorld.ref = true
		refWorld.label('world')
		expression.initialize('Job', initialization('buffer', a.label('World').label('Active').call('GetExistingSystem', 'EndSimulationEntityCommandBufferSystem'.type).call('CreateCommandBuffer').call('ToConcurrent'))).call('Schedule', a.label('simulation'), refWorld, a.label('dependencies'))
		getHandle.expression = expression
		
		method.commands.add(getHandle)
		
		var complete = createAssignment
		complete.access = a.label('handle').call('Complete')
		
		method.commands.add(complete)
		
		var returns = createReturn
		returns.expression = a.label('handle')
		
		method.commands.add(returns)
		
		member.value = method
		return member
	}
	
	def contactJob()
	{
		var member = createMember
		member.visibility = PRIVATE
		
		var struct = createType
		struct.type = STRUCT
		struct.name = 'Job'.type
		struct.superTypes.add('IContactsJob'.type)
		
		struct.members.add(buffer)
		struct.members.add(executeContacts)
		
		member.value = struct
		return member
	}
	
	def buffer()
	{
		var member = createMember
		member.visibility = PUBLIC
		
		var field = createField
		field.type = type(name('EntityCommandBuffer', 'Concurrent'))
		field.name = 'buffer'
		
		member.value = field
		return member
	}
	
	def executeContacts()
	{
		var member = createMember
		member.visibility = PUBLIC
		
		var method = createMethod
		method.type = 'void'.type
		method.name = 'Execute'
		method.arguments.add(refArgument('ModifiableContactHeader'.type, 'header'))
		method.arguments.add(refArgument('ModifiableContactPoint'.type, 'contact'))
		
		var getEntity = createAssignment
		getEntity.setVar = true
		getEntity.access = a.label('entity')
		getEntity.assignmentType = SET
		getEntity.expression = a.label('buffer').call('CreateEntity', 0.number)
		var newA = createAccess
		newA.setNew = true
		newA.initialize('a', initialization('Value', a.label('header').label('Entities').label('EntityA')))
		var newB = createAccess
		newB.setNew = true
		newB.initialize('b', initialization('Value', a.label('header').label('Entities').label('EntityB')))
		var createA = createAssignment
		createA.access = a.label('buffer').call('AddComponent', number(0), a.label('entity'), newA)
		var createB = createAssignment
		createB.access = a.label('buffer').call('AddComponent', number(0), a.label('entity'), newB)
		
		method.commands.add(getEntity)
		method.commands.add(createA)
		method.commands.add(createB)
		
		member.value = method
		return member
	}
	
	def contactA()
	{
		var member = createMember
		member.visibility = PUBLIC
		
		var struct = createType
		struct.type = STRUCT
		struct.name = 'a'.type
		struct.superTypes.add('IComponentData'.type)
		
		struct.members.add(entityValue)
		
		member.value = struct
		return member
	}
	
	def contactB()
	{
		var member = createMember
		member.visibility = PUBLIC
		
		var struct = createType
		struct.type = STRUCT
		struct.name = 'b'.type
		struct.superTypes.add('IComponentData'.type)
		
		struct.members.add(entityValue)
		
		member.value = struct
		return member
	}
	
	def entityValue()
	{
		var member = createMember
		member.visibility = PUBLIC
		
		var field = createField
		field.type = 'Entity'.type
		field.name = 'Value'
		member.value = field
		return member
	}
}