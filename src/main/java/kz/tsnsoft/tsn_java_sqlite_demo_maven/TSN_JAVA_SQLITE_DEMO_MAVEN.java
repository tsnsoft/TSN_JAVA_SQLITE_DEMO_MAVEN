package kz.tsnsoft.tsn_java_sqlite_demo_maven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TSN_JAVA_SQLITE_DEMO_MAVEN {

    public static void main(String[] args) {
        try (
                // Создание соединения с базой данных
                Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db"); Statement statement = connection.createStatement();) {

            statement.setQueryTimeout(30);  // Устанавливаем таймаут на 30 секунд

            statement.executeUpdate("drop table if exists person"); // Удаляем таблицу person, если она существует
            statement.executeUpdate("create table person (id integer, name string)"); // Создаем таблицу person
            statement.executeUpdate("insert into person values(1, 'Сергей')"); // Добавляем запись в таблицу person
            statement.executeUpdate("insert into person values(2, 'Neo')"); // Добавляем запись в таблицу person
            ResultSet rs = statement.executeQuery("select * from person"); // Выполняем запрос к таблице person
            while (rs.next()) { // Перебираем все записи
                System.out.println("name = " + rs.getString("name")); // Выводим значение поля name
                System.out.println("id = " + rs.getInt("id")); // Выводим значение поля id
            }
        } catch (SQLException e) {  // Обрабатываем исключение
            e.printStackTrace(System.err); // Выводим информацию об ошибке
        }
    }
}
