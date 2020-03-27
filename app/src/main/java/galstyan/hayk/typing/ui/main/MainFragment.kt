package galstyan.hayk.typing.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import galstyan.hayk.typing.R
import galstyan.hayk.typing.di.AppContainer
import galstyan.hayk.typing.repository.TextRepository
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

		viewModel.loadNewText()
		viewModel.textObservable.observe(viewLifecycleOwner, Observer {
			out.text = it
		})
	}
}
