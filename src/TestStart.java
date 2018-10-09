import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import java.util.*;


public class TestStart {

    //static TreeMap<Integer, Method> listTest = new TreeMap<>();
    static HashMap<Integer, ArrayList<Method>> listTest = new HashMap<>();


    public static  void start(Class myClass) {
        annoTestSort(myClass);
        annoTestExecute(myClass);


    }

    public static  void start(String nameClass) {
        Class myClass = null;
        try {
            myClass = Class.forName(nameClass);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        annoTestSort(myClass);
        annoTestExecute(myClass);
    }

    private static void annoTestSort(Class myClass) {
        listTest.clear();
        Test anoTest;
        Method[] methods = myClass.getDeclaredMethods();
        for (Method o : methods) {
            anoTest = o.getAnnotation(Test.class);
            if (anoTest != null) {
                ArrayList<Method> currentList = listTest.get(anoTest.priority());
                if (currentList == null) {
                    currentList = new ArrayList<>();
                    currentList.add(o);
                }else{
                    currentList.add(o);
                }
                listTest.put(anoTest.priority(), currentList);
                //System.out.println(anoTest.priority());
            }
        }
    }

    private static <myClass> void annoTestExecute(Class myClass) {
        myClass objClass = null;
        try {
            objClass = (myClass) myClass.newInstance();

            //Class myClassChablon = Class.forName(myClass.getName());
            //Class one = myClassChablon.newInstance();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        for (Map.Entry<Integer, ArrayList<Method>> e : listTest.entrySet()){
            //System.out.println(e.getKey() + " ");
            for (int i=0; i<e.getValue().size(); i++){
                Method m = e.getValue().get(i);
                try {
                    m.invoke(objClass);
                }catch (Exception ex) {
                    ex.printStackTrace();
                }
                //System.out.println(e.getValue().get(i));
            };
        }
    }



}
