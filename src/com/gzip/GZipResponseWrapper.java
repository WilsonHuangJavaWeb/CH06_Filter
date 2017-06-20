package com.gzip;


import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;


/**
 * Created by ki264 on 2017/6/20.
 */
public class GZipResponseWrapper extends HttpServletResponseWrapper {

    //預設的response
    private HttpServletResponse response;

    //自訂的outputStream，執行close()的時候對資料壓縮，並輸出
    private GZipOutputStream gzipOutputStream;

    //自訂的printWriter，將內容輸出到GZipOutputStream
    private PrintWriter writer;

    public GZipResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (gzipOutputStream == null) {
            gzipOutputStream = new GZipOutputStream(response);
        }
        return gzipOutputStream;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if (writer == null) {
            writer = new PrintWriter(new OutputStreamWriter(new GZipOutputStream(response), "utf-8"));
        }
        return writer;
    }

    @Override
    public void setContentLength(int len) {
        //壓縮後資料長度會發生變化，因此將該方法內容置空
    }

    @Override
    public void flushBuffer() throws IOException {
        gzipOutputStream.flush();
    }

    public void finishResponse() throws IOException {
        if (gzipOutputStream != null) {
            gzipOutputStream.close();
        }
        if (writer != null) {
            writer.close();
        }
    }
}
