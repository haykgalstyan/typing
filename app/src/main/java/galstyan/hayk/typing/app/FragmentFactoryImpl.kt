package galstyan.hayk.typing.app

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import galstyan.hayk.typing.di.AppContainer


class FragmentFactoryImpl(
	private val appContainer: AppContainer,
	private val viewModelFactory: ViewModelProvider.Factory
) : FragmentFactory() {

	override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
		return try {
			val cls = loadFragmentClass(classLoader, className)
			cls.getConstructor(
				AppContainer::class.java,
				ViewModelProvider.Factory::class.java
			).newInstance(appContainer, viewModelFactory)
		} catch (e: InstantiationException) {
			super.instantiate(classLoader, className)
		}
	}
}