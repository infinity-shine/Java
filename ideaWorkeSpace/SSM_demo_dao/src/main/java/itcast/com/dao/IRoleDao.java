package itcast.com.dao;

import itcast.com.daomain.Permission;
import itcast.com.daomain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface IRoleDao {



    @Select("select * from role where id in(select roleId from user_role where userId=#{userId})")
     List<Role> findRoleByUserId(String userId);

    @Select("select * from role")
    List<Role> findAll();


    @Select("select * from role where id in (SELECT roleId FROM user_role WHERE userId=#{roleId})")
    @Results({
            @Result(id = true,property = "id",column ="id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "itcast.com.dao.IPermissionDao.findPermissionByRoleId"))
    })
    List<Role> findById(@Param("roleId") Long roleId);


    @Insert("insert into role(roleName,roleDesc) values (#{roleName},#{roleDesc})")
    void save(Role role);

        //擦汗寻所有角色和角色拥有的权限（角色添加）
    @Select("select * from role where id=#{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "itcast.com.dao.IPermissionDao.findPermissionByRoleId"))
    })
    Role findByIds(Integer id);

        //查询角色没有的权限啊（角色添加）
    @Select("select * from permission where id not in(select permissionId from role_permission where roleId=#{id})")
    List<Permission> findOtherRoleToPermission(Integer id);


    //添加权限（角色保存）
    @Insert("insert into role_permission values(#{roleId},#{permissionId})")
    void saveRoleAndPermission(@Param("roleId") Integer id,@Param("permissionId") Integer ids);
    @Delete("delete from role where id=#{id}")
    void delete(@Param("id") Integer id);
}
