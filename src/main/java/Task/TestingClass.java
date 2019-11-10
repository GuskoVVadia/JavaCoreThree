package Task;

import org.apache.logging.log4j.core.util.JsonUtils;
import org.w3c.dom.ls.LSOutput;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class TestingClass {
    static TreeMap<Integer, Method> map = new TreeMap();
    static Method[] methodsInTestClass = null;
    static Method before = null;
    static Method after = null;
    static int checkBefore = 0;
    static int checkAfter = 0;

    public static void start(Class testClass) {

        methodsInTestClass = testClass.getDeclaredMethods();

        for (Method o : methodsInTestClass) {
            Annotation[] annotations = o.getDeclaredAnnotations();
            for (Annotation a: annotations){
                if (a == o.getAnnotation(AfterSuite.class)) {
                    if (checkAfter > 1) throw new RuntimeException("Больше чем одна аннотация с пометкой AfterSuite");
                    checkAfter++;
                    after = o;
                }
                if (a == o.getAnnotation(Test.class)){
                    Test t = o.getAnnotation(Test.class);
                    map.put(t.value(), o);
                }
                if (a == o.getAnnotation(BeforeSuite.class)){
                    if (checkBefore > 1) throw new RuntimeException("Больше чем одна аннотация с пометкой BeforeSuite");
                    checkBefore++;
                    before = o;
                }
            }
        }
        if (checkBefore == 0) throw new RuntimeException("Нет аннотаций с пометкой AfterSuite");
        if (checkAfter == 0) throw new RuntimeException("Нет аннотаций с пометкой BeforeSuite");

        try {
            Constructor constructor = testClass.getConstructor();
            Object object = constructor.newInstance();

            before.invoke(object);

            for (Map.Entry<Integer, Method> item : map.entrySet() ){
                if (item.getValue().getName().equals("calculate")){
                    if (item.getValue().invoke(object, 5, 5).equals(10)){
                        System.out.println("Правильно");
                    } else System.out.println("Method " + item.getValue() + " работает не верно.");
                }
                if (item.getValue().getName().equals("div")){
                    if (item.getValue().invoke(object, 10, 5).equals(2.0)){
                        System.out.println("Правильно");
                    } else System.out.println("Method " + item.getValue() + " работает не верно.");
                }
            }

            after.invoke(object);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println("Конец тестирования.");
        }

    }

    public static void main(String[] args) {
        start(ExampleClassTest.class);
    }
}
