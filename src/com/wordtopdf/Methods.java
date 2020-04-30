package com.wordtopdf;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.aspose.words.Document;
import com.aspose.words.SaveFormat;

public class Methods {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		String UrlLink="http://bluealgo.com:8085/Attachment/4d91d28e-2b7f-460b-8f97-0fbf21784fe0.docx";
		/*String UrlLink="http://bluealgo.com:8082/Attachment/c3acc884-adc0-4a90-b2f1-16e254d725d9.docx";
		String pdfUrl=wordToPdfMethod(UrlLink);
		System.out.println(pdfUrl);*/
		
		/*try{
		String savePath="D:\\omnichannelCaseLead\\Dashboard\\537PPT_check2.pdf";
		Document doc = new Document("D:\\omnichannelCaseLead\\Dashboard\\537PPT_check2.pptx");
		doc.save(savePath,SaveFormat.PDF);
		System.out.println("converted");
		}catch(Exception e){
			e.printStackTrace();
		}*/
		
	}
	
	
	public static String wordToPdfMethod(String UrlLink){
		String pdfUrl="";
		try {
			String savePath="/home/vil/sling tomcat/apache-tomcat-6.0.35/webapps/ROOT/Attachment/";
//			String savePath="D:\\omnichannelCaseLead\\Dashboard\\";
//			String savePath="D:\\apache-tomcat-9.0.12\\webapps\\ROOT\\DocumentGeneration\\";
			String docxUrlWindows= downloadFileFromUrl(UrlLink, savePath);
			System.out.println(docxUrlWindows);
			Document doc = new Document(docxUrlWindows);
			
			if (UrlLink.lastIndexOf("/") != -1) {
				String documentData = UrlLink.substring(UrlLink.lastIndexOf("/") + 1);
				if (documentData.contains(".docx")) {
					documentData = documentData.substring(0,documentData.indexOf(".docx"));
					doc.save(savePath+documentData+".pdf",SaveFormat.PDF);
					
					pdfUrl=savePath+documentData+".pdf";
				}
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pdfUrl;
	}
	
	
public static String downloadFileFromUrl(String UrlLink, String savePath){
		
		String docxUrlWindows="";
		if (UrlLink.lastIndexOf("/") != -1) {
			String documentData = UrlLink.substring(UrlLink.lastIndexOf("/") + 1);
			System.out.println("documentData:: "+documentData);
			if (documentData.contains(".docx")) {
				try (BufferedInputStream in = new BufferedInputStream(new URL(UrlLink).openStream());
						  FileOutputStream fileOutputStream =new FileOutputStream(savePath+documentData)) {
						    byte dataBuffer[] = new byte[1024];
						    int bytesRead;
						    while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
						        fileOutputStream.write(dataBuffer, 0, bytesRead);
						    }
						    
						    docxUrlWindows=savePath+documentData;
						    System.out.println("docxUrlWindows:: "+docxUrlWindows);
						    
						} catch (Exception e) {
						    // handle exception
							e.printStackTrace();
						}
				
				System.out.println("downloaded: "+documentData);
				
			}  // contains docx check here

		}
		return docxUrlWindows;
			
	}

public static String getResponseWordToPdfApi(String filePath) {
    String count=null;
		try {

			URL obj = new URL("http://gpl.bluealgo.com:8085/WordToPdfApi/WordToPdfServletApi");
			HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
			postConnection.setRequestMethod("POST");
//			postConnection.setRequestProperty("Content-Type", "application/json");
			postConnection.setDoOutput(true);
			OutputStream os = postConnection.getOutputStream();
			os.write(filePath.getBytes());
			os.flush();
			os.close();
			int responseCode = postConnection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // success
				BufferedReader in = new BufferedReader(new InputStreamReader(postConnection.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
					count=response.toString();
				}
				in.close();
			} else {
				System.out.println("POST NOT WORKED");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;

	}

	

}
