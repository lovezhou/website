package com.jessrun.common.support.spring.view;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.beans.PropertyDescriptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.SheetSettings;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.jessrun.common.support.spring.view.anno.ExcelColumsHead;
import com.jessrun.common.support.spring.view.anno.ExcelTitle;
import com.jessrun.common.web.WebApplicationContextUtils;

@SuppressWarnings("rawtypes")
public class ExcelView extends AbstractView {
	
	private Class entityClass;

	private ExcelBean excelBean;
	
	public ExcelView(){
		
	}
	
	public ExcelView(Class entityClass){
		this.entityClass = entityClass;
		excelBean = new ExcelBean();
		@SuppressWarnings("unchecked")
		ExcelTitle et = (ExcelTitle)entityClass.getAnnotation(ExcelTitle.class);
		String title = null;
		String titleCode = null;
		if(et != null){
			title = et.defTitleName();
			titleCode = et.titleCode();
			if(title == null){
				title = "";
			}
			if(titleCode == null){
				titleCode = "";
			}
		}
		excelBean.setTitle(title);
		excelBean.setTitleCode(titleCode);
		
		//System.out.println(title);
		
		ExcelColumsHead ech = null;
		Map<Integer,ExcelRowBean> tempMap = new HashMap<Integer,ExcelRowBean>();
		ExcelRowBean erb = null;

		Integer index = 1;
		for(Field f : entityClass.getDeclaredFields()){
			ech = f.getAnnotation(ExcelColumsHead.class);
			if(ech == null){
				continue;
			}
			//System.out.println(ech.columHeadName() + ech.sort() + ech.isTotal());
			erb = new ExcelRowBean();
			erb.setColumName(ech.defaultColumHeadName());
			erb.setColumCode(ech.columHeadCode());
			erb.setFieldname(f.getName());
			erb.setTotal(ech.isTotal());
			erb.setSort(ech.sort());
			
			tempMap.put(Integer.parseInt(index.toString() + ech.sort()), erb);
			index++;
		}
		
		
		List<ExcelRowBean> listTitles = new LinkedList<ExcelRowBean>();
		
		Object[] sortArray = tempMap.keySet().toArray();
		if(sortArray != null && sortArray.length > 0){
			Arrays.sort(sortArray);
			for(Object str : sortArray){
				listTitles.add(tempMap.get(str));
			}
		}
		
		excelBean.setListTitles(listTitles);
	}
	
