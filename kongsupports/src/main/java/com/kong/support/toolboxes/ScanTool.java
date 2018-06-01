package com.kong.support.toolboxes;

import com.kong.support.exceptions.common.ClassNotFoundException;
import com.kong.support.exceptions.common.ResourceAccessException;

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
    /**
     * 获取此目录下的文件
     * get all files that contain directory  under the current directory
     * @param dirFile
     * @return
     */
    public File[] getFiles(File dirFile) throws ResourceAccessException;
    /**
     * get all files that contain directory  under the current directory
     * @param dir
     * @return
     */
    public String[] getFilePaths(String dir )throws ResourceAccessException;
    /**
     * get all files that contain directory  under the current directory
     * @param dir
     * @return
     */
    public String[] getFilePaths(File dir)throws ResourceAccessException;
    /**
     * 获取当前目录下所有的目录
     * @param dir
     * @return
     */
    public File[] getDirs(String dir)throws ResourceAccessException;
    /**
     * 获取当前目录下所有的目录
     * @param dir
     * @return
     */
    public File[] getDirs(File dir)throws ResourceAccessException;
    /**
     * 获取当前目录下所有的目录
     * @param dir
     * @return
     */
    public String[] getDirPaths(String dir)throws ResourceAccessException;
    /**
     * 获取当前目录下所有的目录
     * @param dir
     * @return
     */
    public String[] getDirPaths(File dir)throws ResourceAccessException;

    /**
     * 扫描此目录下的
     * @param dir
     * @return
     */
    public File[] scanCurrentDir(String dir)throws ResourceAccessException;
    /**
     * 扫描此目录下的
     * @param dirFile
     * @return
     */
    public File[] scanCurrentDir(File dirFile)throws ResourceAccessException;
    /**
     * 扫描此目录下的
     * @param dir
     * @return
     */
    public String[] scanCurrentFilePaths(String dir)throws ResourceAccessException;
    /**
     * 扫描此目录下的
     * @param dirFile
     * @return
     */
    public String[] scanCurrentFilePaths(File dirFile)throws ResourceAccessException;


    /**
     * 扫描当前目录下所有匹配条件的文件
     * @param dir
     * @param filter
     * @return
     */
    public File[] scanCurrentDir(String dir, Predicate<File> filter) throws ResourceAccessException;
    /**
     * 扫描当前目录下所有匹配条件的文件
     * @param dir
     * @param filter
     * @return
     */
    public File[] scanCurrentDir(File dir, Predicate<File> filter) throws ResourceAccessException;
    /**
     * 扫描当前目录下所有匹配条件的文件
     * @param dir
     * @param filter
     * @return
     */
    public String[] scanCurrentFilePaths(String dir, Predicate<File> filter) throws ResourceAccessException;
    /**
     * 扫描当前目录下所有匹配条件的文件
     * @param dir
     * @param filter
     * @return
     */
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
    /**
     * 获取扫描到的文件，并进行处理
     * @param dir
     * @return
     */
    public <T> List<T> scanFullFile(String dir, Predicate<File> fileFilter, java.util.function.Function<File,T> function)  throws ResourceAccessException;
    /**
     * 获取扫描到的文件，并进行处理
     * @param
     * @return
     */
    public <T> void  scanFullFile(List<T> list,File dirFile, Predicate<File> fileFilter, java.util.function.Function<File,T> function)  throws ResourceAccessException;

    /**
     * 迭代扫描此目录下的所有的类
     * @param baseDir
     * @return
     * @throws ClassNotFoundException
     */
    public List<Class> scanAllClasses(String baseDir)throws ClassNotFoundException;
    /**
     * 迭代扫描此目录下的所有的类
     * @param baseDirFile
     * @return
     * @throws ClassNotFoundException
     */
    public List<Class> scanAllClasses(File baseDirFile) throws ClassNotFoundException;
    /**
     * 迭代扫描此目录下的所有的类
     * @param baseDirFile
     * @return
     * @throws ClassNotFoundException
     */
    public void scanAllClasses(List<Class> list,File baseDirFile, Consumer<Class> duelClass) throws ClassNotFoundException;

    /**
     * 扫描包下面的所有类
     * @param packageName
     * @return
     * @throws ClassNotFoundException
     */
    public List<Class> scanAllClassesOfPackage(String packageName) throws ClassNotFoundException;
    /**
     * 扫描包下面的所有类,没扫描一个类，消费一个类进行自定义处理
     * @param packageName
     * @return
     * @throws ClassNotFoundException
     */
    public List<Class> scanAllClassesOfPackage(String packageName, Consumer<Class> duelClass) throws ClassNotFoundException ;

}
