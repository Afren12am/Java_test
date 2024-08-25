import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class AppTest {

    @Test
    public void FirstAddProductTest() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost:9092/mem:testdb", "user", "pass");

        Statement statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY
        );
        ResultSet res = statement.executeQuery("SELECT food_id FROM food");
        res.last();

        /*Добавление товара*/
        String insert = "insert into food values(?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insert);
        preparedStatement.setInt(1, res.getInt("food_id") + 1);
        preparedStatement.setString(2, "аПЕlсин!.");
        preparedStatement.setString(3, "FRUIT");
        preparedStatement.setBoolean(4, true);
        preparedStatement.executeUpdate();

        /*Вывод таблицы, для проверки коректности добавления товара*/
        ResultSet resultSet = statement.executeQuery("SELECT * FROM food");
        while (resultSet.next()) {
            int id = resultSet.getInt("food_id");
            String name = resultSet.getString("food_name");
            String type = resultSet.getString("food_type");
            Boolean exotic = resultSet.getBoolean("food_exotic");
            System.out.printf("%d %s %s %b\n", id, name, type, exotic);
        }

        /*Удаление последнего элемента, чтобы не засорялась таблица
        statement.executeUpdate("DELETE FROM food WHERE food_id > 4");*/

        connection.close();
    }

    @Test
    public void SecondAddProductTest() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost:9092/mem:testdb", "user", "pass");

        Statement statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY
        );
        ResultSet res = statement.executeQuery("SELECT food_id FROM food");
        res.last();

        /*Добавление товара*/
        String insert = "insert into food values(?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insert);
        preparedStatement.setInt(1, res.getInt("food_id") + 1);
        preparedStatement.setString(2, "МоРkОВЬ-?");
        preparedStatement.setString(3, "VEGETABLE");
        preparedStatement.setBoolean(4, false);
        preparedStatement.executeUpdate();

        /*Вывод таблицы, для проверки коректности добавления товара*/
        ResultSet resultSet = statement.executeQuery("SELECT * FROM food");
        while (resultSet.next()) {
            int id = resultSet.getInt("food_id");
            String name = resultSet.getString("food_name");
            String type = resultSet.getString("food_type");
            Boolean exotic = resultSet.getBoolean("food_exotic");
            System.out.printf("%d %s %s %b\n", id, name, type, exotic);
        }

        /*Удаление последнего элемента, чтобы не засорялась таблица
        statement.executeUpdate("DELETE FROM food WHERE food_id > 4");*/

        connection.close();
    }
}
