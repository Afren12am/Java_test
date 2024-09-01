package art.steps;

import io.cucumber.java.bg.И;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.*;
import java.util.concurrent.TimeUnit;

public class AddProductSqlSteps {

    private ResultSet res;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;

    @И("Подключение базы данных и вывод таблицы")
    public void before() throws SQLException {
        connection = DriverManager.getConnection("jdbc:h2:tcp://localhost:9092/mem:testdb", "user", "pass");

        statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY
        );
        res = statement.executeQuery("SELECT food_id FROM food");
        res.last();
    }

    @И("Добавление товара с полями {string}, {string}, {string}")
    public void addProduct(String name, String type_prod, String exotic) throws SQLException {
        String insert = "insert into food values(?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(insert);
        preparedStatement.setInt(1, res.getInt("food_id") + 1);
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, type_prod);
        preparedStatement.setBoolean(4, Boolean.valueOf(exotic));
        preparedStatement.executeUpdate();
    }

    @И("Вывод и проверка таблицы с новым полем {string}")
    public void checkTable(String name_prod) throws SQLException {
        res = statement.executeQuery("SELECT * FROM food");
        while (res.next()) {
            int id = res.getInt("food_id");
            String name = res.getString("food_name");
            String type = res.getString("food_type");
            Boolean exotic = res.getBoolean("food_exotic");
            System.out.printf("%d %s %s %b\n", id, name, type, exotic);
        }
    }

    @И("Удаление последнего элемента {string}")
    public void DeleteProduct(String name) throws SQLException {
        statement.executeUpdate("DELETE FROM food WHERE food_id > 4");
    }
}
