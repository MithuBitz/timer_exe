package com.example.timerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    //TODO Variable for Timer which will be initialize later
    private var countDownTimer: CountDownTimer? = null
    //TODO Variable for the duration of the timer in millisecond
    private var timeDuration: Long = 60000
    //TODO Variable for pause offset (pauseOffset = timerDuration - time left)
    private var pauseOffset: Long = 0

    private var timerTv: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startBtn: Button = findViewById(R.id.startBtn)
        val pauseBtn: Button = findViewById(R.id.pauseBtn)
        val restartBtn: Button = findViewById(R.id.resetBtn)

        timerTv = findViewById(R.id.timerTV)
        timerTv?.text = (timeDuration/1000).toString()

        startBtn.setOnClickListener {
            startTimer(pauseOffset)
        }

        pauseBtn.setOnClickListener {
            pauseTimer()
        }

        restartBtn.setOnClickListener {
            resetTimer()
        }

    }

    //Create a function to start the timer for 60 Sec\
    private fun startTimer(pauseOffsetL: Long) {
        //Assign countDownTimer to a object of CountDownTimer with two parameters millisInfuture and CountDownInterval
        countDownTimer = object : CountDownTimer(timeDuration - pauseOffsetL, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                //which can assign pauseOffset which can be calculated by timeDuration minus millisUntillfinish
                pauseOffset = timeDuration - millisUntilFinished
                //set the tvTimer text with millisUntillfinish/1000
                timerTv?.text = (millisUntilFinished/1000).toString()
            }

            //Override the onFinish method with a Toast
            override fun onFinish() {
                Toast.makeText(this@MainActivity, "Timer Finished", Toast.LENGTH_LONG).show()
            }
        }.start()
        //Finally start() the function
    }

    //Create a function to pause the countDownTimer
    private fun pauseTimer() {
        //if countDownTimer is not null then only this function work
        if(countDownTimer != null) {
            //if it is not null then set the countDownTimer to cancel()
            countDownTimer!!.cancel()
        }
    }

    //Create a funtion for reset the timer
    private fun resetTimer() {
        //and if countDownTimer is not null
        if (countDownTimer != null) {
            // then cancel() the timer then
            countDownTimer!!.cancel()
            //set the timerText to timerDutation
            timerTv?.text = (timeDuration/1000).toString()
            //set the countDownTimer to null
            countDownTimer = null
            // pauseOfset also set to 0
            pauseOffset = 0
        }
    }

}