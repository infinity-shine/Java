package itcast.com.dao;


import com.sun.javaws.security.AppContextUtil;
import itcast.com.daomain.Role;
import itcast.com.daomain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserDao {




    @Select("select * from user where username=#{name}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "itcast.com.dao.IRoleDao.findRoleByUserId"))
    })
    public UserInfo findAllByName(String  name);

         @Select("select * from user")
         List<UserInfo> finAll();

                //登陆时用到的查找
                @Select("select * from user where id=#{id}")
                @Results({
                        @Result(id = true, property = "id", column = "id"),
                        @Result(property = "username", column = "username"),
                        @Result(property = "email", column = "email"),
                        @Result(property = "password", column = "password"),
                        @Result(property = "phoneNum", column = "phoneNum"),
                        @Result(property = "status", column = "status"),
                        @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "itcast.com.dao.IRoleDao.findById"))
                })
                UserInfo findById(Long id);


         //查询没有找到的角色
            @Select("select * from role where id not in(select roleId from user_role where userId=#{id} )")
                List<Role> findByOtherRole(Long id);


    @Insert("insert into user_role(userId,roleId) values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId")Integer userId, @Param("roleId") Integer roleId);

            @Insert("insert into user(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
            void save(UserInfo userInfo);
}
