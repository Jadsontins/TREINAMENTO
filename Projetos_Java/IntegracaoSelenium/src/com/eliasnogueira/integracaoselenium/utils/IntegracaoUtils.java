package com.eliasnogueira.integracaoselenium.utils;

public class IntegracaoUtils {
	
	private boolean erro;
	private String msgErro;
	private String evidenciaErro;
	
	
	public String getEvidenciaErro() {
		return evidenciaErro;
	}
	
	public void setEvidenciaErro(String evidenciaErro) {
		this.evidenciaErro = evidenciaErro;
	}
	
	public String getMsgErro() {
		return msgErro;
	}
	
	public void setMsgErro(String msgErro) {
		this.msgErro = msgErro;
	}
	
	public boolean isErro() {
		return erro;
	}
	
	public void setErro(boolean erro) {
		this.erro = erro;
	}
	
}
