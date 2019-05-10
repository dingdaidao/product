package com.ding.demo2;

import com.ding.demo2.entity.Layui;
import com.ding.demo2.entity.Product;
import com.ding.demo2.service.CommonService;
import com.ding.demo2.utils.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.management.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.LayerUI;
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
        String name = httpServletRequest.getParameter("Name");
        String password = httpServletRequest.getParameter("Password");
        System.out.println(name + " pwd: " + password);
        String sName = mCommonService.login(name, password);
        System.out.print(sName);
        if (sName == null) {
            return "error";
        } else {
            return "redirect:/backstage";
        }
    }

    @GetMapping(value = "/backstage")
    public String home() {
        return "/backstage";
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
    @ResponseBody
    public Layui queryList(HttpServletRequest httpServletRequest, ModelAndView modelAndView) {
        String name = httpServletRequest.getParameter("name");
        String no = httpServletRequest.getParameter("no");
        System.out.println("name: "+name+ "no :");
        List<Product> productList = mCommonService.searchProduct(name, no);
        //查询列表数据
        int total = productList.size();
        //分页
//        PageUtils pageUtil = new PageUtils(menuList, total, query.getLimit(), query.getPage());
        return Layui.data(total, productList);

    }

}
