package com.eliasnogueira.seleniumpaginacao;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestePaginacao {

	public static void main(String[] args) {
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.eliasnogueira.com/selenium/exercicios/selenium_ide/ajybuyje/avancado/datadriven/examples/example.php");
		
		// variaveis iniciais
		String nome = "Teste 6";
		int count = 2;
		int paginacao = 1;
		String localizacaoNome = null;
		boolean saida = false;
		
		do {
			localizacaoNome = driver.findElement(By.xpath("//tr[" + count + "]/td/span")).getText();
			
			// se encontrou o nome para remover clica no botao 'remover'
			if (nome.equals(localizacaoNome)) {
				driver.findElement(By.xpath("//tr[" + count + "]/td[5]/input")).click();
				Alert alerta = driver.switchTo().alert();
				
				if (alerta.getText().equals("Tem certeza que voce deseja remover este item?")) alerta.accept();
				
				saida = true;	// seta o final do while
				
			} else {
				count++;
				
				// se ja passou pelos tres elementos na tela clica na proxima paginacao
				if (count > 4) {
					count = 2;
					paginacao++;
					driver.findElement(By.linkText(String.valueOf(paginacao))).click();
				}
			}
			
		} while (!saida);
		
		driver.close();
	}

}
