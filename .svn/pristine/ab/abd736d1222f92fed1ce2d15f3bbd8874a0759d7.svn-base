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
@Table(name="t_transaction")
public class Transaction {
	
	public static final String STATUS_PAID       ="Paid";
	public static final String STATUS_UNPAID     ="Unpaid";
	public static final String STATUS_CANCELED   ="Canceled";
	public static final String STATUS_PENDING    ="Pending";
	public static final String STATUS_FAILED    ="Failed";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="f_id")
	private Long id;
	
	/*@OneToOne
    @JoinColumn(name = "userid", referencedColumnName = "f_id", insertable = true, updatable = true)
	private User user;*/
	@Column(name="f_userId")
	private Long useId;
	
	@Column(name="f_subscriptoinId")
	private Long subscriptionId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="f_insertedDate")
	private Date insertedDate;
	

	@Column(name="f_orderNo")
	private String orderNo;
	
	@Column(name="f_amount")
	private Double amount;
	
	@Column(name="f_promoCode")
	private String promoCode;
	
	@Column(name="f_status")
	private String status;	

	@Column(name="f_discount")
	private Double discount;
	
	@Column(name="f_payableAmount")
	private Double payableAmount;	
	
	@Column(name = "f_dateTimeZone")
	private String dateTimeZone = Utility.convertDateToStringWithZone(new Date());

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

	public Long getUseId() {
		return useId;
	}

	public void setUseId(Long useId) {
		this.useId = useId;
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

	public String getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getPayableAmount() {
		return payableAmount;
	}

	public void setPayableAmount(Double payableAmount) {
		this.payableAmount = payableAmount;
	}
	
	public Long getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(Long subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public Date getInsertedDate() {
		return insertedDate;
	}

	public void setInsertedDate(Date insertedDate) {
		this.insertedDate = insertedDate;
	}
	
	

}
