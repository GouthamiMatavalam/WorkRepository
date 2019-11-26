package com.workersdocuments.response;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.xml.sax.InputSource;


import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.workersdocuments.decoders.Base64ToPdf;
import com.workersdocuments.object.WorkerDocumentData;


import java.util.ArrayList;
import java.util.List;


public class GetWorkersDocumentsResponse {
	
	final static Logger logger = Logger.getLogger(Base64ToPdf.class);
	
	public static List<WorkerDocumentData> convertStringToXMLDocument(String xmlString)
    {
        //Parser that produces DOM object trees from XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         
        List<WorkerDocumentData> workerList = new ArrayList<WorkerDocumentData>();

        //API to obtain DOM Document instance
        DocumentBuilder builder = null;
        try
        {
            //Create DocumentBuilder with default configuration
            builder = factory.newDocumentBuilder();
             
            //Parse the content to Document object
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            
            logger.info(doc.getFirstChild().getNodeName());
            
            doc.getDocumentElement().normalize();
            logger.info("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName(" Tag Name of XML field");
            //now XML is loaded as Document in memory, lets convert it to Object List
            for (int i = 0; i < nodeList.getLength(); i++) {
            	workerList.add(getWorkerDocumentData(nodeList.item(i)));
            }
           
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return workerList;
    }
	
	private static WorkerDocumentData getWorkerDocumentData(Node node) {
        //XMLReaderDOM domReader = new XMLReaderDOM();
		WorkerDocumentData workerData = new WorkerDocumentData();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            workerData.setID(Integer.parseInt(getTagValue("wd:ID", element)));
            workerData.setFilename(getTagValue("wd:Filename", element));
            workerData.setComment(getTagValue("wd:Comment", element));
            workerData.setFile(getTagValue("wd:File", element));
     
        }

        return workerData;
    }


    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }

    public static void convertEachWorkerData(String response)
    {
		List<WorkerDocumentData> workerList=GetWorkersDocumentsResponse.convertStringToXMLDocument(response);
		
		 //lets print WorkerDocument list information
       for (WorkerDocumentData worker : workerList) {
           logger.info(worker.getID());
           
           String fileName = worker.getFilename();
           String b64 = worker.getFile();
           
           Base64ToPdf converter= new Base64ToPdf();
           converter.base64Pdf(fileName,b64);
       }
    }
}
