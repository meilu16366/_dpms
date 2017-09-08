package com.zs.frame.sys.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 管理员
 * @author ml
 * @date 2017-07
 * @company 广东振森智能科技有限公司
 */
@Entity
@Table(name="sys_manager")
public class Manager implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private @Id Long mnid;
	
	private String mnname;
	
	private String password;

	public Long getMnid() {
		return mnid;
	}

	public void setMnid(Long mnid) {
		this.mnid = mnid;
	}

	public String getMnname() {
		return mnname;
	}

	public void setMnname(String mnname) {
		this.mnname = mnname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mnid == null) ? 0 : mnid.hashCode());
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
		Manager other = (Manager) obj;
		if (mnid == null) {
			if (other.mnid != null)
				return false;
		} else if (!mnid.equals(other.mnid))
			return false;
		return true;
	}
	
	
	
}
