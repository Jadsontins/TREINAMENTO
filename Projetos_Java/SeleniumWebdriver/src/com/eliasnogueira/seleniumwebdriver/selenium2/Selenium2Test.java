package com.eliasnogueira.seleniumwebdriver.selenium2;

import static org.junit.Assert.*;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Selenium2Test {
	
	WebDriver driver;
	
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
	}

	@Test
	public void testSelenium1() throws Exception {
		driver.get("http://eliasnogueira.com/selenium/exercicios/selenium_ide/ajybuyje/avancado/elementos/");
		
		driver.findElement(By.name("nome")).sendKeys("Elias");									// digitacao do nome
		driver.findElement(By.name("email")).sendKeys("elias.nogueira@gmail.com");				// digitacao do email
		driver.findElement(By.name("senha")).sendKeys("123456");								// digitacao da senha
		driver.findElement(By.name("newsletter")).click();										// click no checkbox
		driver.findElement(By.xpath("//input[@name='formato' and @value='texto']")).click();	// click no item 'texto' do radiobutton
		
		Select select = new Select(driver.findElement(By.name("tipo")));						// criando um objeto 'Select' para fazer a selecao
		select.selectByVisibleText("Profissional - Paga");										// selecionando o valor na combo
		
		select = new Select(driver.findElement(By.name("interesse[]")));						// utilizando o mesmo objeto 'Select'
		select.selectByVisibleText("Teste de Software");										// selecionado um dos valores
		select.selectByVisibleText("Gerencia PMI/Agil");										// selecionando outro valor no mesmo elemento
		
		driver.findElement(By.name("comentario")).sendKeys("Nao quero receber spam");			// digitacao da observacao

		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);				// criando objeto para capura da screenshot
		FileUtils.copyFile(scrFile, new File("C:\\Users\\Elias\\workspace\\SeleniumWebdriver\\evidencias\\Selenium2_imagem1.png"));
		
		driver.findElement(By.name("submit")).click();
		
		FileUtils.copyFile(scrFile, new File("C:\\Users\\Elias\\workspace\\SeleniumWebdriver\\evidencias\\Selenium2_imagem2.png"));
		
		assertEquals("Caro, Elias\nO email cadastrado foi: elias.nogueira@gmail.com\n\nVocê não deseja receber newsletter\nO email deve ser enviado com formato: Texto\nO Tipo de Assinatura selecionado foi: Profissional - Paga\n\nSeus assuntos de interesse são:\nTeste de Software\nGerencia PMI/Agil\n\nComentário:\nNao quero receber spam", 
				driver.findElement(By.id("resposta")).getText());
	}

	@After
	public void tearDown() throws Exception {
		driver.close();
	}
}
