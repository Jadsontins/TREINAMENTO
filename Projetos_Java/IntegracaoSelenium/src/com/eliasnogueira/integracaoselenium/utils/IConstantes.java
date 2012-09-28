package com.eliasnogueira.integracaoselenium.utils;

/**
 * Constantes para a comunicação com o Mantis e Testlink
 * @author Elias Nogueira <elias.nogueira@gmail.com>
 */
public interface IConstantes {

	// ****************
	// DADOS DO MANTIS
	// ****************
	
	/**
	 * Local da API de conexao com o Mantis
	 */
	static final String MANTIS_URL = "http://www.eliasnogueira.com/arquivos_blog/mantisbt-1.2.3/api/soap/mantisconnect.php";

	/**
	 * Nome do Usuario para conexão
	 */
	static final String MANTIS_USER = "Administrator";

	/**
	 * Senha do Usuario para conexao
	 */
	static final String MANTIS_PWD = "root";

	/**
	 * Nome do projeto para reportar o defeito
	 */
	static final String MANTIS_PROJETO = "Projeto Teste";

	
	// ******************
	// DADOS DO TESTLINK
	// ******************
	
	/**
	 * Chave para conexão com o Testlink 
	 */
	final String TESTLINK_DEVKEY = "6124518907fec74a176763d573fcf401";
	
	/**
	 * Local da API XML-RPC do Testlink
	 */
	final String TESTLINK_URL = "http://localhost/testlink-1.9.3/lib/api/xmlrpc.php";
	
	/**
	 * Projeto no Testlink
	 */
	final String TESTLINK_PROJETO = "Projeto Teste";
	
	/**
	 * Plano de Teste do Testlink
	 */
	final String TESTLINK_PLANO = "Plano AjaxCRUD";
	
	/**
	 * Build do Testlink
	 */
	final String TESTLINK_BUILD = "Ciclo 1";

}
