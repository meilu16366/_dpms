package com.kx.realdata.controller;

import java.text.DecimalFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kx.collect.services.MemoryHandler;



@Controller
@RequestMapping("/monitor/svg")
public class RealDataSVGMonitor{
	@Autowired
	private MemoryHandler memoryHandler;
	@ResponseBody
	@RequestMapping(value= "/realdata")
	public void getSvgValue(String idvalue,String ids,HttpServletRequest request, HttpServletResponse response) throws Exception{
	       StringBuffer result = new StringBuffer("");
	        if (idvalue != null && !"".equals(idvalue)) {
	            result.append("{");
	            String keys[] = idvalue.split(",");
	            int len = keys.length;
	            if (len > 0) {
	               
	                for (int i = 0; i <= len - 1; i++) {
	                    String val = getSingleKey(keys[i].split("\\*")[0], keys[i].split("\\*")[1], keys[i].split("\\*")[2],
	                            keys[i].split("\\*")[3]);
	                    if (val != null && !"".equals(val)) {
	                        result.append("\"" + keys[i].split("\\*")[0] + "\":" + val);
	                        result.append(",");
	                    }
	                }
	                if (result.length()>1) {
	                    result.deleteCharAt(result.length() - 1);
	                }
	            }
	            result.append("}");
	        } else {
	            if (ids != null && !"".equals(ids) && ids.indexOf(",") > -1) {
	                result.append("{");
	                String keys[] = ids.split(",");
	                int len = keys.length;
	                if (len > 0) {
	                    for (int i = 0; i <= len - 1; i++) {
	                        result.append("\"" + keys[i] + "\":");
	                        result.append(getSingleKey(keys[i], null, null, null));
	                        if (i != len - 1) {
	                            result.append(",");
	                        }
	                    }
	                }
	                result.append("}");
	            } else {
	                result.append(getSingleKey(ids, null, null, null));
	            }
	        }
	        response.getWriter().write(result.toString());
	}
	
	
    private String getSingleKey(String ids, String oldvalue, String oldmarks, String oldflares) {
        if (oldvalue != null) {
            oldvalue = oldvalue.replace("-", "");
            oldmarks = oldmarks.replace("-", "");
            oldflares = oldflares.replace("-", "");
        }

        StringBuffer result = new StringBuffer("");
        if (ids != null && !"".equals(ids)) {
            String value = formatDouble(memoryHandler.getRealValue(ids));
            String marks = ""; // 是否挂牌
            String flares = ""; // 闪烁
            String breaks = ""; //通道
            result.append("{");
            result.append("\"value\":\"" + value + "\",");
            result.append("\"marks\":\"" + marks + "\",");
            result.append("\"flares\":\"" + flares + "\",");
            result.append("\"breaks\":\"" + breaks + "\"");
            result.append("}");
           
        }
        return result.toString();
    }
    public static String formatDouble(Object value) {
        DecimalFormat df = new DecimalFormat("0.00");
        if (value == null) {
            return "";
        } else {
            return df.format(toDouble(value));
        }

    }
    public static Double toDouble(Object value) {
        String val = value != null ? String.valueOf(value) : null;
        try {
            if (!val.equals("null")&& !val.equals("-")) {
                return Double.valueOf(val);
            }
        } catch (Exception ex) {
        }
        return 0.0;
    }
    
    @RequestMapping("/page")
    public String svgPage() {
    	return "/realdata/svgpage";
    }
    
    
}
