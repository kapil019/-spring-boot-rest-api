package com.example.fl.Response;

import java.util.ArrayList;

public class BaseListResp {

    private ArrayList data;

    private Error error;

    public ArrayList getData() {
        return this.data;
    }

    public void setData(ArrayList data) {
        this.data = data;
    }

    public Error getError() {
        return this.error;
    }

    public void setError(Error error) {
        this.error = error;
    }

}
