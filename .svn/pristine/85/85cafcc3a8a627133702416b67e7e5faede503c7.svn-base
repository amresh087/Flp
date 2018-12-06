package com.flp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.flp.util.Utility;



@Entity
@Table(name="t_priceList")
public class PriceList {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "f_id")
    private Long id;
	
    // subject id -1 represent all subjects in that grade.
    @Column(name = "f_subjectId")
    private Long subjectId;
    
    // see plan type in subscriptoin.java
    @Column(name = "f_planType")
    private int planType;
    
    @Column(name = "f_price")
    private Double price;
    
    @Column(name = "f_boardId")
    private Long boardId;
    
    @Column(name = "f_gradeId")
    private Long gradeId;
    
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
    
    
    // getter & setters
    
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public int getPlanType() {
		return planType;
	}

	public void setPlanType(int planType) {
		this.planType = planType;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getBoardId() {
		return boardId;
	}

	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}

	public Long getGradeId() {
		return gradeId;
	}

	public void setGradeId(Long gradeId) {
		this.gradeId = gradeId;
	}

	
    
	
	

}