	public void loadData(List dataList){
		excelBean.setDataList(dataList);
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> arg0,HttpServletRequest arg1, HttpServletResponse response) throws Exception {
		response.reset();
		response.setContentType(contentType);
		
		ApplicationContext applicationContext = WebApplicationContextUtils.getContext(arg1.getSession().getServletContext());
		Locale locale = RequestContextUtils.getLocaleResolver(arg1).resolveLocale(arg1);
		
		String fileName = applicationContext.getMessage(excelBean.getTitleCode(),null,excelBean.getTitle(),locale) + suffix ;
		
		response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("gbk"), "ISO8859-1") + "\"");
		
		OutputStream out = null;
		try{
			out = response.getOutputStream();
			writeExcel(out,applicationContext,locale);
			response.flushBuffer();
		}catch(Exception e){
			
		}finally{
			if(out != null){
				try{
					out.close();
				}catch(Exception e){
				}
			}
		}
	}
	
	private void writeExcel(OutputStream out,ApplicationContext applicationContext,Locale locale) throws Exception{
		try {
			//创建工作簿
			//WritableWorkbook workbook = Workbook.createWorkbook(new File("d:/abc.xls"));
			WritableWorkbook workbook = Workbook.createWorkbook(out);
			
			//创建工作表
			WritableSheet sheet = workbook.createSheet("Sheet1", 0);
			SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);
			
			//设置单元格字体
			WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);
			WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD);
			
			// 用于标题居中
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(false); // 文字是否换行
			
			// 用于标题居中
			WritableCellFormat wcf_center_thin = new WritableCellFormat(NormalFont);
			wcf_center_thin.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center_thin.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center_thin.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center_thin.setWrap(false); // 文字是否换行
			
			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.LEFT); // 文字水平对齐
			wcf_left.setWrap(false); // 文字是否换行
			
			int lineNum = 0;
			
			if(excelBean != null){
				List<ExcelRowBean> list = excelBean.getListTitles();
				if(list != null && list.size() > 0){
					
					if(!excelBean.getTitleCode().equals("") && !excelBean.getTitle().equals("")){
						//写标题
						sheet.addCell(new Label(0, lineNum, applicationContext.getMessage(excelBean.getTitleCode(),null,excelBean.getTitle(),locale), wcf_center));
						sheet.mergeCells(0, lineNum, list.size(), lineNum);
						lineNum++;
					}
					
					
					//写表头
					sheet.addCell(new Label(0, lineNum, applicationContext.getMessage(DEF_EXCELVIEW_INDEXCOLUMHEAD_CODE,null,DEF_EXCELVIEW_INDEXCOLUMHEAD_NAME,locale), wcf_center));
					int colIndex = 1;
					for(ExcelRowBean erb : list){
						sheet.addCell(new Label(colIndex, lineNum, applicationContext.getMessage(erb.getColumCode(),null,erb.getColumName(),locale), wcf_center));
						colIndex++;
					}
					lineNum++;
					
					List dataList = excelBean.getDataList();
					Map<Integer,Double> totalMap = new HashMap<Integer,Double>();
					//写数据
					if(dataList != null){
						PropertyDescriptor pd = null;
						Method rM = null;
						Object inputObj = null;
						Double totalVal = null;
						int rowCount = 1;
						for(Object o : dataList){
							sheet.addCell(new Label(0, lineNum,String.valueOf(rowCount), wcf_center_thin));
							colIndex = 1;
							for(ExcelRowBean erb : list){
								
								pd = new PropertyDescriptor(erb.getFieldname(), entityClass);
								rM = pd.getReadMethod();
								inputObj = rM.invoke(o);
								sheet.addCell(new Label(colIndex, lineNum,inputObj == null ? "" : inputObj.toString(), wcf_left));
								
								if(erb.isTotal()){
									totalVal = totalMap.get(colIndex);
									if(totalVal == null){
										totalVal = 0.00;
									}
									try{
										totalVal += inputObj == null ? 0 : Long.parseLong(inputObj.toString());
									}catch(Exception e){
									}
									totalMap.put(colIndex, totalVal);
								}
								
								colIndex++;
							}
							rowCount++;
							lineNum++;
						}
					}
					
					// 用于合计中间不合计的单元格
					WritableCellFormat wcf_bottom = new WritableCellFormat(NormalFont);
					wcf_bottom.setBorder(Border.BOTTOM, BorderLineStyle.THIN); // 线条
					wcf_bottom.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
					wcf_bottom.setAlignment(Alignment.LEFT); // 文字水平对齐
					wcf_bottom.setWrap(false); // 文字是否换行
					
					
					
					sheet.addCell(new Label(0, lineNum,applicationContext.getMessage(DEF_EXCELVIEW_TOTALCOLUMHEAD_CODE,null,DEF_EXCELVIEW_TOTALCOLUMHEAD_NAME,locale), wcf_center));
					for(int i = 1; i <= list.size() ; i++){
						if(totalMap.get(i) != null){
							sheet.addCell(new Label(i, lineNum,totalMap.get(i).toString(), wcf_left));
						}else{
							if(i + 1 > list.size() ){
								// 用于合计结尾
								WritableCellFormat wcf_end = new WritableCellFormat(NormalFont);
								wcf_end.setBorder(Border.BOTTOM, BorderLineStyle.THIN); // 线条
								wcf_end.setBorder(Border.RIGHT, BorderLineStyle.THIN); // 线条
								wcf_end.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
								wcf_end.setAlignment(Alignment.LEFT); // 文字水平对齐
								wcf_end.setWrap(false); // 文字是否换行
								sheet.addCell(new Label(i, lineNum,"", wcf_end));
							}else{
								sheet.addCell(new Label(i, lineNum,"", wcf_bottom));
							}
						}
						
					}
				}
			}
			
			
			//将以上缓存中的内容写到EXCEL文件中
			workbook.write();
			//关闭文件
			workbook.close();
			
		}catch(Exception e){
			throw e;
		}
	}
	
	private static final String contentType = "application/vnd.ms-excel;charset=UTF-8";
	private static final String suffix = ".xls";
	
	private static final String DEF_EXCELVIEW_INDEXCOLUMHEAD_CODE = "excelview.indexcolum.name";
	private static final String DEF_EXCELVIEW_INDEXCOLUMHEAD_NAME = "序号";
	
	private static final String DEF_EXCELVIEW_TOTALCOLUMHEAD_CODE = "excelview.totalcolumhead.name";
	private static final String DEF_EXCELVIEW_TOTALCOLUMHEAD_NAME = "合计";
	
	
