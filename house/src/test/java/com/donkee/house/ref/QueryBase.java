package com.donkee.house.ref;

import com.donkee.house.dao.Dept;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class QueryBase {

    @Test
    public void testRef() throws Exception {
        //核心类Class类
        //1.得到Class类的对象的方法
        //1)方式一Object类中的getClass方法
        Dept dept = new Dept();//创建Dept和Class类两个对象
        Class clazz1 = dept.getClass();//任何实例getClass方法得到Class类的实例
        //2)方式二
        Class clazz2 = Dept.class;
        //3)方式三
        Class clazz3 = Class.forName("com.donkee.house.entity.Dept");

        //2.通过Class对象可以获取某个类中的：构造方法、成员变量、成员方法；并访问成员
       // Field[] fields = clazz3.getFields(); //获取所有的"公有字段"
        Field[] fields = clazz3.getDeclaredFields(); //获取所有字段，包括：私有、受保护、默认、公有
        System.out.println("****************所有字段****************");
        for (Field field : fields){
            System.out.println(field.getName());
        }
        System.out.println("****************所有方法****************");
        //Method[] methods = clazz3.getMethods(); //获取所有"公有方法"、(包含了父类的方法也包含Object类)
        Method[] methods = clazz3.getDeclaredMethods();//获取所有的成员方法，包括私有的(不包括继承的)
        for (Method method : methods){
            System.out.println(method.getName());
        }

        //已知字段名字，为字段赋值
        Object obj = clazz3.newInstance();//得到指定类的一个实例，调用指定类的不带参的构造方法
        String fieldName = "pid";
        Field field = clazz3.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj,1);
    }
    @Test
    public void test3(){
        //普通集合
         List list1 = new ArrayList<>();
         Dept dept1 = new Dept();
         Dept dept2 = new Dept();
         String str = new String("abc");
         list1.add(dept1);list1.add(dept2);list1.add(str);
         Dept d1 = (Dept) list1.get(0);
         //泛型集合的优点：数据类型检查，安全；性能高
        List<Dept> list2 = new ArrayList<Dept>();
        list2.add(dept1);list1.add(dept2);//list2.add(str);
        Dept d2 = list2.get(0);//不需要强制转换，性能高
    }
}
