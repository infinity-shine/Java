package itcast.com.dao;

import itcast.com.daomain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {

    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{id} )")
    List<Permission> findPermissionByRoleId( Integer id);

    @Select("select * from permission")
    List<Permission> findAll();
    @Delete("delete from permission where id=#{id}")
    void delete(@Param("id") Integer id);


    //权限详情
    @Select("select * from permission where id=#{id}")
    Permission findById(@Param("id") Integer id);
}
