package com.example.customview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        graph_view.setData(generateRandomPoints())
    }

    fun generateRandomPoints(): List<DataPoint> {
        val rand = Random()
        return (0..20).map {
            DataPoint(it, rand.nextInt(50) + 1)
        }
    }
}