//	public static void main(String[] args){
//		
//		ExcelView ev = new ExcelView(TestEntity.class);
//		List<TestEntity> list = new LinkedList<TestEntity>();
//		TestEntity te = new TestEntity();
//		te.setI(1);
//		te.setB("a");
//		te.setO("o");
//		list.add(te);
//		
//		te = new TestEntity();
//		te.setI(2);
//		te.setB("b");
//		te.setO("o2");
//		list.add(te);
//		
//		ev.loadData(list);
//		
//		try {
//			ev.writeExcel();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	class ExcelBean{
		private String title;
		private String titleCode;
		private List<ExcelRowBean> listTitles;
		private List dataList;
		
		public ExcelBean(){
			
		}
		
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		
		public List<ExcelRowBean> getListTitles() {
			return listTitles;
		}
		public void setListTitles(List<ExcelRowBean> listTitles) {
			this.listTitles = listTitles;
		}
		public List getDataList() {
			return dataList;
		}
		public void setDataList(List dataList) {
			this.dataList = dataList;
		}

		public String getTitleCode() {
			return titleCode;
		}

		public void setTitleCode(String titleCode) {
			this.titleCode = titleCode;
		}
		
	}
	
	class ExcelRowBean{
		private String columName;
		private String columCode;
		private String fieldname;
		private boolean isTotal;
		private int sort;
		public String getColumName() {
			return columName;
		}
		public void setColumName(String columName) {
			this.columName = columName;
		}
		public String getFieldname() {
			return fieldname;
		}
		public void setFieldname(String fieldname) {
			this.fieldname = fieldname;
		}
		public boolean isTotal() {
			return isTotal;
		}
		public void setTotal(boolean isTotal) {
			this.isTotal = isTotal;
		}
		public int getSort() {
			return sort;
		}
		public void setSort(int sort) {
			this.sort = sort;
		}
		public String getColumCode() {
			return columCode;
		}
		public void setColumCode(String columCode) {
			this.columCode = columCode;
		}
		
		
	}
}

//@ExcelTitle("ABC电脑风扇电机")
//class TestEntity{
//	@ExcelColumsHead(columHeadName = "第一列",isTotal = true,sort = 100)
//	private int i;
//	@ExcelColumsHead(columHeadName = "第二列")
//	private String b;
//	@ExcelColumsHead(columHeadName = "第三列")
//	private Object o;
//	public int getI() {
//		return i;
//	}
//	public void setI(int i) {
//		this.i = i;
//	}
//	public String getB() {
//		return b;
//	}
//	public void setB(String b) {
//		this.b = b;
//	}
//	public Object getO() {
//		return o;
//	}
//	public void setO(Object o) {
//		this.o = o;
//	}
//	
//}



