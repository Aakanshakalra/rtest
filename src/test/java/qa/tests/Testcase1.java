package qa.tests;

import org.testng.annotations.Test;

import Base.BaseTest;
import PageEvents.LoginEvents;
import PageEvents.SponserEvents;
import utils.ElementFetch;

public class Testcase1 extends BaseTest{
	ElementFetch ele = new ElementFetch();
	LoginEvents login = new LoginEvents();
	SponserEvents sponser = new SponserEvents();
	@Test
	public void methodtologinRPAS() {
		logger.info("Sign in into RPAS");
		login.Login();
		logger.info("Credentials entered");
		sponser.verifyifsponserlistloaded();
		logger.info("List loaded and Selected the sponser");
		sponser.enterSponser();
		

	}
}
