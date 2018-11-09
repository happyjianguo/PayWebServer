package com.work.general.parameters;

import com.work.general.util.StringUtil;

public class InputParam extends CommonParam {

    @Override
    public void putParam(String key, Object value) {
        params.put(key, value);
    }

    @Override
    public void putParamRmNull(String key, Object value) {
        if (!StringUtil.isEmpty(value)) {
            params.put(key, value);
        }
    }

    @Override
    public Object getParam(String key) {
        return params.get(key);
    }

    @Override
    public String getParamsStr(String key) {
        return StringUtil.toString(params.get(key));
    }

    @Override
    public String toString() {
        return "InputParam{" +
                "params=" + params +
                '}';
    }
}
