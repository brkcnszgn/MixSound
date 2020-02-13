package live.codemy.mixsound

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import kotlinx.android.synthetic.main.activity_main.*
import live.codemy.mixsoundlib.MixSound
import live.codemy.mixsoundlib.SoundType

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mixSound = MixSound.getInstance(this)
        /**
         * Kotlinde ui uzerinde bulunan bir nesneye id olmasi sartiyla direk ismiyle import edibiliriz.
         * */

        val btnRecord = findViewById<ImageView>(R.id.btnRecord)
        val cardView1 = findViewById<CardView>(R.id.cardView1)

        btnRecord.setOnClickListener {
            //"Record button Cliked" showToast this
            MixSound.getInstance(this).recordSound()
        }
        cardView1.setOnClickListener {
            if (mixSound != null)
            MixSound.getInstance(this).changeSound(SoundType.Chipmunk)
        }
        cardView2.setOnClickListener {
            MixSound.getInstance(this).changeSound(SoundType.DarthVader)
        }
        cardView3.setOnClickListener {
            MixSound.getInstance(this).changeSound(SoundType.Echo)
        }
//        imageFour.setOnClickListener {
//            MixSound.getInstance(this).changeSound(SoundType.Reverb)
//        }
//        imageFive.setOnClickListener {
//            MixSound.getInstance(this).changeSound(SoundType.Slow)
//        }


    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            MixSound.CODE_SPEECH_RECOGNIZER -> {
                data?.let {
                    val result = it.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    //  MixSound.recordSound = result.first()
                    MixSound.recordSound = """
                      Sen Allahın bir lütfusun
                        Gözlerimin nurusun
                        Seni gören şifa bulur
                        Gel de gönlüm şifa bulsun
                        Seni seven aşkı bulur
                    """.trimIndent()
                }
            }
        }
    }


    override fun onStop() {
        super.onStop()
        MixSound.getInstance(this)
    }

    override fun onPause() {
        super.onPause()

    }

    infix fun String.showToast(context: Context) {
        Toast.makeText(context, this, Toast.LENGTH_LONG).show()
    }
}