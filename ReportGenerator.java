package com.automation.test.report;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.automation.test.report.annotation.TestReportConfig;

import junit.framework.TestCase;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

@TestReportConfig(
		reportOutputDirectory = "target/cucumber-test-automation-report", 
		jsonFiles = {"target/cucumber.json" }, 
		jenkinsBasePath = "", 
		projectName = "Cucumber-Report", 
		skippedFails = true, 
		pendingFails = false, 
		undefinedFails = true, 
		missingFails = true, 
		runWithJenkins = false, 
		parallelTesting = false)
public class CukeReportIT extends TestCase {

	private final static int DEFAULT_BUILD_NUM = 1;
	private static Logger logger = Logger.getLogger(CukeReportIT.class);

	@Before
	public void initialize() {
		logger.info("Initilise Test Report Generator...");
	}


	public void testReportGeneration() throws Throwable {
		this.testReportGeneration(DEFAULT_BUILD_NUM);
	}

	public void testReportGeneration(int buildNum) throws Throwable {
		String buildNumber = buildNum + "";
		logger.info("Start to generate test report for Build: " + buildNumber);
		
		TestReportConfiguration rptConfig = TestReportConfigurationFactory.getAnnotatedReportConfiguration(CukeReportIT.class);
		if(rptConfig==null){
			logger.warn("Tets report configuation is missing");
			return;
		}
		File reportOutputDirectory = new File(rptConfig.getReportOutputDirectory());
		List<String> jsonFiles = new ArrayList<>();
		String[] jsonSrcFiles = rptConfig.getJsonFiles();
		for(String jsonSrcFile : jsonSrcFiles){
			jsonFiles.add(jsonSrcFile);			
		}	 

		String jenkinsBasePath = rptConfig.getJenkinsBasePath();
		String projectName = rptConfig.getProjectName();
		boolean skippedFails = rptConfig.isSkippedFails();
		boolean pendingFails = rptConfig.isPendingFails();
		boolean undefinedFails = rptConfig.isUndefinedFails();
		boolean missingFails = rptConfig.isMissingFails();
		boolean runWithJenkins = rptConfig.isRunWithJenkins();
		boolean parallelTesting = rptConfig.isParallelTesting();

		Configuration configuration = new Configuration(reportOutputDirectory, projectName);
		// optionally only if you need
		configuration.setStatusFlags(skippedFails, pendingFails, undefinedFails, missingFails);
		configuration.setParallelTesting(parallelTesting);
		configuration.setJenkinsBasePath(jenkinsBasePath);
		configuration.setRunWithJenkins(runWithJenkins);
		configuration.setBuildNumber(buildNumber);

		ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
		reportBuilder.generateReports();
		logger.info("test report generation completed.");
	}

	public static void main(String[] args) throws Throwable {
		CukeReportIT tester = new CukeReportIT();
		tester.initialize();
		if (args == null || args.length < 1) {
			logger.info("No argugments passed in. Use default build number: " + DEFAULT_BUILD_NUM);
			tester.testReportGeneration();
		} else {
			logger.info("The build number you have passed in: " + args[0]);
			int buildNum = DEFAULT_BUILD_NUM;
			try {
				buildNum = Integer.parseInt(args[0]);
			} catch (NumberFormatException ne) {
				logger.warn(
						"None or wrong format of build number found. Use default build number: " + DEFAULT_BUILD_NUM);
			}
			tester.testReportGeneration(buildNum);
		}

	}

}
