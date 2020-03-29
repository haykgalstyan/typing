package galstyan.hayk.typing.app

import android.app.Application
import galstyan.hayk.typing.repository.TextRepository
import galstyan.hayk.typing.ui.AppViewModelFactory


class App : Application() {

	val appContainer = AppContainerImpl(
		repositories = mapOf(
			TextRepository::class.java to TextMockRepositoryImpl()
		),
		textMatcherFactory = TextMatcherFactoryImpl(),
		timeToFinish = 1000 * 30
	)

	// these are framework specific not domain, only implementations depend on these
	val viewModelFactory = AppViewModelFactory(appContainer)
	val fragmentFactory = FragmentFactoryImpl(appContainer, viewModelFactory)
}