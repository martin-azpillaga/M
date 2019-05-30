
package games.minim.scoping

import games.minim.m.Access
import games.minim.m.Audio
import games.minim.m.Call
import games.minim.m.Constraint
import games.minim.m.Control
import games.minim.m.EngineComponentType
import games.minim.m.EngineTransformationType
import games.minim.m.EngineVoidType
import games.minim.m.Enumeration
import games.minim.m.Game
import games.minim.m.Initialization
import games.minim.m.Loop
import games.minim.m.MFactory
import games.minim.m.Name
import games.minim.m.Range
import games.minim.m.Real1
import games.minim.m.Real2
import games.minim.m.Real3
import games.minim.m.Real4
import games.minim.m.Reference
import games.minim.m.Sensor
import games.minim.m.Sprite
import games.minim.m.Tag
import games.minim.m.Timer
import games.minim.m.Trigger
import games.minim.m.VariableAssignment
import games.minim.m.Vector
import games.minim.m.impl.NameImpl
import java.util.ArrayList
import java.util.Collections
import java.util.HashMap
import java.util.List
import org.eclipse.emf.common.util.BasicEList
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.diagnostics.IDiagnosticConsumer
import org.eclipse.xtext.linking.impl.DefaultLinkingService
import org.eclipse.xtext.linking.impl.IllegalNodeException
import org.eclipse.xtext.linking.lazy.LazyLinker
import org.eclipse.xtext.nodemodel.INode
import org.eclipse.xtext.scoping.IScope
import org.eclipse.xtext.scoping.Scopes

import static games.minim.m.MPackage.Literals.*

class EngineComponent extends NameImpl
{
	public EngineComponentType type
}

class EngineTransformation extends NameImpl
{
	public EngineTransformationType type
}

class EngineVoid extends NameImpl
{
	public EngineVoidType type
}

class StandardLibrary extends LazyLinker 
{
	public static Resource libraryResource
	public static HashMap<String, Resource> deductionOf
	
	/*
	def void analyze(IResource member, EObject model)
	{
		if (member instanceof IFolder)
		{
			for (m : member.members)
			{
				analyze(m,model)
			}
		}
		else if (member instanceof IFile)
		{
			if(member.name.endsWith('.zzz'))
			{
				val memberUri = URI.createURI('platform:/resource'+member.fullPath)
				
				model.eResource.resourceSet.getResource(memberUri,true)
			}
		}
	}*/
	def gatherResources(EObject model)
	{
		// From the project
		var file = model.eResource.URI.path
		var projectPath = file.substring(9)
		var projectName = projectPath.substring(1,projectPath.substring(1).indexOf('/')+1)
		/*var project = ResourcesPlugin.workspace.root.getProject(projectName)
		for (member : project.members)
		{
			analyze(member, model)
		}*/
		
		if (deductionOf === null)
		{
			deductionOf = new HashMap<String,Resource>
		}
		if (!deductionOf.containsKey(projectName))
		{
			var resource = model.eResource.resourceSet.createResource(URI.createURI(projectName))
			var deduction = MFactory.eINSTANCE.createGame
		  	deduction.clear
		  	deduction.title = projectName
		  	resource.contents.add(deduction)
		  	
			deductionOf.put(projectName, resource)
		}
		

		if (StandardLibrary.libraryResource === null)
		{
			libraryResource = model.eResource.getResourceSet.createResource(URI.createURI('library'))
			var engine = MFactory.eINSTANCE.createGame
		  	engine.reset
		  	libraryResource.contents.add(engine)
		}
		else
		{
			model.eResource.resourceSet.resources.add(StandardLibrary.libraryResource)
		}
	}
    override void beforeModelLinked(EObject model, IDiagnosticConsumer diagnosticsConsumer) 
    {
    	if (model.eResource.resourceSet.resources.size == 1)
    	{
    		gatherResources(model)
    	}
	
    	
	    super.beforeModelLinked(model, diagnosticsConsumer);

		for (deduction : deductionOf.values)
		{
			var game = deduction.contents.get(0) as Game
			game.clear
		}
	}
	
