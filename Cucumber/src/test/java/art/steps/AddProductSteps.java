package art.steps;

import io.cucumber.java.bg.И;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class AddProductSteps {

    private WebDriver driver;

    @И("Открытие вкладки {string} и установка таймаутов")
    public void before(String string) {
        driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "D:\\tests\\Cucumber\\src\\test\\resources\\chromedriver.exe");

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().getScriptTimeout();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://localhost:8080/food");
    }

    @И("Нажатие на кнопку {string} и проверка на появление окна добавления товара")
    public void pushBtnAdd(String string) {
        WebElement btnAdd = driver.findElement(By.xpath("//button[@data-toggle=\"modal\"]"));
        btnAdd.click();
        Assertions.assertTrue(driver.findElement(By.id("editModalLabel")).isEnabled(),
                "Не появилось окно добавления товара");
    }

    @И("Заполняется поле Наименование значением {string}")
    public void AddAndCheckName(String name) {
        WebElement addName = driver.findElement(By.xpath("//input[@id='name']"));
        addName.sendKeys(name);
        Assertions.assertEquals(name, addName.getAttribute("value"),
                "В поле наименования не установилось значение");
    }

    @И("Выбирается в поле Тип, значение {string}")
    public void CheckDropList(String type_prod) {
        WebElement selectTypeFood = driver.findElement(By.xpath("//select[@id='type']"));
        Select select = new Select(selectTypeFood);
        if (type_prod.equals("VEGETABLE")) {
            select.selectByIndex(0);
        } else {
            select.selectByIndex(1);
        }

        Assertions.assertEquals(selectTypeFood.getAttribute("value"), type_prod,
                "Не корректно выбирается пункт из выпадающего списка");
    }

    @И("Проставление галочки в поле \"Экзотический\"")
    public void CheckRadioButton(){
        WebElement radioBtnExotic = driver.findElement(By.xpath("//input[@id=\"exotic\"]"));
        radioBtnExotic.click();
        Assertions.assertTrue(radioBtnExotic.isSelected(), "Не проставилась галочка");
    }

    @И("Отображение овоща в таблице")
    public void CheckCorrectAddFirstProduct() {
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
    }

    // Не смог придумать, как сделать чтобы в одной функции, могли проверяться любые товары, поэтому разбил на 2 функции
    @И("Отображение фрукта в таблице")
    public void CheckCorrectAddSecondProduct() {
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
                        "Поменялся флаг в поле 'Экзотический'")
        );
    }

    @И("Закрытие вкладки браузера")
    public void CloseWindow() {
        driver.close();
        driver.quit();
    }

}
