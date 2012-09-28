package com.eliasnogueira.selenium2pageobject.test;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * Executa todas as acoes na pagina IndexPage
 */
public class IndexPage {

	/**
	 * Instancia privada do webDriver que vira da suite de teste
	 */
	private final WebDriver driver;
	
	/**
	 * Construtor que ira adicionar a instancia do WebDriver para utilizacao dos metodos
	 */
	public IndexPage(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Metodo para adicionar um cliente. Note que estamos colocando como parametros
	 * todos os dados necessarios para a criacao do cliente
	 */
	public IndexPage adicionaCliente(final String nome, String sobrenome, String pagamento, String observacao) {
		driver.get("http://eliasnogueira.com/selenium/exercicios/selenium_ide/ajybuyje/avancado/datadriven/examples/example.php");
		driver.findElement(By.cssSelector("input.btn.editingSize")).click();
		driver.findElement(By.cssSelector("table[name=form] > tbody > tr > td > input[name=fldField1]")).sendKeys(nome);
		driver.findElement(By.name("fldField2")).sendKeys(sobrenome);
		new Select(driver.findElement(By.name("fldCertainFields"))).selectByValue(pagamento);
		driver.findElement(By.name("fldLongField")).sendKeys(observacao);
		driver.findElement(By.xpath("//input[@value='Salvar Item']")).submit();
		
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            @Override
			public Boolean apply(WebDriver d) {
            	return driver.findElement(By.tagName("body")).getText().contains(nome);
            }
        });
		return new IndexPage(driver);
	}
	
	/**
	 * Metodo para remover o cliente
	 */
	public IndexPage removeCliente() {
		try {
			
		driver.get("http://eliasnogueira.com/selenium/exercicios/selenium_ide/ajybuyje/avancado/datadriven/examples/example.php");
		driver.findElement(By.xpath("//input[@value='delete']")).click();
		
		Alert alert = driver.switchTo().alert();
		String expected = alert.getText();
		
		Assert.assertEquals("Tem certeza que você deseja remover este item?", expected);
		alert.accept();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new IndexPage(driver);
	}
	
	/**
	 * Metodo para verificar se o usuario criado esta presente na pagina
	 * Por isso passamos o parametro 'nome' para o metodo
	 * Ele retorna 'true' se encontrar e ou 'false' se nao encontrar
	 */
	public boolean usuarioCriadoComSucesso(String nome) {
		return driver.findElement(By.tagName("body")).getText().contains(nome);
	}
	
	/**
	 * Metodo para verificar se o usuario foi removido da pagina
	 * No retorno estamos efetuando uma negacao "!" onde o metodo de busca pelo nome
	 * vai retornar false, mas negando fica true
	 */	
	public boolean usuarioRemovidoComSucesso(String nome) {
		return !driver.findElement(By.tagName("body")).getText().contains(nome);
	}
}
