package galstyan.hayk.typing.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import galstyan.hayk.typing.R
import galstyan.hayk.typing.di.AppContainer
import galstyan.hayk.typing.ui.AppBaseFragment
import galstyan.hayk.typing.ui.BaseAppFragment
import kotlinx.android.synthetic.main.main_fragment.*


class MainFragment(appContainer: AppContainer) : AppBaseFragment(appContainer) {

	private val viewModel: MainViewModel by lazy {
		ViewModelProvider(this).get(MainViewModel::class.java)
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
		out.text = appContainer.
	}
}
