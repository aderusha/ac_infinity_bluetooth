package com.eternal.framework.component;

import android.app.Activity;
import androidx.fragment.app.Fragment;
import java.util.Iterator;
import java.util.Stack;

public class AppManager {
    private static Stack<Activity> activityStack;
    private static Stack<Fragment> fragmentStack;
    private static AppManager instance;
    private static int started;

    private AppManager() {
    }

    public static AppManager getAppManager() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    public static Stack<Activity> getActivityStack() {
        return activityStack;
    }

    public static Stack<Fragment> getFragmentStack() {
        return fragmentStack;
    }

    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    public void addStart() {
        started++;
    }

    public void subStart() {
        started--;
    }

    public boolean isBack() {
        return started == 0;
    }

    public void removeActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
        }
    }

    public boolean isActivity() {
        Stack<Activity> stack = activityStack;
        if (stack != null) {
            return !stack.isEmpty();
        }
        return false;
    }

    public Activity currentActivity() {
        return (Activity) activityStack.lastElement();
    }

    public int getActivityCount() {
        return activityStack.size();
    }

    public void finishActivity() {
        finishActivity((Activity) activityStack.lastElement());
    }

    public void finishActivity(Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            activity.finish();
        }
    }

    public void finishActivity(Class<?> cls) {
        Iterator it = activityStack.iterator();
        while (it.hasNext()) {
            Activity activity = (Activity) it.next();
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                return;
            }
        }
    }

    public void finishOtherActivity(Class<?> cls) {
        Iterator it = activityStack.iterator();
        while (it.hasNext()) {
            Activity activity = (Activity) it.next();
            if (!activity.getClass().equals(cls)) {
                finishActivity(activity);
                return;
            }
        }
    }

    public void finishAllActivity() {
        int size = activityStack.size();
        for (int i = 0; i < size; i++) {
            if (activityStack.get(i) != null) {
                finishActivity((Activity) activityStack.get(i));
            }
        }
        activityStack.clear();
    }

    public Activity getActivity(Class<?> cls) {
        Stack<Activity> stack = activityStack;
        if (stack == null) {
            return null;
        }
        Iterator it = stack.iterator();
        while (it.hasNext()) {
            Activity activity = (Activity) it.next();
            if (activity.getClass().equals(cls)) {
                return activity;
            }
        }
        return null;
    }

    public void addFragment(Fragment fragment) {
        if (fragmentStack == null) {
            fragmentStack = new Stack<>();
        }
        fragmentStack.add(fragment);
    }

    public void removeFragment(Fragment fragment) {
        if (fragment != null) {
            fragmentStack.remove(fragment);
        }
    }

    public boolean isFragment() {
        Stack<Fragment> stack = fragmentStack;
        if (stack != null) {
            return !stack.isEmpty();
        }
        return false;
    }

    public Fragment currentFragment() {
        Stack<Fragment> stack = fragmentStack;
        if (stack != null) {
            return (Fragment) stack.lastElement();
        }
        return null;
    }

    public void AppExit() {
        try {
            finishAllActivity();
        } catch (Exception e) {
            activityStack.clear();
            e.printStackTrace();
        }
    }
}
