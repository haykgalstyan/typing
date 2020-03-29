package galstyan.hayk.typing.ui.main

import android.os.Bundle
import android.text.Spannable
import android.text.style.CharacterStyle
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import galstyan.hayk.typing.R
import galstyan.hayk.typing.di.AppContainer
import galstyan.hayk.typing.model.TextMatcher
import galstyan.hayk.typing.ui.AppBaseFragment
import galstyan.hayk.typing.ui.AppViewModelFactory
import galstyan.hayk.typing.ui.getColor
import kotlinx.android.synthetic.main.main_fragment.*
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.util.*


class MainFragment(appContainer: AppContainer) : AppBaseFragment(appContainer) {

	private val timeToFinishMillis = 1000L * 30
	private val timeFormat = SimpleDateFormat("mm:ss", Locale.getDefault())

	private val progressStyle by lazy { ForegroundColorSpan(getColor(R.color.colorAccent)) }
	private val errorStyle by lazy { ForegroundColorSpan(getColor(R.color.colorError)) }

	private val viewModel by lazy {
		ViewModelProvider(this, AppViewModelFactory(appContainer)).get(MainViewModel::class.java)
	}


	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return inflater.inflate(R.layout.main_fragment, container, false)
	}


	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		input.requestFocus()
		viewModel.loadNewText(timeToFinishMillis)
		viewModel.textObservable.observe(viewLifecycleOwner, Observer { (text, matcher, timer) ->
			onNewText(text, timer, onTextChanged = { s ->
				onTextChanged(s, matcher)
			})
		})
	}


	private fun onNewText(
		text: String,
		timer: FinishTimer,
		onTextChanged: (s: CharSequence) -> Unit
	) {

		time.setTextColor(getColor(R.color.colorPrimary))
		timer.setListener(object : FinishTimer.TimerListener {
			override fun onTick(millisUntilFinished: Long) {
				time.text = timeFormat.format(millisUntilFinished)
			}

			override fun onFinish() {
				time.setTextColor(getColor(R.color.colorError))
				showStats()
			}
		}).start()

		output.setText(text, TextView.BufferType.SPANNABLE)
		input.doOnTextChanged { s, _, _, _ -> onTextChanged(s ?: "") }
	}


	private fun showStats() {
		// todo
	}


	private fun onTextChanged(inputText: CharSequence, matcher: TextMatcher) {
		val matchIndex = matcher.match(inputText)
		applyStyleSpan(inputText, errorStyle, start = matchIndex)
		applyStyleSpan(output.text, progressStyle, end = matchIndex)
	}


	private fun applyStyleSpan(
		chars: CharSequence?,
		span: CharacterStyle,
		start: Int = 0,
		end: Int = chars?.length ?: 0
	) {
		(chars as Spannable).apply {
			getSpans(start, end, span::class.java).forEach { removeSpan(it) }
			setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
		}
	}
}

