package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import jm.task.core.jdbc.util.Util;

import javax.swing.event.MouseInputListener;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }
    public void createUsersTable() {
        String sql = "CREATE TABLE if not exists users (id INT PRIMARY KEY AUTO_INCREMENT ,name VARCHAR(20) NOT NULL, " +
                "lastName VARCHAR(20) NOT NULL, age INT NOT NULL);";

        try (Connection connection = Util.getConnection(); PreparedStatement preparedStatement =
                connection.prepareStatement(sql)) {

            preparedStatement.executeUpdate();
            preparedStatement.executeUpdate("AlTER TABLE users AUTO_INCREMENT = 1;");

            try {
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE if exists users");

            try {
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "insert into users values(default, ?, ?, ?)";
        try (Connection connection = Util.getConnection(); PreparedStatement preparedStatement =
                connection.prepareStatement(sql)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();

            try {
                connection.commit();
                System.out.println("User с именем " + name + " добавлен в базу данных");
            } catch (SQLException e) {
                connection.rollback();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "delete from users where id = ?";
        try (Connection connection = Util.getConnection(); PreparedStatement preparedStatement =
                connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

            try {
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> people = new ArrayList<>();
        try ( Connection connection = Util.getConnection();
              Statement statement = connection.createStatement();
              ResultSet resultSet =
                      statement.executeQuery("select * from users")) {

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setAge(resultSet.getByte("age"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                people.add(user);
            }
            System.out.println(people);

            try {
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return people;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection(); PreparedStatement preparedStatement =
                connection.prepareStatement("delete from users")) {

            preparedStatement.executeUpdate();
            connection.commit();

            try {
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
