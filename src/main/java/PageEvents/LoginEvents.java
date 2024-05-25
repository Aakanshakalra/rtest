package PageEvents;

import PageObject.LoginElements;
import utils.ElementFetch;

public class LoginEvents {

	ElementFetch ele = new ElementFetch();
	public void Login() {
		ele.getWebElement("NAME",LoginElements.UserNameInput).sendKeys(LoginElements.userid);
		ele.getWebElement("NAME",LoginElements.PassWordInput).sendKeys(LoginElements.pass);
		ele.getWebElement("NAME",LoginElements.loginbtn).click();
	}

}
