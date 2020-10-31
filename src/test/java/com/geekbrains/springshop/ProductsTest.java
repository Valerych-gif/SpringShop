package com.geekbrains.springshop;

import org.hamcrest.Matcher;
import org.hamcrest.core.StringContains;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductsTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void isProductPageExists() throws Exception {
        mockMvc.perform(get("/shop"))
                .andExpect(status().isOk())
                .andExpect(content().string(StringContains.containsString("20&quot; Телевизор Samsung UE20NU7170U")));
    }

    @Test
    public void isProductTitleExist() throws Exception {
        mockMvc.perform(get("/product/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(StringContains.containsString("<h5 class=\"card-title\">")));
    }
}
