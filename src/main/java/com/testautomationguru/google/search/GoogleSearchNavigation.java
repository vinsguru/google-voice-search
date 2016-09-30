package com.testautomationguru.google.search;


import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.findby.FindByJQuery;
import org.openqa.selenium.WebElement;

public class GoogleSearchNavigation{
	
	@FindByJQuery("div.hdtb-mitem:contains('All')")
	private WebElement all;
	
	@FindByJQuery("div.hdtb-mitem:contains('Videos')")
	private WebElement videos;
	
	@FindByJQuery("div.hdtb-mitem:contains('Images')")
	private WebElement images;
	
	@FindByJQuery("div.hdtb-mitem:contains('Shopping')")
	private WebElement shopping;

	public void goToVideos(){
		System.out.println("Clicking on Videos");
		Graphene.guardAjax(videos).click();
	}
	
	public void goToImages(){
		System.out.println("Clicking on Images");
		Graphene.guardHttp(images).click();
	}
	
	public void goToShopping(){
		System.out.println("Clicking on Shopping");
		Graphene.guardAjax(shopping).click();
	}
		
	public void goToAll(){
		System.out.println("Clicking on All");
		Graphene.guardHttp(all).click();
	}	
}
