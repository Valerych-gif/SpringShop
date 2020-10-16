package com.geekbrains.springshop.aspects;

import com.geekbrains.springshop.entities.Category;
import com.geekbrains.springshop.entities.SystemUser;


import com.geekbrains.springshop.services.CategoryService;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectLogger {
    Logger file = Logger.getLogger("file");

    @Autowired
    CategoryService categoryService;

    @AfterReturning(pointcut = "execution(public * com.geekbrains.springshop.services.UserServiceImpl.save(..))",
            returning = "isUserCreated")
    public void createNewUser(JoinPoint joinPoint, Boolean isUserCreated) {
        if (isUserCreated) {
            Object[] args = joinPoint.getArgs();
            SystemUser systemUser = (SystemUser) args[0];
            file.info("The new user " + systemUser.getUserName() + " was created");
        } else {
            file.info("New user wasn't created");
        }
    }

    @After("execution(public * com.geekbrains.springshop.services.CategoryService.addNewCategory(..))")
    public void createNewCategory(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Category category = (Category) args[0];
        file.info("New category " + category.getTitle() + " was created");
    }

    @Before("execution(public * com.geekbrains.springshop.services.CategoryService.deleteCategoryById(..))")
    public void deleteCategory(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Long id = (Long)args[0];
        Category category = categoryService.getCategoryById(id);
        file.info("Category " + category.getTitle() + " was deleted");
    }
}
