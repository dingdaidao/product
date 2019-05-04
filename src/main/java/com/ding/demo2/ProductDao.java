package com.ding.demo2;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface ProductDao {
    @Insert("INSERT INTO product(no,name,size,count,color) VALUES(#{no}))")
    public String insert(@Param("no") String no, @Param("name") String name,@Param("size") String size,
                         @Param("count") int count,@Param("color")String color);

}
