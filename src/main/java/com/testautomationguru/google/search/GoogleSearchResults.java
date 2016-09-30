package com.testautomationguru.google.search;


import java.util.List;

import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.findby.FindByJQuery;
import org.openqa.selenium.By;

public class GoogleSearchResults {

	@FindByJQuery(".rc")
	private List<GoogleSearchResult> results;
	
	public int getCount(){
		waits();
		return results.size();
	}
	
	public void show(){
		waits();
		System.out.println("\nResults:\n");
		for(GoogleSearchResult result: results)
			System.out.println(result.getResultHeader());	
	}
	
	private void waits(){
		Graphene.waitGui().until().element(By.className("rc")).is().present();
	}
	
}

