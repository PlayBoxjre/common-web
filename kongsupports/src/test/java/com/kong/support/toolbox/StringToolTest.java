package com.kong.support.toolbox;

import com.kong.support.toolboxes.StringTool;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringToolTest {
    Logger logger = LoggerFactory.getLogger(StringToolTest.class);
    @Test
    public void groupsTest(){
        String isNull = null;
        String longString = "1234567890\nabcdefghijklmnopqrstuvwxyz\nABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shortString = "abc";
        int unitLong = 100;
        int unitMiddle = 20;
        int unitShort = 5;
        int unitOne = 1;

        String[] groups = StringTool.groups(isNull, 100);
        Assert.assertNull(groups);

        String[] groups1 = StringTool.groups(shortString, 100);
        Assert.assertEquals(groups1.length,1);
        Assert.assertEquals(groups1[0],shortString);

        String[] groups2 = StringTool.groups(shortString, unitOne);
        Assert.assertEquals(groups2.length , 3);
        logger.info(" length : {}",longString.length());
        String[] groups3 = StringTool.groups(longString, 5);
        Assert.assertEquals(groups3.length,longString.length()/5+1);
        int count = 0;
        for (String s: groups3){
            logger.info("{} --> {}",count++,s);
        }


        String srcStr = "hello world";
        String joinStr = "--!!--";

        for (int i = 0;i<= srcStr.length()+1;i++){
            String join = StringTool.join(srcStr, joinStr, i);
            logger.info("join string --> {}",join);
        }

    }

}
