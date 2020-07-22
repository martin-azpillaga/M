package m.generator;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.generator.AbstractGenerator;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.generator.IGeneratorContext;

import com.google.inject.Inject;

import m.validation.MValidator;

public class MGenerator extends AbstractGenerator
{
	@Inject
	MValidator validator;
	
	public void doGenerate(Resource resource, IFileSystemAccess2 fsa, IGeneratorContext context)
	{
		var game = validator.getGame();
		if (game != null)
		{
			new Unity().generate(game, fsa);
		}
	}
}
