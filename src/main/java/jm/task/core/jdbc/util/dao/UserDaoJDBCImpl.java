package jm.task.core.jdbc.util.dao;
import jm.task.core.jdbc.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import jm.task.core.jdbc.util.Util;

public class UserDaoJDBCImpl extends Util implements UserDao {
    Connection connection = getConnection();
    public UserDaoJDBCImpl() {
        // должен быть пустой
    }
    public void createUsersTable() {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("CREATE TABLE users (id INT PRIMARY KEY AUTO_INCREMENT ,name VARCHAR(20) NOT NULL, " +
                            "lastName VARCHAR(20) NOT NULL, age INT NOT NULL);");
            preparedStatement.executeUpdate();
            preparedStatement.executeUpdate("AlTER TABLE users AUTO_INCREMENT = 1;");
            connection.commit();
        }
        catch(Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void dropUsersTable() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE users");
            connection.commit();
        }
        catch(Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert into users value(default, ?, ?, ?);");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
            connection.commit();
            System.out.println("User с именем " + name + " добавлен в базу данных");
        }
        catch(Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void removeUserById(long id) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("delete from users where id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        }
        catch(Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    public List<User> getAllUsers() {
        List<User> people = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setAge(resultSet.getByte("age"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                people.add(user);
            }
            System.out.println(people);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        return people;
    }

    public void cleanUsersTable() {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("delete from users");
            preparedStatement.executeUpdate();
            connection.commit();
        }
        catch(Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
