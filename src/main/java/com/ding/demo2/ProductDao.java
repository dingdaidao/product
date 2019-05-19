package com.ding.demo2;

import com.ding.demo2.entity.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public interface ProductDao {
    @Insert("INSERT INTO product_info(shop_name,stock_name,commodity_sku,commodity_name,size,color,stock,date)" +
            " VALUES(#{shop_name},#{stock_name},#{commodity_sku},#{commodity_name},#{size},#{color},#{stock},#{updatetime})")
    public void insert(@Param("shop_name") String shop_name, @Param("stock_name") String stock_name, @Param("commodity_sku") String sku,
                       @Param("commodity_name") String name, @Param("size") String size, @Param("color") String color,
                       @Param("stock") int stock, @Param("updatetime") String updatetime);

    /**
     * @param commodity_sku
     * @return
     */
    @Select("select * from product_info where commodity_sku =#{commodity_sku}")
    public Product queryById(@Param("commodity_sku") String commodity_sku);

    @Insert("UPDATE product_info set shop_name=#{shop_name},stock_name=#{stock_name},commodity_sku=#{sku},size=#{size},color=#{color},commodity_name=#{name},stock=#{stock},update=#{updatetime} where id=#{id}")
    public void updateByNo(@Param("shop_name") String shop_name, @Param("stock_name") String stock_name, @Param("commodity_sku") String sku, @Param("size") String size, @Param("color") String color, @Param("commodity_name") String name,
                           @Param("stock") int stock, @Param("update") String update);

    @Select("select * from product_info")
    public List<Product> queryAll();

    @Select("select * from product_info where stock_name like CONCAT('%',#{stock_name},'%') and commodity_name like CONCAT('%',#{name},'%') ")
    public List<Product> queryByNoOrName(@Param("commodity_name") String name, @Param("stock_name") String stock_name);

    /**
     * @param commodity_name 商品名
     * @return
     */
    @Select("select * from product_info where commodity_name like CONCAT('%',#{commodity_name},'%') ")
    public List<Product> queryByName(@Param("commodity_name") String commodity_name);

    @Select("select commodity_sku from product_info where shop_name =#{shopName} and stock_name =#{stockName} and commodity_sku =#{commoditySku} and " +
            "commodity_name =#{commodityName} and size =#{size} and color =#{color} and stock =#{stock} and date =#{date}")
    public String quryId(@Param("shopName") String shopName, @Param("stockName") String stockName, @Param("commoditySku") String commoditySku,
                      @Param("commodityName") String commodityName, @Param("size") String size, @Param("color") String color, @Param("stock") String stock,
                      @Param("date") String update);

    @Delete("delete from product_info where commodity_sku=#{commodity_sku} ")
    public int deleteRecord(@Param("commodity_sku") String commodity_sku);
    @Insert("UPDATE product_info set stock=#{stock},date=#{update} where commodity_sku=#{commodity_sku}")
    public int updateStockAndDate(@Param("commodity_sku") String sku, @Param("stock") int stock, @Param("update") String update);
}
