
#Advanced Page Objects Pattern
Aim of this project is to show the Page Fragment concepts to create advanced page object pattern using Arquillian Graphene framework &
its Fluent Waiting API.

More details are [here](http://www.testautomationguru.com/arquillian-graphene-page-fragments/).

#Voice Search
This project also includes simple automation scripts to automate google voice search. We need a talking library to google voice search.
Please download and refercence the jar lib files from [here](http://freetts.sourceforge.net/docs/index.php). (It requires you to accept terms & conditions - 
so I have not included thr jars in this project).


#Talking Java
```java
import com.sun.speech.freetts.VoiceManager;
  
public class SpeakUtil {
 
    static com.sun.speech.freetts.Voice systemVoice = null;
     
    public static void allocate(){
        systemVoice = VoiceManager.getInstance().getVoice("kevin16");
        systemVoice.allocate();
    }
     
    public static void speak(String text){
        systemVoice.speak(text);
    }
     
    public static void deallocate(){
        systemVoice.deallocate();
    }
}
```

#Google search widget
```java
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
        //at this point google will stop listening and start its search
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
```

#TestNG Test
```java
public class GoogleVoiceTest extends Arquillian{
     
    @Page
    Google google;
     
    @BeforeClass
    public void setup(){
        SpeakUtil.allocate();
    }
             
    @Test(dataProvider = "voiceSearch")
    public void googleVoiceSearchTest(String searchText){
         
        google.goTo();
         
        //start listening
        google.getSearchWidget().startListening();
         
        //speak the given text
        SpeakUtil.speak(searchText);
         
        //wait for google to stop listening
        google.getSearchWidget().stopListening();
         
        //assert if google has understood correctly
        Assert.assertEquals(searchText,
                            google.getSearchWidget().getVoiceSearchText().toLowerCase());
    }
     
    @DataProvider(name = "voiceSearch")
    public static Object[][] voiceSearchTestData() {
         
       //test data for google voice test
       return new Object[][] {
               {"weather today"},
               {"show me the direction for atlanta"},
               {"magnificent 7 show timings"},
               {"will it rain tomorrow"},
               {"arquillian graphene"}
       };
    }
     
    @AfterClass
    public void deallocate(){
        SpeakUtil.deallocate();
    }
}
```
