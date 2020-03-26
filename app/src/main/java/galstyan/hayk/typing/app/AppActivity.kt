package galstyan.hayk.typing.app

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import galstyan.hayk.typing.ui.main.MainFragment


class AppActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appContainer = (application as App).appContainer

        val layout = FrameLayout(this)
        layout.id = View.generateViewId()
        setContentView(layout)

        val navigation = NavigationImpl(supportFragmentManager, layout.id, null)
        navigation.replace(MainFragment.newInstance())
    }
}
