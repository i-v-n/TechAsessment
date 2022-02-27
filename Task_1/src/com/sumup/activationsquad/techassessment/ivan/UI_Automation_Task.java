package com.sumup.activationsquad.techassessment.ivan;

import static org.junit.Assert.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.google.common.collect.Iterables;


class UI_Automation_Task {
	
	private static ChromeOptions options;
	private static ChromeDriver driver;
	
	@Test
	public void Do_stuff_on_page_() {
		
		/* ***************************************************************/
		/* TEST DATA                                                     */
		/* ***************************************************************/
		final String URL_HELP_EN_GB = "https://help.sumup.com/en-GB";
		final String US = "United States";
		final String UK = "United Kingdom";
		final String SEARCH_TERM = "rare";
		final int EXPECTED_RESULTS = 3;
		/* ***************************************************************/
		
		// instantiate needed helper classes 
		GeneralHelper helper = new GeneralHelper();
		HelpPageObjects helpPage = new HelpPageObjects();
		
        // open a Chrome browser and maximize it
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        
        /*
         * a. Start from https://help.sumup.com/en-GB
         */
        
        // go to the help page
        driver.navigate().to(URL_HELP_EN_GB);
        
        // Give the page 5 seconds to stabilize, I get flakiness otherwise
        helper.waitForSeconds(5);
        
        /*
         * b. Using‌‌ the‌‌ country‌‌ drop-down‌‌ on‌‌ the‌‌ bottom‌‌ of the page, navigate to "United States"
         */
        
        // Get the country selection element with text "United Kingdom"
        helpPage.getCountryFromMenu(driver, UK).click();
        
        // Only for the sake of visibility - scroll down to the bottom of the page
        helper.scrollToBottomOfPage(driver);
        
        // Give the menu 2 seconds to stabilize, I get flakiness otherwise
        helper.waitForSeconds(2);
        
        // Click the United States element, even if it is not visible
        helpPage.getCountryFromMenu(driver, US).click();
        
        /*
         * c. Using‌‌ the‌‌ search box on top of the page, search for a term
         */        
        
        // Find search box and type text
        helpPage.getSearchBox(driver).sendKeys(SEARCH_TERM + Keys.RETURN);
        
        /*
         * d. Verify the number of search results displayed
         */
        
        int numberREsultsDisplayed = helpPage.getReportedNumberOfResults(driver);
        assertEquals("Number of search results for " + SEARCH_TERM + " is not as expected!", EXPECTED_RESULTS, numberREsultsDisplayed);        
                
        /*
         * e. Click on the very last result and verify the result item title matches the title of the selection
         */
        
        // Get all result items
        WebElement lastResultElement = Iterables.getLast(helpPage.getSearchResultItems(driver));
        
        // Get the last result item title
        String resultItemTitle = lastResultElement.getText();
        
        // Click the very last result
        lastResultElement.click();
        
        // Get the title element and extract the text from the title attribute
        assertEquals("Result item title and article title do not match!", resultItemTitle, helpPage.getArticleTitle(driver));
        
        /*
         * f. Leave the help section and return to the main site using the navigation 
         */
        
        // click the Home button in the navigation
        helpPage.getHomeButton(driver).click();
        
        driver.quit();
	}

	@BeforeAll
    public static void setup() throws Exception {
		
		// Set the path to the chrome driver executable, depending on the operating system
		String os = System.getProperty("os.name").toLowerCase();
		String chromeDriverBin = null;
		if (os.contains("win")){
		    //Operating system is based on Windows
			chromeDriverBin  = "chromedriver.exe";
		}
		else if (os.contains("nix") || os.contains("nux")){
		    //Operating system is based on Linux/Unix
			chromeDriverBin = "chromedriver";
		} else {
			throw new Exception("Unsupported operating system! Only Windows and Linux supported");
		}
		
		Path currentPath = Paths.get(System.getProperty("user.dir"));
		Path filePath = Paths.get(currentPath.toString(), "chromeDriver", chromeDriverBin);
		System.setProperty("webdriver.chrome.driver", filePath.toAbsolutePath().toString());
		
        // initialize chrome driver
		options = new ChromeOptions();
		
		// This option prevents the SumUp page from presenting bot challenges (CAPTCHAs)
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
	}

	@AfterEach
	public void teardown() {
		driver.quit();
	}
}
