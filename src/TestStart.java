import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;


public class TestStart {

    public static  void start(Class myClass) {
        annotationTest(myClass);

    }

    public static  void start(String nameClass) {
        Class myClass = null;
        try {
            myClass = Class.forName(nameClass);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        annotationTest(myClass);
    }

    private static void annotationTest(Class myClass) {
        Method[] methods = myClass.getDeclaredMethods();
        for (Method o : methods) {
            if (o.getAnnotation(Test.class) != null) {
                System.out.println(o);
            }
        }
    }

}
