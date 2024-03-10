package com.example.alias

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
import android.widget.TextView

var counterTeamOne = 0
var counterTeamTwo = 0
var rounds = 1


class GameActivity : AppCompatActivity() {
    private lateinit var timer: TextView
    private lateinit var word: TextView
    private lateinit var guess: Button
    private lateinit var skip: Button
    private lateinit var countDownTimer: CountDownTimer
    private lateinit var roundStartTimer: CountDownTimer
    private var currentIndex = 0
    private lateinit var shuffledWords: List<String>
    private lateinit var wordsList: WordsList


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        wordsList = WordsList(
            listOf(
                "Арбуз", "Мяч", "Автобус", "Бабочка", "Часы",
                "Молния", "Ключ", "Дракон", "Огонь", "Книга",
                "Микрофон", "Галстук", "Замок", "Дом", "Компьютер",
                "Море", "Жираф", "Солнце", "Музыка", "Дерево",
                "Глаз", "Кот", "Молоко", "Камень", "Шар",
                "Картина", "Рыба", "Карандаш", "Стул", "Телефон",
                "Радуга", "Кровать", "Птица", "Кошка", "Деньги",
                "Звезда", "Пицца", "Вода", "День", "Муравей",
                "Космос", "Парашют", "Рука", "Спичка", "Шапка",
                "Почта", "Танец", "Снег", "Шоколад", "Котенок",
                "Осень", "Папка", "Кольцо", "Собака", "Печенье",
                "Фонарь", "Футбол", "Роза", "Дверь", "Пират",
                "Птичка", "Ракета", "Звук", "Овощ", "Сыр",
                "Слон", "Гриб", "Корова", "Цветок", "Лампа",
                "Скрипка", "Коса", "Банан", "Собака", "Танец",
                "Баба", "Гора", "Герой", "Дракон", "Дракон",
                "Бульдозер", "Кинжал", "Пианино", "Картошка", "Щенок",
                "Берег", "Гармошка", "Пирамида", "Монстр", "Фламинго",
                "Река", "Флаг", "Метро", "Тюлень", "Тряпка",
                "Палец", "Книга", "Динозавр", "Корона", "Вера"
            )
        )

        timer = findViewById(R.id.timer_text)
        word = findViewById(R.id.word_text)
        guess = findViewById(R.id.guess_button)
        skip = findViewById(R.id.skip_button)

        shuffledWords = wordsList.words.shuffled()

        val firstWord = shuffledWords.firstOrNull()

        word.text = firstWord

        guess.setOnClickListener {
            if (rounds % 2 == 1) {
                counterTeamOne++
            } else {
                counterTeamTwo++
            }
            if (currentIndex < shuffledWords.size) {
                val nextWord = shuffledWords[currentIndex]
                updateWord(nextWord)
                currentIndex++
            } else {
                word.text = "Список слов закончился"
            }
        }

        skip.setOnClickListener {
            if (currentIndex < shuffledWords.size) {
                val nextWord = shuffledWords[currentIndex]
                updateWord(nextWord)
                currentIndex++
            } else {
                word.text = "Список слов закончился"
            }
        }
        timerStart()
    }

    private fun timerStart() {
        guess.isEnabled = true
        skip.isEnabled = true

        // Устанавливаем таймер на 60 секунд с интервалом обновления каждую секунду
        countDownTimer = object : CountDownTimer(15000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // Каждую секунду обновляем текст в TextView, отображающем оставшееся время
                val secondsRemaining = millisUntilFinished / 1000
                timer.text = secondsRemaining.toString()

            }

            override fun onFinish() {
                // По завершении таймера выполняем нужные действия
                timer.text = "Время истекло!"
                startNextRoundTimer()
                guess.isEnabled = false
                skip.isEnabled = false
                rounds++
                checkWinner()
            }
        }
        countDownTimer.start()
        Log.d("MyTag", "Team one $counterTeamOne")
        Log.d("MyTag", "Team two $counterTeamTwo")

    }

    private fun startNextRoundTimer() {
        roundStartTimer = object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = millisUntilFinished / 1000
                timer.text = " Время истекло \n  Следующий раунд через $secondsRemaining секунд"
            }

            override fun onFinish() {
                timer.text = ""
                timerStart()
            }
        }
        roundStartTimer.start()
    }


    override fun onDestroy() {
        super.onDestroy()
        // Важно остановить таймер, чтобы избежать утечек ресурсов
        countDownTimer.cancel()
    }

    private fun updateWord(newWord: String) {
        word.text = newWord
    }

    private fun checkWinner(){
        if (counterTeamOne >= 15 || counterTeamTwo >= 15){
            val intent = Intent(this, FinishActivity::class.java)
            startActivity(intent)
        }
    }
}