package com.kong.web.supports;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kong.support.exceptions.BaseException;
import com.kong.support.toolboxes.StringTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * Author    lantoev & kong xiang
 * DATE      2018-05-18
 * EMAIL     playboxjre@Gmail.com
 */
public class MyGlobalExceptionResolver extends SimpleMappingExceptionResolver {

    private static Logger log = LoggerFactory.getLogger(MyGlobalExceptionResolver.class);

    @Autowired
     private ObjectMapper objectMapper;

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//        WebApplicationContext webApplicationContext = RequestContextUtils.findWebApplicationContext(request, request.getServletContext());
//        objectMapper = (ObjectMapper)webApplicationContext.getBean("objectMapper");
        String viewName = determineViewName(ex, request);
        if (viewName == null  ){
            Writer writer = null;
            try {
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.setHeader("Cache-Control", "no-cache");

                Map<String, Object> hashMap = new HashMap<String, Object>();
                hashMap.put("success", false);
                if ((ex instanceof BaseException)){
                    BaseException ex1 = (BaseException) ex;
                    hashMap.put("code",ex1.getCode());
                    hashMap.put("message",StringTool.isNullSetBlank(ex1.getMessage()));
                } else {
                    hashMap.put("code",1);
                    hashMap.put("message",StringTool.isNullSetDefault(ex.getMessage(),"系统异常"));
                }
                writer = response.getWriter();
                String result = this.objectMapper.writeValueAsString(hashMap);
                writer.write(result);
            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                if (writer != null){
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return new ModelAndView();
        } else {
            // Apply HTTP status code for error views, if specified.
            // Only apply it if we're processing a top-level request.
            Integer statusCode = determineStatusCode(request, viewName);
            if (statusCode != null) {
                applyStatusCodeIfPossible(request, response, statusCode);
                return getModelAndView(viewName, ex, request);
            }
        }

        return null;
    }
}
