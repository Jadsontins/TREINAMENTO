package com.eliasnogueira.seleniumselectdriver.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.opera.core.systems.OperaDriver;

/**
 * Classe Utils para o Seleium
 * Ela esta implementando a classe 'SeleniumInterface' para utilizar o nome dos browsers
 * @author Elias Nogueira <elias.nogueira@gmail.com>
 *
 */
public class SeleniumUtils implements SeleniumInterface {

	private static WebDriver driver = null;
	
	public static WebDriver getDriver(String browser) {
		
		if (driver == null) {
			
			// se o browser for firefox instancia o driver do Firefox
			if (browser.equals(FIREFOX)) driver = new FirefoxDriver();

			// se o browser for Google Chrome seta algumas configuracoes para execucao
			if (browser.equals(GOOGLECHROME)) {
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				
				capabilities.setJavascriptEnabled(true);
				capabilities.setCapability("chrome.binary", "C:\\Users\\Elias\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe");
				System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe"); 
				
				driver = new ChromeDriver(capabilities);
			}
			
			// se o browser for Internet Explorer configura a nao exibicao de warnings do IE
			if (browser.equals(IE)) {
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				driver = new InternetExplorerDriver(capabilities);
			}
			
			// se o browser for Opera instancia o driver do Opera
			if (browser.equals(OPERA)) driver = new OperaDriver();
			
		}
		return driver;
	}
	
	
	/**
	 * Metodo para pegar o valor de alguma propriedade no arquivo de configuracao do Selenium
	 * O caminho e o nome do arquivo pode ser trocados
	 */
	public static String getSeleniumProperties(String name) {
		Properties properties = new Properties();
		String value = null;
		
		try {
		    properties.load(new FileInputStream("properties/selenium.properties"));	// se necessitar altere o caminho e/ou o nome do arquivo
		    value = properties.getProperty(name);
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return value;
	}
	
}
