package galstyan.hayk.typing.app

import android.app.Application
import galstyan.hayk.typing.repository.TextRepository


class App : Application() {

	val appContainer = AppContainerImpl(
		repositories = mapOf(
			TextRepository::class.java to TextMockRepositoryImpl()
		)
	)
}