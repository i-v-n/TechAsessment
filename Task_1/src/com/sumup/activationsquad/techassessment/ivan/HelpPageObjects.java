package com.sumup.activationsquad.techassessment.ivan;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelpPageObjects {

	/**
	 * @param driver
	 * @return
	 */
	public WebElement getCountryFromMenu(ChromeDriver driver, String country) {
		return new WebDriverWait(driver, Duration.ofSeconds(10))
		.until(d -> driver.findElement(By.xpath("//p[text()='" + country + "']")));
	}

	/**
	 * @param resultsText
	 * @return
	 */
	int getReportedNumberOfResults(ChromeDriver aDriver) {
		String resultsText = getSearchResultsText(aDriver);
		String relevantResultString = resultsText
	    								.substring(0, resultsText.indexOf("results for \""))
	    								.strip();
	    
	    int reportedNumberOfResults = 0;
	    if (relevantResultString.contains("No")) {
	    	reportedNumberOfResults = 0;
	    } else {
	    	reportedNumberOfResults = Integer.parseInt(relevantResultString);
	    }
		return reportedNumberOfResults;
	}

	/**
	 * @param driver
	 * @return
	 */
	WebElement getSearchBox(ChromeDriver driver) {
		String searchbox_id = "query";
	    WebElement searchBox =  new WebDriverWait(driver, Duration.ofSeconds(10))
	    		.until(d -> driver.findElement(By.id(searchbox_id)));
		return searchBox;
	}

	/**
	 * @param driver
	 * @return
	 */
	String getSearchResultsText(ChromeDriver driver) {
		WebElement resultsTextElement = new WebDriverWait(driver, Duration.ofSeconds(10))
	    		.until(d -> driver.findElement(By.xpath("//p[@class='article-count text--mega']"))); 
	
	    // Extract the text and parse the number
	    String resultsText = resultsTextElement.getText();
		return resultsText;
	}

	/**
	 * @param driver
	 * @return
	 */
	List<WebElement> getSearchResultItems(ChromeDriver driver) {
		List<WebElement> resultLinkElements =  new WebDriverWait(driver, Duration.ofSeconds(10))
	    		.until(d -> driver.findElements(By.xpath("//a[@class='subsection__article__title']")));
		return resultLinkElements;
	}

	/**
	 * @param driver
	 * @return
	 */
	String getArticleTitle(ChromeDriver driver) {
		WebElement articleTitleElement = new WebDriverWait(driver, Duration.ofSeconds(10))
	    		.until(d -> driver.findElement(By.xpath("//h1[@class='article-title heading--peta']")));
	    
	    String articleTitle = articleTitleElement.getAttribute("title");
		return articleTitle;
	}

	/**
	 * @param driver
	 * @return
	 */
	WebElement getHomeButton(ChromeDriver driver) {
		return driver.findElement(By.xpath("//a[@class='header-controls__item menu-marketing']"));
	}

}
