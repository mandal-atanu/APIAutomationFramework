package com.thetestingacademy.listners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int retrycount = 0;
    private static final int maxRetryCount = 3;
    @Override
    public boolean retry(ITestResult Result) {
        if (retrycount < maxRetryCount){
            retrycount++;
            return true;
        }
        return false;
    }
}
