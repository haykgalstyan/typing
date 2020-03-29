package galstyan.hayk.typing.app

import android.app.Application
import galstyan.hayk.typing.repository.HistoryRepository
import galstyan.hayk.typing.repository.TextRepository
import galstyan.hayk.typing.ui.AppViewModelFactory
import io.paperdb.Paper


class App : Application() {


	private val appContainer = AppContainerImpl(
		repositories = mapOf(
			TextRepository::class.java to TextMockRepositoryImpl(),
			HistoryRepository::class.java to HistoryLocalRepositoryImpl()
		),
		textMatcherFactory = TextMatcherFactoryImpl(),
		timeToFinish = 1000 * 60
	)

	
	// these are framework specific not domain, only implementations depend on these
	val viewModelFactory = AppViewModelFactory(appContainer)
	val fragmentFactory = FragmentFactoryImpl(viewModelFactory)


	override fun onCreate() {
		super.onCreate()

		// Some random, fast to integrate DB
		// Not really a problem since its an implementation details in this architecture
		Paper.init(this);
	}
}