package com.eliasnogueira.selenium2pageobject.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ExecutaTeste {
	
	protected WebDriver driver;
	
	/**
	 * Antes de executar o teste cria uma nova instancia do WebDriver
	 */
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
	}


	/**
	 * Teste que executa o fluxo de adicionar e remover o cliente
	 */
	@Test
	public void execucao() {
		IndexPage indexPage = new IndexPage(driver);
		
		indexPage.adicionaCliente("Elias", "Nogueira", "Pagamento Digital", "WebDriver rocks!");
		Assert.assertEquals(indexPage.usuarioCriadoComSucesso("Elias"), true);

		indexPage.removeCliente();
		Assert.assertEquals(indexPage.usuarioRemovidoComSucesso("Elias"), true);
	}

	/**
	 * Depois que executa o teste fecha o browser
	 */
	@After
	public void tearDown() throws Exception {
		driver.close();
	}
}
