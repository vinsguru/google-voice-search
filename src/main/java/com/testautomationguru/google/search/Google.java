package com.testautomationguru.google.search;


import org.jboss.arquillian.drone.api.annotation.Drone;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class Google {
	
	@Drone
	private WebDriver driver;
	
	@FindBy(id="searchform")
	private GoogleSearchWidget searchWidget;
	
	@FindBy(id="rso")
	private GoogleSearchResults results;

	@FindBy(id="hdtb-msb")
	private GoogleSearchNavigation navigation;
	
	@FindBy(css="div.sbsb_a")
	private GoogleSearchSuggestions suggestions;
	
	
	public void goTo(){
		driver.get("https://www.google.com");
	}
	
	public boolean isAt(){
		return driver.getTitle().equals("Google");
	}
	
	public GoogleSearchWidget getSearchWidget(){
		return searchWidget;
	}
	
	public GoogleSearchResults getSearchResults(){
		return results;
	}
	
	public GoogleSearchNavigation getTopNavigation(){
		return navigation;
	}
	
	public GoogleSearchSuggestions getSuggestions(){
		return suggestions;
	}

}
