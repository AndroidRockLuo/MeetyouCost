package com.meiyou.meetyoucost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CostLog {

    public static Map<String, Long> sStartTime = new HashMap<>();
    public static Map<String, Long> sEndTime = new HashMap<>();

    /**
     * 设置开始时间，内部使用
     * @param methodName
     * @param time
     */
    public static void setStartTime(String methodName, long time) {
        sStartTime.put(methodName, time);
    }
    /**
     * 设置结束时间，内部使用
     * @param methodName
     * @param time
     */
    public static void setEndTime(String methodName, long time) {
        sEndTime.put(methodName, time);
    }

    /**
     * 获取某方法耗时结果，内部使用
     * @param methodName
     * @return
     */
    public static String getCostTime(String methodName) {
        long start = sStartTime.get(methodName);
        long end = sEndTime.get(methodName);

        String log = "Usopp MeetyouCost Method:==> " + methodName + " ==>Cost:" + Long.valueOf(end - start) / (1000 * 1000) + " ms";
        if (MeetyouCost.mOnLogListener != null) {
            MeetyouCost.mOnLogListener.log(log);
        }
        if(MeetyouCost.isOpenLogCache)
            MeetyouCost.mLogCache.add(log);
        return log;
    }

}
