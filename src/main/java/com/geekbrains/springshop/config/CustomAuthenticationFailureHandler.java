package com.geekbrains.springshop.config;

import com.geekbrains.springshop.services.UserService;
import com.geekbrains.springshop.utils.RabbitProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private UserService userService;

    @Autowired
    private RabbitProvider rabbitProvider;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        rabbitProvider.openConnect();
        rabbitProvider.sendMsg("Fail to login attempt");
        httpServletResponse.sendRedirect("/springshop/login");
    }
}
