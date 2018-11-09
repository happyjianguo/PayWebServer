package com.work.general.parameters;


import java.util.HashMap;
import java.util.Map;

public abstract class CommonParam {

    protected Map<String, Object> params = new HashMap<String, Object>();

    protected abstract void putParam(String key, Object value);

    protected abstract void putParamRmNull(String key, Object value);

    protected abstract Object getParam(String key);

    protected abstract String getParamsStr(String key);

}
