package com.workersdocuments.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import java.util.List;
import java.util.Properties;

public class ClientServiceImpl implements ClientService {
	
	public String invokeGetWorkerDocumentsService(List<String> fileId) {
		String completerequest = null;
		String serviceResponse = null;
		
		Properties prop = new Properties();
		InputStream configFile=null;
		
		try {
			configFile = new FileInputStream("src/main/resources/config.properties");
			prop.load(configFile);
		}
		catch (IOException ex) {
	        ex.printStackTrace();
		}
		
		String soapEnvHeader = "Header data "
					+ " to read username from properties file prop.getProperty(\"http.username\")"
					+ "	to read password from properties file Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText\"+prop.getProperty(\"http.password\")";	
		GetWorkerDocumentsReqPayload payload = new GetWorkerDocumentsReqPayload();
				
		if (!payload.getWorkerDocuments(fileId).toUpperCase().contains("</ENV:BODY>")) {
				String body = payload.getWorkerDocuments(fileId);
				String soapEnvFooter = " </env:Body> " + " </env:Envelope> ";
				completerequest = soapEnvHeader.concat(body).concat(
						soapEnvFooter);
			} else {
				completerequest = payload.getWorkerDocuments(fileId);
			}

			/**
			 * Creating Http Connection to call WebService Over HTTP Protocol
			 */
					
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(300000).setSocketTimeout(300000)
					.setProxy(new HttpHost(prop.getProperty("http.proxy.host"), 80, "http")).build(); // 5 minutes
			
			CloseableHttpClient httpclient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build(); 
			CloseableHttpResponse httpResponse = null;
			try {
				// Prepare HTTP post
				HttpPost post = new HttpPost(prop.getProperty("http.endpoint.url"));
				// Request content will be retrieved directly from the input stream
				StringEntity entity = new StringEntity(completerequest);
				post.setEntity(entity);
				// post.setHeader("SOAPAction", "");
				// Execute request
				httpResponse = httpclient.execute(post);
				
			if(httpResponse.getEntity()!=null)
			{
				serviceResponse=EntityUtils.toString(httpResponse.getEntity());
				
/*				if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
								}*/
			}
			
		httpclient.close();

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return serviceResponse;
	}  
}
