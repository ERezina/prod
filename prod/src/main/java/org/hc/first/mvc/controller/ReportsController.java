package org.hc.first.mvc.controller;

import javax.servlet.http.HttpSession;

import org.hc.exceptions.mvc.js.JSReportTable;
import org.hc.reports.ReportsTableGetter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="api/reports")
public class ReportsController {
	private ReportsTableGetter reportsTableGetter;
	
	@RequestMapping(value="/getData", method = RequestMethod.POST)
	public @ResponseBody Object getPersonalTable(@RequestBody JSReportTable js,HttpSession session){
		return reportsTableGetter.getDataForTable(js);
	}
}
