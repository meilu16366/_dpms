package com.kx.base.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

/**
 *  环境监测仪
 */
@Entity
@Table(name="f_emi")
public class EmiInfo implements Serializable {
	
	 private static final long serialVersionUID = 1L;
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private @Id Long id;
	 /**编号*/
	 private  String code;
	 /**名称*/
	 private  String name;
	 /**位置*/
	 private  String place;
	 /**型号*/
	 private  String models;
	 /**安装方式(1水平，2倾斜)*/
	 private  Integer inittype;
	 /**角度*/
	 private  Double angle ;
	 /**厂家*/
	 private  String company;
	 /**采集id*/
	 private  Integer collectid;
	 /**是否是主气象仪*/
	 @Type(type="yes_no")
	 private  boolean mains;

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getModels() {
		return models;
	}
	public void setModels(String models) {
		this.models = models;
	}
	public Integer getInittype() {
		return inittype;
	}
	public void setInittype(Integer inittype) {
		this.inittype = inittype;
	}
	public Double getAngle() {
		return angle;
	}
	public void setAngle(Double angle) {
		this.angle = angle;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public Integer getCollectid() {
		return collectid;
	}
	public void setCollectid(Integer collectid) {
		this.collectid = collectid;
	}
	public Boolean getMains() {
		
		return mains;
	}
	public void setMains(Boolean  mains) {
		this.mains = mains;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setMains(boolean mains) {
		this.mains = mains;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmiInfo other = (EmiInfo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


   
}
