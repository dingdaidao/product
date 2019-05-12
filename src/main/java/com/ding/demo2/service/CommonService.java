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
public class CommonService {
    @Autowired
    public UserDao mUserDao;
    @Autowired
    public ProductDao productDao;

    public String login(String userName, String spwd) {
        return mUserDao.login(userName, spwd);
    }

    public void insertProduct(Product product) {
        if (productDao.queryById(product.getId()) != null) {
            productDao.updateByNo(product.getId(), product.getShop_name(), product.getStock_name(), product.getCommodity_sku(), product.getCommodity_name(),
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
        int i=0;
        try {
           i =productDao.quryId(shopName, stockName, commoditySku, commodityName, size, color, stock, update);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (i > 0){
            return 0;
        }else {
            productDao.insert(shopName, stockName, commoditySku, commodityName, size, color, Integer.valueOf(stock), update);
            return 1;
        }
    }
}
