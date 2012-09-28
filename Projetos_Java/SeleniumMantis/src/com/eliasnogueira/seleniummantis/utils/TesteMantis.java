package com.eliasnogueira.seleniummantis.utils;


public class TesteMantis {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String projeto = "Projeto Teste";
		String sumario = "Teste Report Automatico";
		String descricao = "Este erro foi gerado automaticamente";
		String categoria = "General";
		String informacaoAdicional = "Nenhuma";
		String evidencia = "";
		String nomeArquivo = "";
		
		MantisMetodo.reporIssue(projeto, sumario, descricao, categoria, informacaoAdicional, evidencia, nomeArquivo);

	}

}
