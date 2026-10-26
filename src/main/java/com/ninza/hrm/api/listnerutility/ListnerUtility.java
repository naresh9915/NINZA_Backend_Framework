package com.ninza.hrm.api.listnerutility;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.ninza.hrm.api.baseClass.BaseApiClass;
import com.ninza.hrm.api.genericutility.JavaUtility;


public class ListnerUtility implements ISuiteListener,ITestListener {

	ExtentSparkReporter spark;
	ExtentReports report;
	ExtentTest test;
	JavaUtility jutil = new JavaUtility();
	
	
	@Override
	public void onStart(ISuite suite) {
		Reporter.log("onStart",true);
		 //step 1:create object for ExtentSparkReportor class and pass the file path
		spark = new ExtentSparkReporter("./reports/ReportFile.html "+jutil.getSystemDateYYYYDDMM());
		spark.config().setTheme(Theme.DARK);
//		spark.config().setTimeStampFormat(
//		        "dd-MM-yyyy HH:mm:ss");
		//Step 2:create object for extent reports
		report = new ExtentReports();
		//Step 3:attach spark to report
		report.attachReporter(spark);
		//Step 4:create test and store in local variable of return type ExtentTest
		test = report.createTest(" Report for RestAssured API Testing");
		 // Add system information
        report.setSystemInfo("OS", "Windows 11");
        report.setSystemInfo("Browser", "Chrome");
        report.setSystemInfo("Tester", "Naresh");
        report.setSystemInfo("Environment", "QA");

		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Reporter.log("onTestSuccess",true);
		//Step 5:log message
		test.log(Status.PASS,"API test case pass");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		Reporter.log("onTestFailure",true);
		
	
		//Step 5:log message
		test.log(Status.FAIL,"API test case fail");
	
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		Reporter.log("onTestSkipped",true);
		test.log(Status.SKIP,"test case skipped");
	}

	

	@Override
	public void onFinish(ISuite suite) {
		Reporter.log("onFinish",true);
		//step 6:save the report
				report.flush();
	}
}
