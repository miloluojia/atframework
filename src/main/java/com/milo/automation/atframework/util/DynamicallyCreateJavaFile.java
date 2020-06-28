package com.milo.automation.atframework.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Date;

public class DynamicallyCreateJavaFile {

//    private final static String path = "/Users/milo/atframework/src/main/java/com/milo/automation/atframework/testng/";
    private static String className;
    private static String sourceName;
    public static com.sun.tools.javac.Main tool;
    static URLClassLoader classLoader;



//    public static String getPath() {
//        return path;
//    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public void createIt(String classNameReq) {
        try {
//            类名和源文件名
            className = classNameReq;

//            URL[] urls = ((URLClassLoader) (Thread.currentThread().getContextClassLoader())).getURLs();
//            System.out.println(urls);

//            List<URI> classpath = new ClassGraph().getClasspathURIs();

//            ClassLoader cl = ClassLoader.getSystemClassLoader();
//            URL[] urls = ((URLClassLoader) cl).getURLs();
//            for (URL url: urls) {
//                System.out.println(url.getFile());
//            }

            sourceName = className + ".java";
            
//            生成源文件
//            File file = new File(path + sourceName);
            FileWriter aWriter = new FileWriter(sourceName,true);
            aWriter.write("package com.milo.automation.atframework.testng;\n");
            aWriter.write("public class "+ this.className + "{");
            aWriter.write(" public void doit() {");
            aWriter.write(" System.out.println(\""+this.className +"has been created! \");");
            aWriter.write(" }}\n");
            aWriter.flush();
            aWriter.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void loadExecutableInterface()
            throws MalformedURLException {

        final File file = new File("."); // <-- the directory where the generated java class is defined

        try {
            classLoader = URLClassLoader.newInstance(new URL[] { file.toURI().toURL() });
            try {
                Class<?> cls = Class.forName(className, true, classLoader);
            } catch (final ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (final MalformedURLException e) {
            e.printStackTrace();
        }
        System.out.println(file.toURI().toURL());

    }

    public boolean compileIt() {

//        try {
//            File root = new File(".");
//            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { root.toURI().toURL() });
//            Class<?> cls = Class.forName(this.getClassName(), true, classLoader);
//        } catch (ClassNotFoundException | MalformedURLException e) {
//            e.printStackTrace();
//        }
//
//        String [] source = { new String(sourceName)};
//        ByteArrayOutputStream baos= new ByteArrayOutputStream();
//
////        new sun.tools.javac.Main(baos,source[0]).compile(source);
//        // if using JDK >= 1.3 then use
////        public static int com.sun.tools.javac.Main.compile(source);
//        tool.compile(source);
//
//        return (baos.toString().indexOf("error")==-1);


        final String[] source = { "-classpath", "target/classes", sourceName };
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        new sun.tools.javac.Main(baos, source[0]).compile(source);
        System.out.print(baos.toString());
        return (baos.toString().indexOf("error") == -1);
    }

    public void runIt() {
        try {
            Class params[] = {};
            Object paramsObj[] = {};
            Class thisClass = Class.forName(className);
            Object iClass = thisClass.newInstance();
            Method thisMethod = thisClass.getDeclaredMethod("doit", params);
            thisMethod.invoke(iClass, paramsObj);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        try {
//            File root = new File(".");
//            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { root.toURI().toURL() });
//            Class<?> cls = Class.forName(this.getClassName(), true, classLoader);
//        } catch (ClassNotFoundException | MalformedURLException e) {
//            e.printStackTrace();
//        }

    }

    public static void main (String args[]) throws MalformedURLException {
        DynamicallyCreateJavaFile mtc = new DynamicallyCreateJavaFile();
        mtc.createIt("haha");
        loadExecutableInterface();
        if (mtc.compileIt()) {
            System.out.println("Running " + mtc.className + ":\n\n");
            mtc.runIt();
        } else{
            System.out.println(mtc.sourceName + " is bad.");
        }
    }
}
