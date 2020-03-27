package galstyan.hayk.typing.app

import android.app.Application
import galstyan.hayk.typing.di.AppContainer
import galstyan.hayk.typing.repository.TextRepository


class App : Application() {


	val appContainer: AppContainer by lazy {
		AppContainerImpl(
			mapOf(
				TextRepository::class.java to TextRepositoryMockImpl()
			)
		)
	}


	override fun onCreate() {
		super.onCreate()


	}
}