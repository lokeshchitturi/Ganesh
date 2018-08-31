package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

import javax.swing.plaf.synth.SynthSpinnerUI;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONUtils {
	
	public static JSONObject getJsonObject(String registrationType) throws FileNotFoundException, IOException, ParseException
	{
		JSONParser parser = new JSONParser();
		
		Object obj=parser.parse(new FileReader(System.getProperty("user.dir")+"\\src\\main\\resources\\TestData.json"));
		JSONObject jsonObject=(JSONObject) obj;
		JSONObject td1=(JSONObject) jsonObject.get(registrationType);
		Set<String> s=td1.keySet();
		for (String string : s) {
			System.out.println(td1.get(string)+"------"+string.substring(0, 2));
			
		}
		return td1;
	}
	
	public static JSONArray getJsonArray(String registrationType) throws FileNotFoundException, IOException, ParseException
	{
		JSONParser parser = new JSONParser();
		
		Object obj=parser.parse(new FileReader(System.getProperty("user.dir")+"\\src\\main\\resources\\TestData.json"));
		JSONObject jsonObject=(JSONObject) obj;
		JSONArray td1=(JSONArray) jsonObject.get("corporate_radios1");
		return td1;
	}
	
public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
	
	
}
}
