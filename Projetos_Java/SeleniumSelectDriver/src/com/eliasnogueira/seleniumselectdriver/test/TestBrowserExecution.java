package com.eliasnogueira.seleniumselectdriver.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.eliasnogueira.seleniumselectdriver.util.SeleniumUtils;
import com.eliasnogueira.seleniumselectdriver.util.SeleniumInterface;

public class TestBrowserExecution implements SeleniumInterface {

	protected WebDriver driver;
	
	@Before
	public void setup() {
		driver = SeleniumUtils.getDriver(SeleniumUtils.getSeleniumProperties("selenium.browser"));
	}
	
	@Test
	public void testaAcesso() {
		driver.get("http://eliasnogueira.com/selenium/exercicios/selenium_ide/ajybuyje/avancado/");
		driver.findElement(By.tagName("body")).getText().contains("Elementos HTML");
	}
	
	
	@After
	public void tearDown() {
		driver.close();
	}
}
