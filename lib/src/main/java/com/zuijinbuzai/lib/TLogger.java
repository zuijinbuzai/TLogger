package com.zuijinbuzai.lib;

import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by zuijinbuzai on 2017/2/22.
 * 用于记时
 */
public class TLogger {

    private static boolean debug = false;

    private String mLabel;
    private ArrayList<Long> mSplits;
    private ArrayList<String> mSplitLabels;
    private ArrayList<String> mPrefixLables;
    private int mMaxPreLength;  //max length the pre message

    public static void debug(boolean value) {
        debug = value;
    }

    public TLogger() {
    }

    public void begin(String lable) {
        if (!debug) {
            return ;
        }
        mLabel = lable;
        if (mSplits == null) {
            mSplits = new ArrayList<>();
            mSplitLabels = new ArrayList<>();
            mPrefixLables = new ArrayList<>();
        } else {
            mSplits.clear();
            mSplitLabels.clear();
            mPrefixLables.clear();
        }
        addSplit(null);
    }

    public void addSplit(String splitLabel) {
        if (!debug) {
            return ;
        }

        long now = SystemClock.elapsedRealtime();
        mSplits.add(now);
        mSplitLabels.add(splitLabel);
        StackTraceElement st;
        if (splitLabel == null) {
            st = new Throwable().getStackTrace()[2];
        } else {
            st = new Throwable().getStackTrace()[1];
        }
        formatPrefix(st);
    }

    public void dumpToLog() {
        if (!debug) {
            return ;
        }
        formatPrefix(new Throwable().getStackTrace()[1]);

        String message = String.format("begin ----------------------------------------------------");
        printLineMessage(mPrefixLables.get(0), message);

        final long first = mSplits.get(0);
        long now = first;
        for (int i = 1; i < mSplits.size(); i++) {
            now = mSplits.get(i);
            final String splitLabel = mSplitLabels.get(i);
            final long prev = mSplits.get(i - 1);
            message = String.format("      %6d ms, %s", now - prev, splitLabel);
            printLineMessage(mPrefixLables.get(i), message);
        }
        message = String.format("end,  %6d ms ------------------------------------------", now - first);
        printLineMessage(mPrefixLables.get(mPrefixLables.size() - 1), message);
    }

    private void formatPrefix(StackTraceElement st) {
        String fullClassName = st.getClassName();
        String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
        String methodName = st.getMethodName();
        String lineNumber = String.valueOf(st.getLineNumber());
        String msg =  String.format("at %s.%s(%s:%s)", className, methodName, st.getFileName(), lineNumber);
        if (msg.length() > mMaxPreLength) {
            mMaxPreLength = msg.length();
        }
        mPrefixLables.add(msg);
    }

    private void printLineMessage(String prefix, String message) {
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        for (int i = prefix.length(); i < mMaxPreLength; ++i) {
            sb.append(" ");
        }
        if (!TextUtils.isEmpty(mLabel)) {
            sb.append(mLabel);
            sb.append(": ");
        } else {
            sb.append(" ");
        }
        sb.append(message);
        Log.i("TLogger", sb.toString());
    }
}