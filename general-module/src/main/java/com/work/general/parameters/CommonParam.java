package com.work.general.parameters;


import java.util.HashMap;
import java.util.Map;

public abstract class CommonParam {

    private static final long serialVersionUID = 1L;

    protected Map<String, String> params = new HashMap<String, String>();

    protected abstract void putParam(String key, String value);

    protected abstract void putParamRmNull(String key, String value);

    protected abstract String getParam(String key);

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
