package com.zs.frame.sys.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 系统参数
 * @author ml
 * @date 2017-07
 * @company 广东振森智能科技有限公司
 */
@Entity
@Table(name="sys_param")
public class Parameter implements Serializable{

	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private @Id Long paramid;
	/**参数名称*/
	private String name;
	/**参数值*/
	private String value;
	/**选项1*/
	private String option1;
	/**选项2*/
	private String option2;
	/**描述*/
	private String description;
	/**备注*/
	private String remark;

	public Long getParamid() {
		return paramid;
	}

	public void setParamid(Long paramid) {
		this.paramid = paramid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((paramid == null) ? 0 : paramid.hashCode());
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
		Parameter other = (Parameter) obj;
		if (paramid == null) {
			if (other.paramid != null)
				return false;
		} else if (!paramid.equals(other.paramid))
			return false;
		return true;
	}
	
}
