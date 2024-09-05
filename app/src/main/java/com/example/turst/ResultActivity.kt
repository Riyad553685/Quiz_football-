package com.example.turst

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.turst.databinding.ActivityMainBinding
import com.example.turst.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val skip = intent.getIntExtra("skip",0)
        val correct = intent.getIntExtra("correct",0)
        val wrong = intent.getIntExtra("wrong",0)

        binding.resultTV.text="Number Of Correct:$correct"
        binding.SkipTV.text="Number of Skip: $skip"
        binding.wrongTV.text="Number of wrong :$wrong"



    }
}