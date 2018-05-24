/*
 * Copyright [2018] [ kong&xiang ]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kong.web.supports;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kong.support.exceptions.BaseException;
import com.kong.support.toolboxes.StringTool;
import com.kong.web.supports.model.RestResultSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

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
            RestResultSet<String> error = new RestResultSet<>();
            Writer writer = null;
            String code = "1";
            String message;
            try {
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.setHeader("Cache-Control", "no-cache");
                if(ex instanceof MethodArgumentNotValidException){//valid 参数不正确
                    MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) ex;
                    BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
                    List<FieldError> fieldErrors = bindingResult.getFieldErrors();
                    int fieldErrorCount = bindingResult.getFieldErrorCount();
                    StringBuilder sb = new StringBuilder();
                    ObjectError globalError = bindingResult.getGlobalError();
                    Object[] arguments = globalError.getArguments();
                    String[] codes = globalError.getCodes();


                    for (int i = 0; i < fieldErrorCount; i++) {
                        FieldError obj = fieldErrors.get(i);
                        Object rejectedValue = obj.getRejectedValue();

                        sb.append(i).append(':').append(obj.getObjectName()).append(':')
                                .append(obj.getField()).append(':')
                                .append(obj.getDefaultMessage()).append('\n')
                        ;
                    }
                    message = sb.toString();

                }else if ((ex instanceof BaseException)){
                    BaseException ex1 = (BaseException) ex;
                    code = ""+ex1.getCode();
                    message = ex1.getMessage();
                   } else {
                    message = StringTool.isNullSetDefault(ex.getMessage(),"系统异常");
                }
                writer = response.getWriter();
                error.setCode(code);
                error.setMessage(message);
                String result = this.objectMapper.writeValueAsString(error);
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
