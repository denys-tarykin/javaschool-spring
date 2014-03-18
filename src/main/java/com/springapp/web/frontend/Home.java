package com.springapp.web.frontend;

import com.springapp.dao.Factory;
import com.springapp.domain_objects.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.SQLException;
import java.util.List;

@Controller

public class Home {
    @RequestMapping(value="/backend",method = RequestMethod.GET)
	public String index(ModelMap model) {
        try {
            List<Category> cats = Factory.getInstance().DAOCategory().getAll();
            model.addAttribute("cats", cats);
        } catch (SQLException e) {
            model.addAttribute("errors", e);
        }
        //model.addAttribute("cats", ProductService.listProduct());
		return "MainTemplate";
	}
}