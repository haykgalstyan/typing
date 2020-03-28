package galstyan.hayk.typing.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import galstyan.hayk.typing.di.AppContainer
import galstyan.hayk.typing.model.TextMatcher
import galstyan.hayk.typing.repository.TextRepository
import galstyan.hayk.typing.ui.AppViewModel
import kotlinx.coroutines.launch


class MainViewModel(appContainer: AppContainer) : AppViewModel(appContainer) {

	val textObservable: LiveData<String> get() = _textObservable
	val progressObservable: LiveData<Int> get() = _progressObservable
	val errorTextObservable: LiveData<String> get() = _errorTextObservable

	private val _textObservable: MutableLiveData<String> = MutableLiveData<String>()
	private val _errorTextObservable: MutableLiveData<String> = MutableLiveData<String>()
	private val _progressObservable: MutableLiveData<Int> = MutableLiveData<Int>()

	private val textRepository = appContainer.getRepository(TextRepository::class.java)
	var textMatcher: TextMatcher? = null


	fun loadNewText() {
		viewModelScope.launch {
			val text = textRepository.getText()
			textMatcher = appContainer.textMatcherOf(text)
			_textObservable.value = text
		}
	}


	fun onInput(input: String) {
		val matchIndex = textMatcher?.match(input) ?: 0
		val errorText = input.substring(matchIndex, input.length)

		_errorTextObservable.value = errorText
		_progressObservable.value = matchIndex
	}
}
