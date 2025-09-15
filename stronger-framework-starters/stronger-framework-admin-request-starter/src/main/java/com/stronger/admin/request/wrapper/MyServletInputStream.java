package com.stronger.admin.request.wrapper;


import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @author stronger
 * @version release-1.0.0
 * @class MyServletInputStream.class
 * @department Platform R&D
 * @date 2025/9/15
 * @desc MyServletInputStream
 */
public class MyServletInputStream extends ServletInputStream {

    private final ByteArrayInputStream bis;

    public MyServletInputStream(ByteArrayInputStream bis) {
        this.bis = bis;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void setReadListener(ReadListener listener) {

    }

    @Override
    public int read() throws IOException {
        return bis.read();
    }
}
