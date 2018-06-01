package com.kong.support;

import com.kong.support.exceptions.common.ClassNotFoundException;
import com.kong.support.exceptions.common.ResourceAccessException;
import com.kong.support.resources.defines.Resource;
import com.kong.support.resources.imps.ResourceBundle;
import com.kong.support.resources.imps.ByteResource;
import com.kong.support.toolboxes.ScanTool;
import com.kong.support.toolboxes.imps.ScanToolImp;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * PROJECT     :   commonweb
 * AUTHOR      :   Kong Xiang&Aaron
 * CREATEDAT   :   2018/4/11 0:43 星期三
 * EMAIL       :   playboxjre@Gmail.com
 * DESCRIPTION :
 */
public class ResourceTest {
    Logger logger = LoggerFactory.getLogger(ResourceTest.class);

    @Test
    public void test() {
        String path = "src/main/resources/log4j.properties";
        String path1 = "http://www..com";
        Resource resource = new ByteResource(path1);
        URI uri = resource.getURI();
        byte[] bytes = resource.getBytes();
        String ret = new String(bytes, Charset.forName("ISO-8859-1"));

        logger.info("ret {}", ret);
        Resource local = new ByteResource(path);
         uri = local.getURI();
         bytes = local.getBytes();
         ret = new String(bytes, Charset.forName("ISO-8859-1"));


        logger.info("ret {}", ret);
    }

    @Test
    public void resourceBundleTest() throws ResourceAccessException {
        String path = "src/main/resources/log4j.properties";
        String path1 = "http://www.konvigne.com";
        ResourceBundle resourceBundle = ResourceBundle.getInstance();
        Resource resource = resourceBundle.getResource(path);
        byte[] bytes = resource.getBytes();
        if (bytes!=null) {
            String ret = new String(bytes, Charset.forName("UTF-8"));

            logger.info("ret {}", ret);
        }
    }


    @Test
    public void testFileList() throws ResourceAccessException, IOException {
        File dir = new File(getClass().getResource("/").getPath());
        dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                logger.info("dir {} | filename : {} ", dir.getAbsoluteFile(),name);
                return false;
            }
        });



        logger.info("-- scan start ...");
        logger.info("----- dir path :: {} ",dir.getPath());

        ScanTool scanTool = new ScanToolImp();

        File[] files = scanTool.scanCurrentDir(dir);

        printObjects(files);

        logger.info(" -- get Files ");
        File[] filePaths = scanTool.getFiles(dir);
        printObjects(filePaths);
        assert filePaths.length == 1;

        logger.info(" -- get dirs");

        File[] dirPaths = scanTool.getDirs(dir);
        printObjects(dirPaths);
        assert dirPaths.length == 1;

        logger.info("scan current path::::");

        List<File> list = new ArrayList<>();
        scanTool.scanFullFile(list,dir,(f)->{
            return f.isDirectory();
        },(f)-> {return f;});

        for (File f :
                list) {
            File[] filePaths1 = scanTool.scanCurrentDir(f);
            logger.info("DIR: {} " ,f.getPath());
            printObjects(filePaths1);
        }

        //printObjects(list.toArray());
    }

    private void printObjects(Iterable objects){
        for (Object o :
                objects) {
            logger.info("object : {}",o.toString());

        }
    }
    private void printObjects(Object[] objects){
        for (Object o :
                objects) {
            logger.info("object : {}",o.toString());

        }
    }

    @Test
    public void testScanClasses() throws ClassNotFoundException {
        ScanTool scanTool = new ScanToolImp();

        List<Class> classes = scanTool.scanAllClassesOfPackage("com.kong.supports");
        printObjects(classes);
    }

}
