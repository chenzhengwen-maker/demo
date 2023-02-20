package com.example.demo.reflect;

import com.example.demo.proxy.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

public class ReflectDemo {
    @Test
    public void test1() throws IllegalAccessException, InstantiationException {
        Class<Person> Person = Person.class;
        Person obj = Person.newInstance();
        System.out.println(obj);
    }

    @Test
    public void test2(){
        Class<Person> obj = Person.class;

        //获取属性结构
        //getFields():获取当前运行时类及其父类中声明为public访问权限的属性
        Field[] fields = obj.getFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }

        //getDeclaredFields():获取当前运行时类中声明的所属性。（不包含父类中声明的属性
        Field[] declaredFields = obj.getDeclaredFields();
        for(Field field : declaredFields){
            System.out.println(field.getName());
        }

    }

    @Test
    public void test3(){
        Class<Person> personClass = Person.class;
        //getMethods():获取当前运行时类及其所父类中声明为public权限的方法
        Method[] methods = personClass.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }
        //getDeclaredMethods():获取当前运行时类中声明的所方法。（不包含父类中声明的方法
        Method[] declaredMethods = personClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod.getName());
        }
    }

    /*
    获取构造器结构

     */
    @Test
    public void test4(){
        Class<Person> personClass = Person.class;
        //getConstructors():获取当前运行时类中声明为public的构造器
        Constructor<?>[] constructors = personClass.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor.getName());
        }
        //getDeclaredConstructors():获取当前运行时类中声明的所的构造器
        Constructor[] declaredConstructors = personClass.getDeclaredConstructors();
        for(Constructor c : declaredConstructors){
            System.out.println(c);
        }
    }

    /*
        获取运行时类的父类

         */
    @Test
    public void test5(){
        Class clazz = Person.class;
        Class superclass = clazz.getSuperclass();
        System.out.println(superclass);
    }

    /*
   获取运行时类的带泛型的父类

    */
    @Test
    public void test6(){
        Class clazz = Person.class;

        Type genericSuperclass = clazz.getGenericSuperclass();
        System.out.println(genericSuperclass);
        ParameterizedType paramType = (ParameterizedType) genericSuperclass;
        //获取泛型类型
        Type[] actualTypeArguments = paramType.getActualTypeArguments();
//        System.out.println(actualTypeArguments[0].getTypeName());
        System.out.println(((Class)actualTypeArguments[0]).getName());
    }

    /*
    获取运行时类实现的接口
     */
    @Test
    public void test7(){
        Class clazz = UserService.class;

        Class[] interfaces = clazz.getInterfaces();
        for(Class c : interfaces){
            System.out.println(c);
        }

        System.out.println();
        //获取运行时类的父类实现的接口
        Class[] interfaces1 = clazz.getSuperclass().getInterfaces();
        for(Class c : interfaces1){
            System.out.println(c);
        }

    }

    /*
        获取运行时类所在的包

     */
    @Test
    public void test8(){
        Class clazz = Person.class;

        Package pack = clazz.getPackage();
        System.out.println(pack);
    }

    /*
        获取运行时类声明的注解

     */
    @Test
    public void test9(){
        Class clazz = Person.class;

        Annotation[] annotations = clazz.getAnnotations();
        for(Annotation annos : annotations){
            System.out.println(annos);
        }
    }

}
