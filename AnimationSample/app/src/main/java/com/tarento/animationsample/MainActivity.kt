package com.tarento.animationsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), Animation.AnimationListener {

    //TextView
    lateinit var txtMessage: TextView
    //Button
    lateinit var btn: Button
    // Animation
    lateinit var animation: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtMessage = findViewById<TextView>(R.id.text)
        btn = findViewById<Button>(R.id.btn)
        //loading Animation
        animation = AnimationUtils.loadAnimation(
            applicationContext,
            R.anim.move_to)

        //handling aniamtion on button click
        btn.setOnClickListener {
            txtMessage.setVisibility(View.VISIBLE)
            // starting the animation
            txtMessage.startAnimation(animation)
        }
    }

    override fun onAnimationRepeat(animation: Animation?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAnimationEnd(animation: Animation?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAnimationStart(animation: Animation?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
