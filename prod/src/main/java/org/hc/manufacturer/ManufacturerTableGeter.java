package org.hc.manufacturer;

import org.hc.exceptions.mvc.js.JSReportTable;
import org.springframework.stereotype.Repository;

@Repository
public class ManufacturerTableGeter {
	public JSReportTable getTable(JSReportTable filter){
		JSReportTable njr = new JSReportTable();
		njr.name = "namename";
		njr.num = (long) 123;
		return njr;
	}
}
