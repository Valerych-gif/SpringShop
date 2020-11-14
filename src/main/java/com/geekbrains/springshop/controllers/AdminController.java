package com.geekbrains.springshop.controllers;

import com.geekbrains.springshop.entities.*;
import com.geekbrains.springshop.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private DeliveryAddressService deliveryAddressService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ImageSaverService imageSaverService;

    @Autowired
    private MailService mailService;

    @GetMapping("")
    public String adminHome(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        List<Role> roles = roleService.getAllRoles();
        int undeliveredOrderCount = orderService.getAllUndeliveredOrders().size();
        int countOfOutProducts = productService.getAllOutOfStoreProducts().size();
        model.addAttribute("categories", categories);
        model.addAttribute("roles", roles);
        model.addAttribute("undeliveredOrderCount", undeliveredOrderCount);
        model.addAttribute("countOfOutProducts", countOfOutProducts);
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

    @GetMapping("/orders")
    public String showOrders(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "admin/orders-page";
    }

    @GetMapping("/undelivered_orders")
    public String undeliveredOrders(Model model){
        List<Order> undeliveredOrders = orderService.getAllUndeliveredOrders();
        model.addAttribute("orders", undeliveredOrders);
        return "admin/orders-page";
    }

    @GetMapping("/order/{id}")
    public String orderReview(Model model, @PathVariable("id") Long id){
        List<OrderItem> orderItems = orderService.getOrderItems(id);
        Order order = orderService.findById(id);
        String orderStatus = order.getStatus().getTitle();
        Long user_id = order.getUser().getId();
        User user = userService.findById(user_id);
        if (user==null){
            user = new User("Неизвестный", "", "", "", "", "");
        }

        List<DeliveryAddress> deliveryAddress = deliveryAddressService.getUserAddresses(user_id);
        String userDeliveryAddress = deliveryAddress.size()==0?"Не задан":deliveryAddress.get(0).getAddress();
        model.addAttribute("user", user);
        model.addAttribute("order", order);
        model.addAttribute("orderItems", orderItems);
        model.addAttribute("deliveryAddress", userDeliveryAddress);
        model.addAttribute("orderStatus", orderStatus);
        return "admin/order";
    }

    @GetMapping("/order/collect/{id}")
    public void orderReady(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Long id) throws Exception {
        Order order = orderService.findById(id);
        orderService.changeOrderStatus(order, 2L);
        response.sendRedirect(request.getHeader("referer"));
    }

    @GetMapping("/order/send/{id}")
    public void sendOrder(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Long id) throws Exception {
        Order order = orderService.findById(id);
        Long userId = order.getUser().getId();
        orderService.changeOrderStatus(order, 3L);

        mailService.sendOrderMail(order);
        response.sendRedirect(request.getHeader("referer"));
    }

    @GetMapping("/delete_product/{id}")
    public void deleteProduct(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Long id) throws Exception {
        Product product = productService.getProductById(id);
        product.setQuantity(0L);
        productService.saveProduct(product);
        response.sendRedirect(request.getHeader("referer"));
    }


    @GetMapping("/edit_product/{id}")
    public String edit(Model model, @PathVariable(name = "id") Long id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            product = new Product();
            product.setId(0L);
            product.setQuantity(0L);
        }
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/edit-product";
    }

    @PostMapping("/edit_product")
    public String processProductAddForm(@Validated @ModelAttribute("product") Product product, BindingResult bindingResult, Model model, @RequestParam("file") MultipartFile file) {

        if (product.getId() == 0 && productService.isProductWithTitleExists(product.getTitle())) {
            bindingResult.addError(new FieldError("product", "title", "Товар с таким названием уже существует"));
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            return "/admin/edit-product";
        }

        if (!file.isEmpty()) {
            String pathToSavedImage = imageSaverService.saveFile(file);
            ProductImage productImage = new ProductImage();
            productImage.setPath(pathToSavedImage);
            productImage.setProduct(product);
            product.addImage(productImage);
        }

        product.setCreateAt(LocalDateTime.now());
        product.setUpdateAt(LocalDateTime.now());
        productService.saveProduct(product);
        return "redirect:/shop";
    }

    @GetMapping("/out_of_store")
    public String outOfStoreProducts(Model model){
        List<Product> products = productService.getAllOutOfStoreProducts();
        model.addAttribute("products", products);
        return "admin/out-of-store";
    }
}
