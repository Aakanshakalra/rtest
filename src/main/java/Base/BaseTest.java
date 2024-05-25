package Base;

import java.io.File;
import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Cons;


public class BaseTest {
	public ExtentSparkReporter spark;
	public ExtentReports extent;
	public static WebDriver driver;
	public ExtentTest logger;
	@BeforeTest
	public void beforetestMethod() {
		spark = new ExtentSparkReporter(System.getProperty("user.dir") + File.separator+"Reports"+File.separator+"STMExtentReport.html");
		extent = new ExtentReports();
		extent.attachReporter(spark);
		spark.config().setTheme(Theme.STANDARD);
		extent.setSystemInfo("Host Name", "RPAS");
		extent.setSystemInfo("Environment", "Production");
		extent.setSystemInfo("User Name", "Aakansha Kalra");
		spark.config().setDocumentTitle("Automation Report");
		spark.config().setReportName("Automation Test Results ");
	}
	@BeforeMethod
	@Parameters("browser")
	public void beforemethod(String browser,Method testMethod) {
		logger = extent.createTest(testMethod.getName());
		setupDriver(browser);
		driver.manage().window().maximize();
		driver.get(Cons.url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(200));
	}
	@AfterMethod
	public void aftermethod(ITestResult result){
		if(result.getStatus() == ITestResult.FAILURE)
		{
			logger.log(Status.FAIL,MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
		}
		else if(result.getStatus() == ITestResult.SKIP){
			logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
		}
		else if(result.getStatus() == ITestResult.SUCCESS)
		{
			logger.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
		}
		driver.quit();
	}
	@AfterTest
	public void aftertestMethod() {
		extent.flush();

	}
	public void setupDriver(String browser) {
		if(browser.equalsIgnoreCase("chrome")){
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		if(browser.equalsIgnoreCase("firefox")){
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		if(browser.equalsIgnoreCase("edge")){
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
	}
}
