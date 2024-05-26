package com.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.Base.Helper;

public class Utils extends Helper {
	public static final long IMPLICITLY_WAIT_TIME = 10;
	public static final long PAGE_LOADE_TIME = 10;
	public static String TEST_DATA_SHEET_PATH = "../datadrivenTest/zsData.xlsx";
	static Workbook book;
	static Sheet sheet;
	public static FileInputStream fis;

	
	public static Object[][] getTestData(String sheetName){
		try {
			fis = new FileInputStream(TEST_DATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(fis);
		} catch (IOException e) {
 			e.printStackTrace();
		} 
		
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(1).getLastCellNum()];
		System.out.println(sheet.getLastRowNum()+ " ROW's and ==========="+sheet.getRow(0).getLastCellNum()+" COLUMN's");
		for(int i =0; i<sheet.getLastRowNum(); i++) {
			for(int j = 0; j<sheet.getRow(0).getLastCellNum(); j++) {
				data[i][j]= sheet.getRow(i+1).getCell(j).toString(); // i+1 cell--0
				System.out.println(data[i][j]+"  ");
			}
		}
		return data;
		
	}
	public static void takeScreenShots() throws IOException {
		File srsFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(srsFile, new File(currentDir+"/Screen_Shoots/"+System.currentTimeMillis()+".png"));
	}
	public static void expliciteWait(String locator) {
		WebDriverWait wait = new WebDriverWait(driver, IMPLICITLY_WAIT_TIME);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator)));
		}
	public static void selectFunctions(WebElement locator) {
		Select select = new Select(locator);
		select.selectByIndex(click);
		
	}
	public static void getAllertHandles() {
		Alert alert = driver.switchTo().alert();
	}
 
	public static int randomId() {
		Random r = new Random();
		int i = 199;
		int j = 180;
		int res = r.nextInt(i-j);
		System.out.println(res);
		return res;
	}
}