	def clear(Game game)
	{
		game.tagComponents.clear
		game.real1Components.clear
		game.real2Components.clear
		game.real3Components.clear
		game.real4Components.clear
		game.enumerationComponents.clear
		game.entityComponents.clear
		game.rangeComponents.clear
		game.triggerComponents.clear
		game.sensorComponents.clear
		game.timerComponents.clear
		game.vectorComponents.clear
		game.spriteComponents.clear
		game.audioComponents.clear
		game.meshComponents.clear
		game.materialComponents.clear
		game.textComponents.clear
	}
	
	def reset(Game game)
	{
		for (value : EngineComponentType.values)
	  	{
	  		var component = new EngineComponent
	  		component.name = value.toString
	  		component.type = value
	  		switch value
	  		{
				case ACCELERATION: game.real2Components.add(component)
				case AUDIO: game.audioComponents.add(component)
				case BODY_TYPE: {}
				case CANVAS: {}
				case CANVAS_RENDERER: {}
				case CANVAS_SCALER: game.real2Components.add(component)
				case CLEAR_COLOR: game.real4Components.add(component)
				case CLEAR_FLAGS: {}
				case EARS: game.tagComponents.add(component)
				case EMISSION: game.real4Components.add(component)
				case EVENT_SYSTEM: {}
				case EXTENT: game.real2Components.add(component)
				case FONT: game.fontComponents.add(component)
				case FORCE: game.real2Components.add(component)
				case FRICTION: game.real1Components.add(component)
				case GRAPHIC_RAYCASTER: {}
				case INPUT_MODULE: {}
				case INTENSITY: game.real1Components.add(component)
				case LIGHT_TYPE: {}
				case MASS: game.real1Components.add(component)
				case MATERIAL: game.materialComponents.add(component)
				case MESH: game.meshComponents.add(component)
				case NEAR_CLIP: game.real1Components.add(component)
				case NUMBER: game.real1Components.add(component)
				case ORTHOGRAPHIC: {}
				case ORTHOGRAPHIC_SIZE: {}
				case PERSPECTIVE: game.tagComponents.add(component)
				case POSITION: game.real2Components.add(component)
				case RADIUS: game.real1Components.add(component)
				case RANGE: game.real1Components.add(component)
				case RESTITUTION: game.real1Components.add(component)
				case ROTATION: game.real3Components.add(component)
				case SCALE: game.real2Components.add(component)
				case SCREEN_EXPAND: {}
				case SCREEN_POSITION: {}
				case SCREEN_SIZE: {}
				case SERIALIZED_VERSION: {}
				case SPOT_ANGLE: game.real1Components.add(component)
				case SPRITE: game.spriteComponents.add(component)
				case TEXT: game.textComponents.add(component)
				case UI_SCALE_MODE: {}
				case VELOCITY: game.real2Components.add(component)
				case VIEW_ANGLE: game.real1Components.add(component)
				case VIEW_DISTANCE: game.real1Components.add(component)
				case DOPPLER_EFFECT: game.real1Components.add(component)
				case PITCH: game.real1Components.add(component)
				case VOLUME: game.real1Components.add(component)
				case TRIGGER: game.real1Components.add(component)
				case RAY: game.real2Components.add(component)
				case PHYSICAL: game.tagComponents.add(component)
	  		}
	  	}
	  	
		for (value : EngineTransformationType.values)
	  	{
	  		var transformation = new EngineTransformation
	  		transformation.name = value.toString
	  		transformation.type = value
	  		game.transformations.add(transformation)
	  	}
	  	
	  	for (value : EngineVoidType.values)
	  	{
	  		var transformation = new EngineVoid
	  		transformation.name = value.toString
	  		transformation.type = value
	  		
	  		game.voids.add(transformation)
	  	}
	}
}

class TypeInference extends DefaultLinkingService 
{
	Game deduction
	Game engine
	
