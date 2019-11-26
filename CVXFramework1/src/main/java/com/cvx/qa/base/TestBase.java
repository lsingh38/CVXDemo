package com.cvx.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;

import com.cvx.qa.util.WebEventListener;

public class TestBase {

	public static WebDriver driver;
	AndroidDriver<AndroidElement> anddriver;
	IOSDriver<IOSElement> iosdriver;
	public static Properties prop;
	public static WebDriverWait wait;
	public static Actions action;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static String browserName;
	DesiredCapabilities capabilities;
	String projectPath = System.getProperty("user.dir");

	// Constructor to initiate the config.properties file
	public TestBase() {

		prop = new Properties();
		FileInputStream ip;
		try {
	//		ip = new FileInputStream(projectPath + "\\src\\main\\java\\com\\cvx\\qa\\config\\config.properties");
			ip = new FileInputStream(projectPath + "//src//main//java//com//cvx//qa//config//config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getBrowserName(String selectedBrowserName) {
		browserName = selectedBrowserName;
	}
	// Method to initialize the browser and launch the browser

	public void initialization() {
		// String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();

			driver = new ChromeDriver();
			System.out.println("Chrome Browser detected");
			driver.manage().window().maximize();
		}

		else if (browserName.equalsIgnoreCase("IE11")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			System.out.println("IE11 Browser detected");
			driver.manage().window().maximize();
		}

		else if (browserName.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			System.out.println("Microsoft Edge Browser detected");
			driver.manage().window().maximize();
		}

		else if (browserName.equalsIgnoreCase("Safari")) {

			// Safari 10+ -One time manual steps is needed to perform
			// Once its completed, no need to setup through WebDriverManager
			// Safari10+ will take care itself

			driver = new SafariDriver();
			System.out.println("Safari Browser detected");
			driver.manage().window().maximize();
		}

		else if (browserName.equalsIgnoreCase("AndroidMobileChrome")) {

			capabilities = DesiredCapabilities.android();
			capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, prop.getProperty("androidMobileDeviceName"));
			capabilities.setCapability(MobileCapabilityType.UDID,prop.getProperty("androidMobileUDID"));

			try {

				anddriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Chrome Browser detected in Mobile");
			anddriver.context("CHROMIUM");
			driver = anddriver;

		}

		else if (browserName.equalsIgnoreCase("AndroidTabChrome")) {

			capabilities = DesiredCapabilities.android();
			capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, prop.getProperty("androidTabDeviceName"));
			capabilities.setCapability(MobileCapabilityType.UDID,prop.getProperty("androidTabUDID"));
			try {

				anddriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Chrome Browser detected in Tablet");
			anddriver.context("CHROMIUM");
			driver = anddriver;

		} 
		else if (browserName.equalsIgnoreCase("IOSIphoneSafari")) {

			capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.SAFARI);
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "ios");
	    	capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13.2");
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, prop.getProperty("IOSMobileDeviceName"));
		//	capabilities.setCapability(MobileCapabilityType.UDID,prop.getProperty("IOSMobileUDID"));

			try {

				iosdriver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Safari Browser detected in Iphone");
			
			driver = iosdriver;

		}

		else if (browserName.equalsIgnoreCase("IOSTabSafari")) {

			capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.SAFARI);
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "ios");
	    	capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13.2");
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, prop.getProperty("IOSTabDeviceName"));
		//	capabilities.setCapability(MobileCapabilityType.UDID,prop.getProperty("IOSMobileUDID"));

			try {

				iosdriver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Safari Browser detected in ITab");
			
			driver = iosdriver;


		} 
		else {
			System.out.println(browserName + "Browser has not been implemented");

		}

		e_driver = new EventFiringWebDriver(driver);

		// Now create object of EventListnerHandler to register with
		// EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;

		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("flightSearchUrl"));
		driver.manage().timeouts().pageLoadTimeout(Long.parseLong(prop.getProperty("pageLoadTimeout")),
				TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Long.parseLong(prop.getProperty("implicitlyWait")), TimeUnit.SECONDS);
		action = new Actions(driver);
		wait = new WebDriverWait(driver, 50);

	}

}
