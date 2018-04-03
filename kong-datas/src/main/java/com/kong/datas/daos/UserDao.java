package com.kong.datas.daos;

import com.kong.datas.models.User;

import java.util.List;

/**
 * 用戶表操作接口
 */
public interface UserDao {
    /**
     * id 找到用户信息
     *
     * @param id
     * @return
     */
    public User findUserById(int id);

    /**
     * 用户名 找到用户信息
     *
     * @param name
     * @return
     */
    public User findUserByName(String name);

    /**
     * 查找莫一段时间内注册的用户
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public List<User> findUsersByTime(long startTime, long endTime);

    /**
     * 添加一个用户
     *
     * @param user
     * @return
     */
    public User addUser(User user);

    /**
     * 修改一个用户的信息
     *
     * @param user
     * @return
     */
    public int updateUser(User user);

    /**
     * 删除一些用户，根据user ids
     *
     * @param ids
     * @return
     */
    public int deleteUserByIds(int... ids);
}
