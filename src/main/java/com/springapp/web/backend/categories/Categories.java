package com.springapp.web.backend.categories;

import com.springapp.dao.Factory;
import com.springapp.domain_objects.AuthUser;
import com.springapp.domain_objects.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller

public class Categories {

    @RequestMapping("/backend/categories")
    public String LoadGet(ModelMap model,HttpServletRequest request) {
        HttpSession session = request.getSession();
        AuthUser User_Auth = (AuthUser) session.getAttribute("userInfo");
        if (User_Auth == null)
            User_Auth = new AuthUser();
        if (User_Auth.IsLogin().equals("true")) {
            try {
                java.util.List<Category> cats = Factory.getInstance().DAOCategory().getAll();
                model.addAttribute("cats", cats);
            } catch (SQLException e) {
                model.addAttribute("errors", e);
            }
            return "backend/category-list";
        } else
            return "redirect:/";
    }

    @RequestMapping("/backend/categories/delete/{categoryId}/")
    public String Delete(@PathVariable("categoryId") Integer categoryId ,HttpServletRequest request) {
        HttpSession session = request.getSession();
        AuthUser User_Auth = (AuthUser) session.getAttribute("userInfo");
        if (User_Auth == null)
            User_Auth = new AuthUser();
        if (User_Auth.IsLogin().equals("true")) {
            try {
                Factory.getInstance().DAOCategory().remove(categoryId);
                return "redirect:/backend/category";
            } catch (SQLException e) {
                request.setAttribute("errors", e);
            }
            return "backend/category-list";
        } else
            return "redirect:/";
    }

    @RequestMapping("/backend/categories/add")
    public String AddGet(HttpServletRequest request) {
        HttpSession session = request.getSession();
        AuthUser User_Auth = (AuthUser) session.getAttribute("userInfo");
        if (User_Auth == null)
            User_Auth = new AuthUser();
        if (User_Auth.IsLogin().equals("false")) {
            return "redirect:/";
        }
        return "backend/category-add";
    }

    @RequestMapping(method = RequestMethod.POST ,value ="/backend/categories/add")
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
        try {
            Category cat = new Category();
            cat.setName(name);
            cat.setDescription(desc);
            Factory.getInstance().DAOCategory().add(cat);
            return "redirect:/backend/category-list";
        } catch (SQLException e) {
            model.addAttribute("errors", e);
        }
        return "backend/category-add";
    }

    @RequestMapping("/backend/categories/edit/{categoryId}")
    public String EditGet(@PathVariable("categoryId") Integer categoryId,ModelMap model,HttpServletRequest request) {
        HttpSession session = request.getSession();
        AuthUser User_Auth = (AuthUser) session.getAttribute("userInfo");
        if (User_Auth == null)
            User_Auth = new AuthUser();
        if (User_Auth.IsLogin().equals("false")) {
            return "redirect:/";
        }
        try {
            Category cat = Factory.getInstance().DAOCategory().getById(categoryId);
            model.addAttribute("cat", cat);
        }catch (SQLException e) {
            model.addAttribute("errors", e);
        }
        return "backend/category-edit";
    }

    @RequestMapping(method = RequestMethod.POST ,value ="/backend/categories/edit/{categoryId}")
    public String EditPost(@PathVariable("categoryId") Integer categoryId,ModelMap model,HttpServletRequest request) {
        HttpSession session = request.getSession();
        AuthUser User_Auth = (AuthUser) session.getAttribute("userInfo");
        if (User_Auth == null)
            User_Auth = new AuthUser();
        if (User_Auth.IsLogin().equals("false")) {
            return "redirect:/";
        }
        String name = request.getParameter("name");
        String desc = request.getParameter("description");
        try {
            Category cat = Factory.getInstance().DAOCategory().getById(categoryId);
            cat.setName(name);
            cat.setDescription(desc);
            Factory.getInstance().DAOCategory().update(cat);
            return "redirect:/backend/category-list";
        }catch (SQLException e) {
            model.addAttribute("errors", e);
        }
        return "backend/category-edit";
    }
}
