package galstyan.hayk.typing.ui.main

import android.os.CountDownTimer


class FinishTimer(
	private var millisToFinish: Long
) : CountDownTimer(millisToFinish, 1000) {

	private var _listener: TimerListener? = null


	interface TimerListener {
		fun onTick(millisUntilFinished: Long)
		fun onFinish()
	}

	fun setListener(listener: TimerListener): FinishTimer {
		_listener = listener
		return this
	}

	override fun onFinish() {
		_listener?.onFinish();
	}

	override fun onTick(millisUntilFinished: Long) {
		millisToFinish = millisUntilFinished;
		_listener?.onTick(millisUntilFinished);
	}
}