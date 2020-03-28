package galstyan.hayk.typing.ui

import android.text.Editable
import android.text.TextWatcher

/**
 * Adapter for [TextWatcher] to prevent empty overrides
 */
interface OnInputListener : TextWatcher {
	override fun afterTextChanged(s: Editable?) {}
	override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
	override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
}