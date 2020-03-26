package galstyan.hayk.typing.app

import android.app.Application
import galstyan.hayk.typing.di.AppContainer


class App : Application() {


    val appContainer: AppContainer by lazy {
        AppContainerImpl()
    }


    override fun onCreate() {
        super.onCreate()


    }
}