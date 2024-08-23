import com.beust.ah.A;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class FirstTest {
    @Test
    void firstAddProductTest() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "D:\\tests\\first-project\\src\\main\\resources\\chromedriver.exe");

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().getScriptTimeout();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://localhost:8080/food");

        /*Нажатие на кнопку "Добавить" и проверка на появление окна добавления товара*/
        WebElement btnAdd = driver.findElement(By.xpath("//button[@data-toggle=\"modal\"]"));
        btnAdd.click();
        Assertions.assertTrue(driver.findElement(By.id("editModalLabel")).isEnabled(),
                "Не появилось окно добавления товара");

        /*Проверка на корректную установку наименования в поле*/
        WebElement addName = driver.findElement(By.xpath("//input[@id='name']"));
        addName.sendKeys("Огyrчик1!");
        Assertions.assertEquals("Огyrчик1!", addName.getAttribute("value"),
                "В поле наименования не установилось значение");

        /*Проверка на корректность установки значения в выпадающем списке*/
        WebElement selectTypeFood = driver.findElement(By.xpath("//select[@id='type']"));
        Select select = new Select(selectTypeFood);
        select.selectByIndex(0);
        Assertions.assertEquals(selectTypeFood.getAttribute("value"), "VEGETABLE",
                "Не корректно выбирается пункт из выпадающего списка");

        /*Проверка корректности проставления галочки в radio button*/
        WebElement radioBtnExotic = driver.findElement(By.xpath("//input[@id=\"exotic\"]"));
        radioBtnExotic.click();
        Assertions.assertTrue(radioBtnExotic.isSelected(), "Не проставилась галочка");

        /*Проверка на корректное добавление товара в таблицу*/
        WebElement btnSaveProduct = driver.findElement(By.xpath("//button[@id=\"save\"]"));
        btnSaveProduct.click();
        Assertions.assertAll(
                () -> Assertions.assertEquals("Огyrчик1!",
                        driver.findElement(By.xpath("//td[.='Огyrчик1!']")).getText(),
                        "Не было добавлено наименование товара"),
                () -> Assertions.assertEquals("Овощ",
                        driver.findElement(By.xpath("(//td[.='Овощ'])[last()]")).getText(),
                        "Не был добавлен тип товара "),
                () -> Assertions.assertEquals("true",
                        driver.findElement(By.xpath("(//td[.='true'])[last()]")).getText(),
                        "Не поменялся флаг в поле 'Экзотический'")
        );

        Thread.sleep(3000);
        driver.close();
    }

    @Test
    void secondAddProductTest() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "D:\\tests\\first-project\\src\\main\\resources\\chromedriver.exe");

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().getScriptTimeout();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://localhost:8080/food");

        /*Нажатие на кнопку "Добавить" и проверка на появление окна добавления товара*/
        WebElement btnAdd = driver.findElement(By.xpath("//button[@data-toggle=\"modal\"]"));
        btnAdd.click();
        Assertions.assertTrue(driver.findElement(By.id("editModalLabel")).isEnabled(),
                "Не появилось окно добавления товара");

        /*Проверка на корректную установку наименования в поле*/
        WebElement addName = driver.findElement(By.xpath("//input[@id='name']"));
        addName.sendKeys("Персик3?");
        Assertions.assertEquals("Персик3?", addName.getAttribute("value"),
                "В поле наименования не установилось значение");

        /*Проверка на корректность установки значения в выпадающем списке*/
        WebElement selectTypeFood = driver.findElement(By.xpath("//select[@id='type']"));
        Select select = new Select(selectTypeFood);
        select.selectByIndex(1);
        Assertions.assertEquals(selectTypeFood.getAttribute("value"), "FRUIT",
                "Не корректно выбирается пункт из выпадающего списка");


        /*Проверка на корректное добавление товара в таблицу*/
        WebElement btnSaveProduct = driver.findElement(By.xpath("//button[@id=\"save\"]"));
        btnSaveProduct.click();
        Assertions.assertAll(
                () -> Assertions.assertEquals("Персик3?",
                        driver.findElement(By.xpath("//td[.='Персик3?']")).getText(),
                                "Не было добавлено наименование товара"),
                () -> Assertions.assertEquals("Фрукт",
                        driver.findElement(By.xpath("(//td[.='Фрукт'])[last()]")).getText(),
                                "Не был добавлен тип товара "),
                () -> Assertions.assertEquals("false",
                        driver.findElement(By.xpath("(//td[.='false'])[last()]")).getText(),
                                "Не поменялся флаг в поле 'Экзотический'")
                );

        Thread.sleep(3000);
        driver.quit();
    }
}
