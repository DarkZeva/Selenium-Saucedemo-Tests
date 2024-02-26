import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class MultipleExcelUpload {
    @Test
    public void FileUpload(){
        System.setProperty("webdriver.chrome.driver", "E:\\Chrome\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://www.dia-pen.hr/orbicon/3rdParty/rad-plus/test.html");

        int i = 0;
        while("" != Utility.ReadExcel("paths.xlsx","PathsSheet", i, 0)){
            String fileName = Utility.ReadExcel("paths.xlsx","PathsSheet", i, 0);
            WebElement file = driver.findElement(By.xpath("//*[@type='file'][@name='userfile[0]']"));
            file.sendKeys(System.getProperty("user.dir")+"/src/main/resources/"+fileName);
            WebElement submitButton = driver.findElement(By.xpath("//*[@type='submit']"));
            submitButton.click();
            WebElement uploadedFile = driver.findElement(By.xpath("//td[contains(text(),'" + fileName + "')]"));
            Assert.assertEquals(uploadedFile.getText(), fileName);
            driver.navigate().back();
            i++;
        }


    }
}
