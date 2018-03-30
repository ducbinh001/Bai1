import java.awt.List;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonWriter;

public class MyThead implements Runnable {
	
	URL url;
	String urlString="http://news.admicro.vn:10002/api/realtime?domain=kenh14.vn";
	String result;
	int countLevel=0;
	int userOldTime=0;
	JsonObjectBuilder jsonObjectBuilder;
	JsonReader jsonReader;
	FileOutputStream fos;
	java.util.logging.Logger log;
	LevelLogger debug;
	LevelLogger noname;
	
	
	public MyThead() {
		jsonObjectBuilder=Json.createObjectBuilder();
		log=Logger.getLogger("Bai 1");
		debug=new LevelLogger("Debug", Level.SEVERE.intValue()-1);
		noname=new LevelLogger("No", debug.intValue()-1);
		try {
			log.addHandler(new FileHandler("log.txt"));
			url=new URL(urlString);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
		try {
			System.out.println(userOldTime);
			jsonReader=Json.createReader(url.openConnection().getInputStream());
			JsonObject jsonObject=jsonReader.readObject();
			System.out.println(jsonObject);
			if(jsonObject.getInt("user")>(userOldTime*3/100) && countLevel!=6) {
				log.log(noname, ""+jsonObject.getInt("user"));
				countLevel++;
			}else if(jsonObject.getInt("user")>(userOldTime*3/100) && countLevel==6) {
				log.log(debug, ""+jsonObject.getInt("user"));
				countLevel=0;
			}else {
				log.log(java.util.logging.Level.INFO, ""+jsonObject.getInt("user"));
				countLevel++;
			}
			userOldTime=jsonObject.getInt("user");
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(jsonReader!=null) {
				jsonReader.close();
			}
		}
		}
		
		
		
		
	}
	
	

}
