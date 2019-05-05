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
    @Insert("INSERT INTO product(no,name,size,count,updatetime) VALUES(#{no},#{name},#{size},#{count},#{updatetime})")
    public void insert(@Param("no") String no, @Param("name") String name, @Param("size") String size,
                       @Param("count") int count, @Param("updatetime") Date updatetime);

    /**
     * @param no
     * @return
     */
    @Select("select * from product where no =#{no}")
    public Product queryByNo(@Param("no") String no);

    @Insert("UPDATE product set name=#{name},size=#{size},count=#{count},updatetime=#{updatetime} where no=#{no}")
    public void updateByNo(@Param("no") String no, @Param("name") String name, @Param("size") String size,
                           @Param("count") int count, @Param("updatetime") Date updatetime);

    @Select("select * from product")
    public List<Product> queryAll();

    @Select("select * from product where no like CONCAT('%',#{no},'%') and name like CONCAT('%',#{name},'%') ")
    public List<Product> queryByNoOrName(@Param("name") String name,@Param("no") String no);
}
