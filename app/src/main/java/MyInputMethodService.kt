@file:Suppress("DEPRECATION")

import android.inputmethodservice.Keyboard  // TODO: replace deprecated class
import android.inputmethodservice.KeyboardView  // TODO: replace deprecated class
import android.text.TextUtils
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputConnection
import com.example.hex_keyboard.R


class MyInputMethodService : android.inputmethodservice.InputMethodService(), KeyboardView.OnKeyboardActionListener {
    private var caps = false

    override fun swipeRight() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPress(primaryCode: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRelease(primaryCode: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun swipeLeft() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun swipeUp() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun swipeDown() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onKey(primaryCode: Int, keyCodes: IntArray?) {
        val inputConnection = currentInputConnection
        if (inputConnection!=null) {
            when (primaryCode) {
                Keyboard.KEYCODE_DELETE -> {
                    val selectedText = inputConnection.getSelectedText(0)
                    if (TextUtils.isEmpty(selectedText)) {
                        inputConnection.deleteSurroundingText(1, 0)
                    }
                    else {
                        inputConnection.commitText("", 1)
                    }
                }
                Keyboard.KEYCODE_DONE -> {
                    inputConnection.sendKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER))
                }
                else -> {
                    val code = primaryCode.toChar()  // TODO: change val to var for caps
                    // TODO: if caps is needed - here is the place to convert code.toUpperCase
                    inputConnection.commitText(code.toString(), 1)
                }
                // TODO: currently caps is not used for caps lock,
                //  if needed, add case for Keyboard.KEYCODE_SHIFT here,
                //  change keyboard display to caps and change caps flag
                //  java code - caps = !caps; keyboard.setShifted(caps); keyboardView.invalidateAllKeys();
            }

        }
    }

    override fun onText(text: CharSequence?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

// TODO: delete this java implementation when kotlin implementation below is added
//    override fun onCreateInputView(): View {
//        keyboardView = getLayoutInflater().inflate(R.layout.keyboard_view, null)
//        keyboard = Keyboard(R.xml.keys_layout)
//        keyboardView.setKeyboard(keyboard)
//        keyboardView.setOnKeyboardActionListener(this)
//        return keyboardView
//    }

    override fun onCreateInputView(): View {
        return layoutInflater.inflate(R.layout.keyboard_view, null).apply {
            if (this is MyKeyboardView) { //  TODO: MyKeyboardView is an instance of a custom implementation of KeyboardView that renders a Keyboard.
                                          //   what does this mean?
                setOnKeyboardActionListener(this@MyInputMethodService)
                // keyboard = latinKeyboard
                keyboard = android.inputmethodservice.Keyboard(
                    this,
                    com.example.hex_keyboard.R.xml.keys_layout
                )
            }
        }
}



}