package com.niknax.attachment

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var backPressed = 0L

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

        /* topAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.settings -> {
                    Toast.makeText(this, "Настройки", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        } */
        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.favorites -> {
                    //Toast.makeText(this, "Избранное", Toast.LENGTH_SHORT).show()

                    // логика запуска нашего фрагмента с избранным
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_placeholder, FavoritesFragment())
                        .addToBackStack(null)
                        .commit()
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

    //выход из приложения двойным кликом с учетом возвратов по фрагментам
    //или попап окно
    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount == 1) {
            //выход двойным кликом
            /*if (backPressed + TIME_INTERVAL > System.currentTimeMillis()) {
                super.onBackPressed()
                finish()
            } else {
                Toast.makeText(this, "Для выхода кликните дважды", Toast.LENGTH_SHORT).show()
            }*/

            // выводит Диалоговое окно. Popup
            // ContextThemeWrapper(this, R.style.MyDialog) - задает стиль окна. стиль в values\themes.xml.
            // без стилий AlertDialog.Builder(this)
            AlertDialog.Builder(ContextThemeWrapper(this, R.style.MyDialog))
                .setTitle("Вы хотите выйти?")
                .setIcon(R.drawable.ic_baseline_sensor_door_24)
                .setMessage ("«Нам бы не хотелось бы, чтобы вы уходили»")
                .setPositiveButton("Да") { _, _ ->
                    super.onBackPressed()
                    finish()
                }
                .setNegativeButton("Нет") { _, _ ->

                }
                .setNeutralButton("Не знаю") { _, _ ->
                    Toast.makeText(this, "Решайся", Toast.LENGTH_SHORT).show()
                }
                .show()

            backPressed = System.currentTimeMillis()
        } else {
            super.onBackPressed()
        }
    }
    companion object {
        const val TIME_INTERVAL = 2000
    }


}
