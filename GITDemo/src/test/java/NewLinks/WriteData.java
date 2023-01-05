package NewLinks;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;

public class WriteData 
{
	static int i = 0, pnum = 0, j = 0, fnum = 0;
	static String keywords;
	static String description;
	static String title;	
	public static void metaData(String linkUrl) throws IOException 
	{
		Document doc = Jsoup.connect(linkUrl).get();
		title = doc.title();
		System.out.println("title is: " + title);
		Document metadoc = Jsoup.connect(linkUrl).get();
		keywords = metadoc.select("meta[name=keywords]").first().attr("content");
		System.out.println("Meta keyword : " + keywords);
		description = metadoc.select("meta[name=description]").get(0).attr("content");
		System.out.println("Meta description : " + description);
		File src = new File("D:\\veena_automation\\Excel Files\\Header Footer Links\\Status.xlsx");
		FileInputStream fileinput = new FileInputStream(src);
		URL url = new URL(linkUrl);
		HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();
		httpURLConnect.connect();
		try (XSSFWorkbook workbook = new XSSFWorkbook(fileinput))
		{
			Sheet sheet = workbook.getSheet("Pass");
			Sheet sheet1 = workbook.getSheet("Fail");

			sheet.createRow(0);
				sheet.getRow(0).createCell(0).setCellValue("Sr No");
				sheet.getRow(0).createCell(1).setCellValue("Meta Title");
				sheet.getRow(0).createCell(2).setCellValue("Meta Keyword");
				sheet.getRow(0).createCell(3).setCellValue("Meta Description");
				sheet.getRow(0).createCell(4).setCellValue("Request URL");
				sheet.getRow(0).createCell(5).setCellValue("Status Code");
				sheet.getRow(0).createCell(6).setCellValue("Response Message");
				sheet.getRow(0).createCell(7).setCellValue("Response Time");
			sheet1.createRow(0);
				sheet1.getRow(0).createCell(0).setCellValue("Sr No");
				sheet1.getRow(0).createCell(1).setCellValue("Meta Title");
				sheet1.getRow(0).createCell(2).setCellValue("Meta Keyword");
				sheet1.getRow(0).createCell(3).setCellValue("Meta Description");
				sheet1.getRow(0).createCell(4).setCellValue("Request URL");
				sheet1.getRow(0).createCell(5).setCellValue("Status Code");
				sheet1.getRow(0).createCell(6).setCellValue("Response Message");
				sheet1.getRow(0).createCell(7).setCellValue("Response Time");
			if (httpURLConnect.getResponseCode()>=400)
			{
				sheet1.getRow(i).createCell(1).setCellValue(title);
				sheet1.getRow(i).createCell(2).setCellValue(keywords);
				sheet1.getRow(i).createCell(3).setCellValue(description);
				;
				System.out.println("Data added in fail sheet");
			}
			if (httpURLConnect.getResponseCode()<400)
			{
				sheet.getRow(j).createCell(1).setCellValue(title);
				sheet.getRow(j).createCell(2).setCellValue(keywords);
				sheet.getRow(j).createCell(3).setCellValue(description);
				System.out.println("Data added in pass sheet");
			}

			try (FileOutputStream outputStream = new FileOutputStream(src)) 
			{
				workbook.write(outputStream);
			}

		}
	}


	public static void writeDataToExcel(WebDriver driver,String linkUrl,int code,String msg,long totalTime)throws Throwable
		{
			File src = new File("D:\\veena_automation\\Excel Files\\Header Footer Links\\Status.xlsx");
			FileInputStream fileinput = new FileInputStream(src);
			//URL url = new URL(linkUrl);
			//HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();
		//	httpURLConnect.connect();
			try (Workbook workbook = new XSSFWorkbook(fileinput))
			{
				Sheet sheet = workbook.getSheet("Pass");
				Sheet sheet1 = workbook.getSheet("Fail");
				
				sheet.createRow(0);
				sheet.getRow(0).createCell(0).setCellValue("Sr No");
				sheet.getRow(0).createCell(1).setCellValue("Meta Title");
				sheet.getRow(0).createCell(2).setCellValue("Meta Keyword");
				sheet.getRow(0).createCell(3).setCellValue("Meta Description");
				sheet.getRow(0).createCell(4).setCellValue("Request URL");
				sheet.getRow(0).createCell(5).setCellValue("Status Code");
				sheet.getRow(0).createCell(6).setCellValue("Response Message");
				sheet.getRow(0).createCell(7).setCellValue("Response Time");
			
				sheet1.createRow(0);
				sheet1.getRow(0).createCell(0).setCellValue("Sr No");
				sheet1.getRow(0).createCell(1).setCellValue("Meta Title");
				sheet1.getRow(0).createCell(2).setCellValue("Meta Keyword");
				sheet1.getRow(0).createCell(3).setCellValue("Meta Description");
				sheet1.getRow(0).createCell(4).setCellValue("Request URL");
				sheet1.getRow(0).createCell(5).setCellValue("Status Code");
				sheet1.getRow(0).createCell(6).setCellValue("Response Message");
				sheet1.getRow(0).createCell(7).setCellValue("Response Time");
				
				if (code >= 400) 
				{
	 				System.out.println("Inside 400 "+code);
	 				++i;
	 				System.out.println("Inside loop"+code);
					sheet1.createRow(i);
					sheet1.getRow(i).createCell(0).setCellValue(++fnum);
					sheet1.getRow(i).createCell(4).setCellValue(linkUrl);
					sheet1.getRow(i).createCell(5).setCellValue(code);
					sheet1.getRow(i).createCell(6).setCellValue(msg);
					sheet1.getRow(i).createCell(7).setCellValue(totalTime);	
					System.out.println("Data added in fail sheet");
				}
				if (code < 400) 
				{
	 				System.out.println("Inside 200   "+code);
					++j;
					sheet.createRow(j);
					sheet.getRow(j).createCell(0).setCellValue(++pnum);
					sheet.getRow(j).createCell(4).setCellValue(linkUrl);
					sheet.getRow(j).createCell(5).setCellValue(code);
					sheet.getRow(j).createCell(6).setCellValue(msg);
					sheet.getRow(j).createCell(7).setCellValue(totalTime);
					System.out.println("Data added in pass sheet");

				}
	
				try (FileOutputStream outputStream = new FileOutputStream(src)) 
				{
					workbook.write(outputStream);
				}
			}
		}
}
