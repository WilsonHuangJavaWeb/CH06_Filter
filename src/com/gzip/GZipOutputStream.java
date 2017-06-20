package com.gzip;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ki264 on 2017/6/20.
 */
public class GZipOutputStream extends ServletOutputStream {

    private HttpServletResponse response;

    //JDK內建的壓縮資料類別
    private GZIPOutputStream gzipOutputStream;

    //將壓縮後的資料放到ByteArrayOutputStream物件中
    private ByteArrayOutputStream byteArrayOutputStream;

    public GZipOutputStream(HttpServletResponse response) throws IOException {
        this.response = response;
        byteArrayOutputStream = new ByteArrayOutputStream();
        gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);

    }

    @Override
    public void write(int b) throws IOException {
        gzipOutputStream.write(b);
    }

    public void close() throws IOException {

        //壓縮完畢後一定要呼叫該方法
        gzipOutputStream.finish();

        //將壓縮後的資料輸出到客戶端
        byte[] content = byteArrayOutputStream.toByteArray();

        //設定壓縮方式為GZIP，客戶端的瀏覽器會自動將資料解壓縮
        response.addHeader("Content-Encoding", "gzip");
        response.addHeader("Content-Length", Integer.toString(content.length));

        //輸出
        ServletOutputStream out = response.getOutputStream();
        out.write(content);
        out.close();
    }


    public void flush() throws IOException {
        gzipOutputStream.flush();
    }

    @Override
    public void write(byte[] b) throws IOException {
        gzipOutputStream.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        gzipOutputStream.write(b, off, len);
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {

    }
}
