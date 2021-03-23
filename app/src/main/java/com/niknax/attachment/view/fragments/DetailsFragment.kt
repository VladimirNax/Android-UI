package com.niknax.attachment.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.niknax.attachment.data.ApiConstants
import com.niknax.attachment.R
import com.niknax.attachment.databinding.FragmentDetailsBinding
import com.niknax.attachment.domain.Film


class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Получаем наш фильм из переданного бандла
        val film = arguments?.get("film") as Film
        bind(film)

//нажатие на кнопки меню. сообщение Snackbar

        binding.toolbarMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.favorites_details -> {
                    Snackbar.make(binding.detailsLayout, "Избранное", Snackbar.LENGTH_SHORT).show()
                    true
                }
                R.id.watch_later_details -> {
                    Snackbar.make(binding.detailsLayout, "Посмотреть позже", Snackbar.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }

        // Тост при  клике на иконку details_fab -Поделиться
        binding.detailsFab.setOnClickListener {
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
        binding.detailsFabFavorites.setImageResource(
            if (film.isInFavorites) R.drawable.ic_baseline_favorite_24
            else R.drawable.ic_baseline_favorite_border_24
        )
        // обработка кнопки Избранное.
        binding.detailsFabFavorites.setOnClickListener {
            if (!film.isInFavorites) {
                binding.detailsFabFavorites.setImageResource(R.drawable.ic_baseline_favorite_24)
                film.isInFavorites = true
            } else {
                binding.detailsFabFavorites.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                film.isInFavorites = false
            }
        }

    }



    fun bind(film: Film) {
        //Устанавливаем заголовок
        binding.detailsToolbar.title = film.title
        //Устанавливаем картинку
        Glide.with(this)
            .load(ApiConstants.IMAGES_URL + "w780" + film.poster)
            .centerCrop()
            .into(binding.detailsPoster)
        //Устанавливаем описание
        binding.detailsDescription.text = film.description
    }
}