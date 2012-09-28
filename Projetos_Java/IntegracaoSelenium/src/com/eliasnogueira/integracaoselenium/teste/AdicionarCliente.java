package com.eliasnogueira.integracaoselenium.teste;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import br.eti.kinoshita.testlinkjavaapi.model.ExecutionStatus;

import com.eliasnogueira.integracaoselenium.utils.IConstantes;
import com.eliasnogueira.integracaoselenium.utils.IntegracaoUtils;
import com.eliasnogueira.integracaoselenium.utils.MantisMetodos;
import com.eliasnogueira.integracaoselenium.utils.TestlinkMetodos;
import com.thoughtworks.selenium.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.server.SeleniumServer;



public class AdicionarCliente extends IntegracaoUtils implements IConstantes {
	
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

			assertTrue(selenium.isTextPresent("Fulanoo"));
			assertTrue(selenium.isTextPresent("da Silva"));
			assertTrue(selenium.isTextPresent("PagSeguro"));
			assertTrue(selenium.isTextPresent("Enviar por Sedex"));
			
		} catch (AssertionError ae) {
			reportError(ae);
			
		} catch (Exception e) {
			reportError(e);

		} finally {
			if (isErro()) {
				String sumarioMantis = "Erro ao Adicionar Cliente";
				String descricaoMantis = "Ao tentar Adicionar um Cliente, ocorreu um erro";
				
				String bugId = MantisMetodos.reporIssue(MANTIS_PROJETO, sumarioMantis, descricaoMantis, "General", getMsgErro(), getEvidenciaErro(), "AdicionarClienteErro");
				TestlinkMetodos.executarCasoDeTeste(TESTLINK_PLANO, TESTLINK_PROJETO, "Suite CRUD", "Adiconar um novo Cliente", ExecutionStatus.FAILED, getMsgErro(), bugId);
				
				TestCase.fail(getMsgErro());
			} else {
				
				// Reporta o resultado OK para o Testlink
				TestlinkMetodos.executarCasoDeTeste(TESTLINK_PLANO, TESTLINK_PROJETO, "Suite CRUD", "Adiconar um novo Cliente", ExecutionStatus.PASSED, null, null);
			}
		}
	}
		

	@After
	public void tearDown() throws Exception {
		selenium.stop();
		server.stop();
	}
	
	/**
	 * Metodo para coletar dados sempre que um erro ocorrer
	 * @param e Exception ocorrida
	 */
	private void reportError(Throwable e) {
		setErro(true);
		setMsgErro(e.getMessage());
		setEvidenciaErro(selenium.captureScreenshotToString());
	}
	
}
