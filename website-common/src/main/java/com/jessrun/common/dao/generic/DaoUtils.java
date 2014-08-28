package com.jessrun.common.dao.generic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DaoUtils {

    private static final Pattern FROM_PATTERN    = Pattern.compile("(^|\\s)from\\s");
    private static final Pattern GROUPBY_PATTERN = Pattern.compile("\\sgroup\\s+by\\s");
    private static final Pattern ORDERBY_PATTERN = Pattern.compile("\\sorder\\s+by\\s");
    private static final Pattern HAVING_PATTERN  = Pattern.compile("\\shaving(\\s|\\()");

    /**
     * 判断hql中是否存在 having 子句
     * 
     * @author xujianguo
     * @return
     */
    public static final boolean isExistHavingHql(String hql) {
        return HAVING_PATTERN.matcher(hql).find();
    }

    public static final String countHql(String hql) {

        hql = hql.trim();
        String tempHql = hql.toLowerCase();
        int hqlLength = tempHql.length();
        Matcher fromMatcher = FROM_PATTERN.matcher(tempHql);
        if (fromMatcher.find()) {
            int fromIndex = fromMatcher.end();
            Matcher groupByMathcer = GROUPBY_PATTERN.matcher(tempHql);
            int groupByIndex = -1;
            int groupByEnd = -1;
            if (groupByMathcer.find()) {
                groupByIndex = groupByMathcer.start();
                groupByEnd = groupByMathcer.end();
            }
            Matcher orderByMathcer = ORDERBY_PATTERN.matcher(tempHql);
            int orderByIndex = orderByMathcer.find() ? orderByMathcer.start() : -1;
            Matcher havingMathcer = HAVING_PATTERN.matcher(tempHql);
            int havingIndex = havingMathcer.find() ? havingMathcer.start() : -1;

            int endIndex = groupByIndex;
            if (orderByIndex != -1) {
                if (endIndex != -1) endIndex = endIndex < orderByIndex ? endIndex : orderByIndex;
                else endIndex = orderByIndex;
            }
            if (havingIndex != -1) {
                if (endIndex != -1) endIndex = endIndex < havingIndex ? endIndex : havingIndex;
                else endIndex = havingIndex;
            }
            endIndex = endIndex == -1 ? hqlLength : endIndex;
            int groupByFieldIndex = -1;
            if (groupByIndex != -1) {
                if (orderByIndex > groupByIndex) {
                    groupByFieldIndex = orderByIndex;
                }
                if (havingIndex > groupByIndex) {
                    if (groupByFieldIndex != -1) groupByFieldIndex = groupByFieldIndex < havingIndex ? groupByFieldIndex : havingIndex;
                    else groupByFieldIndex = havingIndex;
                }
                groupByFieldIndex = groupByFieldIndex == -1 ? hqlLength : groupByFieldIndex;
            }
            String selectHql = groupByIndex == -1 ? "select count(*)" : "select count(distinct "
                                                                        + hql.substring(groupByEnd, groupByFieldIndex)
                                                                        + ")";
            if (havingIndex == -1) return selectHql + " from " + hql.substring(fromIndex, endIndex);
            else {
                return selectHql + " from " + hql.substring(fromIndex, orderByIndex == -1 ? hqlLength : orderByIndex);
            }
        }
        return null;
    }
}
