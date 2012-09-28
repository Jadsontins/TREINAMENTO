package com.eliasnogueira.integracaoselenium.utils;

import java.net.MalformedURLException;
import java.net.URL;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.TestLinkAPIException;

/**
 * Classe para efetuar a conexão com o Testlink
 * @author Elias Nogueira <elias.nogueira@gmail.com>
 *
 */
public class TestlinkUtil {
	
	/**
	 * Metodo para retornar uma instancia da API do Testlink, que trara os metodos necessarios
	 * @return instancia da API do Testlink
	 * @throws MalformedURLException erro na criação da URL
	 * @throws TestLinkAPIException erro na criação da instancia da API do Testlink
	 */
	public static TestLinkAPI getTestlinkAPI() throws MalformedURLException, TestLinkAPIException {
		
		/**
		 * URL da API do Testlink
		 * O final e sempre o mesmo "/lib/api/xmlrpc.php"
		 * Voce deve ter o cuidado com o inicio, que pode varias de ambiente para ambiente
		 */
	    String url = "http://localhost/testlink-1.9.3/lib/api/xmlrpc.php";
	    
	    /**
	     * DevKey que e obtida habilitando a automacao no Testlink e gerando a Key pelo  item "Pessoal"
	     */
	    String devKey = "6124518907fec74a176763d573fcf401";
	    
	    /**
	     * Instancia da API do Testlink
	     */
	    TestLinkAPI api = null;
	    
	    try {
	    	new URL(url);						// cria uma nova URL
		    api = new TestLinkAPI(url, devKey);	// cria uma intancia da API do Testlink
		    
		// se ocorrer algum erro    
	    } catch ( Exception e ) {
	    	e.printStackTrace();	// exibe erro no console
	    	System.exit(-1);		// forca a saida da aplicacao
	    }
	    
	    return api;		// retorna, se nao ocorrerem erros, a instancia da API do Testlink
	}

}

