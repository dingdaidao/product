package com.ding.demo2.service;

import com.ding.demo2.ProductDao;
import com.ding.demo2.UserDao;
import com.ding.demo2.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
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
        if (productDao.queryByNo(product.getNo()) != null) {
            productDao.updateByNo(product.getNo(), product.getName(), product.getSize(), product.getCount(), product.getUpdatetime());
        } else {
            productDao.insert(product.getNo(), product.getName(), product.getSize(), product.getCount(), product.getUpdatetime());
        }
    }

    public List<Product> getProducts() {
        return productDao.queryAll();
    }

    public List<Product> searchProduct(String name, String no) {
        if (name.isEmpty()) {

        } else {

        }
        return productDao.queryByNoOrName(name, no);
    }
}
