package com.work.generaldb.model;

import java.io.Serializable;

public class TblMerchant implements Serializable{
    private String merId;

    private String name;

    private String aliasName;

    private String contactName;

    private String servicePhone;

    private String address;

    private String aliSubMerId;

    private String wxSubMerId;

    private String mcc;

    private String createTime;

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId == null ? null : merId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName == null ? null : aliasName.trim();
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    public String getServicePhone() {
        return servicePhone;
    }

    public void setServicePhone(String servicePhone) {
        this.servicePhone = servicePhone == null ? null : servicePhone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getAliSubMerId() {
        return aliSubMerId;
    }

    public void setAliSubMerId(String aliSubMerId) {
        this.aliSubMerId = aliSubMerId == null ? null : aliSubMerId.trim();
    }

    public String getWxSubMerId() {
        return wxSubMerId;
    }

    public void setWxSubMerId(String wxSubMerId) {
        this.wxSubMerId = wxSubMerId == null ? null : wxSubMerId.trim();
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc == null ? null : mcc.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }
}