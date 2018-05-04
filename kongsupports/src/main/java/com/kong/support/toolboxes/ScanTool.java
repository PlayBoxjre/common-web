package com.kong.support.toolboxes;

import com.kong.support.exceptions.ClassNotFoundException;
import com.kong.support.exceptions.ResourceAccessException;

import java.io.File;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface ScanTool {

    /**
     * 获取此目录下的文件
     * get all files that contain directory  under the current directory
     * @param dir
     * @return
     */
    public File[]  getFiles(String dir)throws ResourceAccessException;
    public File[] getFiles(File dirFile) throws ResourceAccessException;
    /**
     * get all files that contain directory  under the current directory
     * @param dir
     * @return
     */
    public String[] getFilePaths(String dir )throws ResourceAccessException;
    public String[] getFilePaths(File file)throws ResourceAccessException;
    /**
     * 获取当前目录下所有的目录
     * @param dir
     * @return
     */
    public File[] getDirs(String dir)throws ResourceAccessException;
    public File[] getDirs(File file)throws ResourceAccessException;
    public String[] getDirPaths(String dir)throws ResourceAccessException;
    public String[] getDirPaths(File file)throws ResourceAccessException;

    /**
     * 扫描此目录下的
     * @param dir
     * @return
     */
    public File[] scanCurrentDir(String dir)throws ResourceAccessException;
    public File[] scanCurrentDir(File dirFile)throws ResourceAccessException;
    public String[] scanCurrentFilePaths(String dir)throws ResourceAccessException;
    public String[] scanCurrentFilePaths(File dirFile)throws ResourceAccessException;


    /**
     * 扫描目录下所有匹配条件的文件
     * @param dir
     * @param filter
     * @return
     */
    public File[] scanCurrentDir(String dir, Predicate<File> filter) throws ResourceAccessException;
    public File[] scanCurrentDir(File dir, Predicate<File> filter) throws ResourceAccessException;
    public String[] scanCurrentFilePaths(String dir, Predicate<File> filter) throws ResourceAccessException;
    public String[] scanCurrentFilePaths(File dir, Predicate<File> filter) throws ResourceAccessException;


    /**
     * 获取扫描的所有文件
     * @param dir
     * @return
     * @throws ResourceAccessException
     */
    public  List<File> scanFullFile(String dir)  throws ResourceAccessException;
    /**
     * 获取扫描到的文件，并进行处理
     * @param dir
     * @return
     */
    public <T> List<T> scanFullFile(String dir, java.util.function.Function<File,T> function)  throws ResourceAccessException;
    public <T> List<T> scanFullFile(String dir, Predicate<File> fileFilter, java.util.function.Function<File,T> function)  throws ResourceAccessException;
    public <T> void  scanFullFile(List<T> list,File dirFile, Predicate<File> fileFilter, java.util.function.Function<File,T> function)  throws ResourceAccessException;

    /**
     * 迭代扫描此目录下的所有的类
     * @param baseDir
     * @return
     * @throws ClassNotFoundException
     */
    public List<Class> scanAllClasses(String baseDir)throws ClassNotFoundException;
    public List<Class> scanAllClasses(File baseDirFile) throws ClassNotFoundException;
    public void scanAllClasses(List<Class> list,File baseDirFile, Consumer<Class> duelClass) throws ClassNotFoundException;

    /**
     * 扫描包下面的所有类
     * @param packageName
     * @return
     * @throws ClassNotFoundException
     */
    public List<Class> scanAllClassesOfPackage(String packageName) throws ClassNotFoundException;

    public List<Class> scanAllClassesOfPackage(String packageName, Consumer<Class> duelClass) throws ClassNotFoundException ;

}
