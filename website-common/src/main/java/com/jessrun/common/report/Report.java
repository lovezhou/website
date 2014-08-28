package com.jessrun.common.report;

import java.util.LinkedList;
import java.util.List;

public class Report {
	private String reportName;
	
	private int colSize;
	
	private int rowSize;
	
	private ReportColHead[][] headRows;
	
	private List<ReportColHead> heads;
	
	private List<String> index;
	
	public void addHead(ReportColHead head){
		if(heads == null){
			heads = new LinkedList<ReportColHead>();
		}
		
		if(head.getParentId() == null){
			heads.add(head);
			return;
		}
		
		putHead(heads,head);
	}
	
	private static boolean putHead(List<ReportColHead> parentHeads,ReportColHead head){
		for(ReportColHead temp : parentHeads){
			if(temp.getItemId() == head.getParentId()){
				head.setUpHead(temp);
				temp.addNextReportColHead(head);
				return true;
			}
			if(temp.getNextReportColHeads() != null){
				if(putHead(temp.getNextReportColHeads(),head)){
					return true;
				}
			}
		}
		
		return false;
	}
	
	public void createIndex(){
		if(heads == null){
			return;
		}
		
		int count = 0;
		for(ReportColHead head : heads){
			head.setIndex(count);
			colSize += head.getColspan();
			count += head.getColspan();
			if(rowSize < head.getRowspan()){
				rowSize = head.getRowspan();
			}
		}
		
		headRows = new ReportColHead[rowSize][];
		
		getHeads(heads,headRows,0);
		count = 0;
		for(ReportColHead[] reportHeadRow : headRows){
			for(ReportColHead reportHead : reportHeadRow){
				reportHead.setRowspan(rowSize - count - reportHead.getRowspan() + 1);
			}
			count++;
		}
		
		index = getIndexKey(heads);
	}
	
	private static void getHeads(List<ReportColHead> tempheads,ReportColHead[][] headrows,int rownum){
		ReportColHead[] array = new ReportColHead[tempheads.size()];
		int count = 0;
		for(ReportColHead head : tempheads){
			
			if(head.getNextReportColHeads() != null){
				getHeads(head.getNextReportColHeads(),headrows,rownum + 1);
			}
			array[count] = head;
			count++;
		}

		if(headrows[rownum] != null){
			ReportColHead[] temp = new ReportColHead[headrows[rownum].length + array.length];
			int i = 0;
			for(; i < headrows[rownum].length; i++){
				temp[i] = headrows[rownum][i];
			}
			
			for(int j = 0 ; j < array.length ; j++,i++){
				temp[i] = array[j];
			}
			headrows[rownum] = temp;
		}else{
			headrows[rownum] = array;
		}

	}
	
	private static List<String> getIndexKey(List<ReportColHead> nodes){
		List<String> keys = new LinkedList<String>();
		if(nodes == null){
			return keys;
		}
		
		for(ReportColHead head : nodes){
			if(head.getNextReportColHeads() != null){
				keys.addAll(getIndexKey(head.getNextReportColHeads()));
			}else{
				keys.add(head.getItemCode());
			}
		}
		return keys;
	}
	

	public List<ReportColHead> getHeads() {
		return heads;
	}

	public void setHeads(List<ReportColHead> heads) {
		this.heads = heads;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public List<String> getIndex() {
		return index;
	}

	public void setIndex(List<String> index) {
		this.index = index;
	}

	public int getColSize() {
		return colSize;
	}

	public void setColSize(int colSize) {
		this.colSize = colSize;
	}

	public int getRowSize() {
		return rowSize;
	}

	public void setRowSize(int rowSize) {
		this.rowSize = rowSize;
	}

	public ReportColHead[][] getHeadRows() {
		return headRows;
	}

	public void setHeadRows(ReportColHead[][] headRows) {
		this.headRows = headRows;
	}
	
//	public String toString(){
//		
//		return null;
//	}
	
	public static void main(String[] args){
		Report r = new Report();
		ReportColHead head = new ReportColHead();
		head.setItemCode("A");
		head.setItemId(1);
		head.setItemName("列A");
		r.addHead(head);
		
		
		head = new ReportColHead();
		head.setItemCode("B");
		head.setItemId(2);
		head.setItemName("列b");
		r.addHead(head);
		
		head = new ReportColHead();
		head.setItemId(3);
		head.setItemName("列c");
		r.addHead(head);
		
		
		head = new ReportColHead();
		head.setItemId(4);
		head.setItemName("列d");
		head.setParentId(3);
		r.addHead(head);
		
		head = new ReportColHead();
		head.setItemId(5);
		head.setItemName("列e");
		head.setParentId(3);
		r.addHead(head);
		
		head = new ReportColHead();
		head.setItemId(6);
		head.setItemName("列f");
		head.setParentId(5);
		r.addHead(head);
		
		head = new ReportColHead();
		head.setItemId(7);
		head.setItemName("列g");
		head.setParentId(5);
		r.addHead(head);
		
		head = new ReportColHead();
		head.setItemId(8);
		head.setItemName("列h");
		head.setParentId(5);
		r.addHead(head);
		
		
		r.createIndex();
		
		System.out.println(" reportName:" + r.getReportName() + " reportHeadColCount:" + r.getColSize() + " reportHeadRowCount:" + r.getRowSize());
		
		int row = 0;
		for(ReportColHead[] heads : r.getHeadRows()){
			System.out.println("row:" + row );
			
			for(ReportColHead temp : heads){
				System.out.print(" [headName:" + temp.getItemName() + " headColspan:" + temp.getColspan()  + " headRowspan" + temp.getRowspan() + " headIndex:" + temp.getIndex() + "] ");
			}
			System.out.println();
			row++;
		}
	}
}
