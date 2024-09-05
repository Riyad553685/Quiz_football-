package com.example.turst

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.turst.databinding.ActivityMainBinding
import com.example.turst.databinding.ActivityPlayBinding

class playActivity : AppCompatActivity() {
    lateinit var binding: ActivityPlayBinding

    val quizlist = listOf<Quiz>(

        Quiz("Which country has won the most World Cups?",
            "Bangladesh",
            "Argentina",
            "Brazil",
            "France",
            "Brazil"
        ),

        Quiz("Which country won the first ever World Cup in 1930?",
            "Uruguay",
            "Argentina",
            "Brazil",
            "France",
            "Uruguay"
        ),

        Quiz("Most Popular Sports In The World in 2024 ?",
            "Football",
            "Cricket",
            "Golf",
            "Rugby",
            "Football"
        ),

        Quiz("Which male footballer has the most goals??",
            "Cristiano Ronaldo",
            "Mohamed Salah",
            "Lionel Messi",
            "Mesut Ozil",
            "Cristiano Ronaldo"
        )

    )

    var index = 0
    var updateQuestion = 1
    var hasFinished = false

    var skip = -1
    var correct = 0
    var wrong = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initQuestion()
        binding.nextBT.setOnClickListener {
            showNextQuestion()
        }

    }

    private fun initQuestion() {

        val quiz = quizlist[index]
        binding.apply {

            questionTV.text = quiz.question
            optionBTN1.text = quiz.Option1
            optionBTN2.text = quiz.Option2
            optionBTN3.text = quiz.Option3
            optionBTN4.text = quiz.Option4
        }

    }
    private fun showNextQuestion(){
        checkAnswer()

        binding.apply {

            if (updateQuestion < quizlist.size){
                updateQuestion++
                initQuestion()

            }else if (index <= quizlist.size - 1){

                index++

            }else{
                hasFinished = true

            }
            radiog.clearCheck()
        }
    }

    private fun checkAnswer() {

        binding.apply {

            if (radiog.checkedRadioButtonId == -1) {
                skip++

            } else {

                val checkButton = findViewById<RadioButton>(radiog.checkedRadioButtonId)
                val checkAnswer = checkButton.text.toString()

                if (checkAnswer == quizlist[index].answer) {
                    correct++
                    showAlertDialog("Correct Answer")

                } else {
                    wrong++
                    showAlertDialog("Wrong Answer")
                }

            }

            if (index<=quizlist.size -1){

                index++

            }else{
                showAlertDialog("Finished")
            }



        }
    }
        fun showAlertDialog(message:String){
            val builder = AlertDialog.Builder(this)
            builder.setTitle(message)

            builder.setPositiveButton("Ok",object : DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {

                    if (message == "Finished"){
                        val intent = Intent (this@playActivity,ResultActivity::class.java)

                        intent.putExtra("skip",skip)
                        intent.putExtra("correct",correct)
                        intent.putExtra("wrong",wrong)

                        startActivity(intent)
                    }



                }


            })

            val alertDialog = builder.create()
            alertDialog.show()

        }

    }
