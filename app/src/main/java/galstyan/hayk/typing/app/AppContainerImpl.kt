package galstyan.hayk.typing.app

import galstyan.hayk.typing.di.AppContainer


class AppContainerImpl : AppContainer {

	val textRepository = TextRepositoryMockImpl()

}