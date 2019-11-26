package com.workersdocuments.decoders;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Base64;

import org.apache.log4j.Logger;


public class Base64ToPdf {
	
	final static Logger logger = Logger.getLogger(Base64ToPdf.class);

	public static void base64Pdf(String fileName,String b64)
	{
		 File file = new File("provide file name here"+fileName);

		    try ( FileOutputStream fos = new FileOutputStream(file); ) {

		    	byte[] decoder = Base64.getDecoder().decode(b64);

		      fos.write(decoder);
		      logger.info("PDF File Saved");
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		  }

}
