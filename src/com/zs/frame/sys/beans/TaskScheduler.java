package com.zs.frame.sys.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

/**
 * 管理员
 * @author ml
 * @date 2017-07
 * @company 广东振森智能科技有限公司
 */
@Entity
@Table(name="sys_taskscheduler")
public class TaskScheduler implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private @Id Long taskid;
	/**作业编号*/
	private String code;
	/**作业名称*/
	private String name;
	/**类名*/
	private String className;
	/**周期*/
	private String cycle;
	/**停用*/
	@Type(type="yes_no")
	private boolean disabled;
	/**备注*/
	private String remark;
	public Long getTaskid() {
		return taskid;
	}
	public void setTaskid(Long taskid) {
		this.taskid = taskid;
	}
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
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getCycle() {
		return cycle;
	}
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
	public Boolean getDisabled() {
		return disabled;
	}
	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
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
		result = prime * result + ((taskid == null) ? 0 : taskid.hashCode());
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
		TaskScheduler other = (TaskScheduler) obj;
		if (taskid == null) {
			if (other.taskid != null)
				return false;
		} else if (!taskid.equals(other.taskid))
			return false;
		return true;
	}
	
	
}
