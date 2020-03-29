package galstyan.hayk.typing.ui.main

import galstyan.hayk.typing.model.TextMatcher


data class Text(val text: String, val matcher: TextMatcher, val timer: FinishTimer)