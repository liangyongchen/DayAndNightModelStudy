package knowledgesummarystudy.asen.com.myapplication.base;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;

import java.util.Calendar;

/**
 * Created by asus on 2017/12/14.
 */

public class app extends Application {

    public static Context AppContext;

    private static Context mContext;

    public static synchronized app getContext() {
        return (app) mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AppContext = getApplicationContext();
        mContext = getApplicationContext();
        initTheme();


    }

    // 设置 app 的 主题 是什么模式
    private void initTheme() {

        SettingUtil sUtil = SettingUtil.getInstance();

        // 判断是否是自动模式
        if (sUtil.getIsAutoNightMode()) {

            // 获取设置的时间
            int nightStartHour = Integer.parseInt(sUtil.getNightStartHour());
            int nightStartMinute = Integer.parseInt(sUtil.getNightStartMinute());
            int dayStartHour = Integer.parseInt(sUtil.getDayStartHour());
            int dayStartMinute = Integer.parseInt(sUtil.getDayStartMinute());

            // 获取当前的时间
            Calendar calendar = Calendar.getInstance(); // 日历
            int currentHour = calendar.get(Calendar.HOUR_OF_DAY); // 24小时制
            int currentMinute = calendar.get(Calendar.MINUTE);    // 分钟

            int nightValue = nightStartHour * 60 + nightStartMinute;
            int dayValue = dayStartHour * 60 + dayStartMinute;
            int currentValue = currentHour * 60 + currentMinute;

            // 判断 是 日间/夜间 模式
            if (currentValue >= nightValue || currentValue <= dayValue) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                sUtil.setIsNightMode(true);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                sUtil.setIsNightMode(false);
            }

        } else {

            // 获取当前主题
            if (sUtil.getIsNightMode()) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }

        }

    }


}
