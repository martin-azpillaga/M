package games.minim

class MStandaloneSetup extends MStandaloneSetupGenerated 
{
	def static void doSetup() 
	{
		new MStandaloneSetup().createInjectorAndDoEMFRegistration()
	}
}