	override List<EObject> getLinkedObjects(EObject context, EReference reference, INode node) throws IllegalNodeException 
	{
		if (deduction === null)
		{
			var file = context.eResource.URI.path
			var projectPath = file.substring(9)
			var projectName = projectPath.substring(1,projectPath.substring(1).indexOf('/')+1)
			deduction = StandardLibrary.deductionOf.get(projectName).contents.get(0) as Game
			engine = StandardLibrary.libraryResource.contents.get(0) as Game
		}
		/*
		if (reference == ENTITY__REFERENCE)
		{
			var found = deduction.entities.findFirst[it.name==node.text]
			if (found !== null)
			{
				var c = context as Entity
				/*
				}found.removed.clear
				found.removed.addAll(c.removed)
				found.children.clear
				found.children.addAll(c.children)
				return Collections.singletonList(found)
			}
			else
			{
				var entity = MFactory.eINSTANCE.createEntity
				entity.name = node.text
				deduction.entities.add(entity)
				return Collections.singletonList(entity)
			}
		}
		
		else if (reference == SYSTEM__REFERENCE)
		{
			var found = deduction.systems.filter(System).findFirst[it.name==node.text]
			if (found !== null)
			{
				
				var c = context as System
				found.commands.clear
				found.commands.addAll(c.commands)
				
				return Collections.singletonList(found)
			}
			else
			{
				var system = MFactory.eINSTANCE.createSystem
				system.name = node.text
				deduction.systems.add(system)
				return Collections.singletonList(system)
			}
		}*/
		
		var found = find(node.text)
		
		if (found !== null)
		{
			return Collections.singletonList(found)
		}
		else
		{
			create(context,reference,node.text)
		}

		return super.getLinkedObjects(context, reference, node)
	}
	
	/*
	def checkResource(EObject context)
	{
		var uri = URI.createURI(StandardLibrary.deducedURI)
		var resource = context.eResource.resourceSet.getResource(uri, false)
		game = resource.contents.get(0) as Game
		
		uri = URI.createURI(StandardLibrary.engineURI)
		resource = context.eResource.resourceSet.getResource(uri, false)
		engine = resource.contents.get(0) as Game
	}*/
	
	def find(String text)
	{
		var Name found
		found = deduction.tagComponents.findFirst[it.name==text]
		if (found !== null) return found
		found = deduction.real1Components.findFirst[it.name==text]
		if (found !== null) return found
		found = deduction.real2Components.findFirst[it.name==text]
		if (found !== null) return found
		found = deduction.real3Components.findFirst[it.name==text]
		if (found !== null) return found
		found = deduction.real4Components.findFirst[it.name==text]
		if (found !== null) return found
		found = deduction.enumerationComponents.findFirst[it.name==text]
		if (found !== null) return found
		found = deduction.entityComponents.findFirst[it.name==text]
		if (found !== null) return found
		found = deduction.spriteComponents.findFirst[it.name==text]
		if (found !== null) return found
		found = deduction.audioComponents.findFirst[it.name==text]
		if (found !== null) return found
		found = deduction.textComponents.findFirst[it.name==text]
		if (found !== null) return found
		found = deduction.meshComponents.findFirst[it.name==text]
		if (found !== null) return found
		found = deduction.materialComponents.findFirst[it.name==text]
		if (found !== null) return found
		found = engine.tagComponents.findFirst[it.name==text]
		if (found !== null) return found
		found = engine.real1Components.findFirst[it.name==text]
		if (found !== null) return found
		found = engine.real2Components.findFirst[it.name==text]
		if (found !== null) return found
		found = engine.real3Components.findFirst[it.name==text]
		if (found !== null) return found
		found = engine.real4Components.findFirst[it.name==text]
		if (found !== null) return found
		found = engine.enumerationComponents.findFirst[it.name==text]
		if (found !== null) return found
		found = engine.entityComponents.findFirst[it.name==text]
		if (found !== null) return found
		found = engine.spriteComponents.findFirst[it.name==text]
		if (found !== null) return found
		found = engine.audioComponents.findFirst[it.name==text]
		if (found !== null) return found
	}
	
