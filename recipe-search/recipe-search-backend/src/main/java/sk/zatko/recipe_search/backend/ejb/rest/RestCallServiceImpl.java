package sk.zatko.recipe_search.backend.ejb.rest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Stateful;

import org.apache.log4j.Logger;

@Stateful
public class RestCallServiceImpl implements RestCallService {

	private static final Logger logger = Logger.getLogger(RestCallServiceImpl.class);
	
	private HttpURLConnection connection;
	
	private String lastResponse;
	private int lastResponseCode;
	
	
	@Override
	@PostActivate
	public void openConnection(String uri, String verb) throws IOException {
	
		URL url = new URL(uri);
		
		this.connection = (HttpURLConnection) url.openConnection();
		this.connection.setRequestMethod(verb);
		
		this.connection.setRequestProperty("Accept", "application/json");
		this.connection.setRequestProperty("Content-type", "application/json");
		
	}

	@Override
	public HttpURLConnection request(String body) throws IOException {
		
		this.connection.setUseCaches(false);
		this.connection.setDoOutput(true);
		
		DataOutputStream wr = new DataOutputStream(this.connection.getOutputStream());

		logger.info(this.connection.getRequestMethod() + " " + this.connection.getURL().toString());
		logger.info(body);
		
		wr.writeBytes(body);
		wr.close();
		
		try {
			this.lastResponseCode = this.connection.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.lastResponse = getResponseBody(this.connection);

		logger.info("Return " + this.lastResponseCode);
		
		return this.connection;
	}

	@Override
	@PrePassivate
	@PreDestroy
	public void closeConnection() {
		this.connection.disconnect();
	}
	
	@Override
	public String getLastResponse() {
		return this.lastResponse;
	}

	@Override
	public int getLastResponseCode() {
		return this.lastResponseCode;
	}

	private String getResponseBody(HttpURLConnection  connection) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
		
		String responseMsg = "";
		
		String output;
		try {
			while ((output = br.readLine()) != null) {
				responseMsg += output;
			}
		} catch (IOException e) {
			throw new IOException(e);
		} finally {
			close(br);
		}
		
		return responseMsg;
	}
	
	private void close(BufferedReader br) {
		
		try {
			br.close();
		} catch (IOException e) {
			logger.error(e);
		}
	}
	
}
