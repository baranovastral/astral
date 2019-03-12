package ru.rt.astral.repository;

import java.sql.ResultSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.rt.astral.model.User;

@Repository(value = "jdbcUserDAO")
public class JDBCUserDAO extends AbstractUserDAO{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void createUser(User user) {
        jdbcTemplate.execute(""
                + "insert into users"
                + "(login,password) "
                + "values ("
                + "'"
                + user.getLogin()
                + "'"
                + ","
                + "'"
                + user.getPassword()
                + "'"
                + ");");
    }

    @Override
    public User loadUserByUsername(String login) {
        List<User> users =  jdbcTemplate.query(
                ""
                        + "select * "
                        + "from users where login = '"
                        + login
                        + "';", 
                (ResultSet rs, int rowNum) -> {
                    User user = new User();
                    user.setId(rs.getLong("id"));
                    user.setLogin(rs.getString("login"));
                    user.setPassword(rs.getString("password"));
                    return user;
        });
        if (users.isEmpty())
            return null;
        return users.get(0);
    }
    
}
