package com.jzhu.study.mf.base;

import android.content.Context;
import android.support.multidex.MultiDexApplication;
import android.util.Log;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jzhu.study.mf.BuildConfig;
import com.jzhu.study.mf.injection.component.ApplicationComponent;
import com.jzhu.study.mf.injection.component.DaggerApplicationComponent;
import com.jzhu.study.mf.injection.module.ApplicationModule;


public class BaseApplication extends MultiDexApplication {

    public static boolean isCheckedUpdate;
    private ApplicationComponent applicationComponent;

    public static Context getContext() {
        return context;
    }

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        initializeInjector();
        if (BuildConfig.DEBUG) {
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

    @Override
    public void onLowMemory() {
        Log.d("***", "memory_low");
        super.onLowMemory();
    }
}
