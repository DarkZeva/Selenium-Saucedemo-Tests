import Log4j2.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SeleniumDev {


    @Test
    public void selenium_full_form_test() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "E:\\Chrome\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);

        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        WebElement textInput = driver.findElement(By.xpath("//label[contains(text(),'Text input')]//following-sibling::input"));
        textInput.sendKeys("Random text");
        Assert.assertEquals(textInput.getAttribute("value"), "Random text");

        WebElement password = driver.findElement(By.xpath("//label[contains(text(), 'Password')]//following-sibling::input"));
        password.sendKeys("1234");
        Assert.assertEquals(password.getAttribute("value"), "1234");

        WebElement textArea = driver.findElement(By.name("my-textarea"));
        textArea.sendKeys("Random text");
        Assert.assertEquals(textArea.getAttribute("value"), "Random text");

        WebElement disabledInput = driver.findElement(By.xpath("//*[@type='text'][@name='my-disabled']"));
        Assert.assertEquals(disabledInput.isEnabled(), false);

        WebElement readonlyInput = driver.findElement(By.name("my-readonly"));
        readonlyInput.sendKeys("1234");
        Assert.assertEquals(readonlyInput.getAttribute("value"), "Readonly input");

        Select dropdown = new Select(driver.findElement(By.name("my-select")));
        dropdown.selectByIndex(1);
        String actualDropdown = dropdown.getFirstSelectedOption().getText();
        Assert.assertEquals(actualDropdown, "One");

        WebElement fileInput = driver.findElement(By.name("my-file"));
        fileInput.sendKeys(System.getProperty("user.dir")+"/src/main/resources/saucedemo.xlsx");

        WebElement checkedCheckbox = driver.findElement(By.id("my-check-1"));
        WebElement defaultCheckbox = driver.findElement(By.id("my-check-2"));
        defaultCheckbox.click();
        Assert.assertEquals(defaultCheckbox.isSelected(), true);
        Assert.assertEquals(checkedCheckbox.isSelected(), true);

        WebElement listInput = driver.findElement(By.name("my-datalist"));
        WebElement option = driver.findElement(By.xpath("//datalist[@id='my-options']/option[@value='San Francisco']"));
        listInput.sendKeys(option.getAttribute("value"));
        Assert.assertEquals(listInput.getAttribute("value"), "San Francisco");

        WebElement checkedRadio = driver.findElement(By.id("my-radio-1"));
        WebElement defaultRadio = driver.findElement(By.id("my-radio-2"));
        defaultRadio.click();
        Assert.assertEquals(defaultRadio.isSelected(), true);
        Assert.assertEquals(checkedRadio.isSelected(), false);

        WebElement colorPicker = driver.findElement(By.name("my-colors"));
        colorPicker.sendKeys("#f56fff");
        Assert.assertEquals(colorPicker.getAttribute("value"), "#f56fff");


        WebElement datePicker = driver.findElement(By.xpath("//input[@name='my-date']"));
        datePicker.click();
        WebElement prevButton = driver.findElement(By.xpath("//th[@class='prev']"));
        prevButton.click();
        WebElement day = driver.findElement(By.xpath("//td[contains(text(), '14')]"));
        day.click();

        WebElement range = driver.findElement(By.name("my-range"));
        range.sendKeys(Keys.ARROW_RIGHT);
        range.sendKeys(Keys.ARROW_RIGHT);
        Assert.assertEquals(range.getAttribute("value"), "7");


    }
}
