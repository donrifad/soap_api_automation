package com.yourcompany.qe.tests.createposition;

import com.yourcompany.qe.common.Constants;
import com.yourcompany.qe.request.data.createposition.Keys;
import com.yourcompany.qe.request.data.createposition.ConversionData;
import com.yourcompany.qe.resposne.data.createposition.ResponseData;
import com.yourcompany.qe.util.FileHelper;
import com.yourcompany.qe.util.ResponseUtil;
import com.yourcompany.qe.util.SoapRequestUtil;
import com.yourcompany.qe.util.TestBase;
import io.restassured.path.xml.XmlPath;
import jdk.internal.org.xml.sax.SAXException;
import org.apache.http.HttpResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.yourcompany.qe.common.StatusCodes.STATUS_200;

public class ConvertTest extends TestBase {


    @Test
    public void testCreateNewPosition() throws SAXException, TransformerException, ParserConfigurationException, org.xml.sax.SAXException, IOException, XPathExpressionException {
        String requestFileName = "celsiusToFahrenheitRequest";
        String responseFileName = "celsiusToFahrenheitResponse";
        String requestName = "celsiusToFahrenheit";

        ConversionData conversionData = new ConversionData();

        Map<String, String> valuesMap = new HashMap<String, String>();

        //Setting parameters
        String requestXmlString = new FileHelper().getXmlAsString(String.format(Constants.REQUEST_FOLDER, requestName, requestFileName));
        valuesMap.put(Keys.CELSIUS, conversionData.celsius);


        requestXmlString = FileHelper.replaceData(requestXmlString, valuesMap);
        String responseFilePath = String.format(Constants.RESPONSE_FOLDER, requestName, responseFileName);

        //Sending the requests
        SoapRequestUtil soapRequestUtil = new SoapRequestUtil();
        HttpResponse response = soapRequestUtil.getSoapResponseByPostMethod(Constants.requestUrl, requestXmlString, responseFilePath);

        ResponseUtil responseUtil = new ResponseUtil();
        Assert.assertEquals(responseUtil.getStatus(response), STATUS_200, "Should be equal");


        ResponseData responseData = new ResponseData();
        String convertedValue = responseData.getFahrenheit(new XmlPath(soapRequestUtil.responseString));

        System.out.println("..................converted value..........\n");
        System.out.println(convertedValue);


    }
}
