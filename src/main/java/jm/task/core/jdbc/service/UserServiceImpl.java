package jm.task.core.jdbc.service;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.dao.UserDao;
import jm.task.core.jdbc.util.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.util.dao.UserDaoJDBCImpl;


import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao connector = new UserDaoHibernateImpl();

    public void createUsersTable() {
        connector.createUsersTable();
    }

    public void dropUsersTable() {
        connector.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        connector.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        connector.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return connector.getAllUsers();
    }

    public void cleanUsersTable() {
        connector.cleanUsersTable();
    }
}
