package com.workersdocuments.service;

import java.util.Iterator;
import java.util.List;

public class GetWorkerDocumentsReqPayload implements RequestPayload{

	public String getWorkerDocuments(List<String> fileId) {
		// TODO Auto-generated method stub
	
		String workerDocumentLoad = null;
		
		Iterator<String> it = fileId.iterator();
		while(it.hasNext()){
		workerDocumentLoad=workerDocumentLoad+workerDocumentReferencePayLoad(it.next());
		}		
		
		String soapRequest = " Request Payload ";

			return soapRequest;
	}
	
	public String workerDocumentReferencePayLoad(String id)
	{
	String workerDocumetLoad = " For each worker files ";
	
	return workerDocumetLoad;
	}
	
	
}
