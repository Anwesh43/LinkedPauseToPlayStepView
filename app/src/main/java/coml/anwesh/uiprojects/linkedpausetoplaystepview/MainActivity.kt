package coml.anwesh.uiprojects.linkedpausetoplaystepview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import coml.anwesh.uiprojects.pausetoplaystepview.PauseToPlayStepView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PauseToPlayStepView.create(this)
        fullScreen()
    }
}

fun MainActivity.fullScreen() {
    supportActionBar?.hide()
    window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
}