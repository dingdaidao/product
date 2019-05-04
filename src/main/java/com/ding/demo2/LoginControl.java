package com.ding.demo2;

import com.ding.demo2.entity.Product;
import com.ding.demo2.service.CommonService;
import com.ding.demo2.utils.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class LoginControl {
    @Autowired
    public CommonService mCommonService;

    @GetMapping(value = "/")
    public String login() {
        return "/login";
    }

    @PostMapping("/loginVerify")
    public String loginVerify(HttpServletRequest httpServletRequest, HttpSession session) {
       String name=httpServletRequest.getParameter("username");
        String password=httpServletRequest.getParameter("password");
        System.out.println(name + " pwd: " + password);
        String sName =mCommonService.login(name,password);
        System.out.print(sName);
        if (sName == null) {
            return "error";
        } else {
            return "redirect:/home";
        }
    }
    @GetMapping(value = "/home")
    public String home() {
        return "/home";
    }
    @GetMapping(value = "/error")
    public String error() {
        return "/error";
    }

    @PostMapping("/loadFile")
    public void loadFile(){
        List<Object[]> list =  ExcelUtil.importExcel("C:\\Users\\Administrator\\Downloads\\zaiko.xlsx");
        for (int i = 0; i < list.size(); i++) {
            Product product = new Product();
            product.setCount((Integer) list.get(i)[0]);
            product.setName((String) list.get(i)[1]);
            product.setModel((String) list.get(i)[2]);
            product.setNo((String) list.get(i)[3]);
            System.out.println(product.toString());
        };
    }
}
