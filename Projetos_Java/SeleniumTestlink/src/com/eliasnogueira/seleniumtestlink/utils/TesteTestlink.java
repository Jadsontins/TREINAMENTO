package com.eliasnogueira.seleniumtestlink.utils;

import java.net.MalformedURLException;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.TestLinkAPIException;
import br.eti.kinoshita.testlinkjavaapi.model.TestCase;

public class TesteTestlink {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			TestLinkAPI testlink = TestlinkUtil.getTestlinkAPI();
			
			TestCase[] testcases = testlink.getTestCasesForTestSuite(3, true, null);
			
			for (int i = 0; i < testcases.length; i++) {
				System.out.println(testcases[0]);
				
			}
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TestLinkAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
