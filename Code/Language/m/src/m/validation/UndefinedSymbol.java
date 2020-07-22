package m.validation;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class UndefinedSymbol extends Problem {
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
