package com.example.appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

public class PageView {
    private AppiumDriver<MobileElement> driver;

    @AndroidFindBy(id = "edit")
    @iOSXCUITFindBy(iOSNsPredicate = "name == 'IntegerA'")
    private  MobileElement textField;

    public PageView(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public PageView setTextField(String text) {
        textField.sendKeys(text);
        return this;
    }

    public  String getTextField() {
        return textField.getText();
    }
}
