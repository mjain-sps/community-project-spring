package com.community.client.community;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CommunityAddChromeDriverTests {

    @Value("${local.server.port}")
    private int port;
@Test
    public void testUserInputsAddCommunityPage() throws InterruptedException{
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\c21111089\\OneDrive - Cardiff University\\Pictures\\chromedriver.exe");
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--remote-debugging-port=42227");
    options.addArguments("--headless");
    WebDriver webDriver = new ChromeDriver(options);

    webDriver.get("http://localhost:" + Integer.toString(8081) + "/add-community");
    assertTrue(webDriver.findElement(By.id("add-community_CTA")).getText().contains("ADD COMMUNITY"));
}
}