	def create(EObject context, EReference reference, String text)
	{
		if (reference == ENTITY__BASE)
		{
			/*
			var entity = MFactory.eINSTANCE.createEntity
			entity.name = text
			game.entities.add(entity)*/
		}
		else if (reference == ENTITY__REMOVED)
		{
			
		}
		else if (reference == VALUE__COMPONENT)
		{
			if (context instanceof Tag)
			{
				var component = MFactory.eINSTANCE.createName
				component.name = text
				deduction.tagComponents.add(component)
			}
			else if (context instanceof Real1)
			{
				var component = MFactory.eINSTANCE.createName
				component.name = text
				deduction.real1Components.add(component)
			}
			else if (context instanceof Real2)
			{
				var component = MFactory.eINSTANCE.createName
				component.name = text
				deduction.real2Components.add(component)
			}
			else if (context instanceof Real3)
			{
				var component = MFactory.eINSTANCE.createName
				component.name = text
				deduction.real3Components.add(component)
			}
			else if (context instanceof Real4)
			{
				var component = MFactory.eINSTANCE.createName
				component.name = text
				deduction.real4Components.add(component)
			}
			else if (context instanceof Enumeration)
			{
				var component = MFactory.eINSTANCE.createName
				component.name = text
				deduction.enumerationComponents.add(component)
				
				var enumeration = MFactory.eINSTANCE.createEnum
				enumeration.name = text
				deduction.enumerations.add(enumeration)
			}
			else if (context instanceof Reference)
			{
				var component = MFactory.eINSTANCE.createName
				component.name = text
				deduction.referenceComponents.add(component)
			}
			else if (context instanceof Timer)
			{
				var component = MFactory.eINSTANCE.createName
				component.name = text
				deduction.timerComponents.add(component)
			}
			else if (context instanceof Trigger)
			{
				var component = MFactory.eINSTANCE.createName
				component.name = text
				deduction.triggerComponents.add(component)
			}
			else if (context instanceof Range)
			{
				var component = MFactory.eINSTANCE.createName
				component.name = text
				deduction.rangeComponents.add(component)
			}
			else if (context instanceof Vector)
			{
				var component = MFactory.eINSTANCE.createName
				component.name = text
				deduction.vectorComponents.add(component)
			}
			else if (context instanceof Sensor)
			{
				var component = MFactory.eINSTANCE.createName
				component.name = text
				deduction.sensorComponents.add(component)
			}
			else if (context instanceof Sprite)
			{
				var component = MFactory.eINSTANCE.createName
				component.name = text
				deduction.spriteComponents.add(component)
			}
			else if (context instanceof Audio)
			{
				var component = MFactory.eINSTANCE.createName
				component.name = text
				deduction.audioComponents.add(component)
			}
		}
		else if (reference == ENUMERATION__VALUES) 
		{
			val instance = context as Enumeration
			var enumeration = deduction.enumerations.findFirst[it.name==instance.component.name]
			if (enumeration === null)
			{
				enumeration = MFactory.eINSTANCE.createEnum
				enumeration.name = instance.component.name
				deduction.enumerations.add(enumeration)
				
				var component = MFactory.eINSTANCE.createName
				component.name = instance.component.name
				deduction.enumerationComponents.add(component)
			}
			if (!enumeration.values.exists[it.name==text])
			{
				var enumerationValue = MFactory.eINSTANCE.createName
				enumerationValue.name = text
				enumeration.values.add(enumerationValue)
			}
		}
		else if (reference == SENSOR__VALUES)
		{
			var categories = deduction.enumerations.findFirst[it.name=='category']
			if (categories === null)
			{
				categories = MFactory.eINSTANCE.createEnum
				categories.name = 'category'
				deduction.enumerations.add(categories) 
				
				var component = MFactory.eINSTANCE.createName
				component.name = 'category'
				deduction.enumerationComponents.add(component)
			}
			
			if (!categories.values.exists[it.name==text])
			{
				var enumerationValue = MFactory.eINSTANCE.createName
				enumerationValue.name = text
				categories.values.add(enumerationValue)
			}
		}
		else if (reference == SPRITE__VALUE)
		{
			val enumerationValue = MFactory.eINSTANCE.createName
			enumerationValue.name = text
			if (!deduction.sprites.exists[it.name==enumerationValue.name])
			{
				deduction.sprites.add(enumerationValue)
			}
		}
		else if (reference == AUDIO__VALUE)
		{
			val enumerationValue = MFactory.eINSTANCE.createName
			enumerationValue.name = text
			if (!deduction.audios.exists[it.name == enumerationValue.name])
			{
				deduction.audios.add(enumerationValue)
			}
		}
		else if (reference == MESH__VALUE)
		{
			val enumerationValue = MFactory.eINSTANCE.createName
			enumerationValue.name = text
			if (!deduction.meshes.exists[it.name == enumerationValue.name])
			{
				deduction.meshes.add(enumerationValue)
			}
		}
		else if (reference == MATERIAL__VALUE)
		{
			val enumerationValue = MFactory.eINSTANCE.createName
			enumerationValue.name = text
			if (!deduction.materials.exists[it.name == enumerationValue.name])
			{
				deduction.materials.add(enumerationValue)
			}
		}
		else if (reference == FONT__VALUE)
		{
			val enumerationValue = MFactory.eINSTANCE.createName
			enumerationValue.name = text
			if (!deduction.fonts.exists[it.name == enumerationValue.name])
			{
				deduction.fonts.add(enumerationValue)
			}
		}
		else if (reference == CONSTRAINT__COMPONENT)
		{
			var constraint = context as Constraint
			switch constraint.event
			{
				case ENTER: 
				{
					var component = MFactory.eINSTANCE.createName
					component.name = text
					deduction.sensorComponents.add(component)
				}
				case EXIT: 
				{
					var component = MFactory.eINSTANCE.createName
					component.name = text
					deduction.sensorComponents.add(component)
				}
				case STAY: 
				{
					var component = MFactory.eINSTANCE.createName
					component.name = text
					deduction.sensorComponents.add(component)
				}
				case TAG: 
				{
					var component = MFactory.eINSTANCE.createName
					component.name = text
					deduction.tagComponents.add(component)
				}
				case TIMEOUT: 
				{
					var component = MFactory.eINSTANCE.createName
					component.name = text
					deduction.timerComponents.add(component)
				}
				case TRIGGER: 
				{
					var component = MFactory.eINSTANCE.createName
					component.name = text
					deduction.triggerComponents.add(component)
				}
				
			}
		}
		else if (reference == ACCESS__COMPONENT)
		{
			var access = context as Access
			if (access.range)
			{
				var component = MFactory.eINSTANCE.createName
				component.name = text
				deduction.rangeComponents.add(component)
			}
			else if (access.vector)
			{
				var component = MFactory.eINSTANCE.createName
				component.name = text
				deduction.vectorComponents.add(component)
			}
		}
		else if (reference == POP__VARIABLE)
		{
			
		}
		else if (reference == CALL__FUNCTION)
		{
			
		}
		else if (reference == VARIABLE_ASSIGNMENT__VARIABLE)
		{

		}
	}
}

