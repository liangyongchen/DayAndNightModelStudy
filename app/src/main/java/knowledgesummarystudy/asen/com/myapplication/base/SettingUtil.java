package knowledgesummarystudy.asen.com.myapplication.base;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;

import knowledgesummarystudy.asen.com.myapplication.R;

/**
 * 设置里面的Key的参数设置，key 基本在 xml 的文件夹布局里面设置
 * Created by asus on 2017/12/13.
 * <p>
 * 安卓 getSharedPreferences 与 getPreferences 与 getDefaultSharedPreferences 的区别
 * 1.SharedPreferences pre = Context.getSharedPreferences(String name,int mode);
 * 注：name为本组件的配置文件名(自定义)，当这个文件不存在时，直接创建，如果已经存在，则直接使用
 * （如果想要与本应用程序的其他组件共享此配置文件
 * <p>
 * 2.SharedPreferences pre = Activity.getPreferences(int mode);
 * 注：这个方法默认使用当前类不带包名的类名作为文件的名称。
 * <p>
 * 3.SharedPreferences pre = PreferenceManager.getDefaultSharedPreferences(Context);
 * 注：每个应用都有一个默认的配置文件preferences.xml，使用getDefaultSharedPreferences获取。
 * (在 xml 文件夹下创建的xml文件)
 */

public class SettingUtil {

    private SharedPreferences setting = PreferenceManager.getDefaultSharedPreferences(app.getContext());

    public static SettingUtil getInstance() {
        return SettingsUtilInstance.instance;
    }

    private static final class SettingsUtilInstance {
        private static final SettingUtil instance = new SettingUtil();
    }


    // region // 自动切换 — 日夜间模式 ：时间设置


    // region // 日夜间模式设置 — key : 没有绑定 xml 布局的 key 值

    /**
     * 设置夜间模式
     * <p>
     * key:没有绑定xml布局的key值
     */
    public void setIsNightMode(boolean flag) {
        setting.edit().putBoolean("switch_nightMode", flag).apply();
    }

    /**
     * 获取夜间模式—是否开启
     * <p>
     * key:没有绑定xml布局的key值
     */
    public boolean getIsNightMode() {
        return setting.getBoolean("switch_nightMode", false);
    }

    // endregion

    // region // 时间设置 — key : 没有绑定 xml 布局的 key 值

    // 夜间 — 时
    public String getNightStartHour() {
        return setting.getString("night_startHour", "22");
    }

    public void setNightStartHour(String nightStartHour) {
        setting.edit().putString("night_startHour", nightStartHour).apply();
    }

    // 夜间 — 分
    public String getNightStartMinute() {
        return setting.getString("night_startMinute", "00");
    }

    public void setNightStartMinute(String nightStartMinute) {
        setting.edit().putString("night_startMinute", nightStartMinute).apply();
    }

    // 日间 — 时
    public String getDayStartHour() {
        return setting.getString("day_startHour", "06");
    }

    public void setDayStartHour(String dayStartHour) {
        setting.edit().putString("day_startHour", dayStartHour).apply();
    }

    // 日间 — 分
    public String getDayStartMinute() {
        return setting.getString("day_startMinute", "00");
    }

    public void setDayStartMinute(String dayStartMinute) {
        setting.edit().putString("day_startMinute", dayStartMinute).apply();
    }

    // endregion

    // region // 自动切换日/夜间模式设置 — key : 有绑定 xml 布局的 key 值

    /**
     * 获取 自动切换夜间模式 是否开启
     * key : 有绑定 xml 布局的 key 值
     */
    public boolean getIsAutoNightMode() {
        return setting.getBoolean("auto_nightMode", false);
    }

    /**
     * 获取是否开启自动切换夜间模式
     */
    public void setIsAutoNightMode(boolean flag) {
        setting.edit().putBoolean("auto_nightMode", flag).apply();
    }

    // endregion


    // endregion


    /**
     * 获取主题颜色（xml 有key值）
     */
    public int getColor() {
        int defaultColor = app.getContext().getResources().getColor(R.color.colorPrimaryDark);
        int color = setting.getInt("color", defaultColor);
        if ((color != 0) && Color.alpha(color) != 255) {
            return defaultColor;
        }
        return color;
    }

    /**
     * 设置主题颜色
     */
    public void setColor(int color) {
        setting.edit().putInt("color", color).apply();
    }

    /**
     * 获取图标值(xml 有 key 值)
     */
    public int getCustomIconValue() {
        String s = setting.getString("custom_icon", "0");
        return Integer.parseInt(s);
    }


    /**
     * 获取是否开启导航栏上色
     */
    public boolean getNavBar() {
        return setting.getBoolean("nav_bar", false);
    }

}
