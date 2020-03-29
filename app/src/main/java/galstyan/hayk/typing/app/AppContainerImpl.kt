package galstyan.hayk.typing.app

import galstyan.hayk.typing.di.AppContainer
import galstyan.hayk.typing.model.TextMatcher
import galstyan.hayk.typing.model.TextMatcherFactory
import galstyan.hayk.typing.repository.Repository


class AppContainerImpl(
	private val repositories: Map<Class<out Repository>, Repository>,
	private val textMatcherFactory: TextMatcherFactory,
	private val timeToFinish: Long
) : AppContainer {


	@Suppress("UNCHECKED_CAST")
	override fun <T : Repository> getRepository(repositoryClass: Class<T>): T =
		repositories[repositoryClass] as T


	override fun textMatcherOf(text: String): TextMatcher = textMatcherFactory.create(text)


	override fun getTimeToFinish(): Long = timeToFinish
}