package com.monitor.base.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 逆变器
 * @author Administrator
 *
 */
@Entity
@Table(name="f_inverter")
public class Inverter extends BaseBean implements Serializable{

	private static final long serialVersionUID = 1L;

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
	
	
	
}
