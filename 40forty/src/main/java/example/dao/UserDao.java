package example.dao;

import example.models.UserAccount;

import java.util.List;

public interface UserDao {

    void createUser(UserAccount user);

    List<UserAccount> findAllUsers();

    UserAccount findUserById(int id);

    UserAccount findUser(UserAccount user);

    UserAccount findUserByUsername(String username);

    UserAccount findUserByEmail(String email);

    void deleteUser(UserAccount user);

    void updateUser(UserAccount updatedUser);
}
