package test.doosik.com.fakeeventbus;

import android.app.Activity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by dskim98 on 16. 8. 1..
 */
public class FakeEventBus {

    private static FakeEventBus instance = null;
    private FakeEventBus() {};
    public static FakeEventBus getInstance() {
        if(instance == null) {
            synchronized (FakeEventBus.class) {
                if(instance == null) {
                    instance = new FakeEventBus();
                }
            }
        }
        return instance;
    }

    private ArrayList<Activity> arrayList = new ArrayList<>();


    public synchronized void register(Activity activity) {
        arrayList.add(activity);
    }

    public synchronized void unregister(Activity activity) {
        arrayList.remove(activity);
    }

    public synchronized void post(EventData eventData) {
        for(Activity activity : arrayList) {
//            try {
//                Method method = activity.getClass().getDeclaredMethod(MainActivity.EVENT_RECEIVE_METHOD_NAME, EventData.class);
//                method.invoke(activity, eventData);
//            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }

            for(Method method : activity.getClass().getDeclaredMethods()) {
                if(method.getAnnotation(OnEventReceiver.class) != null) {
                    method.setAccessible(true);
                    try {
                        method.invoke(activity, eventData);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}


