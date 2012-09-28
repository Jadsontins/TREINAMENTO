package com.eliasnogueira.selenium2junit.datadriven;


import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Adiciona um Cliente
 * @author Elias Nogueira <elias.nogueira@gmail.com>
 * 
 * Quando queremos fazer com que o script execute automaticamente os dados via Data Driven teremos que usar no jUnit uma anotacao
 * chamada de 'RunWith' passando a classe de parametrizacao de dados. Assim o jUnit sabe que deve executados os dados parametrizados
 *  
 */
@RunWith(Parameterized.class)
public class AdicionarCliente {
	
	private WebDriver driver;
	
	/**
	 * E necessario criar as variaveis para que os dados da massa de dados sejam inseridos
	 * automaticamente na execucao do teste
	 */
	private String nome;
	private String sobrenome;
	private String pagamento;
	private String observacao;
	
	/**
	 * O construtor e necessario para passar todos os dados do metodo com os dados
	 * para a utilizacao do teste
	 */
	public AdicionarCliente(String nome, String sobrenome, String pagamento, String observacao) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.pagamento = pagamento;
		this.observacao = observacao;
	}
	
	@Before
	public void setup() {
		driver = new InternetExplorerDriver();
	}
	
	@Test
	public void adicionaCliente() {
		/**
		 * Instancia para executar o Selenium no Firefox
		 * Basta trocar o FirefoxDriver por outro "BrowserDriver" para executar em outro browser.
		 * Ex: InternetExplorerDriver();
		 */
		driver = new InternetExplorerDriver();
		
		// Espera implicita no Selenium para todos os elementos
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("http://eliasnogueira.com/selenium/exercicios/selenium_ide/ajybuyje/avancado/datadriven/examples/example.php");							// abre a pagina
		driver.findElement(By.cssSelector("input.btn.editingSize")).click();													// clica no botao Adicionar Item
		driver.findElement(By.cssSelector("table[name=form] > tbody > tr > td > input[name=fldField1]")).sendKeys(nome);		// dado inserido automaticamente
		driver.findElement(By.name("fldField2")).sendKeys(sobrenome);															// dado inserido automaticamente
		new Select(driver.findElement(By.name("fldCertainFields"))).selectByValue(pagamento);									// dado inserido automaticamente
		driver.findElement(By.name("fldLongField")).sendKeys(observacao);														// dado inserido automaticamente
		driver.findElement(By.xpath("//input[@value='Salvar Item']")).submit();													// submete os dados
		
		/**
		 * Espera explicita do Selenium por um texto na pagina.
		 * Esta espera substitui o comando 'isTextPresent()' no Selenium 1
		 */
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            @Override
			public Boolean apply(WebDriver d) {
            	return driver.findElement(By.tagName("body")).getText().contains(nome);											// verificacao do nome precisou ser parametrizada para nao dar erro
            }
        });


	}
	
	/**
	 * Este metodo sempre sera necessario. Primeiro devemos ter a anotacao @Parameters para dizer ao metodos
	 * que ele que passara os parametros para o teste.
	 * Depois e necessario colocar os dados na sequencia de uso das variaveis que criamos para o script
	 */
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
        			{ "Elias", "Nogueira", "PayPal", "Envio por Sedex 10"}, 
        			{ "Fulano", "da Silva", "PagSeguro", "Envio por encom. normal"},
        			{ "Deltrano", "Santos", "Cartao de Credito", "Envio por Sedex"}
                });
    }

    @After
    public void tearDown() {
		driver.close();	// fecha o browser
    }
    
}
