package com.work.general.parameters;

import com.work.general.util.StringUtil;

import java.util.Map;

public class InputParam extends CommonParam {

    @Override
    public void putParam(String key, String value) {
        params.put(key, value);
    }

    @Override
    public void putParamRmNull(String key, String value) {
        if (!StringUtil.isEmpty(value)) {
            params.put(key, value);
        }
    }

    @Override
    public String getParam(String key) {
        return params.get(key);
    }


    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "InputParam{" +
                "params=" + params +
                '}';
    }
}
