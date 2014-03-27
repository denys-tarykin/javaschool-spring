package com.springapp.web.backend.categories;

import com.springapp.api.CategoryService;
import com.springapp.api.implementation.CategoryServiceImpl;
import com.springapp.domain_objects.AuthUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller

public class Categories {

    @RequestMapping("/backend/categories")
    public String LoadGet(ModelMap model,HttpServletRequest request) {
        HttpSession session = request.getSession();
        AuthUser User_Auth = (AuthUser) session.getAttribute("userInfo");
        if (User_Auth == null)
            User_Auth = new AuthUser();
        if (User_Auth.IsLogin().equals("true")) {
            CategoryService catService = new CategoryServiceImpl();
                model.addAttribute("cats", catService.LoadCategories());
            return "backend/category-list";
        } else
            return "redirect:/";
    }

    @RequestMapping("/backend/category/delete/{categoryId}")
    public String Delete(@PathVariable("categoryId") Integer categoryId ,HttpServletRequest request) {
        HttpSession session = request.getSession();
        AuthUser User_Auth = (AuthUser) session.getAttribute("userInfo");
        if (User_Auth == null)
            User_Auth = new AuthUser();
        if (User_Auth.IsLogin().equals("true")) {
            CategoryService catService = new CategoryServiceImpl();
            catService.Delete(categoryId);
            return "redirect:/backend/categories";
        } else
            return "redirect:/";
    }

    @RequestMapping("/backend/category/add")
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

    @RequestMapping(method = RequestMethod.POST ,value ="/backend/category/add")
    public String AddPost(ModelMap model,HttpServletRequest request) {
        HttpSession session = request.getSession();
        AuthUser User_Auth = (AuthUser) session.getAttribute("userInfo");
        if (User_Auth == null)
            User_Auth = new AuthUser();
        if (User_Auth.IsLogin().equals("false")) {
            return "redirect:/";
        }
        CategoryService catService = new CategoryServiceImpl();
        catService.NewCategory(request.getParameter("name"),request.getParameter("description"));
        return "redirect:/backend/categories/";
    }

    @RequestMapping("/backend/category/edit/{categoryId}")
    public String EditGet(@PathVariable("categoryId") Integer categoryId,ModelMap model,HttpServletRequest request) {
        HttpSession session = request.getSession();
        AuthUser User_Auth = (AuthUser) session.getAttribute("userInfo");
        if (User_Auth == null)
            User_Auth = new AuthUser();
        if (User_Auth.IsLogin().equals("false")) {
            return "redirect:/";
        }
        CategoryService catService = new CategoryServiceImpl();
        model.addAttribute("cat",catService.getCategory(categoryId));
        return "backend/category-edit";
    }

    @RequestMapping(method = RequestMethod.POST ,value ="/backend/category/edit/{categoryId}")
    public String EditPost(@PathVariable("categoryId") Integer categoryId,ModelMap model,HttpServletRequest request) {
        HttpSession session = request.getSession();
        AuthUser User_Auth = (AuthUser) session.getAttribute("userInfo");
        if (User_Auth == null)
            User_Auth = new AuthUser();
        if (User_Auth.IsLogin().equals("false")) {
            return "redirect:/";
        }
        CategoryService catService = new CategoryServiceImpl();
        catService.editCategory(categoryId, request.getParameter("name"), request.getParameter("description"));
        return "redirect:/backend/categories";
    }
}
