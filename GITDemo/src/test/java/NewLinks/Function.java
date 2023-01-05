package NewLinks;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Function {
	// static long startTime;
	// static long endTime;
	static String keywords;
	static String description;
	static String msg;
	static String title;
	// static long totalTime;
	static int code;

	public static void callHeaderLinks(WebDriver driver) throws Throwable {
		WebElement header = driver.findElement(By.xpath("//*[@id=\"headPart\"]"));
		// Storing the links in a list and traversing through the links
		List<WebElement> heaedrLinks = header.findElements(By.tagName("a"));
		// startTime = System.currentTimeMillis();
		// This line will print the number of links and the count of links.
		System.out.println("No of links are " + heaedrLinks.size());
		// checking the links fetched.
		for (int i = 0; i < heaedrLinks.size(); i++) {
			WebElement E1 = heaedrLinks.get(i);
			String url = E1.getAttribute("href");
			verifyLinks(driver, url);
		}
	}

	public static void callFooterLinks(WebDriver driver) throws Throwable {
System.out.println("*****************************************************************************************");
		WebElement footer = driver.findElement(By.xpath("/html/body/vw-root/vw-footer-v2"));
		// Storing the links in a list and traversing through the links
		List<WebElement> footerLinks = footer.findElements(By.tagName("a"));
		// This line will print the number of links and the count of links.
		System.out.println("\n \n No of links are " + footerLinks.size());
		// checking the links fetched.
		for (int i = 0; i < footerLinks.size(); i++) {
			WebElement E1 = footerLinks.get(i);
			String url = E1.getAttribute("href");
			verifyLinks(driver, url);
		}
	}

	public static void verifyLinks(WebDriver driver, String linkUrl) throws Throwable {
		try {
			long startTime = System.currentTimeMillis();
			URL url = new URL(linkUrl);
			// Now we will be creating url connection and getting the response code
			HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();
			httpURLConnect.connect();
			System.out.println();
			// httpURLConnect.getInputStream();
			System.out.println("Actual code = " + httpURLConnect.getResponseCode());
			if (httpURLConnect.getResponseCode() >= 400) {
				code = httpURLConnect.getResponseCode();
				msg = httpURLConnect.getResponseMessage();
				System.out.println("Inside function loop  >= 400 " + code);
				System.out.println(linkUrl + " - " + code + "    " + msg);
				long endTime = System.currentTimeMillis();
				// System.out.println("++++++++++++++++++++++++"+endTime);
				long totalTime = endTime - startTime;
				System.out.println("\n Total Page Load Time: " + totalTime + "milliseconds");
				WriteData.writeDataToExcel(driver, linkUrl, code, msg, totalTime);
				WriteData.metaData(linkUrl);
			}

			// Fetching and Printing the response code obtained
			if (httpURLConnect.getResponseCode() < 400) {
				System.out.println("Inside function loop  <400 " + code);
				System.out.println(linkUrl + " - " + httpURLConnect.getResponseCode());
				code = httpURLConnect.getResponseCode();
				msg = httpURLConnect.getResponseMessage();
				long endTime = System.currentTimeMillis();
				// System.out.println("++++++++++++++++++++++++"+endTime);
				long totalTime = endTime - startTime;
				System.out.println("\n Total Page Load Time: " + totalTime + "milliseconds");
				WriteData.writeDataToExcel(driver, linkUrl, code, msg, totalTime);
				WriteData.metaData(linkUrl);
			}
		} catch (Exception e) {
		}
	}

}
