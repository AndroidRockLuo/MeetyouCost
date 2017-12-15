package com.meiyou.meetyoucost;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: ice
 * Date: 17/12/15 09:01.
 */

public class MeetyouCost {

    public static List<String> mLogCache = new ArrayList<>();
    public static interface onLogListener {
        public void log(String log);
    }
    public static onLogListener mOnLogListener;
    public static boolean isOpenLogCache = false;

    /**
     * 设置日志监听
     * @param listener
     */
    public static void setLogListener(onLogListener listener) {
        mOnLogListener = listener;
    }


    /**
     * 打开日志缓存
     * @param flag
     */
    public static void openLogCache(boolean flag){
        isOpenLogCache = false;
    }

    public static List<String> getLogCache() {
        return mLogCache;
    }

}
