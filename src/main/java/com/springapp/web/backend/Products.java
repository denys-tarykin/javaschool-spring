package com.springapp.web.backend;

import com.springapp.dao.Factory;
import com.springapp.domain_objects.AuthUser;
import com.springapp.domain_objects.Category;
import com.springapp.domain_objects.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller

public class Products {

    @RequestMapping("/backend/products")
    public String LoadGet(ModelMap model,HttpServletRequest request) {
        HttpSession session = request.getSession();
        AuthUser User_Auth = (AuthUser) session.getAttribute("userInfo");
        if (User_Auth == null)
            User_Auth = new AuthUser();
        if (User_Auth.IsLogin().equals("true")) {
            try {
                java.util.List<Product> products = Factory.getInstance().DAOProduct().getAll();
                model.addAttribute("products", products);
            } catch (SQLException e) {
                model.addAttribute("errors", e);
            }
            return "backend/products-list";
        } else
            return "redirect:/";
    }

    @RequestMapping("/backend/products/delete/{productId}")
    public String Delete(@PathVariable("productId") Integer productId ,HttpServletRequest request) {
        HttpSession session = request.getSession();
        AuthUser User_Auth = (AuthUser) session.getAttribute("userInfo");
        if (User_Auth == null)
            User_Auth = new AuthUser();
        if (User_Auth.IsLogin().equals("true")) {
            try {
                Factory.getInstance().DAOProduct().remove(productId);
                return "redirect:/backend/category";
            } catch (SQLException e) {
                request.setAttribute("errors", e);
            }
            return "backend/category-list";
        } else
            return "redirect:/";
    }

    @RequestMapping("/backend/products/add")
    public String AddGet(ModelMap model,HttpServletRequest request) {
        HttpSession session = request.getSession();
        AuthUser User_Auth = (AuthUser) session.getAttribute("userInfo");
        if (User_Auth == null)
            User_Auth = new AuthUser();
        if (User_Auth.IsLogin().equals("false")) {
            return "redirect:/";
        }
        try {
            List<Category> cats = Factory.getInstance().DAOCategory().getAll();
            model.addAttribute("cats", cats);
        } catch (SQLException e) {
            model.addAttribute("errors", e);
        }
        return "backend/products-add";
    }

    @RequestMapping(method = RequestMethod.POST ,value ="/backend/products/add")
    public String AddPost(ModelMap model,HttpServletRequest request) {
        HttpSession session = request.getSession();
        AuthUser User_Auth = (AuthUser) session.getAttribute("userInfo");
        if (User_Auth == null)
            User_Auth = new AuthUser();
        if (User_Auth.IsLogin().equals("false")) {
            return "redirect:/";
        }
        String name = request.getParameter("name");
        String desc = request.getParameter("description");
        String[] categories = request.getParameterValues("categories");
        Set<Integer> cats = new HashSet<Integer>();
        for (int i = 0; i < categories.length; i++) {
            cats.add(Integer.parseInt(categories[i]));
        }
        //BigDecimal price = new BigDecimal(request.getParameter("price"), new MathContext(2, RoundingMode.HALF_UP));
        BigDecimal price = new BigDecimal(request.getParameter("price"), new MathContext(2));
        try {
            Product prod = new Product();
            prod.setName(name);
            prod.setDescription(desc);
            prod.setPrice(price);
            Set cat = Factory.getInstance().DAOCategory().getForProductCreate(cats);
            prod.setCategories(cat);
            Factory.getInstance().DAOProduct().add(prod);//TODO: transactions
            return "redirect:/backend/products";
        } catch (SQLException e) {
            model.addAttribute("errors", e);
        }
        return "backend/category-add";
    }

    @RequestMapping("/backend/products/edit/{productId}")
    public String EditGet(@PathVariable("productId") Integer productId,ModelMap model,HttpServletRequest request) {
        HttpSession session = request.getSession();
        AuthUser User_Auth = (AuthUser) session.getAttribute("userInfo");
        if (User_Auth == null)
            User_Auth = new AuthUser();
        if (User_Auth.IsLogin().equals("false")) {
            return "redirect:/";
        }
        try {
            Product product = Factory.getInstance().DAOProduct().getById(productId);
            List<Category> cats = Factory.getInstance().DAOCategory().getAll();
            //List<Category> list = new ArrayList<Category>(product.getCategories());
            //model.addAttribute("check",list.indexOf(cats.get(1)));
            /*if(prod.equals(cats.get(1)))
                model.addAttribute("check","1");
            else
                model.addAttribute("check","2");*/
            model.addAttribute("cats", cats);
            model.addAttribute("product", product);

        } catch (SQLException e) {
            model.addAttribute("errors", e);
        }
        return "backend/products-edit";
    }


}
