package com.springapp.web.backend;

import com.springapp.dao.Factory;
import com.springapp.domain_objects.AuthUser;
import com.springapp.domain_objects.Category;
import com.springapp.domain_objects.Product;
import com.springapp.domain_objects.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller

public class Products {

    @RequestMapping("/backend/products")
    public String LoadGet(ModelMap model, HttpServletRequest request) {
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
    public String Delete(@PathVariable("productId") Integer productId, HttpServletRequest request) {
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
            return "backend/products-list";
        } else
            return "redirect:/";
    }

    @RequestMapping("/backend/products/add")
    public String AddGet(ModelMap model, HttpServletRequest request) {
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

    @RequestMapping(method = RequestMethod.POST, value = "/backend/products/add")
    public String AddPost(ModelMap model, HttpServletRequest request) {
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
        for (String category : categories) {
            cats.add(Integer.parseInt(category));
        }

        //BigDecimal price = new BigDecimal(request.getParameter("price"), new MathContext(2, RoundingMode.HALF_UP));
        BigDecimal price = new BigDecimal(request.getParameter("price")).setScale(2, RoundingMode.HALF_UP);
        String tags = request.getParameter("tags");
        String[] tags_product = tags.split(",");
        Set<String> tags_to_product = new HashSet<String>();
        Collections.addAll(tags_to_product, tags_product);
        try {
            Product prod = new Product();
            prod.setName(name);
            prod.setDescription(desc);
            prod.setPrice(price);
            Set cat = Factory.getInstance().DAOCategory().getByIds(cats);
            prod.setCategories(cat);
            //prod.setTags(tags_to_product);
            Factory.getInstance().DAOProduct().add(prod);
            return "redirect:/backend/products";
        } catch (SQLException e) {
            model.addAttribute("errors", e);
        }
        return "backend/category-add";
    }

    @RequestMapping("/backend/products/edit/{productId}")
    public String EditGet(@PathVariable("productId") Integer productId, ModelMap model, HttpServletRequest request) {
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
            model.addAttribute("cats_to_product", product.getCategories());
            model.addAttribute("cats", cats);
            model.addAttribute("product", product);

        } catch (SQLException e) {
            model.addAttribute("errors", e);
        }
        return "backend/products-edit";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/backend/products/edit/{productId}")
    public String EditPost(@PathVariable("productId") Integer productId, ModelMap model, HttpServletRequest request) {
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
        for (String cat : categories) {
            cats.add(Integer.parseInt(cat));
        }
        BigDecimal price = new BigDecimal(request.getParameter("price")).setScale(2, RoundingMode.HALF_UP);
        String tags = request.getParameter("tags");
        String[] tags_product = tags.split(",");
        Set<String> tag_name = new HashSet<String>();
        for (int i=0;i<tags_product.length;i++) {
            tag_name.add(tags_product[i]);
        }

        try {

           Set<Tag> tag = Factory.getInstance().DAOTag().getByNames(tag_name);
            String[] array_tag=null;
            Set<String> DB_tags =new HashSet<String>();
            for (Tag tg : tag) {
                DB_tags.add(tg.getTag());
            }
            tag_name.removeAll(DB_tags);
            if(tag_name.size()!=0){
                for (String str_tag:tag_name){
                    Tag tg = new Tag();
                    tg.setTag(str_tag);
                    Factory.getInstance().DAOTag().add(tg);
                }
            }

            Product product = Factory.getInstance().DAOProduct().getById(productId);
            product.setName(name);
            product.setDescription(desc);
            Set cat = Factory.getInstance().DAOCategory().getByIds(cats);
            product.setCategories(cat);
            product.setPrice(price);
            //product.setTags(tag);
            Factory.getInstance().DAOProduct().update(product);
        } catch (SQLException e) {
            model.addAttribute("errors", e);
        }
        return "redirect:/backend/products";
    }


}
