package com.eliasnogueira.seleniummantis.utils;

import java.net.MalformedURLException;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.mantisbt.connect.IMCSession;
import org.mantisbt.connect.MCException;
import org.mantisbt.connect.model.IProject;
import org.mantisbt.connect.model.Issue;
import org.mantisbt.connect.model.MCAttribute;

/**
 * Reporta um bug para o Mantis
 * @author Elias Nogueira <elias.nogueira@gmail.com>
 *
 */
public class MantisMetodo {

	/**
	 * Reporta um novo bug
	 * @param sumario Sumario do bug
	 * @param descricao Descrição do Bug
	 * @param categoria Categoria do Bug
	 * @param informacaoAdicional qualquer informação adicional que vai no campo 'Additional Information' no Mantis
	 * @param evidencia dados do arquivo em uma String de Base64 (bytes do arquivo)
	 * @param nomeArquivo nome do arquivo que será visualizado no anexo
	 */
	public static String reporIssue(String projeto, String sumario, String descricao, String categoria, String informacaoAdicional, String evidencia, String nomeArquivo) {
		IMCSession sessao = null;
		String arquivo = nomeArquivo + ".png";
		String bugID = null;
		
		try {
			// efetua a conexao com o Mantis atraves do Singleton
			sessao = MantisUtil.getSessao();
			
			// objeto que representa um projeto no Mantis
			IProject projetoMantis = sessao.getProject(projeto);
			
			// objeto que representa uma issue (bug) no Mantis
            Issue issue = new Issue();
            
            issue.setProject(new MCAttribute(projetoMantis.getId(), projetoMantis.getName()));
            issue.setAdditionalInformation(null);
            issue.setOs(System.getProperty("os.name"));
            issue.setOsBuild(System.getProperty("os.version"));
            issue.setPlatform(System.getProperty("os.arch"));
            issue.setSeverity(new MCAttribute(70, "crash"));
            issue.setReproducibility(new MCAttribute(10, "always"));
            
            // abaixo o sumario sera apresentado com a data. Remova o sumario em execução fora de testes
            issue.setSummary(sumario + new Date());
            issue.setDescription(descricao);
            issue.setCategory(categoria);
            issue.setPriority(new MCAttribute(40, "high"));
            issue.setAdditionalInformation(informacaoAdicional);
            
            // submete o bug no Mantis
            long id = sessao.addIssue(issue);     
            sessao.addIssueAttachment(id, arquivo, "image/png", Base64.decodeBase64(evidencia));
            
            // retorna o ID do bug como String, para fazer o relacionanmento do bug com o Testlink
            bugID = String.valueOf(id);
            
		} catch (MalformedURLException e) {
			System.err.println("Erro na URL de acesso ao Mantis");
			e.printStackTrace();
		} catch (MCException e) {
			System.err.println("Erro na comunicacao com o Mantis");
			e.printStackTrace();
		}
		
        return bugID;
	}
}
