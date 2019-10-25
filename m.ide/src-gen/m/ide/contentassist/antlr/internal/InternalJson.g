/*
 * generated by Xtext
 */
grammar InternalJson;

options {
	superClass=AbstractInternalContentAssistParser;
}

@lexer::header {
package m.ide.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.Lexer;
}

@parser::header {
package m.ide.contentassist.antlr.internal;

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.DFA;
import m.services.JsonGrammarAccess;

}
@parser::members {
	private JsonGrammarAccess grammarAccess;

	public void setGrammarAccess(JsonGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}

	@Override
	protected Grammar getGrammar() {
		return grammarAccess.getGrammar();
	}

	@Override
	protected String getValueForTokenName(String tokenName) {
		return tokenName;
	}
}

// Entry rule entryRuleObject
entryRuleObject
:
{ before(grammarAccess.getObjectRule()); }
	 ruleObject
{ after(grammarAccess.getObjectRule()); } 
	 EOF 
;

// Rule Object
ruleObject 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getObjectAccess().getGroup()); }
		(rule__Object__Group__0)
		{ after(grammarAccess.getObjectAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleField
entryRuleField
:
{ before(grammarAccess.getFieldRule()); }
	 ruleField
{ after(grammarAccess.getFieldRule()); } 
	 EOF 
;

// Rule Field
ruleField 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getFieldAccess().getGroup()); }
		(rule__Field__Group__0)
		{ after(grammarAccess.getFieldAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleValue
entryRuleValue
:
{ before(grammarAccess.getValueRule()); }
	 ruleValue
{ after(grammarAccess.getValueRule()); } 
	 EOF 
;

// Rule Value
ruleValue 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getValueAccess().getAlternatives()); }
		(rule__Value__Alternatives)
		{ after(grammarAccess.getValueAccess().getAlternatives()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleArray
entryRuleArray
:
{ before(grammarAccess.getArrayRule()); }
	 ruleArray
{ after(grammarAccess.getArrayRule()); } 
	 EOF 
;

// Rule Array
ruleArray 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getArrayAccess().getGroup()); }
		(rule__Array__Group__0)
		{ after(grammarAccess.getArrayAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleStringValue
entryRuleStringValue
:
{ before(grammarAccess.getStringValueRule()); }
	 ruleStringValue
{ after(grammarAccess.getStringValueRule()); } 
	 EOF 
;

// Rule StringValue
ruleStringValue 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getStringValueAccess().getValueAssignment()); }
		(rule__StringValue__ValueAssignment)
		{ after(grammarAccess.getStringValueAccess().getValueAssignment()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleFloatValue
entryRuleFloatValue
:
{ before(grammarAccess.getFloatValueRule()); }
	 ruleFloatValue
{ after(grammarAccess.getFloatValueRule()); } 
	 EOF 
;

// Rule FloatValue
ruleFloatValue 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getFloatValueAccess().getValueAssignment()); }
		(rule__FloatValue__ValueAssignment)
		{ after(grammarAccess.getFloatValueAccess().getValueAssignment()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleLongValue
entryRuleLongValue
:
{ before(grammarAccess.getLongValueRule()); }
	 ruleLongValue
{ after(grammarAccess.getLongValueRule()); } 
	 EOF 
;

// Rule LongValue
ruleLongValue 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getLongValueAccess().getValueAssignment()); }
		(rule__LongValue__ValueAssignment)
		{ after(grammarAccess.getLongValueAccess().getValueAssignment()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleBooleanValue
entryRuleBooleanValue
:
{ before(grammarAccess.getBooleanValueRule()); }
	 ruleBooleanValue
{ after(grammarAccess.getBooleanValueRule()); } 
	 EOF 
;

// Rule BooleanValue
ruleBooleanValue 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getBooleanValueAccess().getValueAssignment()); }
		(rule__BooleanValue__ValueAssignment)
		{ after(grammarAccess.getBooleanValueAccess().getValueAssignment()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Rule Boolean
ruleBoolean
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getBooleanAccess().getAlternatives()); }
		(rule__Boolean__Alternatives)
		{ after(grammarAccess.getBooleanAccess().getAlternatives()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Value__Alternatives
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getValueAccess().getObjectParserRuleCall_0()); }
		ruleObject
		{ after(grammarAccess.getValueAccess().getObjectParserRuleCall_0()); }
	)
	|
	(
		{ before(grammarAccess.getValueAccess().getArrayParserRuleCall_1()); }
		ruleArray
		{ after(grammarAccess.getValueAccess().getArrayParserRuleCall_1()); }
	)
	|
	(
		{ before(grammarAccess.getValueAccess().getStringValueParserRuleCall_2()); }
		ruleStringValue
		{ after(grammarAccess.getValueAccess().getStringValueParserRuleCall_2()); }
	)
	|
	(
		{ before(grammarAccess.getValueAccess().getFloatValueParserRuleCall_3()); }
		ruleFloatValue
		{ after(grammarAccess.getValueAccess().getFloatValueParserRuleCall_3()); }
	)
	|
	(
		{ before(grammarAccess.getValueAccess().getLongValueParserRuleCall_4()); }
		ruleLongValue
		{ after(grammarAccess.getValueAccess().getLongValueParserRuleCall_4()); }
	)
	|
	(
		{ before(grammarAccess.getValueAccess().getBooleanValueParserRuleCall_5()); }
		ruleBooleanValue
		{ after(grammarAccess.getValueAccess().getBooleanValueParserRuleCall_5()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Boolean__Alternatives
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getBooleanAccess().getTRUEEnumLiteralDeclaration_0()); }
		('true')
		{ after(grammarAccess.getBooleanAccess().getTRUEEnumLiteralDeclaration_0()); }
	)
	|
	(
		{ before(grammarAccess.getBooleanAccess().getFALSEEnumLiteralDeclaration_1()); }
		('false')
		{ after(grammarAccess.getBooleanAccess().getFALSEEnumLiteralDeclaration_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Object__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Object__Group__0__Impl
	rule__Object__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Object__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getObjectAccess().getObjectAction_0()); }
	()
	{ after(grammarAccess.getObjectAccess().getObjectAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Object__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Object__Group__1__Impl
	rule__Object__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Object__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getObjectAccess().getLeftCurlyBracketKeyword_1()); }
	'{'
	{ after(grammarAccess.getObjectAccess().getLeftCurlyBracketKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Object__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Object__Group__2__Impl
	rule__Object__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__Object__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getObjectAccess().getGroup_2()); }
	(rule__Object__Group_2__0)?
	{ after(grammarAccess.getObjectAccess().getGroup_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Object__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Object__Group__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Object__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getObjectAccess().getRightCurlyBracketKeyword_3()); }
	'}'
	{ after(grammarAccess.getObjectAccess().getRightCurlyBracketKeyword_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Object__Group_2__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Object__Group_2__0__Impl
	rule__Object__Group_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Object__Group_2__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getObjectAccess().getFieldsAssignment_2_0()); }
	(rule__Object__FieldsAssignment_2_0)
	{ after(grammarAccess.getObjectAccess().getFieldsAssignment_2_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Object__Group_2__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Object__Group_2__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Object__Group_2__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getObjectAccess().getGroup_2_1()); }
	(rule__Object__Group_2_1__0)*
	{ after(grammarAccess.getObjectAccess().getGroup_2_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Object__Group_2_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Object__Group_2_1__0__Impl
	rule__Object__Group_2_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Object__Group_2_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getObjectAccess().getCommaKeyword_2_1_0()); }
	','
	{ after(grammarAccess.getObjectAccess().getCommaKeyword_2_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Object__Group_2_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Object__Group_2_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Object__Group_2_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getObjectAccess().getFieldsAssignment_2_1_1()); }
	(rule__Object__FieldsAssignment_2_1_1)
	{ after(grammarAccess.getObjectAccess().getFieldsAssignment_2_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Field__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Field__Group__0__Impl
	rule__Field__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Field__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getFieldAccess().getNameAssignment_0()); }
	(rule__Field__NameAssignment_0)
	{ after(grammarAccess.getFieldAccess().getNameAssignment_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Field__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Field__Group__1__Impl
	rule__Field__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Field__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getFieldAccess().getColonKeyword_1()); }
	':'
	{ after(grammarAccess.getFieldAccess().getColonKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Field__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Field__Group__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Field__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getFieldAccess().getValueAssignment_2()); }
	(rule__Field__ValueAssignment_2)
	{ after(grammarAccess.getFieldAccess().getValueAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Array__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Array__Group__0__Impl
	rule__Array__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Array__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getArrayAccess().getArrayAction_0()); }
	()
	{ after(grammarAccess.getArrayAccess().getArrayAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Array__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Array__Group__1__Impl
	rule__Array__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Array__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getArrayAccess().getLeftSquareBracketKeyword_1()); }
	'['
	{ after(grammarAccess.getArrayAccess().getLeftSquareBracketKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Array__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Array__Group__2__Impl
	rule__Array__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__Array__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getArrayAccess().getGroup_2()); }
	(rule__Array__Group_2__0)?
	{ after(grammarAccess.getArrayAccess().getGroup_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Array__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Array__Group__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Array__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getArrayAccess().getRightSquareBracketKeyword_3()); }
	']'
	{ after(grammarAccess.getArrayAccess().getRightSquareBracketKeyword_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Array__Group_2__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Array__Group_2__0__Impl
	rule__Array__Group_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Array__Group_2__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getArrayAccess().getElementsAssignment_2_0()); }
	(rule__Array__ElementsAssignment_2_0)
	{ after(grammarAccess.getArrayAccess().getElementsAssignment_2_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Array__Group_2__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Array__Group_2__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Array__Group_2__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getArrayAccess().getGroup_2_1()); }
	(rule__Array__Group_2_1__0)*
	{ after(grammarAccess.getArrayAccess().getGroup_2_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Array__Group_2_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Array__Group_2_1__0__Impl
	rule__Array__Group_2_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Array__Group_2_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getArrayAccess().getCommaKeyword_2_1_0()); }
	','
	{ after(grammarAccess.getArrayAccess().getCommaKeyword_2_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Array__Group_2_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Array__Group_2_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Array__Group_2_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getArrayAccess().getElementsAssignment_2_1_1()); }
	(rule__Array__ElementsAssignment_2_1_1)
	{ after(grammarAccess.getArrayAccess().getElementsAssignment_2_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Object__FieldsAssignment_2_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getObjectAccess().getFieldsFieldParserRuleCall_2_0_0()); }
		ruleField
		{ after(grammarAccess.getObjectAccess().getFieldsFieldParserRuleCall_2_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Object__FieldsAssignment_2_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getObjectAccess().getFieldsFieldParserRuleCall_2_1_1_0()); }
		ruleField
		{ after(grammarAccess.getObjectAccess().getFieldsFieldParserRuleCall_2_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Field__NameAssignment_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getFieldAccess().getNameSTRINGTerminalRuleCall_0_0()); }
		RULE_STRING
		{ after(grammarAccess.getFieldAccess().getNameSTRINGTerminalRuleCall_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Field__ValueAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getFieldAccess().getValueValueParserRuleCall_2_0()); }
		ruleValue
		{ after(grammarAccess.getFieldAccess().getValueValueParserRuleCall_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Array__ElementsAssignment_2_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getArrayAccess().getElementsValueParserRuleCall_2_0_0()); }
		ruleValue
		{ after(grammarAccess.getArrayAccess().getElementsValueParserRuleCall_2_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Array__ElementsAssignment_2_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getArrayAccess().getElementsValueParserRuleCall_2_1_1_0()); }
		ruleValue
		{ after(grammarAccess.getArrayAccess().getElementsValueParserRuleCall_2_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__StringValue__ValueAssignment
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getStringValueAccess().getValueSTRINGTerminalRuleCall_0()); }
		RULE_STRING
		{ after(grammarAccess.getStringValueAccess().getValueSTRINGTerminalRuleCall_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__FloatValue__ValueAssignment
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getFloatValueAccess().getValueFLOATTerminalRuleCall_0()); }
		RULE_FLOAT
		{ after(grammarAccess.getFloatValueAccess().getValueFLOATTerminalRuleCall_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__LongValue__ValueAssignment
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getLongValueAccess().getValueLONGTerminalRuleCall_0()); }
		RULE_LONG
		{ after(grammarAccess.getLongValueAccess().getValueLONGTerminalRuleCall_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__BooleanValue__ValueAssignment
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getBooleanValueAccess().getValueBooleanEnumRuleCall_0()); }
		ruleBoolean
		{ after(grammarAccess.getBooleanValueAccess().getValueBooleanEnumRuleCall_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

RULE_LONG : '-'? ('0'..'9')+;

RULE_FLOAT : '-'? ('0'..'9')* '.' ('0'..'9')+ ('e' '-'? ('0'..'9')+)?;

RULE_STRING : '"' ~('"')* '"';

RULE_WHITE_SPACE : (' '|'\t'|'\r'|'\n')+;

RULE_ID : ('0'..'9'|'a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9'|'.'|'-')*;
