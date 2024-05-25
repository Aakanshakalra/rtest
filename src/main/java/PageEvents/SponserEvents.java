package PageEvents;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import PageObject.SponserElements;
import utils.ElementFetch;

public class SponserEvents {
	ElementFetch ele = new ElementFetch();
	public void verifyifsponserlistloaded() {
		Assert.assertTrue(ele.getWebElements("NAME",SponserElements.RequestBtn).size()>0, "Element not Found");
	}
	public void enterSponser() {
		WebElement ddown = ele.getWebElement("NAME",SponserElements.sponserlist);
		Select select = new Select(ddown);
		select.selectByVisibleText(SponserElements.sponsername);
		ele.getWebElement("NAME",SponserElements.RequestBtn).click();

	}

}
