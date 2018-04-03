package com.kong.datas.impls.jdbc;

import com.kong.datas.daos.UserDao;
import com.kong.datas.impls.jdbc.sql.SQL;
import com.kong.datas.models.User;
import com.kong.support.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.IntFunction;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public User findUserById(int id) {
        List<User> query = jdbcTemplate.query(SQL.USER.findUserById, new Object[]{id}, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                return getUser(resultSet);
            }
        });
        return (query.size() == 0) ? null : query.get(0);
    }

    @Override
    public User findUserByName(String name) {
        List<User> query = jdbcTemplate.query(SQL.USER.findUerByName, new Object[]{name}, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                return getUser(resultSet);
            }
        });
        return (query.size() == 0) ? null : query.get(0);
    }

    @Override
    public List<User> findUsersByTime(long startTime, long endTime) {
        String start = DateUtils.unixTime2FormatString(startTime, "yyyy-MM-dd HH:mm:ss");
        String end = DateUtils.unixTime2FormatString(endTime, "yyyy-MM-dd HH:mm:ss");

        return null;
    }

    @Override
    public User addUser(User user) {
        final String sql = SQL.USER.insertUser;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int update = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"user_id", "user_add_time"});
                preparedStatement.setString(1, user.getUserName());
                preparedStatement.setString(2, user.getUserPassword());
                return preparedStatement;
            }
        }, keyHolder);
        List<Map<String, Object>> keyList = keyHolder.getKeyList();
        System.out.println(keyList.toString());
        user.setUserId(update);
        return user;
    }

    @Override
    public int updateUser(User user) {
        return 0;
    }

    @Override
    public int deleteUserByIds(int... ids) {
        Arrays.stream(ids).mapToObj(new IntFunction<Object>() {
            @Override
            public Object apply(int value) {
                return value;
            }
        });

        return 0;
    }

    private User getUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getInt("user_id"));
        user.setUserName(resultSet.getString("user_name"));
        user.setUserPassword(resultSet.getString("user_password"));
        user.setAddTime(resultSet.getDate("user_add_time").toString());
        return user;
    }
}
