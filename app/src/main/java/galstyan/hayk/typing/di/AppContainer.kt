package galstyan.hayk.typing.di

import galstyan.hayk.typing.repository.Repository


interface AppContainer {

	/**
	 * Get a Repository marked by the [Repository] interface
	 * Using java class so this can be used by java code if needed
	 */
	fun <T : Repository> getRepository(repositoryClass: Class<T>): T

}