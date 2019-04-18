package com.yourcompany.qe.util;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

public class FileHelper {
    static Logger logger = Logger.getLogger(FileHelper.class);

    public static String replaceData(String request, Map<String, String> valuesMap) {

        StrSubstitutor sub = new StrSubstitutor(valuesMap);
        return sub.replace(request);

    }

    public File getFileByPathFromResource(String fileName) {
        return new File(getClass().getClassLoader().getResource(fileName).getFile());
    }

    public File getXmlFile(String fileName) {
        return new File(getClass().getClassLoader().getResource(fileName).getFile());
    }


    public String getXmlAsString(String filePath) {

        String result = "";
        StringBuffer sb = new StringBuffer();

        try {
            File file = new File(getClass().getClassLoader().getResource(filePath).getFile());
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                System.out.println(line);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            logger.error("File Not Found :" + e.getStackTrace().toString());
            result = "";
        } catch (Exception e) {
            logger.error("Exception :" + e.getStackTrace().toString());
            result = "";
        }
        return sb.toString();
    }
}
