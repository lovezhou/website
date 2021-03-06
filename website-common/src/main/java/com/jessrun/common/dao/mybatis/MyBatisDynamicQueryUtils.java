package com.jessrun.common.dao.mybatis;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jessrun.platform.util.StringUtils;

/**
 * description
 * 
 * @author luoyifan
 * @version 1.0,2010-9-15
 */
public class MyBatisDynamicQueryUtils {

    private static final Pattern MATCH_FILED_PATTERN = Pattern.compile("\\{[^\\}]*\\}");

    /**
     * mybatis 动态sql解析方法 根据 parameters 的参数与dynamicString中{}内的查询条件进行匹配<br/>
     * 对于匹配不到的就删除该{}以及{}内的数据 <br/>
     * 最终结果为parameters中的参数与{}内的命名参数一一对应<br/>
     * 对于'?'将替换以参数里面的实际值替换掉'?'后的参数名称。<br/>
     * eg. dynamicString = eg: from Customer c where 1=1 {and c.name=:name} {and c.age >:age}{and c.address =:address}
     * {order by ?orderByCondition};
     * parameters.put("name",name);parameters.put("age",age);parameters.put("orderByCondition","age"); return : from
     * Customer c where 1=1 and c.name=#{name} and c.age >#{age} order by age;
     * 
     * @author luoyifan
     * @param dynamicString
     * @param parameters
     * @return
     */

    public static String parser(String dynamicString, Map<String, Object> parameters) {
        if (StringUtils.isNullOrEmpty(dynamicString)) return null;
        dynamicString = dynamicString.trim();
        Matcher mathcer = MATCH_FILED_PATTERN.matcher(dynamicString);
        while (mathcer.find()) {
            String mathcerStr = mathcer.group();
            String[] split = mathcerStr.substring(1, mathcerStr.length() - 1).split(":|\\?");
            if (split == null || split.length != 2) continue;
            String filed = split[1];
            String replaceRegex = convertReplaceRegex(mathcerStr);
            if (parameters.get(filed) == null) {
                dynamicString = dynamicString.replaceFirst(replaceRegex, "");
                continue;
            }

            dynamicString = dynamicString.replaceFirst(replaceRegex,
                                                       convertReplaceString(mathcerStr, parameters.get(filed)));
        }
        return dynamicString.trim().replaceAll("\\s+", " ");
    }

    private static String convertReplaceRegex(String mathcerStr) {
        mathcerStr = "\\{" + mathcerStr.substring(1, mathcerStr.length() - 1) + "\\}";
        if (mathcerStr.indexOf("?") != -1) {
            mathcerStr = mathcerStr.replaceAll("\\?", "\\\\?");
        }
        return mathcerStr;
    }

    private static String convertReplaceString(String mathcerStr, Object paramValue) {
        mathcerStr = mathcerStr.substring(1, mathcerStr.length());
        if (mathcerStr.indexOf("?") != -1) {
            mathcerStr = mathcerStr.replaceAll("\\?.*", paramValue.toString());
        } else {
            mathcerStr = mathcerStr.replaceAll(":", "#{");
        }
        return mathcerStr;

    }

}
