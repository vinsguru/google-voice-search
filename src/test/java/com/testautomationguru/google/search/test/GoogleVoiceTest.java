package com.testautomationguru.google.search.test;

import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.testng.Arquillian;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.testautomationguru.google.search.Google;
import com.testautomationguru.util.SpeakUtil;



public class GoogleVoiceTest extends Arquillian{
	
	@Page
	Google google;
	
    @BeforeClass
    public void setup(){
    	SpeakUtil.allocate();
    }
		    
    @Test(dataProvider = "voiceSearch")
    public void googleVoiceSearchTest(String searchText) throws InterruptedException {
    	
    	google.goTo();
    	
    	google.getSearchWidget().startListening();
    	SpeakUtil.speak(searchText);
    	google.getSearchWidget().stopListening();
    	
    	Assert.assertEquals(searchText, google.getSearchWidget().getVoiceSearchText().toLowerCase());
    }
    
    @DataProvider(name = "voiceSearch")
    public static Object[][] voiceSearchTestData() {
       return new Object[][] {
    		   {"weather today"}, 
    		   {"show me the direction for atlanta from new york"},
    		   {"magnificent seven showtimes"},
    		   {"will it rain tomorrow"}, 
    		   {"arquillian graphene"}
       };
    }
    
    @AfterClass
    public void deallocate(){
    	SpeakUtil.deallocate();
    }
}
