package sk.zatko.recipe_search.backend.ejb.rest;

import java.io.IOException;
import java.net.HttpURLConnection;

import javax.ejb.Local;

@Local
public interface RestCallService {

	/**
	 * Open HTTP connection for REST request
	 */
	public void openConnection(String uri, String verb) throws IOException;
	
	/**
	 * Call REST service via open HTTP connection
	 */
	public HttpURLConnection request(String body) throws IOException;
	
	/**
	 * Close open HTTP connection
	 */
	public void closeConnection();
	
	/**
	 * Get response of last REST request
	 */
	public String getLastResponse();
	
	/**
	 * Get response code of last REST request
	 */
	public int getLastResponseCode();
}
