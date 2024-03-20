package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import library.Jdbc;
import model.User;

public class UserDAO {

    public void insert(User user) {
        String insertQuery = "INSERT INTO [User] (UserID, UserName, [password], Email, Reader, Verificated) VALUES (?, ?, ?, ?, ?, ?)";
        Jdbc.executeUpdate(insertQuery,
                user.getUserID(),
                user.getUserName(),
                user.getPassword(),
                user.getEmail(),
                user.getReader(),
                user.getVerificated());
    }

    public void delete(User user) {
        String deleteQuery = "DELETE FROM [User] WHERE UserID = ?";
        Jdbc.executeUpdate(deleteQuery, user.getUserID());
    }

    public void update(User user) {
        String updateQuery = "UPDATE [User] SET UserName = ?, [password] = ?, Email = ?, Reader = ?, Verificated = ? WHERE UserID = ?";
        Jdbc.executeUpdate(updateQuery,
                user.getUserName(),
                user.getPassword(),
                user.getEmail(),
                user.getReader(),
                user.getVerificated(),
                user.getUserID());
    }

    public void updateByUsername(User user) {
        //hàm châu phát thêm
        String updateQuery = "UPDATE [User] SET [password] = ?, Email = ? WHERE username = ?";
        Jdbc.executeUpdate(updateQuery,
                user.getPassword(),
                user.getEmail(),
                user.getUserName());
    }

    public ArrayList<User> search(String val) {
        String searchQuery = "SELECT * FROM [User] WHERE UserName LIKE ? OR Email LIKE ?";
        return select(searchQuery, "%" + val + "%", "%" + val + "%");
    }

    public ArrayList<User> select() {
        String selectQuery = "SELECT * FROM [User]";
        return select(selectQuery);
    }

    public User selectByID(String id) {
        String selectQuery = "SELECT * FROM [User] WHERE userID = ?";
        ArrayList<User> users = select(selectQuery, id);
        return !users.isEmpty() ? users.get(0) : null;

    }
   
    
    public User selectByUsername(String username) {
        //hàm châu phát thêm
        String select = "select * from [user] where username = ?";
        ArrayList<User> users = select(select, username);
        return !users.isEmpty() ? users.get(0) : null;
    }
    public User selectByEmail(String email) {
        //hàm châu phát thêm
        String select = "select * from [user] where email = ?";
        ArrayList<User> users = select(select, email);
        return !users.isEmpty() ? users.get(0) : null;
    }

    private ArrayList<User> select(String sql, Object... args) {
        ArrayList<User> userList = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = Jdbc.executeQuery(sql, args);
                while (rs.next()) {
                    User user = readFromRS(rs);
                    userList.add(user);
                }
            } finally {
                if (rs != null) {
                    rs.getStatement().getConnection().close();
                }
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw new RuntimeException(e);
        }
        return userList;
    }

    private User readFromRS(ResultSet resultSet) throws SQLException {
        User user = new User(
                resultSet.getString("UserID"),
                resultSet.getString("UserName"),
                resultSet.getString("password"),
                resultSet.getString("Email"),
                resultSet.getBoolean("Reader"),
                resultSet.getBoolean("Verificated")
        );
        return user;
    }

}
