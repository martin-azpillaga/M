package games.minim.generator

import games.minim.m.Game
import org.eclipse.xtext.generator.IFileSystemAccess2
import com.google.inject.Guice
import games.minim.XMRuntimeModule
import org.eclipse.xtext.serializer.impl.Serializer
import games.minim.m.MPackage.Literals
import com.google.inject.Inject
import games.minim.services.XMGrammarAccess
import org.eclipse.xtext.service.AbstractElementFinder.AbstractGrammarElementFinder

class BlockSerializer implements Framework
{
	override represent(Game game, IFileSystemAccess2 fileSystem) 
	{
		var injector = Guice.createInjector(new  XMRuntimeModule());  
		var grammar = injector.getInstance(AbstractGrammarElementFinder) as XMGrammarAccess;  
		
		fileSystem.generateFile(game.title+'.xml',
		'''
		«OPENXML»
		«FOR entity : game.entities»
		«OPENENTITY»
		«OPENNAME»«entity.name»«CLOSEFIELD»
		«IF entity.base !== null»
		«OPENBASE»
		«OPENBASE2»
		«OPENNAME»«entity.base.name»«CLOSEFIELD»
		«ENDIF»
		«CLOSEBLOCK»
		«ENDFOR»
		«CLOSEXML»
		'''
		)
		var n = grammar.OPENNAMERule.toString
		n = n
	}
	
	public String OPENVALUEVALUE= '<value name="value">';
public String OPENTRANSFORMATION= '<block type="transformation" id="%s">';

public String OPENXML= '<xml xmlns="http=//www.w3.org/1999/xhtml">';
public String CLOSEXML= '</xml>';
public String OPENENTITY= '<block type="entity" id="%s" x="%d" y="%d">';
public String CLOSEBLOCK= '</block>';
public String OPENNAME= '<field name="NAME">';
public String CLOSEFIELD= '</field>';
public String OPENCOMPONENTS= '<statement name="components">';
public String CLOSESTATEMENT= '</statement>';
public String OPENTAG= '<block type="tag" id="%s">';
public String OPENBASE= '<value name="base">';
public String OPENBASE2= '<block type="base" id="%s">';
public String CLOSEVALUE= '</value>';
public String OPENREMOVED= '<statement name="removed">';
public String OPENCOMPONENTNAME= '<block type="componentname" id="%s">';
public String OPENSUBENTITIES= '<statement name="subEntities">';
public String OPENREAL1= '<block type="real1" id="%s">';
public String OPENREAL2= '<block type="real2" id="%s">';
public String OPENREAL3= '<block type="real3" id="%s">';
public String OPENREAL4= '<block type="real4" id="%s">';
public String OPENENUMERATION= '<block type="enumeration" id="%s">';
public String OPENREFERENCE= '<block type="reference" id="%s">';
public String OPENSPRITE= '<block type="sprite" id="%s">';
public String OPENAUDIO= '<block type="audio" id="%s">';
public String OPENTEXT= '<block type="textcomponent" id="%s">';
public String OPENMESH= '<block type="mesh" id="%s">';
public String OPENMATERIAL= '<block type="material" id="%s">';
public String OPENFONT= '<block type="font" id="%s">';
public String OPENTIMER= '<block type="timer" id="%s">';
public String OPENTRIGGER= '<block type="trigger" id="%s">';
public String OPENRANGE= '<block type="range" id="%s">';
public String OPENVECTOR= '<block type="vector" id="%s">';
public String OPENSENSOR= '<block type="sensor" id="%s">';
public String OPENCLICK= '<block type="click" id="%s">';
public String OPENX= '<field name="X">';
public String OPENY= '<field name="Y">';
public String OPENZ= '<field name="Z">';
public String OPENW= '<field name="W">';
public String OPENVALUE= '<field name="VALUE">';
public String OPENVALUES= '<statement name="VALUES">';
public String OPENENUMERATIONVALUE= '<block type="enumeration_value" id="%s">';

public String OPENSYSTEM= '<block type="system" id="%s" x="%d" y="%d">';
public String OPENCOMMANDS= '<statement name="commands">';

public String OPENLOOP= '<block type="loop" id="%s">';
public String OPENGROUP= '<field name="GROUP">';
public String OPENCONSTRAINTS= '<statement name="constraints">';
public String OPENBREAK= '<block type="break" id="%s">';
public String OPENINITIALIZATION= '<block type="declaration" id="%s">';
public String OPENCOMPONENTASSIGNMENT= '<block type="component_assignment" id="%s">';
public String OPENASSIGNMENTTYPE= '<field name="assignmentType">';
public String OPENVARIABLEASSIGNMENT= '<block type="variable_assignment" id="%s">';
public String OPENCOMPONENT= '<field name="COMPONENT">';
public String OPENEXPRESSION= '<value name="expression">';
public String OPENENTITYFIELD= '<field name="ENTITY">';
public String INITIALIZE= 'INITIALIZE';
public String OPENELSE= '<statement name="elseCommands">';
public String OPENIFCOMMANDS= '<statement name="ifCommands">';
public String OPENCONDITION= '<value name="condition">';
public String OPENBINARYBOOLEAN= '<block type="binary_boolean" id="%s">';
public String OPENLEFT='<value name="left">';
public String OPENRIGHT='<value name="right">';
public String OPENUNARYBOOLEAN= '<block type="unary_boolean" id="%s">';
public String OPENARITHEMTICBOOLEAN= '<block type="binary_arithmetic_boolean" id="%s">';
public String OPENBINARYARITHMETIC= '<block type="binary_arithmetic" id="%s">';
public String OPENUNARYARITHMETIC= '<block type="unary_arithmetic" id="%s">';
public String OPENOPERATION= '<field name="OPERATION">';
public String OPENPOP= '<block type="pop" id="%s">';
public String OPENVALUENAME= '<value name="NAME">';
public String ADD= 'ADD';
public String SUBTRACT= 'SUBTRACT';
public String INCREMENT= 'INCREMENT';
public String DECREMENT= 'DECREMENT';
public String OPENVARIABLE= '<field name="VARIABLE">';
public String OPENIF= '<block type="if" id="%s">';
public String OPENWHILE= '<block type="while" id="%s">';
public String OPENNEXT= '<next>';
public String CLOSENEXT= '</next>';
public String OPENCONSTRAINT= '<block type="constraint_';
public String OPENCONSTRAINT2= '" id="%s">';
public String OPENNEGATED= '<field name="negated">';
public String OPENDETECTION= '<block type="constraint_detection" id="%s">';
public String OPENMOUSE= '<block type="constraint_mouse" id="%s">';
public String OPENTYPE= '<field name="TYPE">';
public String OR= '<field name="OPERATION">OR</field>';
public String AND= '<field name="OPERATION">AND</field>';
}