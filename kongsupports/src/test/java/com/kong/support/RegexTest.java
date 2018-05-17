package com.kong.support;

import com.kong.support.exceptions.ResourceAccessException;
import com.kong.support.resources.defines.Resource;
import com.kong.support.resources.imps.ResourceBundle;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegexTest {
    Logger logger = LoggerFactory.getLogger(RegexTest.class);
    Pattern pattern;
    ResourceBundle bundle =  ResourceBundle.getInstance();
    Properties properties;

    @Before
    public void before() throws ResourceAccessException, IOException {

        Resource resource = bundle.getResource("src/main/resources/regex_expression.properties");
        properties= new Properties();
        InputStream inStream = resource.openInputStream();
        assert inStream!=null;
        properties.load(inStream);

    }


    @Test
    public void regHtmlTest() throws ResourceAccessException {
        String reg_html = properties.getProperty("reg_idcard");
        pattern = Pattern.compile(reg_html,Pattern.DOTALL);

        Resource resource = bundle.getResource("http://www.baidu.com");
        String text =
                //"<数学 \"hello\"=5 \"name\"=\"kong xiang\" 我iu是2什么>\n</数学>";
                //new String(resource.getBytes());
                //"18551_61885@qq.c.com";
                //"http://www.baidu.com:8080/2/word?a=1&b=8#1";
                //"0516-89992311";
                "210122198801022811";
        Matcher matcher = pattern.matcher(text);



        while (matcher.find()){
            int i = matcher.groupCount();
            logger.info("{}",i);
            for (int z = 0;z < i;z++){
                logger.info("{} \t{}",z,  matcher.group(z));
            }


        }
        //logger.info("{}",matcher.group(i-1));
      /*  while (matcher.find()) {
            logger.info("\t{}",  matcher.group(1));
            logger.info("\t{}",  matcher.group(2));


            Matcher se = pattern.matcher(matcher.group(2));
            while (se.find()) {
                logger.info("\t\t{}  ",   se.group(1));
                // logger.info(matcher.group());
            }

           // logger.info(matcher.group());
        }*/


    }
}
