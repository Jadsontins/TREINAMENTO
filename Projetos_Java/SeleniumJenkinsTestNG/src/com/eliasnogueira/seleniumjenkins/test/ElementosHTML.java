package com.eliasnogueira.seleniumjenkins.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class ElementosHTML {
	WebDriver driver = null;
	
	@BeforeTest
	public void setup() {
		driver = new FirefoxDriver();
	}


	@Test (description = "Execucao do exemplo de Elementos HTML", groups = "smoke")
	public void test() throws IOException, InterruptedException {
		driver.get("http://eliasnogueira.com/selenium/exercicios/selenium_ide/ajybuyje/avancado/elementos/");
		
		driver.findElement(By.name("nome")).sendKeys("Elias");
		driver.findElement(By.name("email")).sendKeys("elias.nogueira@gmail.com");
		driver.findElement(By.name("senha")).sendKeys("123456");
		driver.findElement(By.name("newsletter")).click();
		driver.findElement(By.xpath("//input[@name='formato' and @value='texto']")).click();
		
		Select select = new Select(driver.findElement(By.name("tipo")));
		select.selectByVisibleText("Profissional - Paga");
		
		select = new Select(driver.findElement(By.name("interesse[]")));
		select.selectByVisibleText("Teste de Software");
		select.selectByVisibleText("Gerencia PMI/Agil");
		
		driver.findElement(By.name("comentario")).sendKeys("Nao quero receber spam");
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("C:\\scripts\\Selenium2_imagem1.png"));
		
		driver.findElement(By.name("submit")).click();
		
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            @Override
			public Boolean apply(WebDriver d) {
            	return driver.findElement(By.tagName("body")).getText().contains("Elias");
            }
        });

		scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("C:\\scripts\\Selenium2_imagem2.png"));
		
		assertEquals("Caro, Elias\nO email cadastrado foi: elias.nogueira@gmail.com\n\nVocê não deseja receber newsletter\nO email deve ser enviado com formato: Texto\nO Tipo de Assinatura selecionado foi: Profissional - Paga\n\nSeus assuntos de interesse são:\nTeste de Software\nGerencia PMI/Agil\n\nComentário:\nNao quero receber spam", 
				driver.findElement(By.id("respostaa")).getText());
	}



	@AfterTest
	public void tearDown() {
		driver.close();
	}
}
