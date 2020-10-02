package com.geekbrains.springshop.controllers;

import com.geekbrains.springshop.entities.Category;
import com.geekbrains.springshop.entities.Role;
import com.geekbrains.springshop.services.CategoryService;
import com.geekbrains.springshop.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RoleService roleService;

    @GetMapping("")
    public String adminHome(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("categories", categories);
        model.addAttribute("roles", roles);
        return "admin/admin-panel";
    }

    @PostMapping("/addcategory")
    public String addNewCategory(@RequestParam("categoryname") String categoryName,
                                 @RequestParam(name = "categorydescription", required = false) String categoryDescription) {
        Category category = new Category();
        category.setTitle(categoryName);
        category.setDescription(categoryDescription);
        categoryService.addNewCategory(category);
        return "redirect:/admin";
    }

    @PostMapping("/addrole")
    public String addNewUsersRole(@RequestParam("rolename")String rolename) {
        Role role = new Role(rolename);
        roleService.addNewRole(role);
        return "redirect:/admin";
    }

    @GetMapping("/category/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategoryById(id);
        return "redirect:/admin";
    }

    @GetMapping("/role/delete/{id}")
    public String deleteRole(@PathVariable("id") Long id) {
        roleService.deleteRoleById(id);
        return "redirect:/admin";
    }
}
