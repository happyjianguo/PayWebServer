package com.work.general.parameters;

import com.work.general.util.StringUtil;

public class OutputParam extends CommonParam {

    private String retCode;
    private String retMsg;

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

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    @Override
    public String toString() {
        return "OutputParam{" +
                "retCode='" + retCode + '\'' +
                ", retMsg='" + retMsg + '\'' +
                ", params=" + params +
                '}';
    }
}
