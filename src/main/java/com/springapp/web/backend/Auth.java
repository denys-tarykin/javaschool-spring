package com.springapp.web.backend;

import com.springapp.dao.Factory;
import com.springapp.domain_objects.AuthUser;
import com.springapp.domain_objects.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller

public class Auth {
    @RequestMapping(method = RequestMethod.GET ,value = "/backend/login")
    public String loginGet(ModelMap model,HttpServletRequest request) {
        HttpSession session = request.getSession();
        AuthUser User_Auth = (AuthUser) session.getAttribute("userInfo");
        if (User_Auth == null)
            User_Auth = new AuthUser();
        if (User_Auth.IsLogin().equals("true"))
            return "redirect:/";
        else
            return "backend/backend-login";
    }
    @RequestMapping(method = RequestMethod.POST ,value = "/backend/login")
    public String loginPost(ModelMap model,HttpServletRequest request) {
        HttpSession session = request.getSession();
        AuthUser User_Auth = (AuthUser) session.getAttribute("userInfo");
        if (User_Auth == null)
            User_Auth = new AuthUser();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            User User_Info = Factory.getInstance().DAOUser().getForAuth(username, password);
            if (User_Info != null) {
                User_Auth.Login(username);
                session.setAttribute("userInfo", User_Auth);
                return "redirect:/backend/categories";
            } else {
                model.addAttribute("error", "Incorrect user name - " + username);
            }
        } catch (SQLException e) {
            model.addAttribute("errors", e);
        }
        return "backend/backend-login";
    }
}