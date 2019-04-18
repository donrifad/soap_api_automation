package com.yourcompany.qe.resposne.data.createposition;

import io.restassured.path.xml.XmlPath;

public class ResponseData {


    public String getFahrenheit(XmlPath responseXml) {
        return responseXml.get("Envelope.Body.CelsiusToFahrenheitResponse.CelsiusToFahrenheitResult").toString();
    }

}
