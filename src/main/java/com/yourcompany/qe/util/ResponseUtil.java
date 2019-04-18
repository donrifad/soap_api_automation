package com.yourcompany.qe.util;

import org.apache.http.HttpResponse;

public class ResponseUtil {

    public int getStatus(HttpResponse response) {
        return response.getStatusLine().getStatusCode();
    }
}
