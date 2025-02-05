package mx.edu.itson.theguessnumber

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var minValue = 0
    var maxValue = 100
    var num:Int = 0
    var won = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val guessings: TextView = findViewById(R.id.tvGuessings)
        val down: TextView = findViewById(R.id.btnDown)
        val up: TextView = findViewById(R.id.btnUp)
        val generate: TextView = findViewById(R.id.btnGenerate)
        val guessed: TextView = findViewById(R.id.btnGuessed)

        generate.setOnClickListener {
            num = Random.nextInt(minValue, maxValue)
            guessings.setText(num.toString())
            generate.visibility = View.INVISIBLE
            guessed.visibility = View.VISIBLE
            guessed.setText("Guessed")
        }

        up.setOnClickListener {
            minValue = num
            if (checkingLimits()) {
                num = Random.nextInt(minValue, maxValue)
                guessings.setText(num.toString())
            } else {
                guessings.setText("No puede ser, me ganaste :(")
                guessed.setText("Volver a jugar")
                won = true
            }
        }

        down.setOnClickListener {
            maxValue = num
            if (checkingLimits()) {
                num = Random.nextInt(minValue, maxValue)
                guessings.setText(num.toString())
            } else {
                guessings.setText("No puede ser, me ganaste :(")
                guessed.setText("Volver a jugar")
                won = true
            }
        }

        guessed.setOnClickListener {
            if (!won) {
                guessings.setText("Adiviné tu número, es el: " + num)
                guessed.setText("Volver a jugar")
                won = true
            } else {
                generate.visibility = View.VISIBLE
                generate.setText("Tap on generate to start")
                guessed.visibility = View.GONE
                resetValues()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun resetValues() {
        minValue = 0
        maxValue = 100
        num = 0
        won = false
    }

    fun checkingLimits():Boolean {
        return minValue != maxValue
    }
}