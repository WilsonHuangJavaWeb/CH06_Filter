package com.gzip;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * Created by ki264 on 2017/6/20.
 */
public class GZipResponseWrapper extends HttpServletResponseWrapper{

    public GZipResponseWrapper(HttpServletResponse response) {
        super(response);
    }
}
