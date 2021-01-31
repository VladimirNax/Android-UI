package com.niknax.attachment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
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

        // Тост при  клике на иконку details_fab -Поделиться
        details_fab.setOnClickListener {
            //Toast.makeText(requireContext(), "details_fab", Toast.LENGTH_SHORT).show()

            //"передаем выбранный текст фильма в другое приложение."
            //Создаем интент
            val intent = Intent()
            //Указываем action с которым он запускается
            intent.action = Intent.ACTION_SEND
            //Кладем данные о нашем фильме
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "Check out this film: ${film.title} \n\n ${film.description}"
            )
            //Указываем MIME тип, чтобы система знала, какое приложения предложить
            intent.type = "text/plain"
            //Запускаем наше активити
            startActivity(Intent.createChooser(intent, "Share To:"))
        }


        //логика фильм или находится, или не находится в Избранном
        details_fab_favorites.setImageResource(
            if (film.isInFavorites) R.drawable.ic_baseline_favorite_24
            else R.drawable.ic_baseline_favorite_border_24
        )
        // обработка кнопки Избранное.
        details_fab_favorites.setOnClickListener {
            if (!film.isInFavorites) {
                details_fab_favorites.setImageResource(R.drawable.ic_baseline_favorite_24)
                film.isInFavorites = true
            } else {
                details_fab_favorites.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                film.isInFavorites = false
            }
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