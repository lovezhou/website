package com.jessrun.common.support.spring.view;

import java.beans.PropertyDescriptor;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.jessrun.common.support.spring.view.ExcelView.ExcelBean;
import com.jessrun.common.web.WebApplicationContextUtils;
import com.jessrun.platform.util.reflect.ReflectUtils;

public class DynamicExcelView extends AbstractView {

    public static String GRID_ITEM_DATATYPE_DATE     = "date";

    public static String GRID_ITEM_DATATYPE_DATETIME = "datetime";

    public static String GRID_ITEM_DATATYPE_NUMBER   = "number";
    
    public static String GRID_ITEM_DATATYPE_SUM   = "sum";

    private ExcelBean    excelBean;
    
    private Map confCodeMap;

    public DynamicExcelView(List confGridList, List dataList, String fileName){
        excelBean = new ExcelBean();
        excelBean.setDataList(dataList);
        excelBean.setTitle(fileName);
        //组装codemap
        this.confCodeMap = createCodeMap(confGridList);
        List<ExcelRowBean> listTitles = new LinkedList<ExcelRowBean>();
        try {
            for (int i = 0; i < confGridList.size(); i++) {
                Object obj = confGridList.get(i);
                listTitles.add(getRowBean(obj));
            }
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        excelBean.setListTitles(listTitles);
    }

    public void loadData(List dataList) {
        excelBean.setDataList(dataList);
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> arg0, HttpServletRequest arg1,
                                           HttpServletResponse response) throws Exception {
        response.reset();
        response.setContentType(contentType);

        ApplicationContext applicationContext = WebApplicationContextUtils.getContext(arg1.getSession().getServletContext());
        Locale locale = RequestContextUtils.getLocaleResolver(arg1).resolveLocale(arg1);
        String fileName = excelBean.getTitle() + suffix;
        response.setHeader("Content-Disposition", "attachment; filename=\""
                                                  + new String(fileName.getBytes("gbk"), "ISO8859-1") + "\"");

        OutputStream out = null;
        try {
            out = response.getOutputStream();
            writeExcel(out, applicationContext, locale);
            response.flushBuffer();
        } catch (Exception e) {

        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                }
            }
        }
    }

    private void writeExcel(OutputStream out, ApplicationContext applicationContext, Locale locale) throws Exception {
        try {
            // 创建工作簿
            // WritableWorkbook workbook = Workbook.createWorkbook(new File("d:/abc.xls"));
            WritableWorkbook workbook = Workbook.createWorkbook(out);

            // 创建工作表
            WritableSheet sheet = workbook.createSheet("Sheet1", 0);
            SheetSettings sheetset = sheet.getSettings();
            sheetset.setProtected(false);

            // 设置单元格字体
            WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);
            WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);

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

            if (excelBean != null) {
                List<ExcelRowBean> list = excelBean.getListTitles();
                if (list != null && list.size() > 0) {
                    if (!excelBean.getTitle().equals("")) {
                        // 写标题
                        sheet.addCell(new Label(0, lineNum, excelBean.getTitle(), wcf_center));
                        sheet.mergeCells(0, lineNum, list.size(), lineNum);
                        lineNum++;
                    }

                    // 写表头
                    sheet.addCell(new Label(0, lineNum, DEF_EXCELVIEW_INDEXCOLUMHEAD_NAME, wcf_center));
                    int colIndex = 1;
                    for (ExcelRowBean erb : list) {
                        sheet.addCell(new Label(colIndex, lineNum, erb.getColumName(), wcf_center));
                        colIndex++;
                    }
                    lineNum++;

                    List dataList = excelBean.getDataList();
                    Map<Integer, Double> totalMap = new HashMap<Integer, Double>();

                    // 写数据
                    if (dataList != null) {
                        PropertyDescriptor pd = null;
                        Object inputObj = null;
                        Double totalVal = null;
                        int rowCount = 1;
                        for (Object o : dataList) {
                            sheet.addCell(new Label(0, lineNum, String.valueOf(rowCount), wcf_center_thin));
                            colIndex = 1;
                            for (ExcelRowBean erb : list) {
                                 inputObj = this.getVal(o, erb.getColumCode(),erb.getDataType());
                                sheet.addCell(new Label(colIndex, lineNum, inputObj == null ? "" : inputObj.toString(),
                                                        wcf_left));

                                if (erb.isTotal()) {
                                    totalVal = totalMap.get(colIndex);
                                    if (totalVal == null) {
                                        totalVal = 0.00;
                                    }
                                    try {
                                        totalVal += inputObj == null ? 0 : Long.parseLong(inputObj.toString());
                                    } catch (Exception e) {
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

                    sheet.addCell(new Label(0, lineNum, DEF_EXCELVIEW_TOTALCOLUMHEAD_NAME, wcf_center));
                    for (int i = 1; i <= list.size(); i++) {
                        if (totalMap.get(i) != null) {
                            sheet.addCell(new Label(i, lineNum, totalMap.get(i).toString(), wcf_left));
                        } else {
                            if (i + 1 > list.size()) {
                                // 用于合计结尾
                                WritableCellFormat wcf_end = new WritableCellFormat(NormalFont);
                                wcf_end.setBorder(Border.BOTTOM, BorderLineStyle.THIN); // 线条
                                wcf_end.setBorder(Border.RIGHT, BorderLineStyle.THIN); // 线条
                                wcf_end.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
                                wcf_end.setAlignment(Alignment.LEFT); // 文字水平对齐
                                wcf_end.setWrap(false); // 文字是否换行
                                sheet.addCell(new Label(i, lineNum, "", wcf_end));
                            } else {
                                sheet.addCell(new Label(i, lineNum, "", wcf_bottom));
                            }
                        }

                    }
                }
            }

            // 将以上缓存中的内容写到EXCEL文件中
            workbook.write();
            // 关闭文件
            workbook.close();

        } catch (Exception e) {
            throw e;
        }
    }

    private static final String contentType                       = "application/vnd.ms-excel;charset=UTF-8";
    private static final String suffix                            = ".xls";

    private static final String DEF_EXCELVIEW_INDEXCOLUMHEAD_NAME = "序号";

    private static final String DEF_EXCELVIEW_TOTALCOLUMHEAD_NAME = "合计";


    class ExcelBean {

        private String             title;
        private String             titleCode;
        private List<ExcelRowBean> listTitles;
        private List               dataList;

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

    class ExcelRowBean {

        private String  columName;
        private String  columCode;
        private String  fieldname;
        private String dataType;
        private boolean isTotal;
        private int     sort;
        
        public String getDataType() {
            return dataType;
        }
        
        public void setDataType(String dataType) {
            this.dataType = dataType;
        }

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

    // // 获取表头
    // private String getTitle(Object object) throws SecurityException, IllegalArgumentException, NoSuchFieldException,
    // IllegalAccessException {
    // Object aliasNameObj = ReflectUtils.getValueByFieldName(object, "aliasName");
    // String titleShow = "";
    // if (aliasNameObj != null && !"".equals(String.valueOf(aliasNameObj))) {
    // titleShow = String.valueOf(aliasNameObj);
    // } else {
    // titleShow = String.valueOf(ReflectUtils.getValueByFieldName(object, "itemName"));
    // }
    // return titleShow;
    // }

    // 获取列值
    private ExcelRowBean getRowBean(Object obj) throws SecurityException, IllegalArgumentException,
                                               NoSuchFieldException, IllegalAccessException {
        ExcelRowBean excelRowBean = new ExcelRowBean();
        try {
            // 获取列名
            Object aliasNameObj = ReflectUtils.getValueByFieldName(obj, "aliasName");
            String titleShow = "";
            if (aliasNameObj != null && !"".equals(String.valueOf(aliasNameObj))) {
                titleShow = String.valueOf(aliasNameObj);
            } else {
                titleShow = String.valueOf(ReflectUtils.getValueByFieldName(obj, "itemName"));
            }
            excelRowBean.setColumName(titleShow);
            String vcode = String.valueOf(ReflectUtils.getValueByFieldName(obj, "itemVCode"));
            String dataType = String.valueOf(ReflectUtils.getValueByFieldName(obj, "dataType"));
            // Object objVal = ReflectUtils.getValueByFieldName(dataObj,vcode);
            if (dataType.equals(GRID_ITEM_DATATYPE_NUMBER)) {// 数字
                excelRowBean.setTotal(true);
            }
            excelRowBean.setColumCode(vcode);
            excelRowBean.setDataType(dataType);
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return excelRowBean;
    }

    private Object getVal(Object dataObj, String vcode, String dataType) {
        Object obj = null;
        try {
            if (dataType.equals(GRID_ITEM_DATATYPE_DATE)) {// 日期类型
                obj = ReflectUtils.getValueByFieldName(dataObj, vcode);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                if (obj != null) {
                    obj = dateFormat.format(obj);
                }
            } else if (dataType.equals(GRID_ITEM_DATATYPE_DATETIME)) {// 时间类型
                obj = ReflectUtils.getValueByFieldName(dataObj, vcode);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                if (obj != null) {
                    obj = dateFormat.format(obj);
                }
            } else if (dataType.equals(GRID_ITEM_DATATYPE_NUMBER)) {// 数字
                obj = ReflectUtils.getValueByFieldName(dataObj, vcode);
                if (obj == null) {
                    obj = 0;
                }
            }else if(dataType.equals(GRID_ITEM_DATATYPE_SUM)){//合计
              Method rM = dataObj.getClass().getMethod(vcode+"Sum", Map.class);
              obj = rM.invoke(dataObj,this.confCodeMap);
              if (obj == null || String.valueOf(obj).trim().equals("")) {
                  obj = 0;
              }
            }else{
                obj = ReflectUtils.getValueByFieldName(dataObj, vcode);
            }
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return obj;
    }
    
    private Map createCodeMap(List confGridList){
        Map confCodeMap = new HashMap();
        try {
            for (int i = 0; i < confGridList.size(); i++) {
                Object object = confGridList.get(i);
                String vcode = String.valueOf(ReflectUtils.getValueByFieldName(object, "itemVCode"));
                confCodeMap.put(vcode, object);
            }
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return confCodeMap;
    }
}
