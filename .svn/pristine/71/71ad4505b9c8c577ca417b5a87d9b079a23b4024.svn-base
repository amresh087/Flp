package com.flp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.flp.util.Utility;

@Entity
@Table(name = "t_payment_history")
public class PaymentHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "f_id")
	private Long id;
	
	@Column(name = "f_userId")
	private Long userId;
	
	@Column(name = "f_orderNo")
	private String orderNo;
	
	@Column(name = "f_amount")
	private Double amount;
	
	@Column(name = "f_Bank_Auth_Desc")
	private String bankAuthDesc;
	
	@Column(name = "f_Bank_Resp_Code")
	private String bankRespCode;
	
	@Column(name = "f_Bank_Resp_Msg")
	private String bankRespMsg;
	
	@Column(name = "f_Checksum")
	private String checksum;
	
	@Column(name = "f_Card_Category")
	private String cardCategory;
	
	@Column(name = "f_Nb_Order_No")
	private String nbOrderNo;
	
	@Column(name = "f_Bank_Name")
	private String bankName;
	
	@Column(name = "f_NB_BID")
	private String nbBID;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "f_insertedDate")
	private Date insertedDate;
	
	@Column(name="f_dateTimeZone")
   	private String dateTimeZone=Utility.convertDateToStringWithZone(new Date()); 
	
    public String getDateTimeZone()
	{
		return dateTimeZone;
	}

	public void setDateTimeZone(String dateTimeZone)
	{
		this.dateTimeZone = dateTimeZone;
	}
	
	// getters & setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getBankAuthDesc() {
		return bankAuthDesc;
	}

	public void setBankAuthDesc(String bankAuthDesc) {
		this.bankAuthDesc = bankAuthDesc;
	}

	public String getBankRespCode() {
		return bankRespCode;
	}

	public void setBankRespCode(String bankRespCode) {
		this.bankRespCode = bankRespCode;
	}

	public String getBankRespMsg() {
		return bankRespMsg;
	}

	public void setBankRespMsg(String bankRespMsg) {
		this.bankRespMsg = bankRespMsg;
	}

	public String getChecksum() {
		return checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

	public String getCardCategory() {
		return cardCategory;
	}

	public void setCardCategory(String cardCategory) {
		this.cardCategory = cardCategory;
	}

	public String getNbOrderNo() {
		return nbOrderNo;
	}

	public void setNbOrderNo(String nbOrderNo) {
		this.nbOrderNo = nbOrderNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getNbBID() {
		return nbBID;
	}

	public void setNbBID(String nbBID) {
		this.nbBID = nbBID;
	}

	public Date getInsertedDate() {
		return insertedDate;
	}

	public void setInsertedDate(Date insertedDate) {
		this.insertedDate = insertedDate;
	}
	

}
