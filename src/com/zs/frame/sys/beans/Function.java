package com.zs.frame.sys.beans;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="sys_function")
public class Function implements Serializable {

	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private @Id Long fid;
	/**功能编码*/
	private String code;
	/**功能名称*/
	private String name;
	/**描述*/
	private String note;
	/**url*/
	private String url;
	/**附属url*/
	private String attachurl;
	/**停用*/
	@Type(type="yes_no")
	private boolean disabled;
	/**默认权限*/
	@Type(type="yes_no")
	private boolean defultwork;
	/**排序号*/
	private String orderno;
	/**模块*/
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name="mid")
	private Modul modul;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "sys_role_fun",
	joinColumns = {@JoinColumn(name = "fid")},
	inverseJoinColumns = {@JoinColumn(name = "rid")})
	private Set<Role> roles;
	
	public Long getFid() {
		return fid;
	}
	public void setFid(Long fid) {
		this.fid = fid;
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
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAttachurl() {
		return attachurl;
	}
	public void setAttachurl(String attachurl) {
		this.attachurl = attachurl;
	}
	public Boolean getDisabled() {
		return disabled;
	}
	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fid == null) ? 0 : fid.hashCode());
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
		Function other = (Function) obj;
		if (fid == null) {
			if (other.fid != null)
				return false;
		} else if (!fid.equals(other.fid))
			return false;
		return true;
	}
	public Modul getModul() {
		return modul;
	}
	public void setModul(Modul modul) {
		this.modul = modul;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public Boolean getDefultwork() {
		return defultwork;
	}
	public void setDefultwork(Boolean defultwork) {
		this.defultwork = defultwork;
	}
}
