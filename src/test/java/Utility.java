import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;

class Utility {
    public static WebDriver Login(String username, String password, WebDriver driver){
        System.setProperty("webdriver.chrome.driver", "E:\\Chrome\\chromedriver.exe");

        WebElement usernameBox = driver.findElement(By.id("user-name"));
        usernameBox.isDisplayed();
        usernameBox.isDisplayed();
        usernameBox.sendKeys(username);

        WebElement passwordBox = driver.findElement(By.id("password"));
        passwordBox.isEnabled();
        passwordBox.isEnabled();
        passwordBox.sendKeys(password);

        WebElement button = driver.findElement(By.id("login-button"));
        button.isDisplayed();
        button.isEnabled();
        button.click();

        return driver;
    }

    public static String ReadExcel(String fileName, String sheetName, int row, int column){
        String data = "";
        try{
            FileInputStream fis = new FileInputStream("src/main/resources/" + fileName);
            Workbook wb = WorkbookFactory.create(fis);
            Sheet s = wb.getSheet(sheetName);
            Row r = s.getRow(row);
            Cell c = r.getCell(column);
            data = c.getStringCellValue();

        }catch(Exception e){
            System.out.println("ReadExcel catch block");
            e.printStackTrace();
        }

        return data;
    }
}
