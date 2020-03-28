package galstyan.hayk.typing.ui.main

import android.content.Context
import android.os.Bundle
import android.text.Spannable
import android.text.style.CharacterStyle
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import galstyan.hayk.typing.R
import galstyan.hayk.typing.di.AppContainer
import galstyan.hayk.typing.model.TextMatcher
import galstyan.hayk.typing.ui.AppBaseFragment
import galstyan.hayk.typing.ui.AppViewModelFactory
import kotlinx.android.synthetic.main.main_fragment.*


class MainFragment(appContainer: AppContainer) : AppBaseFragment(appContainer) {


	private val viewModel: MainViewModel by lazy {
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
		super.onViewCreated(view, savedInstanceState)
		input.requestFocus()

		viewModel.loadNewText()
		viewModel.textObservable.observe(viewLifecycleOwner, Observer {
			setNewText(view.context, it.text, it.matcher)
		})
	}


	private fun setNewText(context: Context, text: String, matcher: TextMatcher) {
		output.setText(text, TextView.BufferType.SPANNABLE)
		val progressSpan = createColorSpan(context, R.color.colorAccent)
		val errorSpan = createColorSpan(context, R.color.colorError)

		input.doOnTextChanged { s, _, _, _ ->
			run {
				val matchIndex = matcher.match(s!!)
				applySpan(s, errorSpan, start = matchIndex)
				applySpan(output.text, progressSpan, end = matchIndex)
			}
		}
	}


	private fun createColorSpan(context: Context, @ColorRes colorRes: Int): CharacterStyle =
		ForegroundColorSpan(ContextCompat.getColor(context, colorRes))


	private fun applySpan(
		chars: CharSequence?,
		span: Any,
		start: Int = 0,
		end: Int = chars?.length ?: 0
	) {
		(chars as Spannable).apply {
			getSpans(start, end, span::class.java).forEach { removeSpan(it) }
			setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
		}
	}
}

