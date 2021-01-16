package com.niknax.attachment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



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

//Запускаем фрагмент при старте
        val tag = "fragment_1"
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_placeholder, HomeFragment(), tag)
            .addToBackStack(null)
            .commit()

    }

    fun launchDetailsFragment(film: Film) {
        //Создаем "посылку"
        val bundle = Bundle()
        //Кладем наш фильм в "посылку"
        bundle.putParcelable("film", film)
        //Кладем фрагмент с деталями в перменную
        val fragment = DetailsFragment()
        //Прикрепляем нашу "посылку" к фрагменту
        fragment.arguments = bundle

        //Запускаем фрагмент
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, fragment)
            .addToBackStack(null)
            .commit()
    }



}
