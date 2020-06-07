package com.example.appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import java.net.MalformedURLException;

import static org.testng.Assert.assertEquals;

public class SampleAppTest {
    AppiumDriverLocalService server;
    AppiumDriver<MobileElement> driver;

    @BeforeClass
    public void setUp() {
        String platform = System.getenv("APPIUM_DRIVER");
        platform = platform == null? "ANDROID" : platform.toUpperCase();

        if (platform.equals("ANDROID")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platform", "Android");
            capabilities.setCapability("udid", "RF8M3232YBV");
            capabilities.setCapability("app", "/Users/tetianachmykhal/Downloads/ApiDemos-debug.apk");

            server = new AppiumServiceBuilder().usingAnyFreePort().build();
            server.start();

            driver = new AndroidDriver<MobileElement>(server, capabilities);
            ((AndroidDriver<MobileElement>) driver).startActivity(new Activity("io.appium.android.apis", ".view.TextFields"));
        } else {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platform", "iOS");
            capabilities.setCapability("udid", "7257BA88-7CC6-4B60-A008-A1778DE5162D");
            capabilities.setCapability("deviceName", "iPhone 8 (13.5)");
            capabilities.setCapability("automationName", "XCUITest");
            capabilities.setCapability("app", "/Users/tetianachmykhal/Downloads/TestApp.app.zip");

            server = new AppiumServiceBuilder().usingAnyFreePort().build();
            server.start();

            driver = new IOSDriver<MobileElement>(server, capabilities);
        }
    }

    @Test
    public void testFieldTest() throws MalformedURLException {
        PageView view = new PageView(driver);
        view.setTextField("test");
        assertEquals(view.getTextField(),"test", "Text is wrong");
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
        server.stop();
    }
}


