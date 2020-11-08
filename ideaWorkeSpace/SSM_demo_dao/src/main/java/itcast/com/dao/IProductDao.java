package itcast.com.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import  itcast.com.daomain.*;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductDao {

   @Select("select * from product")
   List<product>  findAll();

   @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{DepartureTime},#{productPrice},#{productDesc},#{productStatus})")
   void saveAll(product product);



   @Select("select * from product where id=#{id}")
   List<product> findById(String id) throws  Exception;
           ;
}
