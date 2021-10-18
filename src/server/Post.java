package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Post implements Closeable {
	private static String host_url = "http://34.64.165.105:8080";
	private HttpURLConnection http;
	
	public Post(String path, JSONObject data) throws MalformedURLException, IOException {
		http = (HttpURLConnection) new URL(host_url + path).openConnection();
		
		http.setRequestMethod("POST");
		http.setRequestProperty("Content-Type", "application/json");
		
		http.setDoOutput(true);
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(http.getOutputStream()));
		
		writer.write(data.toString());
		writer.flush();
		writer.close();
	}
	
	public int getResponsesCode() throws IOException {
		return http.getResponseCode();
	}
	
	public void getResponsesBody() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream()));
		
		String line;
		while((line = reader.readLine()) != null) {
			System.out.println(line);
		}
	}

	@Override
	public void close() throws IOException {
		http.disconnect();
	}
}
