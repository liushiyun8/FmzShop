package top.yundesign.fmz.App;
import android.content.res.Resources;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import top.yundesign.fmz.config.AppConfig;
import top.yundesign.fmz.utils.SpUtils;

@Module
class AppModule {
    private App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public App provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    public ExecutorService provideExecutorService() {
        return Executors.newCachedThreadPool();
    }

    @Provides
    @Singleton
    public DbManager providerDatabaseOpenHelper() {
        DbManager.DaoConfig daoConfig = new DbManager.DaoConfig().setDbName("fmz")
                .setDbVersion(1).setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                        try {
                            db.dropDb();
                        } catch (DbException e) {
                            e.printStackTrace();
                        }
                    }
                });
        return x.getDb(daoConfig);
    }

    @Provides
    @Singleton
    public Resources providerResourceHandler() {
        return application.getResources();
    }

    @Provides
    @Singleton
    public SpUtils providerPreferencesManager() {
        return new SpUtils(application,AppConfig.APP_NAME);
    }
}
