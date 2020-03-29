package galstyan.hayk.typing.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import galstyan.hayk.typing.di.AppContainer


abstract class AppBaseFragment(
	protected val appContainer: AppContainer,
	protected val viewModelFactory: ViewModelProvider.Factory
) : Fragment()



