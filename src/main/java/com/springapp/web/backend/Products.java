package com.springapp.web.backend;

import com.springapp.api.CategoryService;
import com.springapp.api.ProductService;
import com.springapp.api.TagService;
import com.springapp.api.implementation.CategoryServiceImpl;
import com.springapp.api.implementation.ProductServiceImpl;
import com.springapp.api.implementation.TagServiceImpl;
import com.springapp.domain_objects.AuthUser;
import com.springapp.domain_objects.Category;
import com.springapp.domain_objects.Product;
import com.springapp.domain_objects.Tag;
import com.springapp.util.ProductValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
            ProductService prod = new ProductServiceImpl();
            int offset = 0;
            java.util.List<Product> products = prod.getProducts(offset, 2);
            model.addAttribute("products", products);
            return "backend/products-list";
        } else
            return "redirect:/";
    }

    @RequestMapping("/backend/products/page-{page}")
    public String LoadPage(@PathVariable("page") Integer page, ModelMap model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        AuthUser User_Auth = (AuthUser) session.getAttribute("userInfo");
        if (User_Auth == null)
            User_Auth = new AuthUser();
        if (User_Auth.IsLogin().equals("true")) {
            ProductService prod = new ProductServiceImpl();
            int offset = (page - 1) * 2;
            java.util.List<Product> products = prod.getProducts(offset, 2);
            model.addAttribute("products", products);
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
            ProductService prod = new ProductServiceImpl();
            prod.removeProduct(productId);
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
        CategoryService catService = new CategoryServiceImpl();
        List<Category> cats = catService.loadCategories();
        model.addAttribute("cats", cats);
        return "backend/products-add";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/backend/products/add")
    public String AddPost(ModelMap model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
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
        String[] tags_product = tags.split("\\s*,+\\s*");
        Set<String> tags_to_product = new HashSet<String>();
        for (String tag : tags_product) {
            tags_to_product.add(tag.replaceAll("\\s+", " "));
        }

        CategoryService categoryService = new CategoryServiceImpl();
        ProductService productService = new ProductServiceImpl();
        ProductValidator validator = new ProductValidator(name,desc,request.getParameter("price"),categories);
        if(validator.Validate()) {
            //try {
            Product prod = new Product();
            prod.setName(name);
            prod.setDescription(desc);
            prod.setPrice(price);
            //Set cat = Factory.getInstance().DAOCategory().getByIds(cats);
            HashSet<Category> cat = categoryService.loadCategories(cats);
            prod.setCategories(cat);
            //prod.setTags(tags_to_product);
            productService.addProduct(prod);
            //Factory.getInstance().DAOProduct().add(prod);
            return "redirect:/backend/products";
        /*} catch (SQLException e) {
            model.addAttribute("errors", e);
        }
        return "backend/category-add";*/
        }else {
            redirectAttributes.addFlashAttribute("ErrorList", validator.getErrors());
            redirectAttributes.addFlashAttribute("name", name);
            return "redirect:/backend/products/add";
        }

    }

    /*@RequestMapping("/backend/products/edit/{productId}")
    public String EditGet(@PathVariable("productId") Integer productId, ModelMap model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        AuthUser User_Auth = (AuthUser) session.getAttribute("userInfo");
        if (User_Auth == null)
            User_Auth = new AuthUser();
        if (User_Auth.IsLogin().equals("false")) {
            return "redirect:/";
        }
        ProductService productService = new ProductServiceImpl();
        CategoryService categoryService = new CategoryServiceImpl();

        Product product = productService.getProduct(productId);
        List<Category> cats = categoryService.loadCategories();

        //try {
            //Product product = Factory.getInstance().DAOProduct().getById(productId);
            //List<Category> cats = Factory.getInstance().DAOCategory().getAll();
            model.addAttribute("cats_to_product", product.getCategories());
            model.addAttribute("cats", cats);
            model.addAttribute("product", product);

       /* } catch (SQLException e) {
            model.addAttribute("errors", e);
        }
        return "backend/products-edit";
        }*/


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
        String[] tags_product = tags.split("\\s*,+\\s*");
        Set<String> tag_name = new HashSet<String>();
        for (String tag : tags_product) {
            tag_name.add(tag.replaceAll("\\s+", " "));
        }
/* TODO: Validator draft
       ProductValidator.setName().....
        new Product Validator (name,price....)

        bool ProductValidator.Validate()
                if true try-catch else HashMap ProductValidator.getErrorList()
*/
        /*try {

           HashSet<Tag> tag = Factory.getInstance().DAOTag().getByNames(tag_name);
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
            }*/
            TagService tagService = new TagServiceImpl();
        ProductService productService = new ProductServiceImpl();
        CategoryService categoryService = new CategoryServiceImpl();

        HashSet<Tag> tag = tagService.createTagsForProduct(tag_name);

        Product product = productService.getProduct(productId);
        product.setName(name);
            product.setDescription(desc);
        HashSet<Category> cat = categoryService.loadCategories(cats);
        //HashSet cat = Factory.getInstance().DAOCategory().getByIds(cats);
        product.setCategories(cat);
            product.setPrice(price);
        product.setTags(tag);
        productService.edit(product);
        //Factory.getInstance().DAOProduct().update(product);
        /*} catch (SQLException e) {
            model.addAttribute("errors", e);
        }*/
        return "redirect:/backend/products";
    }


}
