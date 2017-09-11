package com.kx.base.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 逆变器
 * @author Administrator
 *
 */
@Entity
@Table(name="f_inverter")
public class Inverter implements Serializable{

	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private @Id Long id;
	
	private String name;
	
	private String place;
	
	private Long collectid;
	
	private String remark;

	private String models;

	private String comply;
	private Double power;
	
	private Double type;
	
	private Double startCap;

	public Double getPower() {
		return power;
	}

	public void setPower(Double power) {
		this.power = power;
	}

	public Double getType() {
		return type;
	}

	public void setType(Double type) {
		this.type = type;
	}

	public Double getStartCap() {
		return startCap;
	}

	public void setStartCap(Double startCap) {
		this.startCap = startCap;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getCollectid() {
		return collectid;
	}

	public void setCollectid(Long collectid) {
		this.collectid = collectid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getModels() {
		return models;
	}

	public void setModels(String models) {
		this.models = models;
	}

	public String getComply() {
		return comply;
	}

	public void setComply(String comply) {
		this.comply = comply;
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
		Inverter other = (Inverter) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