class MScopeProvider extends AbstractMScopeProvider 
{
	Game deduction
	Game engine
	
	override getScope(EObject context, EReference reference)
	{
		if (deduction === null)
		{
			var file = context.eResource.URI.path
			var projectPath = file.substring(9)
			var projectName = projectPath.substring(1,projectPath.substring(1).indexOf('/')+1)
			deduction = StandardLibrary.deductionOf.get(projectName).contents.get(0) as Game
			engine = StandardLibrary.libraryResource.contents.get(0) as Game
		}
		
		var collection = collection(context,reference)
		
		if (collection !== null)
		{
			return Scopes.scopeFor(collection)
		}
		else
		{
			return IScope.NULLSCOPE
		}
	}
	
	def collection(EObject context, EReference reference)
	{
		switch reference
		{
			case ENTITY__BASE: 
			{
				var description = EcoreUtil2.getRootContainer(context) as Game
				return description.entities + deduction.entities + engine.entities
			}
			case ENTITY__REMOVED: allComponents
			case VALUE__COMPONENT: allComponents
			case REFERENCE__ENTITY: 
			{
				var description = EcoreUtil2.getRootContainer(context) as Game
				return description.entities + deduction.entities + engine.entities
			}
			case ENUMERATION__VALUES: 
			{
				if (context instanceof Enumeration)
				{
					val instance = context as Enumeration
					val refName = instance.component.name
					var enumeration = (deduction.enumerations+engine.enumerations).findFirst[it.name==refName]
					if (enumeration !== null)
					{
						return enumeration.values
					}
				}
			}
			case SPRITE__VALUE: deduction.sprites+engine.sprites
			case AUDIO__VALUE: deduction.audios+engine.audios
			case MESH__VALUE: deduction.meshes+engine.meshes
			case MATERIAL__VALUE: deduction.materials+engine.materials
			case FONT__VALUE: deduction.fonts+engine.fonts
			case SENSOR__VALUES: deduction.enumerations.findFirst[it.name=='category']?.values
			
			case ACCESS__GROUP: recursiveGroups(context)
			
			case CONSTRAINT__COMPONENT:
			{
				var constraint = context as Constraint
				switch constraint.event
				{
					case ENTER: deduction.sensorComponents + engine.sensorComponents
					case EXIT: deduction.sensorComponents + engine.sensorComponents
					case STAY: deduction.sensorComponents + engine.sensorComponents
					case TAG: deduction.tagComponents + engine.tagComponents
					case TIMEOUT: deduction.timerComponents + engine.timerComponents
					case TRIGGER: deduction.triggerComponents + engine.triggerComponents
				}
			}
			case ACCESS__COMPONENT: 
			{
				allComponents
			}
			
			case COMPONENT_ASSIGNMENT__COMPONENT: 
			{
				allComponents
			}
			case POP__VARIABLE: (scopeVariables1(context) + recursiveGroups(context) + allComponents)
			case CALL__FUNCTION: deduction.transformations + engine.transformations
			case SUBRUTINE_CALL__SUBRUTINE: deduction.voids + engine.voids
			case COMPONENT_ASSIGNMENT__GROUP: 
			{
				recursiveGroups(context)
			}
			case GAME__MAIN_ENTITIES: 
			{
				(context.eResource.contents.get(0) as Game).entities
			}
		}
	}
	
