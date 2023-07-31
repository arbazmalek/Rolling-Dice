package com.arbaz.whowins


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieDrawable
import com.arbaz.whowins.databinding.ActivitySplashScreenBinding


class splashScreen : AppCompatActivity() {

  private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupAnim()


        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 4000)



    }
    private fun setupAnim() {
        binding.animationView.setAnimation(R.raw.lottieanim)
        binding.animationView.repeatCount = LottieDrawable.INFINITE
        binding.animationView.playAnimation()
    }

}