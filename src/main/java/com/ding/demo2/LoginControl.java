package com.ding.demo2;

import com.ding.demo2.entity.Product;
import com.ding.demo2.service.CommonService;
import com.ding.demo2.utils.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
        String name = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");
        System.out.println(name + " pwd: " + password);
        String sName = mCommonService.login(name, password);
        System.out.print(sName);
        if (sName == null) {
            return "error";
        } else {
            return "redirect:/home";
        }
    }

    @GetMapping(value = "/home")
    public ModelAndView home(ModelAndView modelAndView) {
        modelAndView.setViewName("/home");
        modelAndView.addObject("product", getProducts());
        return modelAndView;
    }

    private List<Product> getProducts() {
        return mCommonService.getProducts();
    }

    @GetMapping(value = "/error")
    public String error() {
        return "/error";
    }

    @PostMapping("/loadFile")
    public String loadFile() {
        List<Product> list = ExcelUtil.importExcel("/Users/wangwangming/Downloads/test.xlsx");
        for (int i = 0; i < list.size(); i++) {
            mCommonService.insertProduct(list.get(i));
        }
        ;
        return "redirect:list";
    }

    @PostMapping("/queryList")
    public ModelAndView queryList(HttpServletRequest httpServletRequest,ModelAndView modelAndView) {
        String name = httpServletRequest.getParameter("name");
        String no = httpServletRequest.getParameter("no");
        List<Product> productList = mCommonService.searchProduct(name, no);
        modelAndView.setViewName("/home");
        modelAndView.addObject("product", productList);
        return modelAndView;

    }

}
