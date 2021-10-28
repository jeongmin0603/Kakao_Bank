package model;

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
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Server implements Closeable {
	private final static String HOST_URL = "http://34.64.165.105:8080";
	private HttpURLConnection http;

	public Server(String type, String path, JSONObject data) throws MalformedURLException, IOException {
		http = (HttpURLConnection) new URL(HOST_URL + path).openConnection();

		http.setRequestMethod(type);
		http.setRequestProperty("Content-Type", "application/json");
		http.setRequestProperty("Authorization", "Bearer " + User.getToken());

		http.setDoOutput(true);

		if (data != null) {
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(http.getOutputStream()));

			writer.write(data.toString());
			writer.flush();
			writer.close();
		}
	}

	public int getResponsesCode() throws IOException {
		return http.getResponseCode();
	}

	public JSONObject getResponsesBody() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream()));

		try {
			JSONObject obj = (JSONObject) new JSONParser().parse(reader);

			return obj;
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void close() throws IOException {
		http.disconnect();
	}
}
