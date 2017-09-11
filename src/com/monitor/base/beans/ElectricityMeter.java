package com.monitor.base.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 电表
 * @author Administrator
 *
 */
@Entity
@Table(name="f_electricitymeter")
public class ElectricityMeter extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Double rate;

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}
	
}
