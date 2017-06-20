package com.response;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * Created by WilsonHuang on 2017/6/19.
 */
public class HttpCharacterResponseWrapper extends HttpServletResponseWrapper {

    private CharArrayWriter charArrayWriter = new CharArrayWriter();

    public HttpCharacterResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(charArrayWriter);
    }

    public CharArrayWriter getCharArrayWriter() {
        return charArrayWriter;
    }
}
