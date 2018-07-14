package top.yundesign.fmz.App;


import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { AppModule.class})
public interface AppComponent {

	void inject(App app);

	void inject(AppActivity activity);

}
