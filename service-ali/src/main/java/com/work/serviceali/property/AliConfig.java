package com.work.serviceali.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:ali-config.yml")
@ConfigurationProperties(prefix = "asdk")
public class AliConfig {
    private String indirectCreate;
    private String indirectQuery;
    private String indirectModify;
    private String tradePrecreate;
    private String tradePay;
    private String tradeCreate;
    private String tradeCancel;
    private String tradeRefundQuery;
    private String tradeRefund;
    private String tradeQuery;
    private String appid;
    private String pid;
    private String merid;
    private String frontTransUrl;
    private String publicKey;
    private String privateKey;

    public String getIndirectCreate() {
        return indirectCreate;
    }

    public void setIndirectCreate(String indirectCreate) {
        this.indirectCreate = indirectCreate;
    }

    public String getIndirectQuery() {
        return indirectQuery;
    }

    public void setIndirectQuery(String indirectQuery) {
        this.indirectQuery = indirectQuery;
    }

    public String getIndirectModify() {
        return indirectModify;
    }

    public void setIndirectModify(String indirectModify) {
        this.indirectModify = indirectModify;
    }

    public String getTradePrecreate() {
        return tradePrecreate;
    }

    public void setTradePrecreate(String tradePrecreate) {
        this.tradePrecreate = tradePrecreate;
    }

    public String getTradePay() {
        return tradePay;
    }

    public void setTradePay(String tradePay) {
        this.tradePay = tradePay;
    }

    public String getTradeCreate() {
        return tradeCreate;
    }

    public void setTradeCreate(String tradeCreate) {
        this.tradeCreate = tradeCreate;
    }

    public String getTradeCancel() {
        return tradeCancel;
    }

    public void setTradeCancel(String tradeCancel) {
        this.tradeCancel = tradeCancel;
    }

    public String getTradeRefundQuery() {
        return tradeRefundQuery;
    }

    public void setTradeRefundQuery(String tradeRefundQuery) {
        this.tradeRefundQuery = tradeRefundQuery;
    }

    public String getTradeRefund() {
        return tradeRefund;
    }

    public void setTradeRefund(String tradeRefund) {
        this.tradeRefund = tradeRefund;
    }

    public String getTradeQuery() {
        return tradeQuery;
    }

    public void setTradeQuery(String tradeQuery) {
        this.tradeQuery = tradeQuery;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getMerid() {
        return merid;
    }

    public void setMerid(String merid) {
        this.merid = merid;
    }

    public String getFrontTransUrl() {
        return frontTransUrl;
    }

    public void setFrontTransUrl(String frontTransUrl) {
        this.frontTransUrl = frontTransUrl;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
}
