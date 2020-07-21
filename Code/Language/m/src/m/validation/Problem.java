package m.validation;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.ParserRule;

import m.m.Expression;
import m.types.Type;

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
	List<Link> links;
	
	public UndecidableType(EObject source, EStructuralFeature feature, List<Link> links)
	{
		this.source = source;
		this.feature = feature;
		this.severity = Severity.WARNING;
		this.links = links;
	}
	
	public List<Link> getLinks()
	{
		return links;
	}
}

