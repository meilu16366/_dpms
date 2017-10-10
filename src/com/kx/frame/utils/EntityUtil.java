package com.kx.frame.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;

  
public class EntityUtil {  
  
  
 /**  
  * 功能描述：获取实体对应的表名  
  *   
  * @param clazz  
  *            实体类  
  * @return 表名  
  */  
 public static String getTableName(Class<?> clazz) {  
	 Annotation[] anno = clazz.getAnnotations();
	 for(Annotation annotation : anno) {
		 if(annotation instanceof Table) {
			 return ((Table)annotation).name();
		 }
	 }
	 return null;  
 }  
 
 
 class AnnotationParam{
	 
	 private String tableName;
	 private Object[] pk;
	 
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public Object[] getPk() {
		return pk;
	}
	public void setPk(Object[] pk) {
		this.pk = pk;
	}
	 
	 
	 
 }
 
 
   
 /** 
  * 功能描述：获取实体对应表的主键字段名称 
  *  
  * @param clazz 
  *            实体类 
  * @return 主键对象primaryKey ，可用primaryKey.getColumn(i).getName() 
  */  
 public static String[] getParamkeys(Class<?> clazz) {  
	 Field[] fls = clazz.getDeclaredFields();
	 String[] files=new String[fls.length]; 
	 int i=0;
	 for(Field f:fls) {
		 Annotation[] anno = f.getAnnotations();
		 for(Annotation annotation : anno) {
			 if(annotation instanceof Id) {
				 files[i]= f.getName();
				 i++;
			 }
		 }
		 
	 }
	 return files;
 } 
  
	public static String[] sqlStrByName(Class from,Class join) {
		Field[] fields = from.getDeclaredFields();
		String sql = "select ";
		String countsql = "select count("+EntityUtil.getParamkeys(from)[0]+")";
		List<String> cols = new ArrayList<String>();
		for(Field f : fields) {
			if(!f.getName().equals("name")&&!f.getName().equals("serialVersionUID"))cols.add("a."+f.getName());
			if(f.getName().equals("name")) {
				cols.add("b.name");
			}
		}
		sql += StringUtils.join(cols.toArray(),",");
		sql += " from " + EntityUtil.getTableName(from) + " a left join ";
		countsql += " from " + EntityUtil.getTableName(from) + " a left join ";
		
		sql += EntityUtil.getTableName(join) + " b on a." + EntityUtil.getParamkeys(from)[0];
		countsql += EntityUtil.getTableName(join) + " b on a." + EntityUtil.getParamkeys(from)[0];
		
		sql += "=b.collectid where 1=1";
		countsql += "=b.collectid where 1=1";
		
		return new String[] {sql,countsql};
	}
	
	
	public static <T> List<T> toEnty(List<Object> datas, Class<T> clazz) throws Exception{
		Field[] fields = clazz.getDeclaredFields();
		List<T> result = new ArrayList<T>();
		for(Object one : datas) {
			Object[] arr = (Object[]) one;
			T o = clazz.newInstance();
			int i = 0;
			for(Field f : fields) {
				if(!f.getName().equals("serialVersionUID")) {
					f.setAccessible(true);
					if(arr[i] != null && arr[i] instanceof Integer) {
						arr[i] = Long.valueOf(arr[i]+"");
					}
					if(arr[i] != null && arr[i] instanceof BigDecimal) {
						arr[i] = Double.valueOf(arr[i]+"");
					}
					f.set(o, arr[i]);
					i++;
				}
			}
			result.add(o);
		}
		return result;
	}
  
  
}
