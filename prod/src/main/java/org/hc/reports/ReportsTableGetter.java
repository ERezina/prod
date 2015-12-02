package org.hc.reports;

import org.hc.exceptions.mvc.js.JSReportTable;
import org.hc.manufacturer.ManufacturerTableGeter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ReportsTableGetter {
	
	@Autowired
	private ManufacturerTableGeter manufacturerTableGeter;
	
	
	public JSReportTable getDataForTable(JSReportTable filter){
		if(filter == null){
			return new JSReportTable();
		}
		if (StringUtils.isEmpty(filter.type)){
			return new JSReportTable();
		}
		if (filter.type.equals("test"))		return manufacturerTableGeter.getTable(filter);
		return new JSReportTable();
	}
}
