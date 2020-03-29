package galstyan.hayk.typing.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import galstyan.hayk.typing.di.AppContainer
import galstyan.hayk.typing.repository.TextRepository
import galstyan.hayk.typing.ui.AppViewModel
import kotlinx.coroutines.launch


class MainViewModel(appContainer: AppContainer) : AppViewModel(appContainer) {

	val textObservable: LiveData<Text> get() = _textObservable
	private val _textObservable: MutableLiveData<Text> = MutableLiveData()

	private var timer: FinishTimer? = null


	init {
		loadNewText(appContainer.getTimeToFinish())
	}


	fun loadNewText(timeToFinishMillis: Long) {
		disposeTimer()
		val repo = appContainer.getRepository(TextRepository::class.java)
		viewModelScope.launch {
			val text = repo.getText()
			val matcher = appContainer.textMatcherOf(text)
			timer = FinishTimer(timeToFinishMillis)
			_textObservable.value = Text(text, matcher, timer!!)
		}
	}


	override fun onCleared() {
		super.onCleared()
		disposeTimer()
	}


	private fun disposeTimer() {
		timer?.dispose()
	}
}
