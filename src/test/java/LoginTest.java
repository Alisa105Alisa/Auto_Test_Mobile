import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class LoginTest {
    @Test
    public void CheckEmptyEmail() throws MalformedURLException, InterruptedException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //вставляем из Appium Inspector
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Pixel");
        capabilities.setCapability("platformVersion", "10");
        capabilities.setCapability("udid", "emulator-5554");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("app", "/Users/alfia/Downloads/Android-NativeDemoApp-0.2.1.apk");

        //      Устанавливаем и открываем приложение.
        // URL оборачиваем в try - catch или прокидываем throw

        MobileDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        MobileElement loginMenuButton = (MobileElement) driver.findElementByAccessibilityId("Login");
        loginMenuButton.click();
        Thread.sleep(2000);

// это можно скопировать из Appium Inspector, send keys
        MobileElement passwordInput = (MobileElement) driver.findElementByAccessibilityId("input-password");
        passwordInput.sendKeys("Qwerty");
        Thread.sleep(2000);
        MobileElement loginButton  = (MobileElement) driver.findElementByXPath("//android.view.ViewGroup[@content-desk=\"button-LOGIN\"]/android.widget.FrameLayout/android.view.ViewGroup");
        loginButton.click();
        Thread.sleep(2000);
        MobileElement errorEmailMessage = (MobileElement) driver.findElementByXPath("//android.widget.ScrollView[@content-desc=\"Login-screen\"]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[4]/android.widget.TextView[1]");
        Assert.assertEquals(errorEmailMessage.getText(), "Please enter a valid email address");

        driver.quit();
    }
}
