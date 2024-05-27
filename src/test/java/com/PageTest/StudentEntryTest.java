package com.PageTest;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
 

import com.Base.Helper;
import com.Pages.StudentEntry;
import com.Utilities.Utils;

public class StudentEntryTest extends Helper {
	StudentEntry studentEntry;
	Utils utils;
	static String sheetName = "studentsData";

	public StudentEntryTest() {
		super();
	}
	
	@BeforeMethod(groups = "Main Functionality")
	public void setup() {
		initializations();
		studentEntry = new StudentEntry();
		utils = new Utils();
	}

	@Test(priority = 1, groups = "UI Eelemnts")
	public void pageTitleTest() throws InterruptedException {
		String title = studentEntry.titleOfThePage();
		Thread.sleep(5000);
		Assert.assertEquals(title, "Zsolution Home Page", "Edxpected Title not found");
		
	}
	@Test(priority = 2, groups = "UI Eelemnts")
	public void moveToStudentsOptions() {
		studentEntry.clickStudentManue();
		studentEntry.validateStudentOption();
	}
	@Test(priority = 3, groups = "UI Eelemnts")
	public void studentPage() {
		studentEntry.clickStudentManue();
		studentEntry.clickOnStudentEntryButton();
		String title = studentEntry.titleOfThePage();
		Assert.assertEquals(title, "Zsolution New Student Entry Form", "Edxpected Title not found");
	}
	@DataProvider
	public Object[][] getStudentData(){
		Object[][] data = Utils.getTestData(sheetName);
		return data;
	}
	@Test(priority = 4, groups = "Test Data", dataProvider = "getStudentData")
	public void addNewStudentData(String grous, String id, String firstName, String lastName, String grade,
			String email) throws InterruptedException {
		studentPage();
		studentEntry.enterNewStudentData(grous, id, firstName, lastName, grade, email);
		studentEntry.clicEntryButton();
		Thread.sleep(4000);
		String message = studentEntry.getConfirmation().getText();
		Assert.assertEquals(message, "Data Stored successfully!!");
		studentEntry.getConfirmation().dismiss();
		studentEntry.backHome();
	}
	
	
	@AfterMethod(groups = "Main Functionality")
	public void closeBrosers() {
		driver.manage().deleteAllCookies();
		studentEntry.closeBrowser();
	}
}
