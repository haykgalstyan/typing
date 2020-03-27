package galstyan.hayk.typing.app

import galstyan.hayk.typing.di.AppContainer
import galstyan.hayk.typing.repository.Repository


class AppContainerImpl(
	private val repositories: Map<Class<out Repository>, Repository>
) : AppContainer {


	override fun <T : Repository> getRepository(repositoryClass: Class<T>): T {
		@Suppress("UNCHECKED_CAST")
		return repositories[repositoryClass] as T
	}
}