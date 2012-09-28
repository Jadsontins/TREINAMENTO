package com.eliasnogueira.selenium2testng.datadriven;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


/**
 * Adiciona um Cliente
 * @author Elias Nogueira <elias.nogueira@gmail.com>
 *
 */
public class ClienteTest {

	private WebDriver driver;
	
	@BeforeTest
	public void setup() {
		driver = new InternetExplorerDriver();
	}
	
	@DataProvider(name = "massaDados")
	public Object[][] createData1() {
		return new Object[][] {
				{ "Elias", "Nogueira", "PagSeguro", "Envio por enc. normal" },
				{ "Joao", "dos Santos", "PayPal", "Envio Sedex 10" },
		};
	}
	
	@Test(description="Adiciona e remove cliente", dataProvider = "massaDados", groups = "sanidade")
	public void adicionaRemoveCliente(String nome, String sobrenome, String pagamento, String observacao) {
		adicionaCliente(nome, sobrenome, pagamento, observacao);
		removeCliente();
	}
	
	public void adicionaCliente(String nome, String sobrenome, String pagamento, String observacao) {
		
		// Espera implicita no Selenium para todos os elementos
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("http://eliasnogueira.com/selenium/exercicios/selenium_ide/ajybuyje/avancado/datadriven/examples/example.php");							// abre a pagina
		driver.findElement(By.cssSelector("input.btn.editingSize")).click();													// clica no botao Adicionar Item
		driver.findElement(By.cssSelector("table[name=form] > tbody > tr > td > input[name=fldField1]")).sendKeys(nome);		// digita dados no campo Nome
		driver.findElement(By.name("fldField2")).sendKeys(sobrenome);															// digita dados no campo Sobrenome
		new Select(driver.findElement(By.name("fldCertainFields"))).selectByValue(pagamento);									// seleciona dado da combo
		driver.findElement(By.name("fldLongField")).sendKeys(observacao);														// digita dado em Observacao
		driver.findElement(By.xpath("//input[@value='Salvar Item']")).submit();													// submete os dados

		Assert.assertEquals(driver.findElement(By.xpath("//span")).getText(), nome);
		
	}

	public void removeCliente() {
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
	}

	@AfterTest
	public void tearDown() {
		driver.close();	// fecha o browser
	}
}
