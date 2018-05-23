package com.kong.web;

import com.kong.web.controllers.HomeController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/dispatcher-servlet.xml"})
@WebAppConfiguration("src/main/webapp")
public class HomeTest {
    Logger logger = LoggerFactory.getLogger(HomeTest.class);
    MockMvc mockMvc;

    @Autowired
    HomeController homeController;

    @Autowired
    ServletContext servletContext;

    @Autowired
    WebApplicationContext applicationContext;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
    }

    @Test
    public void test() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/home.json?format=json")
                .contentType("application/json")
                .characterEncoding("UTF-8")


        )
                .andDo(print())

                .andExpect(status().isOk()).andReturn();

        String contentAsString1 = mvcResult.getResponse().getContentAsString();
        logger.info("content :{}",contentAsString1);

        ModelAndView modelAndView = mvcResult.getModelAndView();
        Map<String, Object> model = modelAndView.getModel();
        String viewName = modelAndView.getViewName();

        model.forEach((k,v)->{
           logger.info("{}:{}",k,v);
        });
        System.out.println(viewName);

        MockHttpServletResponse response = mvcResult.getResponse();
        String contentAsString = response.getContentAsString();
        logger.info(contentAsString);

        HandlerInterceptor[] interceptors = mvcResult.getInterceptors();
        Assert.assertNotNull(interceptors);
        logger.info("{}",interceptors.length);
        for (HandlerInterceptor interceptor : interceptors) {
            logger.info("{}",interceptor.getClass());
        }
    }


    @Test
    public void testAccount(){

    }


}
