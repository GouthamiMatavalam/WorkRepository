package com.workersdocuments;

/*import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;*/

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.workersdocuments.response.GetWorkersDocumentsResponse;
import com.workersdocuments.service.ClientService;
import com.workersdocuments.service.ClientServiceImpl;

public class ClientServiceTest {

	final static Logger logger = Logger.getLogger(ClientServiceTest.class);
	
	public static void main(String[] args) {
		/**
		 * 1. Call getWorkerDocuments Service With SOAP body
		 */
		
		List<String> ids = Arrays.asList("29632","29754");
		
		ClientService service= new ClientServiceImpl();
		String response = service.invokeGetWorkerDocumentsService(ids);
		
		logger.info("response value"+response);
				
		GetWorkersDocumentsResponse.convertEachWorkerData(response);
	}
	


}
