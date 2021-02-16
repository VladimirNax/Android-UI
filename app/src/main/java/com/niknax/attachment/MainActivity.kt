package com.niknax.attachment

import android.app.AlertDialog
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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
                R.id.home -> {
                    val tag = "home"
                    val fragment = checkFragmentExistence(tag)
                    //В первом параметре, если фрагмент не найден и метод вернул null, то с помощью
                    //элвиса мы вызываем создание нового фрагмента
                    changeFragment(fragment ?: HomeFragment(), tag)
                    true
                }
                R.id.favorites -> {
                    val tag = "favorites"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment(fragment ?: FavoritesFragment(), tag)
                    true
                }
                R.id.watch_later -> {
                    val tag = "watch_later"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment(fragment ?: WatchLaterFragment(), tag)
                    true
                }
                R.id.selections -> {
                    val tag = "selections"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment(fragment ?: SelectionsFragment(), tag)
                    true
                }
                else -> false
            }
        }

        //Запускаем фрагмент при старте
        val tag = "home"
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_placeholder, HomeFragment(), tag)
            .addToBackStack(null)
            .commit()

    }

    //Ищем фрагмент по тегу, если он есть то возвращаем его, если нет, то null
    private fun checkFragmentExistence(tag: String): Fragment? =
        supportFragmentManager.findFragmentByTag(tag)

    //Сам запуск фрагмента:
    private fun changeFragment(fragment: Fragment, tag: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, fragment, tag)
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

        //Выход из фрагментов на главный фрагмент, а с главного уже выход из приложения
        val home = supportFragmentManager.findFragmentByTag("home")
        if ( home != null && home.isVisible() ) {
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
                .setMessage("«Нам бы не хотелось бы, чтобы вы уходили»")
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
           //super.onBackPressed()

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_placeholder, HomeFragment(), "home")
                .addToBackStack(null)
                .commit()
        }
    }




}
