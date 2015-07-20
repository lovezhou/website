package com.jessrun.certify.vo;

import com.jessrun.common.support.spring.view.anno.ExcelColumsHead;
import com.jessrun.common.support.spring.view.anno.ExcelTitle;

@ExcelTitle(defTitleName = "加班表", titleCode = "testexceloutput.title")
public class TestExcelOutput {
	
	@ExcelColumsHead(columHeadCode = "testexceloutput.colum.name", defaultColumHeadName = "姓名",sort = 1)
	private String name;
	
	@ExcelColumsHead(columHeadCode = "testexceloutput.colum.age", defaultColumHeadName = "年龄",sort = 2)
	private int age;
	
	@ExcelColumsHead(columHeadCode = "testexceloutput.colum.nation", defaultColumHeadName = "民族",sort = 3)
	private String nation;
	
	@ExcelColumsHead(columHeadCode = "testexceloutput.colum.deptname", defaultColumHeadName = "部门名",sort = 4)
	private String deptName;
	
	@ExcelColumsHead(columHeadCode = "testexceloutput.colum.overtime", defaultColumHeadName = "加班时间",sort = 5,isTotal = true)
	private int overtime;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public int getOvertime() {
		return overtime;
	}
	public void setOvertime(int overtime) {
		this.overtime = overtime;
	}
	
	
}
