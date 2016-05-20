package com.automation.test.report;

import java.lang.reflect.AnnotatedElement;

import org.apache.log4j.Logger;

import com.automation.test.report.annotation.TestReportConfig;

public class TestReportConfigurationFactory {

	private static Logger logger = Logger.getLogger(TestReportConfigurationFactory.class);

	public static synchronized TestReportConfiguration getAnnotatedReportConfiguration(Class clazz) {
		TestReportConfiguration config = getAnnotatedServiceConfig(clazz);
		if (config == null) {
			config = getAnnotatedServiceConfig(clazz.getDeclaredConstructors());
		}
		logger.debug("Test report configuration loaded: " + config);
		return config;
	}

	private static TestReportConfiguration getAnnotatedServiceConfig(AnnotatedElement elm) {

		if (elm == null || !elm.isAnnotationPresent(TestReportConfig.class)) {
			return null;
		}

		TestReportConfig annoConfig = elm.getAnnotation(TestReportConfig.class);
		TestReportConfiguration rptConfig = new TestReportConfiguration();

		String outputDir = annoConfig.reportOutputDirectory();
		if (outputDir != null && !outputDir.equals("unassigned")) {
			rptConfig.setReportOutputDirectory(outputDir);
		}

		String[] jsonFiles = annoConfig.jsonFiles();
		if (jsonFiles != null && jsonFiles.length > 0) {
			rptConfig.setJsonFiles(jsonFiles);
		}

		String jenkinsBasePath = annoConfig.jenkinsBasePath();
		if (jenkinsBasePath != null && !jenkinsBasePath.equals("unassigned")) {
			rptConfig.setJenkinsBasePath(jenkinsBasePath);
		}

		String projectName = annoConfig.projectName();

		if (projectName != null && !projectName.equals("unassigned")) {
			rptConfig.setProjectName(projectName);
		}

		rptConfig.setSkippedFails(annoConfig.skippedFails());

		rptConfig.setPendingFails(annoConfig.pendingFails());

		rptConfig.setUndefinedFails(annoConfig.undefinedFails());

		rptConfig.setMissingFails(annoConfig.missingFails());

		rptConfig.setRunWithJenkins(annoConfig.runWithJenkins());

		rptConfig.setParallelTesting(annoConfig.parallelTesting());

		return rptConfig;

	}

	private static TestReportConfiguration getAnnotatedServiceConfig(AnnotatedElement[] elms) {
		TestReportConfiguration config = null;
		for (AnnotatedElement elm : elms) {
			config = getAnnotatedServiceConfig(elm);
			if (config != null)
				break;
		}
		return config;
	}

}
