package sk.zatko.recipe_search.backend.elasticsearch.seed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;

import sk.zatko.recipe_search.backend.properties.Property;

public class ElasticSearchConnector {

	private static final Logger logger = Logger.getLogger(ElasticSearchConnector.class);
	
	private String elasticNodeUri = "http://localhost:9200/";
	
	protected static String lastResponse;
	
	public ElasticSearchConnector() {
		
		try {
			this.elasticNodeUri = Property.getProperty("ELASTIC_NODE_URI");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public CloseableHttpResponse getRequest(String uri) {
		
		logger.info("GET " + uri);
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(this.elasticNodeUri + uri);

	    setHeader(httpGet);
	    
	    CloseableHttpResponse response = null;
	    
		try {
			response = httpClient.execute(httpGet);
			lastResponse = getResponseBody(response);

		} catch (IOException e) {
			logger.error("Cannot execute request " + httpGet.toString(), e);
		} finally {
			closeQuietly(httpClient);
		}

		return response;
	}
	
	
	public CloseableHttpResponse postRequest(String uri, String requestBody) {
		
		logger.info("POST " + uri);
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(this.elasticNodeUri + uri);

	    setHeader(httpPost);
	    
	    try {
			httpPost.setEntity(new StringEntity(requestBody));
			
		} catch (UnsupportedEncodingException e) {
			logger.error("Cannot set body of request " + httpPost.toString(), e);
		}
	    
	    CloseableHttpResponse response = null;
	    
		try {
			response = httpClient.execute(httpPost);
			lastResponse = getResponseBody(response);
			
		} catch (IOException e) {
			logger.error("Cannot execute request " + httpPost.toString(), e);
		} finally {
			closeQuietly(httpClient);
		}

		return response;
	}
	
	
	public CloseableHttpResponse putRequest(String uri, String requestBody) {
		
		logger.info("PUT " + uri);
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPut httpPut = new HttpPut(this.elasticNodeUri + uri);

	    setHeader(httpPut);
	    
	    try {
			httpPut.setEntity(new StringEntity(requestBody));
			
		} catch (UnsupportedEncodingException e) {
			logger.error("Cannot set body of request " + httpPut.toString(), e);
		}
	    
	    CloseableHttpResponse response = null;
	    
		try {
			response = httpClient.execute(httpPut);
			lastResponse = getResponseBody(response);
			
		} catch (IOException e) {
			logger.error("Cannot execute request " + httpPut.toString(), e);
		} finally {
			closeQuietly(httpClient);
		}

		return response;
	}
	
	
	public CloseableHttpResponse deleteRequest(String uri) {
		
		logger.info("DELETE " + uri);
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpDelete httpDelete = new HttpDelete(this.elasticNodeUri + uri);

	    setHeader(httpDelete);
	    
	    CloseableHttpResponse response = null;
	    
		try {
			response = httpClient.execute(httpDelete);
			lastResponse = getResponseBody(response);
			
		} catch (IOException e) {
			logger.error("Cannot execute request " + httpDelete.toString(), e);
		} finally {
			closeQuietly(httpClient);
		}

		return response;
	}
	

	protected String getResponseBody(CloseableHttpResponse response) {
		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			
		} catch (UnsupportedOperationException | IOException e) {
			e.printStackTrace();
		}

		StringBuffer result = new StringBuffer();
		String line = "";
		try {
			while ((line = reader.readLine()) != null) {
				result.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result.toString();
	}
	
	private void setHeader(HttpRequestBase request) {
		
		request.setHeader("Accept", "application/json");
		request.setHeader("Content-type", "application/json");
	}
	
	
	protected void closeQuietly(CloseableHttpClient httpClient) {
		
		try {
			httpClient.close();
		} catch (IOException e) {
			logger.error("Cannot clode http client", e);
		}
	}
	
	protected String readFile(String filePath) throws IOException {
		
		byte[] encoded = Files.readAllBytes(Paths.get(filePath));
		return new String(encoded, Charset.defaultCharset());
	}
	
	public String convertToUTF8(String s) throws UnsupportedEncodingException {

		return new String(s.getBytes("UTF-8"), "ISO-8859-1");
    }
}