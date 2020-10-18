/**
 * @author teng.xue
 * @date 2020/10/16
 * @since
 **/
package week1;

import java.io.*;
import java.lang.reflect.Method;

public class HelloClassLoader extends ClassLoader{

    private String path;

    public HelloClassLoader(String path){
        this.path=path;
    }

    /**
     * 这里重写了findClass方法
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classDate=getClassData();
        for(int i=0;i<classDate.length;i++){
            classDate[i]=(byte) (255-classDate[i]);
        }

        return defineClass(name,classDate,0,classDate.length);
    }

    /**
     * 从文件中加载字节码.这里使用文件流的方式
     * @return
     */
    private byte[] getClassData()  {
        File classFile=new File(path);
        ByteArrayOutputStream out=null;
        try (FileInputStream in=new FileInputStream(classFile)){
            out=new ByteArrayOutputStream();
            byte[] buffer=new byte[1024];
            int size=0;
            while (((size=in.read(buffer))!=-1)){
                out.write(buffer,0,size);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

    public static void main(String[] args) throws Exception{
        String path="F:/workspaces/java0/Hello.xlass";
        HelloClassLoader myClassLoader=new HelloClassLoader(path);
        Class clazz=myClassLoader.loadClass("Hello");
        Method method=clazz.getMethod("hello",null);
        Object obj=clazz.newInstance();
        method.invoke(obj,null);
    }
}
