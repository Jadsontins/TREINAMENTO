package com.eliasnogueira.selenium2junit.test;


import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Adiciona um Cliente
 * @author Elias Nogueira <elias.nogueira@gmail.com>
 *
 */
public class AdicionarCliente {

	private WebDriver driver;
	
	@Before
	public void setup() {
		driver = new InternetExplorerDriver();
	}
	
	@Test
	public void adicionaCliente() {
		/**
		 * Instancia para executar o Selenium no Firefox
		 * Basta trocar o FirefoxDriver por outro "BrowserDriver" para executar em outro browser.
		 * Ex: InternetExplorerDriver();
		 */
		
		
		// Espera implicita no Selenium para todos os elementos
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("http://localhost/selenium/exercicios/selenium_comp/scysnlkw/ajaxCRUD/exemplo/");							// abre a pagina
		driver.findElement(By.cssSelector("input.btn.editingSize")).click();													// clica no botao Adicionar Item
		driver.findElement(By.cssSelector("table[name=form] > tbody > tr > td > input[name=fldField1]")).sendKeys("Fulano");	// digita dados no campo Nome
		driver.findElement(By.name("fldField2")).sendKeys("da Silva");															// digita dados no campo Sobrenome
		new Select(driver.findElement(By.name("fldCertainFields"))).selectByValue("PayPal");									// seleciona dado da combo
		driver.findElement(By.name("fldLongField")).sendKeys("Enviar por Sedex");												// digita dado em Observacao
		driver.findElement(By.xpath("//input[@value='Salvar Item']")).submit();													// submete os dados
		
		/**
		 * Espera explicita do Selenium por um texto na pagina.
		 * Esta espera substitui o comando 'isTextPresent()' no Selenium 1
		 */
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            @Override
			public Boolean apply(WebDriver d) {
            	return driver.findElement(By.tagName("body")).getText().contains("Fulano");
            }
        });

		Assert.assertEquals(driver.findElement(By.xpath("//span")).getText(), "Fulano");
		
	}

	@After
	public void tearDown() {
		driver.close();	// fecha o browser
	}
}
