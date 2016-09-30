package com.testautomationguru.google.search;


import org.openqa.selenium.By;
import org.jboss.arquillian.graphene.Graphene;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleSearchWidget {
	
	@FindBy(id="gsri_ok0")
	private WebElement microphone;
	
	@FindBy(name="q")
	private WebElement searchBox;
	
	@FindBy(name="btnG")
	private WebElement searchButton;
	
	public void searchFor(String searchString){
		searchBox.clear();
		
		//Google makes ajax calls during search
		int length = searchString.length();
		searchBox.sendKeys(searchString.substring(0, length-1));
		Graphene.guardAjax(searchBox).sendKeys(searchString.substring(length-1));
	}
	
	public void search(){
		Graphene.guardAjax(searchButton).click();
	}
	
	public void startListening(){
		
		//wait for microphone
		Graphene.waitGui()
				.until()
				.element(this.microphone)
				.is()
				.present();
		
		microphone.click();
		
		//wait for big microphone image to appear
		//this is when google starts listening
		Graphene.waitGui()
				.until()
				.element(By.id("spchb"))
				.is()
				.present();
	}
	
	public void stopListening(){
		
		//wait for the microphone image to hide
		//at this point google will stop listening & start its search
		Graphene.waitGui()
				.until()
				.element(By.id("spchb"))
				.is().not()
				.visible();
	}
	
	public String getVoiceSearchText(){
		
		Graphene.waitGui()
				.until()
				.element(this.searchBox)
				.is()
				.visible();
		
		return this.searchBox.getAttribute("value");
	}
}
