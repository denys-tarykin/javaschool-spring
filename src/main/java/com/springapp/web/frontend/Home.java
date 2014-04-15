package com.springapp.web.frontend;

import com.springapp.api.CategoryService;
import com.springapp.api.implementation.CategoryServiceImpl;
import com.springapp.domain_objects.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller

public class Home {
    @RequestMapping(value="/",method = RequestMethod.GET)
	public String index(ModelMap model) {
        //try {
            //List<Category> cats = Factory.getInstance().DAOCategory().getAll();
            CategoryService catService = new CategoryServiceImpl();
        List<Category> categoryList = catService.loadCategories();
        model.addAttribute("cats", categoryList);
        /*} catch (SQLException e) {
            model.addAttribute("errors", e);
        }*/
		return "MainTemplate";
	}
}