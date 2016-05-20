package com.automation.test.report;

import org.apache.commons.lang3.builder.ToStringBuilder;


public class TestReportConfiguration implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1234567890L;

	public String reportOutputDirectory;

	public String[] jsonFiles;

	public String jenkinsBasePath;

	public String projectName;

	public boolean skippedFails;

	public boolean pendingFails;

	public boolean undefinedFails;

	public boolean missingFails;

	public boolean runWithJenkins;

	public boolean parallelTesting;

	public TestReportConfiguration() {
	}

	public String getReportOutputDirectory() {
		return reportOutputDirectory;
	}

	public void setReportOutputDirectory(String reportOutputDirectory) {
		this.reportOutputDirectory = reportOutputDirectory;
	}

	public String[] getJsonFiles() {
		return jsonFiles;
	}

	public void setJsonFiles(String[] jsonFiles) {
		this.jsonFiles = jsonFiles;
	}

	public String getJenkinsBasePath() {
		return jenkinsBasePath;
	}

	public void setJenkinsBasePath(String jenkinsBasePath) {
		this.jenkinsBasePath = jenkinsBasePath;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public boolean isSkippedFails() {
		return skippedFails;
	}

	public void setSkippedFails(boolean skippedFails) {
		this.skippedFails = skippedFails;
	}

	public boolean isPendingFails() {
		return pendingFails;
	}

	public void setPendingFails(boolean pendingFails) {
		this.pendingFails = pendingFails;
	}

	public boolean isUndefinedFails() {
		return undefinedFails;
	}

	public void setUndefinedFails(boolean undefinedFails) {
		this.undefinedFails = undefinedFails;
	}

	public boolean isMissingFails() {
		return missingFails;
	}

	public void setMissingFails(boolean missingFails) {
		this.missingFails = missingFails;
	}

	public boolean isRunWithJenkins() {
		return runWithJenkins;
	}

	public void setRunWithJenkins(boolean runWithJenkins) {
		this.runWithJenkins = runWithJenkins;
	}

	public boolean isParallelTesting() {
		return parallelTesting;
	}

	public void setParallelTesting(boolean parallelTesting) {
		this.parallelTesting = parallelTesting;
	}
	
	public String toString() {		
		return ToStringBuilder.reflectionToString(this);
	}

}
