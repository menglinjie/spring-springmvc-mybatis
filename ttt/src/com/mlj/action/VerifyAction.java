package com.mlj.action;

import com.mlj.util.ImageUtil;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class VerifyAction {
    public InputStream inputStream;

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String execute() throws Exception {
        String number = ImageUtil.getNumber(4);
        try {
            byte[] arr = ImageUtil.getInstance(100, 30, 4, number);
            inputStream = new ByteArrayInputStream(arr);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return "success";
    }
}
