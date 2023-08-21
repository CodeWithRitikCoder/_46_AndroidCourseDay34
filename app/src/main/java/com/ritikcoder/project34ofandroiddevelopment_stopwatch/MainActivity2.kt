package com.ritikcoder.project34ofandroiddevelopment_stopwatch

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.ritikcoder.project34ofandroiddevelopment_stopwatch.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    //deal with the Runnable interface.
    private var isRunning= false
    private var timerSecond= 0

    private val handler= Handler(Looper.getMainLooper())
    private val runnable= object : Runnable {
        override fun run() {
            timerSecond++
            val hours= timerSecond / 3600
            val minutes= (timerSecond % 3600) / 60
            val seconds= timerSecond % 60

            val time= String.format("%02d:%02d:%02d", hours, minutes, seconds)
            binding.textViewForTimer.text= time

            handler.postDelayed(this, 1000)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonForStart.setOnClickListener {
            startTimer()
        }
        binding.buttonForStop.setOnClickListener {
            stopTimer()
        }
        binding.buttonForReset.setOnClickListener {
            resetTimer()
        }

    }

    //Other methods.
    private fun startTimer(){
        if(!isRunning){
            handler.postDelayed(runnable, 1000)
            isRunning= true

            binding.buttonForStart.isEnabled= false
            binding.buttonForStop.isEnabled= true
            binding.buttonForReset.isEnabled= true
        }
    }

    @SuppressLint("SetTextI18n")
    private fun stopTimer(){
        if (isRunning){
            handler.removeCallbacks(runnable)
            isRunning= false

            binding.buttonForStart.isEnabled= true
            binding.buttonForStart.text= "Resume"
            binding.buttonForStop.isEnabled= false
            binding.buttonForReset.isEnabled= true
        }
    }

    @SuppressLint("SetTextI18n")
    private fun resetTimer(){
        stopTimer()

        timerSecond= 0
        binding.textViewForTimer.text= "00:00:00"

        binding.buttonForStart.isEnabled= true
        binding.buttonForReset.isEnabled= false
        binding.buttonForStart.text= "Start"
    }

}