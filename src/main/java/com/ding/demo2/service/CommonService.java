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
            productDao.updateByNo(product.getId(), product.getStock_name(), product.getCommodity_sku(), product.getCommodity_name(),
                    product.getSize(), product.getColor(), product.getStock(), product.getDate());
        } else {
            productDao.insert(product.getStock_name(), product.getCommodity_sku(), product.getCommodity_name(),
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
}
