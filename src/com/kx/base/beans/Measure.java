package com.kx.base.beans;

/**
 * 测点
 */
public class Measure {
	
	private String _id;
	
	private String id;
	/**名称*/
	private String name;
	/**描述*/
	private String desc;
	/**站号*/
	private String station;
	/**点号*/
	private String point;
	/**设备类型*/
	private String eqtype;
	/**测点类型*/
	private String mutype;
	/**设备编号*/
	private String eqid;
	/**是否报警*/
	private String iswarn;
	/**告警级别*/
	private String level;
	/**是否故障点*/
	private String faultpoint;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public String getEqtype() {
		return eqtype;
	}
	public void setEqtype(String eqtype) {
		this.eqtype = eqtype;
	}
	public String getMutype() {
		return mutype;
	}
	public void setMutype(String mutype) {
		this.mutype = mutype;
	}
	public String getEqid() {
		return eqid;
	}
	public void setEqid(String eqid) {
		this.eqid = eqid;
	}
	public String getIswarn() {
		return iswarn;
	}
	public void setIswarn(String iswarn) {
		this.iswarn = iswarn;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
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
		Measure other = (Measure) obj;
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		return true;
	}
	public String getFaultpoint() {
		return faultpoint;
	}
	public void setFaultpoint(String faultpoint) {
		this.faultpoint = faultpoint;
	}
	
}
