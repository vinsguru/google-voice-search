package com.testautomationguru.google.search;


import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleSearchSuggestions {
	
	@FindBy(css="li div.sbqs_c")
	private List<WebElement> suggesions;
	
	public int getCount(){
		return suggesions.size();
	}
	
	public void show(){
		for(WebElement s: suggesions)
			System.out.println(s.getText());
	}

}
