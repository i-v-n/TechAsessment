package com.sumup.activationsquad.techassessment.ivan;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;

public class GeneralHelper {

	/**
	 * @param driver
	 */
	void scrollToBottomOfPage(ChromeDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
	    js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	/**
	 * @param seconds 
	 * 
	 */
	void waitForSeconds(int seconds) {
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

}
