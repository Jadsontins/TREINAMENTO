package com.eliasnogueira.integracaoselenium.utils;

import java.net.MalformedURLException;
import java.util.Map;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.TestLinkAPIException;
import br.eti.kinoshita.testlinkjavaapi.model.Build;
import br.eti.kinoshita.testlinkjavaapi.model.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.model.ReportTCResultResponse;
import br.eti.kinoshita.testlinkjavaapi.model.TestPlan;

/**
 * Classe para centralizar toda a reescrita dos metodos do Testlink
 * @author Elias Nogueira <elias.nogueira@gmail.com>
 *
 */
public class TestlinkMetodos {

	/**
	 * Envia resultado de execucao para o Testlink
	 * @param planoTeste nome do Plano de Teste no Testlink
	 * @param projeto nome do Projeto de Teste no Testlink
	 * @param suite nome da Suite de Teste no Testlink
	 * @param casoDeTeste nome do Caso de Teste no Testlink
	 * @param status status de execucao do teste no Testlink
	 * @param nota nota no Testlink
	 * @return resultado de execucao do Testlink
	 * @throws MalformedURLException erro de criacao de uma URL
	 * @throws TestLinkAPIException  erro na API do Testlink
	 * @see br.eti.kinoshita.testlinkjavaapi.model.ExecutionStatus
	 */
	public static ReportTCResultResponse executarCasoDeTeste(String planoTeste, String projeto, String suite, String casoDeTeste, ExecutionStatus status, String nota, String bugId) throws MalformedURLException, TestLinkAPIException {
		
		/**
		 * Pega a instancia da API do Testlink
		 */
		TestLinkAPI testlink = TestlinkUtil.getTestlinkAPI();
		
		/**
		 * Pega o Plano de Teste no Testlink
		 */
		TestPlan testlinkPlan = testlink.getTestPlanByName(planoTeste, projeto);
		
		/**
		 * Pega uma Build no Testlink
		 */
		Build build = testlink.getLatestBuildForTestPlan(testlinkPlan.getId());
		
		Integer testCaseId = testlink.getTestCaseIDByName(casoDeTeste, suite, projeto, null);	// recebe o ID do Caso de Teste
		Integer testCaseExternalId = 0;															// seta como 0 o controle de caso de teste externo
		Integer testPlanId = testlinkPlan.getId();												// recebe o ID do Plano de Teste
		Integer buildId = build.getId();														// recebe o ID da Build
		String buildName = build.getName();														// recebe o Nome da Build
		String notes = nota;																	// recebe a Nota passada por parametro
		Boolean guess = false;																	// setando falso para usuario convidado
		Integer platformId = 0;																	// setando 0 (sem) para a Plataforma no Testlink
		String platformName = null;																// setando null (sem) para Nome da Plataforma no Testlink
		Map<String, String> customFields = null;												// setando null (sem) para os Campos Personalizados
		Boolean overwrite = false;																// setando false para nao seobrescrever (atualizar) a execucao
		
		// efetuando o envio da execucao para o Testlink
		return testlink.setTestCaseExecutionResult(testCaseId, testCaseExternalId, testPlanId, status, buildId, buildName, notes, guess, bugId, platformId, platformName, customFields, overwrite);
	
	}
	
}
