package com.jessrun.common.report;

import java.util.LinkedList;
import java.util.List;

public class ReportColHead {
	private Integer itemId;
	private String itemName;
	private String itemCode;
	
	private Integer parentId;
	
	private Integer index;
	
	
	private ReportColHead upHead;
	private List<ReportColHead> nextReportColHeads;
	
	private Integer rowspan = 1;
	
	private Integer colspan = 1;
	
	public void addNextReportColHead(ReportColHead next){
		if(nextReportColHeads == null){
			nextReportColHeads = new LinkedList<ReportColHead>();
		}
		nextReportColHeads.add(next);
		next.setUpHead(this);
		if(nextReportColHeads.size() > 1){
			colspan++;
			if(upHead != null){
				upHead.addColspan();
			}
			
		}else{
			rowspan++;
			if(upHead != null){
				upHead.addRowspan();
			}
			
		}
		
	}
	
	public void addColspan(){
		if(upHead != null){
			upHead.addColspan();
		}
		colspan++;
	}
	
	public void addRowspan(){
		if(upHead != null){
			upHead.addRowspan();
		}
		rowspan++;
	}
	
	public ReportColHead getUpHead() {
		return upHead;
	}

	public void setUpHead(ReportColHead upHead) {
		this.upHead = upHead;
	}

	public Integer getRowspan() {
		return rowspan;
	}

	public void setRowspan(Integer rowspan) {
		this.rowspan = rowspan;
	}

	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public List<ReportColHead> getNextReportColHeads() {
		return nextReportColHeads;
	}
	public void setNextReportColHeads(List<ReportColHead> nextReportColHeads) {
		this.nextReportColHeads = nextReportColHeads;
	}

	public Integer getColspan() {
		return colspan;
	}

	public void setColspan(Integer colspan) {
		this.colspan = colspan;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
		int count = 0;
		if(nextReportColHeads == null){
			return;
		}
		for(ReportColHead head : nextReportColHeads){
			head.setIndex(index + count);
			count += head.getColspan();
		}
	}
}
