package cloudTests;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.Constant;

public class SauceLabBaseTest {

	WebDriver driver;

	@Parameters({"browser", "platform", "version"})
	@BeforeMethod
	public void setUp(String browserName, String platformName, String versionName,  Method name) {

		System.out.println("browser name is : " + browserName);
		String methodName = name.getName();

		MutableCapabilities sauceOpts = new MutableCapabilities();
		sauceOpts.setCapability("name", methodName);
		sauceOpts.setCapability("build", Constant.BUILD);
		sauceOpts.setCapability("seleniumVersion", Constant.SELENIUMVERSION);
		sauceOpts.setCapability("username", Constant.SAUCELAB_USERNAME);
		sauceOpts.setCapability("accessKey", Constant.ACCESSKEY);
		sauceOpts.setCapability("tags", Constant.TAGS);

		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("sauce:options", sauceOpts);
		cap.setCapability("browserVersion", versionName);
		cap.setCapability("platformName", platformName);

		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			cap.setCapability("browserName", "chrome");
		} else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			cap.setCapability("browserName", "firefox");
		}
		// https://gagan12345:f01a8450-e53a-44f0-8a8f-64724e118da7@ondemand.us-west-1.saucelabs.com:443/wd/hub
		try {
			driver = new RemoteWebDriver(new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub"), cap);
			driver.manage().timeouts().implicitlyWait(Constant.IMPLICIT_WAIT, TimeUnit.SECONDS);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

}
