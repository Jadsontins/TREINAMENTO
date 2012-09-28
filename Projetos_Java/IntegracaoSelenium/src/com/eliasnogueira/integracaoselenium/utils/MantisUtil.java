package com.eliasnogueira.integracaoselenium.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.mantisbt.connect.IMCSession;
import org.mantisbt.connect.MCException;
import org.mantisbt.connect.axis.MCSession;

/**
 * Classe Singleton para criar a conexao com o Mantis
 * @author Elias Nogueira <elias.nogueira@gmail.com>
 */
public class MantisUtil {

    private static MantisUtil instance = null;
    private static IMCSession sessao = null;
    private String urlMantis = "http://localhost/mantisbt-1.2.6/api/soap/mantisconnect.php";
    private String usuario = "Administrator";
    private String senha = "root";
    
    /**
     * Cria a instancia de conexao com o Mantis
     * @throws MalformedURLException se a URL da API do Mantis está errada
     * @throws MCException se ocorrer algum erro na conexao com o Mantis
     */
    public MantisUtil() throws MalformedURLException, MCException {
        URL url = new URL(urlMantis);
        sessao = new MCSession(url, usuario, senha);
    }

    /**
     * Retorna uma nova instancia ou a instancia existente da conexao com o Mantis
     * @return instancia de conexao do Mantis
     */
    public static MantisUtil getInstance() {
        if (instance == null) {
            try {
                instance = new MantisUtil();
            } catch (MalformedURLException ex) {
                Logger.getLogger(MantisUtil.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MCException ex) {
                Logger.getLogger(MantisUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return instance;
    }

    /**
     * Pega a instancia para a conexao com o Mantis. Se nao existe, cria uma nova instancia
     * @return sessao do Mantis
     * @throws MalformedURLException se a URL da API do Mantis esta errada
     * @throws MCException se ocorrer algum erro na conexao com o Mantis
     */
    public static IMCSession getSessao() throws MalformedURLException, MCException {
        if (sessao == null) {
            getInstance();
        }
        return sessao;
    }	
}
