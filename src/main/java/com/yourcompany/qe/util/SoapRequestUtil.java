package com.yourcompany.qe.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;

public class SoapRequestUtil {
    static Logger logger = Logger.getLogger(SoapRequestUtil.class);
    public static String responseString = null;


    public HttpResponse getSoapResponseByPostMethod(String url, String requestFilePath, String responseFilePath)
            throws FileNotFoundException, IOException, TransformerException, jdk.internal.org.xml.sax.SAXException, ParserConfigurationException, SAXException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-type", "text/xml;charset=UTF-8");
        post.setEntity(new StringEntity(requestFilePath));
        HttpResponse response = client.execute(post);
        writeResponseToFile(responseFilePath, response);

        return response;

    }

    public void writeResponseToFile(String filePath, HttpResponse response) throws IOException, TransformerException, SAXException, ParserConfigurationException {

        filePath = System.getProperty("user.dir") + "/src/main/resources/" + filePath;
        logger.info("......file path....." + filePath);
        BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        StringBuffer sb = new StringBuffer();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        PrintWriter pw = new PrintWriter(filePath);
        pw.write(sb.toString());
        pw.close();
        pw.flush();
        responseString = sb.toString();

        System.out.println("...............result\n............" + sb.toString());
        logger.info("......Result can be found at .....:\n" + filePath);

        //Formatting the result and pushing to response file patj
        XMLPrettyPrintUtility objXMLPrettyPrintUtility = new XMLPrettyPrintUtility();
        objXMLPrettyPrintUtility.getXMLPrettyPrintText(filePath, filePath);
    }

    public String getResponseAsString(HttpResponse response) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        StringBuffer stringBuffer = new StringBuffer();
        while ((line = bufferedReader.readLine()) != null) {
            stringBuffer.append(line);
        }

        return stringBuffer.toString();

    }


}
