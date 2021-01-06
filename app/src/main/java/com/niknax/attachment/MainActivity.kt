package com.niknax.attachment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var filmsAdapter: FilmListRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*main.setOnClickListener {
            Toast.makeText(this, R.string.btn_main, Toast.LENGTH_SHORT).show()
        }
        films.setOnClickListener {
            Toast.makeText(this, R.string.btn_films, Toast.LENGTH_SHORT).show()
        }
        series.setOnClickListener {
            Toast.makeText(this, R.string.btn_series, Toast.LENGTH_SHORT).show()
        }
        cartoons.setOnClickListener {
            Toast.makeText(this, R.string.btn_cartoons, Toast.LENGTH_SHORT).show()
        }
        news.setOnClickListener {
            Toast.makeText(this, R.string.btn_news, Toast.LENGTH_SHORT).show()
        }*/

        topAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.settings -> {
                    Toast.makeText(this, "Настройки", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.favorites -> {
                    Toast.makeText(this, "Избранное", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.watch_later -> {
                    Toast.makeText(this, "Посмотреть позже", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.selections -> {
                    Toast.makeText(this, "Подборки", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }

        // список фильмов на главной
        val filmsDataBase = listOf(
            Film("Декстер", R.drawable.p1, "By day, mild-mannered Dexter is a blood-spatter analyst for the Miami police. But at night, he is a serial killer who only targets other murderers."),
            Film("Пропавшая", R.drawable.p2, "A recently widowed traveler is kidnapped by a cold blooded killer, only to escape into the wilderness where she is forced to battle against the elements as her pursuer closes in on her."),
            Film(" Город воров", R.drawable.p3, "A longtime thief, planning his next job, tries to balance his feelings for a bank manager connected to an earlier heist, and a hell-bent F.B.I Agent looking to bring him and his crew down."),
            Film("One Night in Miami", R.drawable.p4, "One Night in Miami is a fictional account of one incredible night where icons Muhammad Ali, Malcolm X, Sam Cooke, and Jim Brown gathered discussing their roles in the civil rights movement and cultural upheaval of the 60s."),
            Film("Последний из могикан", R.drawable.p5, "Three trappers protect the daughters of a British Colonel in the midst of the French and Indian War."),
            Film("Стартрек: Бесконечность", R.drawable.p6, "The crew of the USS Enterprise explores the furthest reaches of uncharted space, where they encounter a new ruthless enemy, who puts them, and everything the Federation stands for, to the test."),
            Film(" Шоу Трумана", R.drawable.p7, "An insurance salesman discovers his whole life is actually a reality TV show."),
            Film(" Любите Куперов", R.drawable.p8, "The intertwined stories of four generations of Coopers unfold right before the annual family reunion on Christmas Eve. Can they survive the most beautiful time of the year?"),
            Film(" Гламурные боссы", R.drawable.p9, "Two friends with very different ideals start a beauty company together. One is more practical while the other wants to earn her fortune and live a lavish lifestyle."),
            Film(" Ритм-секция", R.drawable.p10, "A woman seeks revenge against those who orchestrated a plane crash that killed her family."),
        )


//находим наш RV
        main_recycler.apply {
            //Инициализируем наш адаптер в конструктор передаем анонимно инициализированный интерфейс,
            //оставим его пока пустым, он нам понадобится во второй части задания
            filmsAdapter = FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener{
                override fun click(film: Film, position: Int) {}
            })
            //Присваиваем адаптер
            adapter = filmsAdapter
            //Присвои layoutmanager
            layoutManager = LinearLayoutManager(this@MainActivity)
            //Применяем декоратор для отступов
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }
//Кладем нашу БД в RV
        filmsAdapter.addItems(filmsDataBase)



    }
}
