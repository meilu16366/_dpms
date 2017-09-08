package com.zs.frame.sys.beans;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 角色
 * @author ml
 * @date 2017-07
 * @company 广东振森智能科技有限公司
 */
@Entity
@Table(name="sys_role")
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private @Id Long rid;
	
	private String rcode;
	
	private String rname;
	
	private String duty;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "sys_role_user",
	joinColumns = {@JoinColumn(name = "rid")},
	inverseJoinColumns = {@JoinColumn(name = "userid")})
	private Set<User> users;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "sys_role_fun",
	joinColumns = {@JoinColumn(name = "rid")},
	inverseJoinColumns = {@JoinColumn(name = "fid")})
	private Set<Function> functions;
	
	
	public Long getRid() {
		return rid;
	}

	public void setRid(Long rid) {
		this.rid = rid;
	}

	public String getRcode() {
		return rcode;
	}

	public void setRcode(String rcode) {
		this.rcode = rcode;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rid == null) ? 0 : rid.hashCode());
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
		Role other = (Role) obj;
		if (rid == null) {
			if (other.rid != null)
				return false;
		} else if (!rid.equals(other.rid))
			return false;
		return true;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Function> getFunctions() {
		return functions;
	}

	public void setFunctions(Set<Function> functions) {
		this.functions = functions;
	}

	@Override
	public String toString() {
		return "Role [rid=" + rid + ", rcode=" + rcode + ", rname=" + rname
				+ ", duty=" + duty + ", users=" + users + ", functions="
				+ functions + "]";
	}
	
}
