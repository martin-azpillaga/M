/*package m.transformation

import m.text.Game
import m.text.Real2
import m.text.impl.Real1Impl
import m.text.impl.Real2Impl
import m.text.Real1
import java.util.ArrayList
import m.text.Sprite
import java.awt.image.BufferedImage
import java.awt.Color
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO
import java.util.Random
import m.json.JsonFactory
import m.prefab.PrefabFactory
import m.prefab.Component
import m.prefab.Entity
import m.prefab.FieldValue
import java.util.List
import m.csharp.CsharpFactory
import m.csharp.File
import m.prefab.Number
import java.util.HashSet
import m.text.Loop
import java.util.HashMap
import org.eclipse.xtext.EcoreUtil2
import m.text.ComponentAssignment
import m.text.Access
import m.text.Initialization
import m.text.Check
import m.text.Pop
import m.text.VariableAssignment
import m.csharp.AssignmentType
import m.text.SubrutineCall
import m.csharp.CreatedAssignment
import m.text.Condition
import m.text.While
import m.text.BooleanExpression
import m.text.ArithmeticExpression
import m.text.Or

import m.text.Plus
import m.text.Minus
import m.text.Times
import m.text.Divide
import m.text.Exponentiation
import m.text.Modulus
import m.text.Increment
import m.text.Decrement
import m.text.ArithmeticBrackets
import m.text.Call
import m.text.And
import m.text.Not
import m.text.BooleanBrackets
import m.text.Comparison
import m.csharp.RelationType
import m.text.BooleanVariable
import m.text.CollisionLoop
import m.text.TextFactory
import m.text.EventType
import m.csharp.Command
import m.csharp.Control

class ModelToModel 
{
	var prefab = PrefabFactory.eINSTANCE
	var json = JsonFactory.eINSTANCE
	var csharp = CsharpFactory.eINSTANCE
	var text = TextFactory.eINSTANCE
	
	def textToCSharp(Game game)
	{
		var result = new ArrayList<File>
		var map = #{Real1Impl -> 'float', Real2Impl -> 'float2'}
		
		for (entity : game.entities)
		{
			for (component : entity.components)
			{
	    		var componentDefinition = csharp.createComponent
	    		var system = csharp.createQualifiedName
	    		system.names.add("System")
	    		componentDefinition.usings.add(system);
	    		var unityEngine = csharp.createQualifiedName
	    		unityEngine.names.add("UnityEngine")
	    		var entities = csharp.createQualifiedName
	    		entities.names.add("Unity")
	    		entities.names.add("Entities")
	    		var mathematics = csharp.createQualifiedName
	    		mathematics.names.add("Unity")
	    		mathematics.names.add("Mathematics")
	    		
	    		componentDefinition.usings.add(unityEngine);
	    		componentDefinition.usings.add(entities);
	    		componentDefinition.usings.add(mathematics);
	    		componentDefinition.name = component.name
	    		componentDefinition.className = '_' + component.name
	    		componentDefinition.proxy = component.name
	    		if (component.value !== null)
	    		{
	    			if (map.containsKey(component.value.class))
	    			{
	    				componentDefinition.type = map.get(component.value.class)
	    				componentDefinition.variable = "Value"
	    				componentDefinition.implicitType1 = map.get(component.value.class)
	    				componentDefinition.implicitType2 = component.name
	    				componentDefinition.implicitVariable1 = component.name
	    				componentDefinition.implicitVariable2 = component.name
	    				componentDefinition.implicitField = "Value"
	    				
	    				componentDefinition.implicitType3 = component.name
	    				componentDefinition.implicitType4 = map.get(component.value.class)
	    				componentDefinition.implicitVariable3 = "v"
	    				componentDefinition.implicitVariable4 = component.name
	    				componentDefinition.implicitField1 = "Value"
	    				componentDefinition.implicitVariable5 = "v"
	    				
	    			}
	    		}
	    		result.add(componentDefinition)
	    		
	    	}
	    	
	    }
	    return result
	    	
	}
	
	def components(HashMap<String,Type> components)
	{
		var result = new ArrayList<File>
		for (component : components.keySet)
		{
			var type = components.get(component).toString
			var componentDefinition = csharp.createComponent
    		var system = csharp.createQualifiedName
    		system.names.add("System")
    		componentDefinition.usings.add(system);
    		var unityEngine = csharp.createQualifiedName
    		unityEngine.names.add("UnityEngine")
    		var entities = csharp.createQualifiedName
    		entities.names.add("Unity")
    		entities.names.add("Entities")
    		var mathematics = csharp.createQualifiedName
    		mathematics.names.add("Unity")
    		mathematics.names.add("Mathematics")
    		
    		componentDefinition.usings.add(unityEngine);
    		componentDefinition.usings.add(entities);
    		componentDefinition.usings.add(mathematics);
    		componentDefinition.name = component
    		componentDefinition.className = '_' + component
    		componentDefinition.proxy = component
    		if (type !== 'tag')
    		{
    			if (type === 'float1')
    			{
    				type = 'float'
    			}
    			if (type === 'range')
    			{
    				var input = csharp.createInputComponent
    				var s = csharp.createUsing
    				s.name = system
    				var ue = csharp.createUsing
    				ue.name = unityEngine
    				var entiti = csharp.createUsing
    				entiti.name = entities
    				var math = csharp.createUsing
    				math.name = mathematics
    				var inputSystem = csharp.createUsing
    				var inputSystemName = csharp.createQualifiedName
    				inputSystemName.names.addAll(#['UnityEngine','InputSystem'])
    				inputSystem.name = inputSystemName
    				input.usings.addAll(#[s,ue,entiti,math,inputSystem])
    				
    				input.class = component
    				input.component = component+'_range'
    				input.component2 = component+'_range'
    				input.float = 'float'
    				input.value = 'Value'
    				result.add(input)
    			}
				componentDefinition.type = type.toString
				componentDefinition.variable = "Value"
				componentDefinition.implicitType1 = type.toString
				componentDefinition.implicitType2 = component
				componentDefinition.implicitVariable1 = component
				componentDefinition.implicitVariable2 = component
				componentDefinition.implicitField = "Value"
				
				componentDefinition.implicitType3 = component
				componentDefinition.implicitType4 = type.toString
				componentDefinition.implicitVariable3 = "v"
				componentDefinition.implicitVariable4 = component
				componentDefinition.implicitField1 = "Value"
				componentDefinition.implicitVariable5 = "v"
    		}
    		if (type !== 'range')
    		{
    			result.add(componentDefinition)
			}
		}
		return result
	}
	
	def textToJson(Game game)
	{		
		var root = json.createObject
		var dependencies = json.createField
		root.fields.add(dependencies)
		
		dependencies.name = "dependencies"
		var list = json.createObject
		dependencies.value = list
		
		list.add("com.unity.entities", "0.1.1-preview")
		//list.add("com.unity.package-manager-ui", "2.0.7")
		list.add("com.unity.inputsystem", "0.9.6-preview")
		
		var modules = #['ai','animation','assetbundle','audio','cloth','director','imageconversion','imgui','jsonserialize','particlesystem','physics','physics2d','screencapture','terrain','terrainphysics','tilemap','ui','uielements','umbra','unityanalytics','unitywebrequest','unitywebrequestassetbundle','unitywebrequestaudio','unitywebrequesttexture','unitywebrequestwww','vehicles','video','vr','wind','xr']
		for (module : modules)
		{
			list.add("com.unity.modules."+module, "1.0.0")
		}
		
		return root
	}
	
	def addField(Component component, String name, String value)
	{
		var field = prefab.createField
		field.name = name
		var stringValue = prefab.createStringValue
		stringValue.value = value
		field.values.add(stringValue)
		component.fields.add(field)
	}
	
	def addField(Component component, String name, Number value)
	{
		var field = prefab.createField
		field.name = name
		var numberValue = prefab.createNumberValue
		numberValue.value = value
		field.values.add(numberValue)
		component.fields.add(field)
	}
	
	
	def addField(Component component, String name, Number x, Number y)
	{
		var field = prefab.createField
		field.name = name
		var value = prefab.createFloat2Value
		value.x = x
		value.y = y
		field.values.add(value)
		component.fields.add(field)
	}
	
	
	def addField(Component component, String name, Number x, Number y, Number z)
	{
		var field = prefab.createField
		field.name = name
		var value = prefab.createFloat3Value
		value.x = x
		value.y = y
		value.z = z
		field.values.add(value)
		component.fields.add(field)
	}
	
	def addField(Component component, String name, List<FieldValue> values)
	{
		var field = prefab.createField
		field.name = name
		for (value : values)
		{
			field.values.add(value)
		}
		component.fields.add(field)
	}
	
	def addIDField(Component component, String name, int value)
	{
		var field = prefab.createField
		field.name = name
		var idValue = prefab.createFileIDValue
		idValue.id = value
		field.values.add(idValue)
		component.fields.add(field)
	}
	
	def addGUIDField(Component component, String name, int id, String guid, int type)
	{
		var field = prefab.createField
		field.name = name
		var idValue = prefab.createGuidValue
		idValue.id = id
		idValue.guid = guid
		idValue.type = type
		field.values.add(idValue)
		component.fields.add(field)
	}
	
	def addComponent(List<Component> list, String name, int id, int hash)
	{
		var component = prefab.createComponent
		component.name = name
		component.id = id
		component.hash = hash
		list.add(component)
		return component
	}
	
	def toFloat(m.text.Number number)
	{
		var result = ''
		if (number.negative) 
		{
			result += '-'
		}
		result += number.number
		if (number.decimal != 0)
		{
			result += '.' + number.decimal
		}
		return Float.parseFloat(result)
	}
	
	def toNumber(int x)
	{
		var number = prefab.createNumber
		number.number = x
		return number
	}
	
	def toNumber(m.text.Number n)
	{
		var number = prefab.createNumber
		number.number = n.number
		number.decimal = n.decimal
		number.negative = n.negative
		return number
	}
	
	def textToSettings(Game game)
	{
		var result = new ArrayList<Entity>
		
		var physics2D = prefab.createEntity
		result.add(physics2D)
		
		var physics = physics2D.components.addComponent("Physics2DSettings", 19, 1)
		physics.addField("serializedVersion", 4.toNumber)
		physics.addField("m_Gravity", 0.toNumber, 0.toNumber)
		
		var playerSettings = prefab.createEntity
		result.add(playerSettings)
		
		var player = playerSettings.components.addComponent("PlayerSettings", 129, 1)
		player.addField("productName", "Title")
		player.addField("enableNativePlatformBackendsForNewInputSystem", 1.toNumber)
		
		var physicsManager = prefab.createEntity
		result.add(physicsManager)
		
		var physics3D = physicsManager.components.addComponent("PhysicsManager", 55, 1)
		physics3D.addField("serializedVersion", 10.toNumber)
		physics3D.addField("m_Gravity", 0.toNumber, 0.toNumber, 0.toNumber)
		
		return result
	}
	
	def textToPrefab(Game game)
	{
		var result = new ArrayList<Entity>
		
		var hash = 1000
		
		var unityComponents = new ArrayList<Component>
		for (entity : game.entities)
		{
			var e = prefab.createEntity
			result.add(e)
			
			var gameObject = unityComponents.addComponent("GameObject", 1, hash++)
			
			e.components.add(gameObject)
			
			gameObject.addField("m_Name", entity.name)
			gameObject.addField("serializedVersion", 6.toNumber)
			gameObject.addField("m_IsActive", 1.toNumber)
			
			
			var transform = unityComponents.addComponent("Transform", 4, hash++)
			transform.addIDField("m_GameObject", 1000)
			var entity_position = entity.components.findFirst[it.name == "position"]
			if (entity_position !== null)
			{
				var pos = entity_position.value as Real2
				transform.addField("m_LocalPosition", pos.x.toNumber, pos.y.toNumber)
			}
			
			var entity_rotation = entity.components.findFirst[it.name == "rotation"]
			if (entity_rotation !== null)
			{
				var rot = entity_position.value as Real1
				transform.addField("m_LocalRotation", 0.toNumber, 0.toNumber, rot.x.toNumber)
			}
			
			var entity_scale = entity.components.findFirst[it.name == "scale"]
			if (entity_scale !== null)
			{
				var scale = entity_scale.value as Real2
				transform.addField("m_LocalScale", scale.x.toNumber, scale.y.toNumber, 0.toNumber)
			}
			
			var goe = unityComponents.addComponent("MonoBehaviour", 114, hash++)
			e.components.add(goe)
			
			goe.addIDField("m_GameObject", 1000)
			goe.addGUIDField("m_Script", 11500000, "5bf10cdea1344482e91a4f2b58506b77", 3)
			
			for (component : entity.components)
			{
	    		if (component.name == "appearance")
	    		{
	    			var renderer = unityComponents.findFirst[it.name=="SpriteRenderer"]
	    			if (renderer === null)
	    			{
	    				renderer = unityComponents.addComponent("SpriteRenderer", 212, hash++)
	    				
	    				renderer.addIDField("m_GameObject", 1000)
	    			}
	    			
	    			renderer.addField("m_Sprite", 21300000.toNumber, (component.value as Sprite).value.hashCode.toNumber, 3.toNumber)	    			
	    		}
	    		
	    		if (component.name == "shading" && entity.components.exists[it.name=="appearance"])
	    		{
	    			var renderer = unityComponents.findFirst[it.name=="SpriteRenderer"]
	    			if (renderer === null)
	    			{
	    				renderer = unityComponents.addComponent("SpriteRenderer", 212, hash++)
	    				
	    				renderer.addIDField("m_GameObject", 1000)
	    			}
	    			
	    			
	    			
					var materials_list = prefab.createListValue
					var materialValue = prefab.createGuidValue
					materialValue.id = 2100000
					materialValue.guid = "f9455c1d02e84a914b8383555e7e4500"
					materialValue.type = 2
					materials_list.value = materialValue
	    			renderer.addField("m_Materials", #[materials_list])	
	    			
	    		}
	    		var value = component.value
	    		var random = new Random
	    		if (value instanceof Sprite)
	    		{
	    			//var name = value.value
	    			
	    			var image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
					image.setRGB(0,0,new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256)).RGB)
					var o = new ByteArrayOutputStream
					ImageIO.write(image, 'png', o)
					
					
					/*
					fsa.generateFile('''�Sprites.folder�/�name�.png''', new ByteArrayInputStream(o.toByteArray))
					fsa.generateFile('''�Sprites.folder�/�name�.png.meta''',
					'''
					fileFormatVersion: 2
					guid: �art.name.uuid(Sprite)�
					TextureImporter:
					  serializedVersion: 9
					  textureFormat: 1
					  compressionQuality: 50
					  spriteMode: 1
					  spriteExtrude: 1
					  spriteMeshType: 1
					  spritePixelsToUnits: 1
					  textureType: 8
					  textureShape: 1
					  spriteSheet:
					    serializedVersion: 2'''
					)*/
	    			/*
	    		}
			}
			
			var list = new ArrayList<FieldValue>
			for (c : unityComponents)
			{
				if (c.hash !== 1000)
				{
					e.components.add(c)
					var listValue = prefab.createListValue
					var value = prefab.createFileIDValue
					listValue.name = "component"
					listValue.value = value
					value.id = c.hash
					list.add(listValue)
				}
			}
			gameObject.addField("m_Component", list)
			
		}
		return result
	}
	
	private def add(m.json.Object list, String name, String value)
	{
		var field = json.createField
		var stringValue = json.createStringValue
		
		field.name = name
		stringValue.value = value
		
		field.value = stringValue
		
		list.fields.add(field)
	}
	
	
	
	
	
	def systems(Game game)
	{
		var result = new ArrayList<File>
		
		for (s : game.systems)
		{
			var original = s as m.text.System
			var system = csharp.createSystem
			system.name = original.name
			
			var entities = csharp.createQualifiedName
			entities.names.add('Unity') entities.names.add('Entities')
			var entitiesImport = csharp.createImport
			entitiesImport.name = entities
			var mathematics = csharp.createQualifiedName
			mathematics.names.add('Unity') mathematics.names.add('Mathematics')
			var mathematicsImport = csharp.createImport
			mathematicsImport.name = mathematics
			var engine = csharp.createQualifiedName
			engine.names.add('UnityEngine')
			var engineImport = csharp.createImport
			engineImport.name = engine
			var collections = csharp.createQualifiedName
			collections.names.add('Unity') collections.names.add('Collections')
			var collectionImport = csharp.createImport
			collectionImport.name = collections
			var jobs = csharp.createQualifiedName
			jobs.names.add('Unity') jobs.names.add('Jobs')
			var jobsImport = csharp.createImport
			jobsImport.name = jobs
			
			system.imports.add(entitiesImport)
			system.imports.add(mathematicsImport)
			system.imports.add(collectionImport)
			system.imports.add(jobsImport)
			system.imports.add(engineImport)
			
			val writes = new HashMap<String,HashSet<String>>
			var assignments = EcoreUtil2.getAllContentsOfType(s,ComponentAssignment)
			for (assignment : assignments)
			{
				if (!writes.containsKey(assignment.group))
				{
					writes.put(assignment.group,new HashSet<String>)
				}
				writes.get(assignment.group).add(assignment.component)
			}
			
			var reads = new HashMap<String,HashSet<String>>
			var accesses = EcoreUtil2.getAllContentsOfType(s,Access)
			for (access : accesses)
			{
				if (!reads.containsKey(access.group))
				{
					reads.put(access.group,new HashSet<String>)
				}
				var name = access.component
				if (access.range)
				{
					name+='_range'
				}
				else if (access.vector)
				{
					name+='_vector'
				}
				reads.get(access.group).add(name)
			}
			
			val allWrites = new HashSet<String>
			for (v : writes.values)
			{
				allWrites.addAll(v)
			}
			val allReads = new HashSet<String>
			for (v : reads.values)
			{
				for (c : v)
				{
					if (!allWrites.contains(c))
					{
						allReads.add(c)
					}
				}
			}
			
			for (query : (writes.keySet + reads.keySet).toSet)
			{
				var queryDeclaration = csharp.createQueryDeclaration
				system.queryDeclarations.add(queryDeclaration)
				queryDeclaration.name = query
				
				var chunkDeclaration = csharp.createDeclareChunk
				chunkDeclaration.name = query
				
				var queryGetter = csharp.createQueryGetter
				queryGetter.name = query
				if (writes.containsKey(query))
				{
				for (component : writes.get(query))
				{
					var c = csharp.createComponentType
					c.type = component
					queryGetter.components.add(c)
				}
				}
				if (reads.containsKey(query))
				{
				for (component : reads.get(query).filter[!writes.containsKey(query) || !writes.get(query).contains(it)])
				{
					var c = csharp.createComponentType
					c.type = component
					c.readonly = true
					queryGetter.components.add(c)
				}
				}
				system.queryGetters.add(queryGetter)
				
				
				var sendChunk = csharp.createSendChunk
				sendChunk.name = query
				sendChunk.queryName = query
				system.sendChunks.add(sendChunk)
				
				var declareChunk = csharp.createDeclareChunk
				declareChunk.name = query
				system.chunks.add(declareChunk)
			}
			
			for (component : allWrites)
			{
				var sendType = csharp.createSendType
				sendType.name = component
				sendType.type = component
				system.sendTypes.add(sendType)
				
				var declareType = csharp.createDeclareType
				declareType.name = component
				declareType.type = component
				system.jobTypes.add(declareType)
			}
			
			for (component : allReads)
			{	
				var sendType = csharp.createSendType
				sendType.name = component
				sendType.type = component
				sendType.readonly = true
				system.sendTypes.add(sendType)
				
				var declareType = csharp.createDeclareType
				declareType.name = component
				declareType.type = component
				declareType.readonly = true
				system.jobTypes.add(declareType)
			}
			
			var sendBuffer = csharp.createSendBuffer
			sendBuffer.name = "buffer"
			system.sendBuffer = sendBuffer
			
			var declareBuffer = csharp.createDeclareBuffer
			declareBuffer.name = "buffer"
			system.buffer = declareBuffer
			
			result.add(system)
			
			
			for (command : s.commands)
			{
				system.addCommand(command)
			}
			
			
		}
		return result
	}
	
	def Command addCommand(m.csharp.Control control, m.text.Command command)
	{
		if (command instanceof m.text.Break)
		{
			var break = csharp.createBreak
			control.commands.add(break)
			return break
		}
		else if (command instanceof Initialization)
		{
			var initialization = csharp.createInitialization
			initialization.name = command.variable
			initialization.type = 'float'
			control.commands.add(initialization)
			return initialization
		}
		else if (command instanceof SubrutineCall)
		{
			var subrutine = csharp.createSubrutineCall
			subrutine.subrutine = command.subrutine
			subrutine.parameters.addAll(command.parameters.map[toCsharp])
			control.commands.add(subrutine)
			return subrutine
		}
		else if (command instanceof Loop)
		{
			var loop = csharp.createLoop
			val group = command.group
			var outIndex = group+'_out'
			var inIndex = group+'_in'
			var chunkName = group + '_chunk'
			
			loop.chunkIterator1 = outIndex
			loop.chunkIterator2 = outIndex
			loop.chunkIterator3 = outIndex
			loop.chunkIterator4 = outIndex
			loop.entityIterator1 = inIndex
			loop.entityIterator2 = inIndex
			loop.entityIterator3 = inIndex
			
			loop.chunksName = group
			loop.chunksName2 = group
			loop.chunkName = chunkName
			loop.chunkName2 = chunkName
			
			var sets = EcoreUtil2.getAllContentsOfType(command, ComponentAssignment).filter[it.group == group].map[component]
			var gets = EcoreUtil2.getAllContentsOfType(command, Access).filter[it.group == group]
			
			for (component : sets)
			{
				var array = csharp.createComponentArray
				var arrayName = group + '_' + component + '_array'
				array.name = arrayName
				array.chunk = chunkName
				array.type = component
				
				var data = csharp.createComponentData
				data.name = group + '_' + component
				data.array = arrayName
				data.index = inIndex
				
				loop.componentArrays.add(array)
				loop.componentDatas.add(data)
			}
			
			for (component : gets)
			{
				var name = component.component
				if (component.range)
				{
					name+='_range'
				}
				else if (component.vector)
				{
					name+='vector'
				}
				
				var array = csharp.createComponentArray
				var arrayName = group + '_' + name + '_array'
				array.name = arrayName
				array.chunk = chunkName
				array.type = name
				
				var data = csharp.createComponentData
				data.name = group + '_' + name
				data.array = arrayName
				data.index = inIndex
				
				loop.componentArrays.add(array)
				loop.componentDatas.add(data)
			}
			
			for (c : command.commands)
			{
				loop.addCommand(c)
			}
			
			control.commands.add(loop)
			return loop
		}
		else if (command instanceof CollisionLoop)
		{
			var loop = csharp.createLoop
			val group = command.first.group
			var outIndex = group+'_out'
			var inIndex = group+'_in'
			var chunkName = group + '_chunk'
			
			loop.chunkIterator1 = outIndex
			loop.chunkIterator2 = outIndex
			loop.chunkIterator3 = outIndex
			loop.chunkIterator4 = outIndex
			loop.entityIterator1 = inIndex
			loop.entityIterator2 = inIndex
			loop.entityIterator3 = inIndex
			
			loop.chunksName = group
			loop.chunksName2 = group
			loop.chunkName = chunkName
			loop.chunkName2 = chunkName
			
			var sets = EcoreUtil2.getAllContentsOfType(command, ComponentAssignment).filter[it.group == group].map[component]
			var gets = EcoreUtil2.getAllContentsOfType(command, Access).filter[it.group == group].map[component]
			var collision = command.relation
			switch(command.type)
			{
				case ENTER:
				{
					collision = collision+'_enter'
				}
				case STAY:
				{
					collision = collision+'_stay'
				}
				case EXIT:
				{
					collision = collision+'_exit'
				}
			}
			for (component : (sets+gets).toSet)
			{
				var array = csharp.createComponentArray
				var arrayName = group + '_' + component + '_array'
				array.name = arrayName
				array.chunk = chunkName
				array.type = component
				
				var data = csharp.createComponentData
				data.name = group + '_' + component
				data.array = arrayName
				data.index = inIndex
				
				loop.componentArrays.add(array)
				loop.componentDatas.add(data)
			}
			
			var loopB = text.createLoop
			loopB.group = command.second.group
			for (constraint : command.second.constraints)
			{
				var c = text.createConstraint
				c.event = constraint.event
				c.negated = constraint.negated
				loopB.constraints.add(c)
			}
			var break = csharp.createBreak
			loop.commands.add(break)
			/*
			for (c : command.commands)
			{
				loopB.commands.add(c)
			}
			
			var added = loop.addCommand(loopB)
			
			for (c : command.commands)
			{
				(added as Control).addCommand(c)
			}*/
			/*
			control.commands.add(loop)
			return loop
		}
		else if (command instanceof Check)
		{
			var branch = csharp.createCheck
			branch.condition = (command.condition as Condition).toCsharp
			branch.conditions.addAll(command.conditions.map[(it as Condition).toCsharp])
			for (c : command.commands)
			{
				branch.addCommand(c)
			}
			control.commands.add(branch)
			return branch
		}
		else if (command instanceof While)
		{
			var created = csharp.createWhile
			created.condition = command.condition.toCsharp
			for (c : command.commands)
			{
				created.addCommand(c)
			}
			control.commands.add(created)
			return created
		}
		else if (command instanceof ComponentAssignment)
		{
			var assignment = csharp.createComponentAssignment
			assignment.group = command.group
			assignment.component = command.component
			assignment.assignment = AssignmentType.SET
			assignment.expression = command.expression.toCsharp
			control.commands.add(assignment)
			return assignment
		}
		else if (command instanceof VariableAssignment)
		{
			var assignment = csharp.createVariableAssignment
			assignment.variable = command.variable
			assignment.assignment = AssignmentType.SET
			assignment.expression = command.expression.toCsharp
			control.commands.add(assignment)
			return assignment
		}
		else if (command instanceof CreatedAssignment)
		{
			var created = csharp.createCreatedAssignment
			created.variable = command.variable
			created.assignment = AssignmentType.SET
			created.expression = command.expression
			control.commands.add(created)
			return created
		}
	}
	
	def toCsharp(Condition condition)
	{
		var created = csharp.createCondition
		created.condition = condition.condition.toCsharp
		for (c : condition.commands)
		{
			created.addCommand(c)
		}
		return created
	}
	
	def m.csharp.BooleanExpression toCsharp(BooleanExpression expression)
	{
		if (expression instanceof Or)
		{
			var or = csharp.createOr
			or.left = expression.left.toCsharp
			or.right = expression.right.toCsharp
			return or
		}
		else if (expression instanceof And)
		{
			var and = csharp.createAnd
			and.left = expression.left.toCsharp
			and.right = expression.right.toCsharp
			return and
		}
		else if (expression instanceof Not)
		{
			var not = csharp.createNot
			not.expression = expression.expression.toCsharp
			return not
		}
		else if (expression instanceof BooleanBrackets)
		{
			var brackets = csharp.createBooleanBrackets
			brackets.expression = expression.expression.toCsharp
			return brackets
		}
		else if (expression instanceof Comparison)
		{
			var comparison = csharp.createComparison
			comparison.left = expression.left.toCsharp
			comparison.type = RelationType.EQUAL
			comparison.right = expression.right.toCsharp
			return comparison
		}
		else if (expression instanceof BooleanVariable)
		{
			var variable = csharp.createBooleanVariable
			variable.variable = expression.variable
			return variable
		}
	}
	
	def m.csharp.ArithmeticExpression toCsharp(ArithmeticExpression expression)
	{
		if (expression instanceof Plus)
		{
			var plus = csharp.createPlus
			plus.left = expression.left.toCsharp
			plus.right = expression.right.toCsharp
			return plus
		}
		else if (expression instanceof Minus)
		{
			var minus = csharp.createMinus
			minus.left = expression.left.toCsharp
			minus.right = expression.right.toCsharp
			return minus
		}
		else if (expression instanceof Times)
		{
			var times = csharp.createTimes
			times.left = expression.left.toCsharp
			times.right = expression.right.toCsharp
			return times
		}
		else if (expression instanceof Divide)
		{
			var divide = csharp.createDivide
			divide.left = expression.left.toCsharp
			divide.right = expression.right.toCsharp
			return divide
		}
		else if (expression instanceof Exponentiation)
		{
			var exp = csharp.createExponentiation
			exp.left = expression.left.toCsharp
			exp.right = expression.right.toCsharp
			return exp
		}
		else if (expression instanceof Modulus)
		{
			var modulus = csharp.createModulus
			modulus.left = expression.left.toCsharp
			modulus.right = expression.right.toCsharp
			return modulus
		}
		else if (expression instanceof Increment)
		{
			var increment = csharp.createIncrement
			increment.left = expression.left.toCsharp
			return increment
		}
		else if (expression instanceof Decrement)
		{
			var decrement = csharp.createDecrement
			decrement.left = expression.left.toCsharp
			return decrement
		}
		else if (expression instanceof ArithmeticBrackets)
		{
			var brackets = csharp.createArithmeticBrackets
			brackets.expression = expression.toCsharp
			return brackets
		}
		else if (expression instanceof Access)
		{
			var access = csharp.createAccess
			access.group = expression.group
			access.component = expression.component
			access.range = expression.range
			access.vector = expression.vector
			return access
		}
		else if (expression instanceof Call)
		{
			var call = csharp.createCall
			call.function = expression.function
			call.parameters.addAll(expression.parameters.map[toCsharp])
			return call
		}
		else if (expression instanceof Pop)
		{
			var pop = csharp.createPop
			pop.variable = expression.variable
			return pop
		}
	}
}*/