package galstyan.hayk.typing.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import galstyan.hayk.typing.di.AppContainer
import galstyan.hayk.typing.repository.TextRepository
import galstyan.hayk.typing.ui.AppViewModel
import kotlinx.coroutines.launch


class MainViewModel(appContainer: AppContainer) : AppViewModel(appContainer) {
	private val textRepository = appContainer.getRepository(TextRepository::class.java)

	val textObservable: MutableLiveData<String> = MutableLiveData<String>()


	fun loadNewText() {
		viewModelScope.launch { textObservable.postValue(textRepository.getText()) }
	}
}
