package galstyan.hayk.typing.ui.main

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.core.text.clearSpans
import androidx.core.text.getSpans
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import galstyan.hayk.typing.R
import galstyan.hayk.typing.di.AppContainer
import galstyan.hayk.typing.ui.AppBaseFragment
import galstyan.hayk.typing.ui.AppViewModelFactory
import galstyan.hayk.typing.ui.OnInputListener
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
		activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
		super.onViewCreated(view, savedInstanceState)
		input.requestFocus()

		viewModel.loadNewText()
		viewModel.textObservable.observe(viewLifecycleOwner, Observer {
			output.setText(it, TextView.BufferType.SPANNABLE)
		})


		input.addTextChangedListener(object : OnInputListener {
			override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
				applySpans(s)
			}
		})
	}


	private fun applySpans(inputChars: CharSequence?) {
		val matchIndex = viewModel.textMatcher?.match(inputChars.toString()) ?: 0

		applyColorSpan(inputChars, Color.RED, start = matchIndex)
		applyColorSpan(output.text, Color.GREEN, end = matchIndex)
	}


	private fun applyColorSpan(
		chars: CharSequence?,
		color: Int,
		start: Int = 0,
		end: Int = chars?.length ?: 0
	) {
		if (chars is Spannable) chars.apply {
			getSpans<ForegroundColorSpan>().forEach { removeSpan(it) }
			setSpan(ForegroundColorSpan(color), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
		}
	}
}

