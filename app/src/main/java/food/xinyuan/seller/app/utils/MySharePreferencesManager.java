package food.xinyuan.seller.app.utils;

import android.content.Context;

import java.util.Set;


public class MySharePreferencesManager {

    public static String USER_PSWD = "userPswd";
    public static String USER_NAME = "userName";

    private static class SingletonHolder {
        private static final MySharePreferencesManager instance = new MySharePreferencesManager();
    }

    public static MySharePreferencesManager getInstance() {
        return MySharePreferencesManager.SingletonHolder.instance;
    }

    private Context context;
    private SecureSPUtils mSecurePrefs;

    public void init(Context context) {
        this.context = context;
    }

    /**
     * Single point for the app to get the secure prefs object
     *
     * @return
     */
    public SecureSPUtils getSharedPreferences() {
        if (mSecurePrefs == null) {
            mSecurePrefs = new SecureSPUtils(context, "", "EGR_SP.xml");
            SecureSPUtils.setLoggingEnabled(true);
        }
        return mSecurePrefs;
    }

    public boolean putString(String key, String value) {
        return getSharedPreferences().edit().putString(key, value).commit();
    }

    public boolean putBoolean(String key, boolean value) {
        return getSharedPreferences().edit().putBoolean(key, value).commit();
    }

    public boolean putInt(String key, int value) {
        return getSharedPreferences().edit().putInt(key, value).commit();
    }

    public boolean putFloat(String key, float value) {
        return getSharedPreferences().edit().putFloat(key, value).commit();
    }

    public boolean putLong(String key, long value) {
        return getSharedPreferences().edit().putLong(key, value).commit();
    }

    public boolean putStringSet(String key, Set<String> value) {
        return getSharedPreferences().edit().putStringSet(key, value).commit();
    }

    public String getString(String key, String defaultValue) {
        return getSharedPreferences().getString(key, defaultValue);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return getSharedPreferences().getBoolean(key, defaultValue);
    }

    public int getInt(String key, int defaultValue) {
        return getSharedPreferences().getInt(key, defaultValue);
    }

    public float getFloat(String key, float defaultValue) {
        return getSharedPreferences().getFloat(key, defaultValue);
    }

    public long getLong(String key, long defaultValue) {
        return getSharedPreferences().getLong(key, defaultValue);
    }

    public Set<String> getStringSet(String key, Set<String> defaultValue) {
        return getSharedPreferences().getStringSet(key, defaultValue);
    }

    public boolean remove(String key) {
        return getSharedPreferences().edit().remove(key).commit();
    }

    public boolean removeAll() {
        return getSharedPreferences().edit().clear().commit();
    }
}
