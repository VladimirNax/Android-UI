package com.niknax.attachment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.fragment_details.view.*

import kotlinx.android.synthetic.main.activity_main.*

class DetailsFragment : Fragment() {

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.fragment_details)

    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Получаем наш фильм из переданного бандла
        val film = arguments?.get("film") as Film
        bind(film)

//нажатие на кнопки меню. сообщение Snackbar
        toolbar_menu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.favorites_details -> {
                    Snackbar.make(details_layout, "Избранное", Snackbar.LENGTH_SHORT).show()
                    true
                }
                R.id.watch_later_details -> {
                    Snackbar.make(details_layout, "Посмотреть позже", Snackbar.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
// Тост при  клике на иконку details_fab
        details_fab.setOnClickListener {
            Toast.makeText(requireContext(), "details_fab", Toast.LENGTH_SHORT).show()
        }


    }
    fun bind(film: Film) {
        //Устанавливаем заголовок
        details_toolbar.title = film.title
        //Устанавливаем картинку
        details_poster.setImageResource(film.poster)
        //Устанавливаем описание
        details_description.text = film.description
    }
}