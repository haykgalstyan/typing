package galstyan.hayk.typing.app

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import galstyan.hayk.typing.ui.main.MainFragment


class AppActivity : AppCompatActivity() {
	private val appContainer = (application as App).appContainer
	private val fragmentFactory = FragmentFactoryImpl(appContainer)

	override fun onCreate(savedInstanceState: Bundle?) {
		supportFragmentManager.fragmentFactory = fragmentFactory
		super.onCreate(savedInstanceState)

		val layout = FrameLayout(this)
		layout.id = View.generateViewId()
		setContentView(layout)

		val navigation = NavigationImpl(supportFragmentManager, layout.id, null)
		navigation.replace(MainFragment(appContainer))
	}
}
