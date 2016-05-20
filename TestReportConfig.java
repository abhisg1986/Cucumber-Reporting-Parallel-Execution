package com.automation.test.report.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ TYPE, CONSTRUCTOR })
public @interface TestReportConfig {

	String reportOutputDirectory() default "target/cucumber-test-automation-report";

	String[] jsonFiles() default {"target/cucumber.json"};	

	String jenkinsBasePath() default "";

	String projectName() default "Cucumber Test Report";

	boolean skippedFails() default true;

	boolean pendingFails() default false;

	boolean undefinedFails() default true;

	boolean missingFails() default true;

	boolean runWithJenkins() default false;

	boolean parallelTesting() default false;

}
