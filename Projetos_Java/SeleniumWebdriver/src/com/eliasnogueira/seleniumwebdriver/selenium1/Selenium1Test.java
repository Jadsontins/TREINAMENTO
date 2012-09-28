package com.eliasnogueira.seleniumwebdriver.selenium1;

import com.thoughtworks.selenium.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.server.SeleniumServer;

@SuppressWarnings("deprecation")
public class Selenium1Test extends SeleneseTestCase {
	
	Selenium selenium;
	SeleniumServer server;
	
	@Before
	public void setUp() throws Exception {
		server = new SeleniumServer();
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://eliasnogueira.com/");
		server.start();
		selenium.start();
	}

	@Test
	public void testSelenium1() throws Exception {
		selenium.open("/selenium/exercicios/selenium_ide/ajybuyje/avancado/elementos/");
		selenium.type("name=nome", "Elias");
		selenium.type("name=email", "elias.nogueira@gmail.com");
		selenium.type("name=senha", "123456");
		selenium.click("name=newsletter");
		selenium.click("//input[@name='formato' and @value='texto']");
		selenium.select("name=tipo", "label=Profissional - Paga");
		selenium.addSelection("name=interesse[]", "label=Teste de Software");
		selenium.addSelection("name=interesse[]", "label=Gerencia PMI/Agil");
		selenium.type("name=comentario", "Nao quero receber spam");
		selenium.captureEntirePageScreenshot("C:\\Users\\Elias\\workspace\\SeleniumWebdriver\\evidencias\\Selenium1_imagem1.png", "");
		selenium.click("name=submit");
		selenium.waitForPageToLoad("30000");
		selenium.captureEntirePageScreenshot("C:\\Users\\Elias\\workspace\\SeleniumWebdriver\\evidencias\\Selenium1_imagem2.png", "");
		assertEquals("Caro, Elias \nO email cadastrado foi: elias.nogueira@gmail.com\n\nVocê não deseja receber newsletter\nO email deve ser enviado com formato: Texto\nO Tipo de Assinatura selecionado foi: Profissional - Paga\n \nSeus assuntos de interesse são: \nTeste de Software\nGerencia PMI/Agil\n \nComentário: \nNao quero receber spam", selenium.getText("id=resposta"));
	} //background=#CCFFDD

	@After
	public void tearDown() throws Exception {
		selenium.stop();
		server.stop();
	}
}
