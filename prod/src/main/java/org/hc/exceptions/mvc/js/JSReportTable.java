package org.hc.exceptions.mvc.js;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class JSReportTable {
	public String type;
	public String name;
	public Long num;
	
	public String toString(){
		return "Type "+ type +
				" Name "+ name+
				"num "+ num;
	}
}
