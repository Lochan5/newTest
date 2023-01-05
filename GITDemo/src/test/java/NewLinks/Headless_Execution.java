package NewLinks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Headless_Execution {

	public static void main(String[] args) throws Throwable {
		
		  /* //For Chrome Browser

		ChromeOptions c_options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
        c_options.setHeadless(true);
		WebDriver driver1 = new ChromeDriver(c_options);
		  driver1.get("https://www.veenaworld.com/");
	      Function.callHeaderLinks(driver1);
	      Function.callFooterLinks(driver1);
	     driver1.quit();
	     
	     //For Mozilla firefox browser

		FirefoxOptions f_options = new FirefoxOptions();
		WebDriverManager.firefoxdriver().setup();
        f_options.setHeadless(true);
		WebDriver driver2 = new FirefoxDriver(f_options);
		  driver2.get("https://www.veenaworld.com/");
	     // Function.callHeaderLinks(driver2);
	      Function.callFooterLinks(driver2);
	     driver2.quit();
	     */
		EdgeOptions e_options = new EdgeOptions();
		WebDriverManager.edgedriver().setup();
        e_options.setHeadless(true);
		WebDriver driver3 = new EdgeDriver(e_options);
		  driver3.get("https://www.veenaworld.com/");
	     // Function.callHeaderLinks(driver3);
	      Function.callFooterLinks(driver3);
	     driver3.quit();
	     
	}

}
