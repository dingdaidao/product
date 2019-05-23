package com.ding.demo2.service;

import com.ding.demo2.ProductDao;
import com.ding.demo2.UserDao;
import com.ding.demo2.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;
import org.thymeleaf.util.TextUtils;

import java.util.List;

@Service
public class CommonService{
    @Autowired
    public UserDao mUserDao;
    @Autowired
    public ProductDao productDao;

    public String login(String userName, String spwd) {
        return mUserDao.login(userName, spwd);
    }

    public void insertProduct(Product product) {
        if (productDao.queryById(product.getCommodity_sku()) != null) {
            productDao.updateByNo(product.getShop_name(), product.getStock_name(), product.getCommodity_sku(), product.getCommodity_name(),
                    product.getSize(), product.getColor(), product.getStock(), product.getDate());
        } else {
            productDao.insert(product.getShop_name(), product.getStock_name(), product.getCommodity_sku(), product.getCommodity_name(),
                    product.getSize(), product.getColor(), product.getStock(), product.getDate());
        }
    }

    public List<Product> getProducts() {
        return productDao.queryAll();
    }

    public List<Product> searchProduct(String name, String no) {
        if (StringUtils.isEmptyOrWhitespace(name)) {
            return productDao.queryAll();
        } else {
            return productDao.queryByName(name);
        }
    }

    public int delete(String shopName, String stockName, String commoditySku, String commodityName, String size, String color, String stock, String update) {
        return productDao.deleteRecord(productDao.quryId(shopName, stockName, commoditySku, commodityName, size, color, stock, update));
    }

    public int addRecord(String shopName, String stockName, String commoditySku, String commodityName, String size, String color, String stock, String update) {
        String sku=null;
        try {
            sku =productDao.quryId(shopName, stockName, commoditySku, commodityName, size, color, stock, update);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (StringUtils.isEmpty(sku)){
            productDao.insert(shopName, stockName, commoditySku, commodityName, size, color, Integer.valueOf(stock), update);
            return 1;
        }else {
            return 0;
        }
    }

    public int updateProduct(String commoditySku, int newStock, String newDate) {
        return productDao.updateStockAndDate(commoditySku,newStock,newDate);
    }
}
