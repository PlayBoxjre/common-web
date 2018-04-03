package com.kong.datas.impls.jdbc.sql;

public class SQL {
    public static final class USER{
      public static final String findUserById = "SELECT user_id,user_name,user_password,user_add_time FROM user WHERE user_id = ?;";
      public static final String findUerByName = "SELECT user_id,user_name,user_password,user_add_time FROM user WHERE user_name = ?;";
      public static final String insertUser = "INSERT INTO user (user_name,user_password) VALUE(?,?);";
      public static final String updateUser = "UPDATE user SET user_name=?,user_password=? WHERE user_id = ?;";
      public static final String deleteUser = "DELETE FROM user WHERE user_id in (?);";
    }
}
