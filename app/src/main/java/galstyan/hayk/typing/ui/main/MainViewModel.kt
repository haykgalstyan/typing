package galstyan.hayk.typing.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import galstyan.hayk.typing.di.AppContainer
import galstyan.hayk.typing.repository.TextRepository
import galstyan.hayk.typing.ui.AppViewModel
import kotlinx.coroutines.launch


class MainViewModel(appContainer: AppContainer) : AppViewModel(appContainer) {

	val textObservable: LiveData<TextResult> get() = _textObservable
	private val _textObservable: MutableLiveData<TextResult> = MutableLiveData()

	fun loadNewText() {
		val repo = appContainer.getRepository(TextRepository::class.java)
		viewModelScope.launch {
			val text = repo.getText()
			val matcher = appContainer.textMatcherOf(text)
			_textObservable.value = TextResult(text, matcher)
		}
	}
}
