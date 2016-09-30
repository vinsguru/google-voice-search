package com.testautomationguru.util;

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