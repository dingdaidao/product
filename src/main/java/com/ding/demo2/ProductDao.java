package com.ding.demo2;

import com.ding.demo2.entity.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public interface ProductDao {
    @Insert("INSERT INTO product(stock_name,commodity_sku,commodity_name,size,color,stock,update)" +
            " VALUES(#{stock_name},#{sku},#{name},#{size}#{color},#{stock},#{updatetime})")
    public void insert(@Param("stock_name") String stock_name, @Param("commodity_sku") String sku,
                       @Param("size") String size, @Param("color") String color, @Param("commodity_name") String name,
                       @Param("stock") int stock, @Param("update") String updatetime);

    /**
     * @param id
     * @return
     */
    @Select("select * from product where id =#{id}")
    public Product queryById(@Param("id") int id);

    @Insert("UPDATE product set stock_name=#{stock_name},commodity_sku=#{sku},size=#{size},color=#{color},commodity_name=#{name},stock=#{stock},update=#{updatetime} where id=#{id}")
    public void updateByNo(@Param("id") int id, @Param("stock_name") String stock_name, @Param("commodity_sku") String sku, @Param("size") String size, @Param("color") String color, @Param("commodity_name") String name,
                           @Param("stock") int stock, @Param("update") String update);

    @Select("select * from product")
    public List<Product> queryAll();

    @Select("select * from product where stock_name like CONCAT('%',#{stock_name},'%') and commodity_name like CONCAT('%',#{name},'%') ")
    public List<Product> queryByNoOrName(@Param("commodity_name") String name, @Param("stock_name") String stock_name);

    /**
     * @param commodity_name 商品名
     * @return
     */
    @Select("select * from product where commodity_name like CONCAT('%',#{commodity_name},'%') ")
    public List<Product> queryByName(@Param("commodity_name") String commodity_name);
}
