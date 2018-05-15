package com.kong.support.toolboxes.imps;

import com.kong.support.exceptions.ClassNotFoundException;
import com.kong.support.exceptions.ResourceAccessException;
import com.kong.support.toolboxes.ScanTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class ScanToolImp implements ScanTool {
    Logger logger = LoggerFactory.getLogger(ScanToolImp.class);

    final static URL classpath;
    private static final Pattern INNER_PATTERN = java.util.regex.Pattern.compile("\\$(\\d+).", java.util.regex.Pattern.CASE_INSENSITIVE);

    static {
         classpath = ClassLoader.getSystemResource(".");
    }

    @Override
    public File[] getFiles(String dir) throws ResourceAccessException {
        return scanCurrentDir(dir, new Predicate<File>() {
            @Override
            public boolean test(File file) {
                return file.isFile();
            }
        });
    }

    @Override
    public File[] getFiles(File dirFile) throws ResourceAccessException {

        return scanCurrentDir(dirFile, new Predicate<File>() {
            @Override
            public boolean test(File file) {
                return file.isFile();
            }
        });
    }

    @Override
    public String[] getFilePaths(String dir) throws ResourceAccessException {
        return scanCurrentFilePaths(dir,(file)->{
            return file.isFile();
        });
    }

    @Override
    public String[] getFilePaths(File dir) throws ResourceAccessException {
        return scanCurrentFilePaths(dir,(file)->{
            return file.isFile();
        });
    }

    @Override
    public File[] getDirs(String dir) throws ResourceAccessException {
        return  scanCurrentDir(dir,(File file)->{
            return file.isDirectory();
        });
    }

    @Override
    public File[] getDirs(File file) throws ResourceAccessException {
        return scanCurrentDir(file,(f)->{
            return f.isDirectory();
        });
    }

    @Override
    public String[] getDirPaths(String dir) throws ResourceAccessException {
        return scanCurrentFilePaths(dir,(f)->{
            return f.isDirectory();
        });
    }

    @Override
    public String[] getDirPaths(File file) throws ResourceAccessException {
        return scanCurrentFilePaths(file,(f)->{
            return f.isDirectory();
        });    }

    @Override
    public File[] scanCurrentDir(String dir) throws ResourceAccessException {
        return scanCurrentDir(dir,(Predicate)null);
    }

    @Override
    public File[] scanCurrentDir(File dirFile) throws ResourceAccessException {
        return scanCurrentDir(dirFile,null);
    }

    @Override
    public String[] scanCurrentFilePaths(String dir) throws ResourceAccessException {
        return scanCurrentFilePaths(dir,null);
    }

    @Override
    public String[] scanCurrentFilePaths(File dirFile) throws ResourceAccessException {
        return scanCurrentFilePaths(dirFile,null);
    }

    @Override
    public File[] scanCurrentDir(String dir, Predicate<File> filter) throws ResourceAccessException {
        if (dir == null)
            throw new IllegalArgumentException("argument is not null");
        return scanCurrentDir(new File(dir),filter);
    }

    @Override
    public File[] scanCurrentDir(File dir, Predicate<File> filter) throws ResourceAccessException {
        if (dir == null )
            throw new IllegalArgumentException("argument is not null");
        if (!dir.exists())
            throw new ResourceAccessException("dir is not exist".hashCode(),String.format("directory %s is not exist ",dir.getPath()));
        return dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (filter!=null)
                    return filter.test(new File(dir,name));
                return true;
            }
        });
    }

    @Override
    public String[] scanCurrentFilePaths(String dir, Predicate<File> filter) throws ResourceAccessException {
        if (dir == null)
            throw new IllegalArgumentException("argument is not null");
        return scanCurrentFilePaths(new File(dir),filter);
    }

    @Override
    public String[] scanCurrentFilePaths(File dir, Predicate<File> filter) throws ResourceAccessException {
        if (dir == null )
            throw new IllegalArgumentException("argument is not null");
        if (!dir.exists())
            throw new ResourceAccessException("dir is not exist".hashCode(),String.format("directory %s is not exist ",dir.getPath()));
        return dir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (filter != null)
                    return filter.test(new File(dir,name));
                return true;
            }
        });
    }

    @Override
    public  List<File> scanFullFile(String dir) throws ResourceAccessException {
        return scanFullFile(dir, (f) -> {
            return true;
        }, (f)->{
            return f;
        });
    }

    @Override
    public <T> List<T> scanFullFile(String dir, Function<File, T> function) throws ResourceAccessException {
        return scanFullFile(dir,(f)->{
            return true;
        },function);
    }

    @Override
    public <T> List<T> scanFullFile(String dir, Predicate<File> fileFilter, Function<File, T> function) throws ResourceAccessException {
        if (dir == null)
            throw new IllegalArgumentException("argument is not null");
        List<T> list = new ArrayList<>();
        scanFullFile(list,new File(dir),fileFilter,function);
        return list;
    }

    @Override
    public <T> void scanFullFile( List<T> list, File dirFile, Predicate<File> fileFilter, Function<File, T> function) throws ResourceAccessException {
        if (dirFile == null || fileFilter == null || function == null  ){
            throw new IllegalArgumentException("argument is not null");
        }
        if (!dirFile.exists())
            throw new ResourceAccessException("dir is not exist".hashCode(),String.format("directory %s is not exist ",dirFile.getPath()));

        File[] files = dirFile.listFiles();
        for (File f :
                files) {
                if (f.isDirectory()) {
                    scanFullFile(list, f, fileFilter, function);
                    if ( fileFilter.test(f))
                        list.add(function.apply(f));
                }
                else{
                    if (fileFilter.test(f))
                        list.add(function.apply(f));
                }
        }
    }
    
    @Override
    public List<Class> scanAllClasses(String baseDir) throws ClassNotFoundException {
        if (baseDir == null)
            throw new IllegalArgumentException("argument is not null");
        return scanAllClasses(new File(baseDir));
    }

    @Override
    public List<Class> scanAllClasses(File baseDirFile) throws ClassNotFoundException {
       List<Class> classes = new ArrayList<>();
       scanAllClasses(classes,baseDirFile,null);
       return classes;
    }

    @Override
    public void scanAllClasses(List<Class> list,File baseDirFile, Consumer<Class> duelClass) throws ClassNotFoundException {
        if (baseDirFile == null || list == null )
            throw new IllegalArgumentException("argument is not null");

        // 1. 扫描包下所有类文件

        // 2. 过滤掉匿名内部类
        Predicate<File> filePredicate = (f)->{
            boolean notFilter = true;
            return f.isFile() && f.getName().endsWith("class") && !INNER_PATTERN.matcher(f.getName()).find();
        };

        Function<File,Class> fileClassFunction = (file -> {
            // 获取类路径
            String classpaths = getClassFullName(classpath,file);

            try {
                Class<?> e = Class.forName(classpaths);
                list.add(e);
                if (duelClass!=null)
                    duelClass.accept(e);
            } catch (java.lang.ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        });

        // 3. 转换成类对象
        try {
            scanFullFile(baseDirFile.getPath(), filePredicate, fileClassFunction);
        } catch (Exception e) {
            e.printStackTrace();
            String ex = "class create failed";
            throw new ClassNotFoundException(ex.hashCode(),ex) ;
        }
    }

    @Override
    public List<Class> scanAllClassesOfPackage(String packageName, Consumer<Class> duelClass) throws ClassNotFoundException {
        if (packageName == null)
            throw new IllegalArgumentException("package name is not null");
        packageName = packageName.replace('.',File.separatorChar);
        if (packageName.startsWith(File.separator)){
            packageName = packageName.substring(1);
            logger.debug("package file path :{}",packageName);
        }

        List<Class> classes = new ArrayList<>();
        scanAllClasses(classes,new File(classpath.getFile(), packageName),duelClass);
        return classes;

    }

    public List<Class> scanAllClassesOfPackage(String packageName) throws ClassNotFoundException {
       return scanAllClassesOfPackage(packageName,null);
    }


        /**
         * 根据文件获取类的全类名
         * @param file
         * @return
         */
    private String getClassFullName(URL classpath,File file)  {
        String classpathFile = classpath.getFile();
        String classFile = file.toString();
        logger.debug("-----> {} ",classFile);
        logger.debug("-----< {} ",classpathFile);
        String substring = classFile.replace(classpathFile,"")
                .replace(File.separatorChar,'.');
         substring = substring.substring(0,substring.lastIndexOf(".class"));
         logger.debug("class relative path :{}", substring);
        if (substring.startsWith("."))
            substring = substring.substring(1);
        return substring;
    }
}
