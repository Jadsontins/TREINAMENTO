package com.eliasnogueira.seleniumtestlink.teste;

import static org.junit.Assert.*;
import junit.framework.TestCase;
import br.eti.kinoshita.testlinkjavaapi.model.ExecutionStatus;

import com.eliasnogueira.seleniumtestlink.utils.TestlinkMetodos;
import com.thoughtworks.selenium.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.server.SeleniumServer;


public class AdicionarCliente {
	
	Selenium selenium;
	SeleniumServer server;
	
	@Before
	public void setUp() throws Exception {
		server = new SeleniumServer();
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://localhost/");
		
		server.start();
		selenium.start();
	}

	@Test
	public void testAdicionarCliente() throws Exception {
		try {
			
			selenium.open("/selenium/exercicios/selenium_comp/scysnlkw/ajaxCRUD/exemplo/");
			selenium.click("css=input.btn.editingSize");
			
			for (int second = 0;; second++) {
				if (second >= 60) fail("timeout");
				try { if (selenium.isElementPresent("css=table[name=form] > tbody > tr > td > input[name=fldField1]")) break; } catch (Exception e) {}
				Thread.sleep(1000);
			}

			selenium.type("css=table[name=form] > tbody > tr > td > input[name=fldField1]", "Fulano");
			selenium.type("name=fldField2", "da Silva");
			selenium.select("name=fldCertainFields", "label=PagSeguro");
			selenium.type("name=fldLongField", "Enviar por Sedex");
			selenium.click("//input[@value='Salvar Item']"); // TEM ERRO AQUI
			
			for (int second = 0;; second++) {
				if (second >= 60) fail("timeout");
				try { if (selenium.isTextPresent("Fulano")) break; } catch (Exception e) {}
				Thread.sleep(1000);
			}

			assertTrue(selenium.isTextPresent("Fulano"));
			assertTrue(selenium.isTextPresent("da Silva"));
			assertTrue(selenium.isTextPresent("PagSeguro"));
			assertTrue(selenium.isTextPresent("Enviar por Sedex"));
			
			TestlinkMetodos.executarCasoDeTeste("Plano AjaxCRUD", "Projeto Teste", "Suite CRUD", "Adiconar um novo Cliente", ExecutionStatus.PASSED, "Passou com sucesso" );
			
		} catch (Exception e) {
			TestlinkMetodos.executarCasoDeTeste("Plano AjaxCRUD", "Projeto Teste", "Suite CRUD", "Adiconar um novo Cliente", ExecutionStatus.FAILED, e.toString());
			TestCase.fail();
		}
	}
		


	@After
	public void tearDown() throws Exception {
		selenium.stop();
		server.stop();
	}
}