	def allComponents()
	{
		return deduction.tagComponents
		+deduction.real1Components
		+deduction.real2Components
		+deduction.real3Components
		+deduction.real4Components
		+deduction.enumerationComponents
		+deduction.referenceComponents
		+deduction.timerComponents
		+deduction.sensorComponents
		+deduction.triggerComponents
		+deduction.rangeComponents
		+deduction.vectorComponents
		+deduction.spriteComponents
		+deduction.audioComponents
		+deduction.textComponents
		+engine.tagComponents
		+engine.real1Components
		+engine.real2Components
		+engine.real3Components
		+engine.real4Components
		+engine.enumerationComponents
		+engine.referenceComponents
		+engine.timerComponents
		+engine.sensorComponents
		+engine.triggerComponents
		+engine.rangeComponents
		+engine.vectorComponents
		+engine.spriteComponents
		+engine.audioComponents
		+engine.textComponents
	}
	
	def recursiveGroups(EObject object)
	{
		var current = object
		var groups = new BasicEList<Name>
		while (current !== null)
		{
			if (current instanceof Control)
			{
				for (command : current.commands)
				{
					if (command instanceof VariableAssignment)
					{
						var expression = command.expression
						if (expression instanceof Call)
						{
							var function = expression.function
							if (function instanceof EngineTransformation)
							{
								if (function.type == EngineTransformationType.CREATE)
								{
									groups.add(command.variable)
								}
							}
						}
					}
				}
			}
			if (current instanceof Loop)
			{
				groups.add(current.group)
			}
			current = current.eContainer
		}
		return groups
	}
	
	def scopeVariables1(EObject object)
	{
		var result = new ArrayList<Name>
		var container = object.eContainer
		while(!(container === null))
		{
			if (container instanceof Control)
			{
				result.addAll(container.commands.filter(Initialization).map[variable])
				result.addAll(container.commands.filter(VariableAssignment).map[variable])
			}
			container = container.eContainer
		}
		return result
	}
}
