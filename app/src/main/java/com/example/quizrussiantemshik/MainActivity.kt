package com.example.quizrussiantemshik

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.quizrussiantemshik.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    private val questions = arrayOf(
        "Кому надо позвонить когда приняли на темке?",
        "Если хомяки продают свою крипту, что надо делать?",
        "Если остановили менты, кому звонить?"
    )


    private val options = arrayOf(
        arrayOf("Начальнику ФСБ", "Пахану", "Дочке Прокурора"),
        arrayOf("Продаем", "Докупаем", "Закладываем хату"),
        arrayOf("Генералу", "Начальнику ЖЭКа", "Начальнику ОБЭП")
    )


    private val correctAnswers = arrayOf(0, 1, 2)

    private var currentQuestionsIndex = 0
    private var score = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayQuestions()

        binding.optionButton.setOnClickListener{
            checkAnswer(0)
        }
        binding.option2Button.setOnClickListener{
            checkAnswer(1)
        }
        binding.option3Button.setOnClickListener{
            checkAnswer(2)
        }
        binding.restartButton.setOnClickListener{
            restartQuiz()
        }
    }


    private fun correctButtonColors(buttonIndex: Int) {
        when (buttonIndex) {
            0 -> binding.optionButton.setBackgroundColor(Color.GREEN)
            1 -> binding.optionButton.setBackgroundColor(Color.GREEN)
            2 -> binding.optionButton.setBackgroundColor(Color.GREEN)
        }
    }


    private fun wrongButtonColors(buttonIndex: Int) {
        when (buttonIndex) {
            0 -> binding.optionButton.setBackgroundColor(Color.RED)
            1 -> binding.optionButton.setBackgroundColor(Color.RED)
            2 -> binding.optionButton.setBackgroundColor(Color.RED)
        }
    }

    private fun resetButtonColors() {
        binding.optionButton.setBackgroundColor(Color.rgb(50, 59, 96))
        binding.option2Button.setBackgroundColor(Color.rgb(50, 59, 96))
        binding.option3Button.setBackgroundColor(Color.rgb(50, 59, 96))
    }

    private fun showResults() {
        Toast.makeText(this, "Your score: $score out of ${questions.size}", Toast.LENGTH_LONG)
            .show()
        binding.restartButton.isEnabled = true
    }

    private fun displayQuestions() {
        binding.questionText.text = questions[currentQuestionsIndex]
        binding.optionButton.text = options[currentQuestionsIndex][0]
        binding.option2Button.text = options[currentQuestionsIndex][1]
        binding.option3Button.text = options[currentQuestionsIndex][2]
        resetButtonColors()
    }

    private fun checkAnswer(selectedAnswerIndex: Int){
        var correctAnswerIndex = correctAnswers[currentQuestionsIndex]
        if (selectedAnswerIndex == correctAnswerIndex){
            score++
            correctButtonColors(selectedAnswerIndex)
        } else {
            wrongButtonColors(selectedAnswerIndex)
            correctButtonColors(correctAnswerIndex)
        }
        if (currentQuestionsIndex < questions.size - 1){
            currentQuestionsIndex++
            binding.questionText.postDelayed({displayQuestions()}, 1000)

        } else {
            showResults()
        }
    }


    private fun restartQuiz(){
        currentQuestionsIndex = 0
        score = 0
        displayQuestions()
        binding.restartButton.isEnabled = false
    }







}