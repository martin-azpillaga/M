package m.validation;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.ParserRule;

public abstract class Problem {
	protected EObject source;
	protected EStructuralFeature feature;
	protected Severity severity;
	
	public EObject getSource() {
		return source;
	}
	
	public EStructuralFeature getFeature() {
		return feature;
	}
	
	public Severity getSeverity() {
		return severity;
	}
}

enum Severity {
	INFO, WARNING, ERROR
}

class SyntaxError extends Problem {
	ParserRule rule;
	
	public SyntaxError(EObject source, ParserRule rule) {
		this.source = source;
		this.severity = Severity.ERROR;
		this.rule = rule;
	}
	
	public ParserRule getRule() {
		return rule;
	}
}

class UndefinedSymbol extends Problem {
	String hint;
	
	public UndefinedSymbol(EObject source, EStructuralFeature feature, String hint) {
		this.source = source;
		this.feature = feature;
		this.severity = Severity.ERROR;
		this.hint = hint;
	}
	
	public String getHint() {
		return hint;
	}
}

class SymbolRedefinition extends Problem {
	EObject definition;
	
	public SymbolRedefinition(EObject source, EObject definition) {
		this.source = source;
		this.severity = Severity.ERROR;
		this.definition = definition;
	}
	
	public EObject getDefinition() {
		return definition;
	}
}

class UndecidableType extends Problem {
	Map<EObject, BindingReason> links;
	
	public UndecidableType(EObject source, EStructuralFeature feature, Map<EObject, BindingReason> links)
	{
		this.source = source;
		this.feature = feature;
		this.links = links;
	}
	
	public Map<EObject, BindingReason> getLinks()
	{
		return links;
	}
}
