package com.work.generaldb.model;

public class TblOrder {
    private String txnSeqId;

    private String txnTime;

    private String outNumber;

    private String outTime;

    private String merId;

    private String subMerId;

    private String orderAmount;

    private String payChannel;

    private String othChannelNumber;

    private String status;

    private String msg;

    public String getTxnSeqId() {
        return txnSeqId;
    }

    public void setTxnSeqId(String txnSeqId) {
        this.txnSeqId = txnSeqId == null ? null : txnSeqId.trim();
    }

    public String getTxnTime() {
        return txnTime;
    }

    public void setTxnTime(String txnTime) {
        this.txnTime = txnTime == null ? null : txnTime.trim();
    }

    public String getOutNumber() {
        return outNumber;
    }

    public void setOutNumber(String outNumber) {
        this.outNumber = outNumber == null ? null : outNumber.trim();
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime == null ? null : outTime.trim();
    }

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId == null ? null : merId.trim();
    }

    public String getSubMerId() {
        return subMerId;
    }

    public void setSubMerId(String subMerId) {
        this.subMerId = subMerId == null ? null : subMerId.trim();
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount == null ? null : orderAmount.trim();
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel == null ? null : payChannel.trim();
    }

    public String getOthChannelNumber() {
        return othChannelNumber;
    }

    public void setOthChannelNumber(String othChannelNumber) {
        this.othChannelNumber = othChannelNumber == null ? null : othChannelNumber.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }
}