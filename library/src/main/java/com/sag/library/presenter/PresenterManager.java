package com.sag.library.presenter;

import java.util.HashMap;

import static android.provider.Contacts.SettingsColumns.KEY;

/**
 * Created by SAG on 2017/6/14 0014.
 */

public class PresenterManager {

    private static final HashMap<String, Presenter> PRE_MAP = new HashMap();

    public static void onDestroy(Presenter presenter) {
        String key = presenter.getClass().getName();
        if (PRE_MAP.containsKey(key)) {
            PRE_MAP.remove(key);
        }
    }

    public static void register(Presenter presenter) {
        PRE_MAP.put(presenter.getClass().getName(), presenter);
    }

    public static Presenter key(Class clazz) {
        String key = clazz.getName();
        if (PRE_MAP.containsKey(key)) {
            return PRE_MAP.get(key);
        }
        try {
            throw new Exception("找寻" + key + "对象不存在");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
