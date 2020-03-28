package galstyan.hayk.typing.app

import galstyan.hayk.typing.model.TextMatcher


class TextMatcherImpl(text: String) : TextMatcher(text) {


	override fun match(input: String): Int {
		var m = 0
		if (input.isNotEmpty())
			while (m < input.length && text[m] == input[m]) m++

		return m
	}
}