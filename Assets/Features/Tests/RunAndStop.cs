
using System.Collections;
using UnityEngine;
using UnityEngine.TestTools;
using NUnit.Framework;

public class RunAndStop
{
	RunAndStopSteps steps = new RunAndStopSteps();
	
	[UnityTest, Timeout(1000)]
	public IEnumerator FirstRun()
	{
		steps.GivenTheAppIsClosed();
		steps.WhenThePlayerRunsTheAppForTheFirstTime();
		steps.ThenTheScreenShowsThePermissionsScreen();
		yield return null;
	}
	[UnityTest, Timeout(1000)]
	public IEnumerator NonFirstRun()
	{
		steps.GivenTheAppIsClosed();
		steps.WhenThePlayerRunsTheAppNotForTheFirstTime();
		steps.ThenTheScreenShowsTheWelcomeScreen();
		yield return null;
	}
}
