package com.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.Utilities.Utils;
import com.Utilities.WebEventListener;


 

public class Helper {

	public static WebDriver driver;
	public static Properties prop;
	public static int click = 5;
	public static EventFiringWebDriver eventFiring;
	public static WebDriverEventListener eventListener;

	public Helper() {
		prop = new Properties();
		FileInputStream fis;
		try {
			fis = new FileInputStream("/Users/khosruzzaman/Current_Works/datadrivenTest/src/main/java/com/Configurations/config.Properties");
			try {
				prop.load(fis);
			} catch (FileNotFoundException fnf) {
				fnf.printStackTrace();
			}
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}
	
	public static void initializations() {
		String browserName = prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "/Users/khosruzzaman/Current_Works/datadrivenTest/Drivers/chromedriver");
			driver = new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("ff")) {
			System.setProperty("webdriver.gecko.driver", "/Users/khosruzzaman/Drivers/geckodriver");
			driver = new FirefoxDriver();
		}else {
			driver = new SafariDriver();
		}
		
		eventFiring = new EventFiringWebDriver(driver);
		
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
	
		eventFiring.register(eventListener);
		driver = eventFiring;
		
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Utils.PAGE_LOADE_TIME, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Utils.IMPLICITLY_WAIT_TIME, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		
	}

}
