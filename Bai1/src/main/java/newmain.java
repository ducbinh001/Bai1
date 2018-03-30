import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.glassfish.json.JsonUtil;

public class newmain {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Thread thread=new Thread(new MyThead());
		thread.start();
	}

}
