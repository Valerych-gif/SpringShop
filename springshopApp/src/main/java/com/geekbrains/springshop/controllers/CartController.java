package com.geekbrains.springshop.controllers;

import com.geekbrains.springshop.services.ShoppingCartService;
import com.geekbrains.springshop.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {

    class ShoppingCartCount{
        int count;

        public ShoppingCartCount() {
            this.count = 0;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        @Override
        public String toString() {
            return "ShoppingCartCount{" +
                    "count=" + count +
                    '}';
        }
    }

    private ShoppingCartService shoppingCartService;
    private ShoppingCartCount shoppingCartCount;

    public CartController() {
        this.shoppingCartCount = new ShoppingCartCount();
    }

    @Autowired
    public void setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public String cartPage(Model model, HttpSession httpSession) {
        ShoppingCart cart = shoppingCartService.getCurrentCart(httpSession);
        model.addAttribute("cart", cart);
        return "cart/cart-page";
    }

    @GetMapping("/add/{id}")
    public String addProductToCart(Model model, @PathVariable("id") Long id, HttpServletRequest httpServletRequest) {
        shoppingCartService.addToCart(httpServletRequest.getSession(), id);
        String referrer = httpServletRequest.getHeader("referer");
        shoppingCartCount.setCount(shoppingCartCount.getCount()+1);
        return "redirect:" + referrer;
    }

    @MessageMapping("/hello") // вход — канал, куда JS-клиент отправляет сообщения
    @SendTo("/topic/items") // выход — канал, на который подписывается JS-клиент
    public ShoppingCartCount updateShoppingCartCounter() throws Exception {
        return shoppingCartCount;
    }
}
