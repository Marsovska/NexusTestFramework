package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
    private static ExtentReports extent;

    public static ExtentReports getExtentReports () {
        if (extent == null) {
            ExtentSparkReporter sparkReporter =
                    new ExtentSparkReporter("test-output/ExtentReport.html");

            sparkReporter.config().setDocumentTitle("Nexus Test Framework Report");
            sparkReporter.config().setReportName("Automation Test Results");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            extent.setSystemInfo("Project", "NexusTestFramework");
            extent.setSystemInfo("Testers", "Margarita and Juan");
            extent.setSystemInfo("Environment", "QA");
        }
        return extent;
    }
}
