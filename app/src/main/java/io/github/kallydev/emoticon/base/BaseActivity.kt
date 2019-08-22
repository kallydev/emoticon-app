package io.github.kallydev.emoticon.base

import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.evernote.android.state.StateSaver
import com.tbruyelle.rxpermissions2.RxPermissions
import io.github.kallydev.emoticon.module.main.MainActivity

abstract class BaseActivity : AppCompatActivity() {

    var rxPermissions: RxPermissions? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rxPermissions = RxPermissions(this)
        setContentView(getLayoutID())
        onInit()
        if (savedInstanceState != null && !savedInstanceState.isEmpty) {
            StateSaver.restoreInstanceState(this, savedInstanceState)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        StateSaver.saveInstanceState(this, outState)
    }

    @LayoutRes
    protected abstract fun getLayoutID(): Int

    protected abstract fun onInit()

    protected fun onThemeChanged() {
        if (this is MainActivity) {
            recreate()
        } else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
            finish()
        }
    }

}
