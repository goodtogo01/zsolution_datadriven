package com.Pages;

import java.awt.print.Pageable;
import java.security.Key;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Base.Helper;
import com.Utilities.Utils;

public class StudentEntry extends Helper {

	// Page factory

	@FindBy(xpath = "/html/body/div[1]/button[2]")
	WebElement student_Home;

	@FindBy(xpath = "//*[@id='Students']")
	WebElement show_Student_Options;

	@FindBy(xpath = "//*[@id='Students']/button")
	WebElement student_Button;

	@FindBy(xpath = "//*[@id='departmentsGroup']")
	WebElement dept_grp;

	@FindBy(xpath = "//*[@id='id']")
	WebElement st_id;

	@FindBy(xpath = "//*[@id='firstName']")
	WebElement st_fName;

	@FindBy(xpath = "//*[@id='lastName']")
	WebElement st_lName;

	@FindBy(xpath = "//*[@id='Grade']")
	WebElement st_grd;
	@FindBy(xpath = "//*[@id='email']")
	WebElement st_email;

	@FindBy(xpath = "/html/body/form/input[7]")
	WebElement entry_data;

	@FindBy(xpath = "/html/body/form/button")
	WebElement backToHomePage;

	// Ininitialing of Page Factory
	public StudentEntry() {
		PageFactory.initElements(driver, this);
	}
	
	// Actions
	public String titleOfThePage() {
		String title = driver.getTitle();
		System.out.println("The Title Of the Page is : " + title);
		return title;
	}

	public void clickStudentManue() {
		student_Home.click();
	}

	public void validateStudentOption() {
		boolean appear = show_Student_Options.isDisplayed();
		System.out.println("Student option has displayed : " + appear);
	}

	public void clickOnStudentEntryButton() {
		student_Button.click();
	}

	public void enterNewStudentData(String grous, String id, String firstName, String lastName, String grade,
			String email) {

		dept_grp.sendKeys(grous);
		st_id.sendKeys(id);
		st_fName.sendKeys(firstName);
		st_lName.sendKeys(lastName);
		st_grd.sendKeys(grade);
		st_email.sendKeys(email);
	}

	public void clicEntryButton() {
		entry_data.click();

	}

	public Alert getConfirmation() {
		Alert alert = driver.switchTo().alert();
		return alert;

	}

	public void backHome() {
		backToHomePage.click();
		titleOfThePage();
	}

	public void closeBrowser() {
		driver.close();
	}
}
