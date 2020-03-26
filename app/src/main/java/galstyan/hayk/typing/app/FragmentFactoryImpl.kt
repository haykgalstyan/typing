package galstyan.hayk.typing.app

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import galstyan.hayk.typing.di.AppContainer


/**
 * Passes DI Container into fragments
 */
class FragmentFactoryImpl(private val appContainer: AppContainer) : FragmentFactory() {

	override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
		return try {
			val cls = loadFragmentClass(classLoader, className)
			cls.getConstructor(AppContainer::class.java).newInstance(appContainer)
		} catch (e: InstantiationException) {
			super.instantiate(classLoader, className)
		}
	}
}
