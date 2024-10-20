package co.wedevx.digitalbank.automation.ui.runners;


import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("ui/features/checking")
@ConfigurationParameter(key=GLUE_PROPERTY_NAME, value="co.wedevx.digitalbank.automation.ui.steps")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, junit:target/cucumberReports/junit.xml, json:target/cucumberReports/report.json, html:target/cucumberReports/report.html")
@IncludeTags("Test")
 public class UiRegressionRunner {
    // No additional code needed here
}
