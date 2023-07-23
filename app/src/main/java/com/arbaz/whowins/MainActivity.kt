package com.arbaz.whowins

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.arbaz.whowins.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private var currentPlayer: Int = 1
    var score1 : Int = 0
    var score2 : Int = 0
    var mMediaPlayer: MediaPlayer? = null
    var mmMediaPlayer: MediaPlayer? = null
    var bool : String = "off"

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.resetBtn.visibility = View.GONE

        binding.audioOnOff.setOnCheckedChangeListener({ _ , isChecked ->
             bool = if (isChecked) "on" else "off"

        })

        binding.button.setOnClickListener {

    if (binding.limitEt.text.isNotEmpty()){
    val limitWin  = binding.limitEt.text.toString().toInt()

        if (bool == "on"){
            stopDiceRollSound()
            diceRollAudio()
        }

          dicerolling()

    if (score1 >= limitWin){
        if (bool == "on"){
            stopDiceRollSound()
            playaudio()}
        binding.winPlayer.setText("player 1 won")

        binding.button.visibility = View.GONE

        binding.resetBtn.visibility = View.VISIBLE
    }
    else if (score2 >= limitWin){
        if (bool == "on"){
            stopDiceRollSound()
            playaudio()}
        binding.winPlayer.setText("player 2 won")

        binding.button.visibility = View.GONE

        binding.resetBtn.visibility = View.VISIBLE
    }
}   else{
                Toast.makeText(this@MainActivity,"enter limit", Toast.LENGTH_SHORT).show()
            }

        }

        binding.resetBtn.setOnClickListener {
           recreate()

        }



    }



   fun dicerolling(){


       var rand = (1..6).random()
       //  Toast.makeText(this@MainActivity, "$rand", Toast.LENGTH_SHORT).show()
       val img = when(rand){
           1 -> R.drawable.dice_1
           2 -> R.drawable.dice_2
           3 -> R.drawable.dice_3
           4 -> R.drawable.dice_4
           5 -> R.drawable.dice_5
           else -> R.drawable.dice_6
       }
       binding.imageView.setImageResource(img)

       if (currentPlayer == 1){
           score1 += rand
           binding.playerOneScore.setText(score1.toString())
           currentPlayer =2
           binding.currentPlayer.setText(currentPlayer.toString())
       }
       else{
           score2 += rand
           binding.playerTwoScore.setText(score2.toString())
           currentPlayer =1
           binding.currentPlayer.setText(currentPlayer.toString())
       }
   }

    fun playaudio(){
        if (mMediaPlayer == null){
            mMediaPlayer = MediaPlayer.create(this,R.raw.winaudio)
            mMediaPlayer!!.start()
        }else mMediaPlayer!!.start()
    }

    fun diceRollAudio(){
        if (mmMediaPlayer == null){
            mmMediaPlayer = MediaPlayer.create(this,R.raw.diceroll)
            mmMediaPlayer!!.start()
        }else mmMediaPlayer!!.start()
    }

    fun stopDiceRollSound() {
        if (mmMediaPlayer != null) {
            mmMediaPlayer!!.stop()
            mmMediaPlayer!!.release()
            mmMediaPlayer = null
        }
    }

    override fun onStop() {
        super.onStop()
        if (mMediaPlayer != null){
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }

        if (mmMediaPlayer != null){
            mmMediaPlayer!!.release()
            mmMediaPlayer = null
        }
    }
}