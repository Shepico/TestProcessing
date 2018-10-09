import java.lang.reflect.Method;
import java.util.*;


public class TestStart {
    //Для сортировки методов Test
    static HashMap<Integer, ArrayList<Method>> listTest = new HashMap<>();


    public static  void start(Class myClass) {
        ArrayList<Method> m = annoBeforeCheck(myClass);
        ArrayList<Method> m2 = annoAfterCheck(myClass);
        annoBeforeSuite(myClass, m);
        annoTestSort(myClass);
        annoTestExecute(myClass);
        annoAfterSuite(myClass, m2);
    }
    //Перезагрузка по строке
    public static  void start(String nameClass) {
        Class myClass = null;
        try {
            myClass = Class.forName(nameClass);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        ArrayList<Method> m = annoBeforeCheck(myClass);
        ArrayList<Method> m2 = annoAfterCheck(myClass);
        annoBeforeSuite(myClass, m);
        annoTestSort(myClass);
        annoTestExecute(myClass);
        annoAfterSuite(myClass, m2);
    }
////////////////////////////////////////////////////////////////////////////////////
    //проверка After
    private static ArrayList<Method> annoAfterCheck(Class myClass) {
        AfterSuite annoAfter;
        Method[] methods = myClass.getDeclaredMethods();
        ArrayList<Method> method = new ArrayList<>();
        for (Method o : methods) {

            annoAfter = o.getAnnotation(AfterSuite.class);
            if (annoAfter != null) {
                method.add(o);
            }

        }
        //если больше одного, то Exception
        if (method.size() > 1) {
            throw new RuntimeException();
        }
        //
        return method;
    }
    //Выполнение After
    private static <myClass> void annoAfterSuite(Class myClass, ArrayList<Method> method) {

        myClass objClass = null;
        try {
            objClass = (myClass) myClass.newInstance();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        Method m = method.get(0);
        m.setAccessible(true); // если метод private
        try {
            m.invoke(objClass);
        }catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /////////////////////////////////////////////////////////////////////////////////////////
    //Проверка Before
    private static ArrayList<Method> annoBeforeCheck(Class myClass) {
        BeforeSuite annoBefore;
        Method[] methods = myClass.getDeclaredMethods();
        ArrayList<Method> method = new ArrayList<>();
        for (Method o : methods) {

            annoBefore = o.getAnnotation(BeforeSuite.class);
            if (annoBefore != null) {
                method.add(o);
            }

        }
        //если больше одного, то Exception
        if (method.size() > 1) {
            throw new RuntimeException();
        }

        return method;
    }
    //Выполнение Before
    private static <myClass> void annoBeforeSuite(Class myClass, ArrayList<Method> method) {
        //
        myClass objClass = null;
        try {
            objClass = (myClass) myClass.newInstance();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        Method m = method.get(0);
        m.setAccessible(true); // если метод private
        try {
            m.invoke(objClass);
        }catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    //////////////////////////////////////////////////////////////////////
    //Сортировка Test
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
            }
        }
    }
    //Выполнение Test
    private static <myClass> void annoTestExecute(Class myClass) {
        myClass objClass = null;
        try {
            objClass = (myClass) myClass.newInstance();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        for (Map.Entry<Integer, ArrayList<Method>> e : listTest.entrySet()){
            for (int i=0; i<e.getValue().size(); i++){
                Method m = e.getValue().get(i);
                try {
                    m.invoke(objClass);
                }catch (Exception ex) {
                    ex.printStackTrace();
                }
            };
        }
    }
}
