package com.zs.frame.sys.beans;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

/**
 * 模块名称
 * @author ml
 * @date 2017-07
 * @company 广东振森智能科技有限公司
 */
@Entity
@Table(name="sys_modul")
public class Modul implements Serializable {

	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private @Id Long mid;
	/**模块名称*/
	private String name;
	
	private String url;
	/**描述*/
	private String note;
	/**排序号*/
	private String orderno;
	/**父id*/
	private Long pid;
	/**停用*/
	@Type(type="yes_no")
	private boolean disable;
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="pid")
	private Set<Modul> moduls;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="modul")
	@OrderBy("orderno asc")
	private Set<Function> functions;
	
	public Long getMid() {
		return mid;
	}
	public void setMid(Long mid) {
		this.mid = mid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public Boolean getDisable() {
		return disable;
	}
	public void setDisable(Boolean disable) {
		this.disable = disable;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mid == null) ? 0 : mid.hashCode());
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
		Modul other = (Modul) obj;
		if (mid == null) {
			if (other.mid != null)
				return false;
		} else if (!mid.equals(other.mid))
			return false;
		return true;
	}
	public Set<Function> getFunctions() {
		return functions;
	}
	public void setFunctions(Set<Function> functions) {
		this.functions = functions;
	}
	public Set<Modul> getModuls() {
		return moduls;
	}
	public void setModuls(Set<Modul> moduls) {
		this.moduls = moduls;
	}
	
}
