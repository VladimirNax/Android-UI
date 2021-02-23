package com.niknax.attachment

import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class) //Поскольку это UI тест, то нам в этом будет помогать AndroidJunit
class MainActivityTest {
    // чтобы тест понимал, какое активити мы хотим запустить,
    // при помощи аннотации @Rule и класса ActivityScenarioRule указываем наше активити
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    // тест на доступность RV FilmViewHolder
    @Test
    fun recyclerViewShouldBeAttached() {
        onView(withId(R.id.main_recycler)).check(matches(isDisplayed()))
        onView(withId(R.id.main_recycler)).perform(RecyclerViewActions.actionOnItemAtPosition<FilmViewHolder>(0, click()))
    }

    // тест SearchView, его присутствие на экране и возможность ввести текст
    @Test
    fun searchViewShouldBeAbleToInputText() {
        val testString = "1111111"
        onView(withId(R.id.search_view)).check(matches(isDisplayed()))
        onView(withId(R.id.search_view)).perform(typeSearchViewText(testString))
    }

    //тест проходится по всем пунктам BottomNavigationView и проверяет, развернулся ли фрагмент
    @Test
    fun allMenuDestinationsShouldWork() {
        onView(withId(R.id.favorites)).perform(click())
        onView(withId(R.id.favorites_fragment)).check(matches(isDisplayed()))

        onView(withId(R.id.watch_later)).perform(click())
        onView(withId(R.id.watch_later_fragment)).check(matches(isDisplayed()))

        onView(withId(R.id.selections)).perform(click())
        onView(withId(R.id.selections_fragment)).check(matches(isDisplayed()))

        onView(withId(R.id.home)).perform(click())
        onView(withId(R.id.home_fragment_root)).check(matches(isDisplayed()))
    }

    // Проверим, как у нас открывается фрагмент с деталями, для этого нам нужно будет взаимодействовать с RV.
    // Для того чтобы проверить, открылся ли фрагмент с деталями, мы просто будем пытаться найти,
    // есть ли принадлежащий ему View на экране, например App Bar
    @Test
    fun shouldOpenDetailsFragment() {
        onView(withId(R.id.main_recycler)).perform(RecyclerViewActions.actionOnItemAtPosition<FilmViewHolder>(0, click()))
        onView(withId(R.id.app_bar)).check(matches(isDisplayed()))
    }

    // проверяем, нажимается ли кнопка «Добавить в избранное»
    @Test
    fun addToFavoritesButtonClickable() {
        onView(withId(R.id.main_recycler)).perform(RecyclerViewActions.actionOnItemAtPosition<FilmViewHolder>(0, click()))
        onView(withId(R.id.details_fab_favorites)).perform(click())
        onView(withId(R.id.details_fab_favorites)).perform(click())
    }




    //отвечает за работу теста searchViewShouldBeAbleToInputText.
    // создание своего ViewAction
    private fun typeSearchViewText(text: String?): ViewAction? {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                //Ensure that only apply if it is a SearchView and if it is visible.
                return allOf(isDisplayed(), isAssignableFrom(SearchView::class.java))
            }

            override fun getDescription(): String {
                return "Change view text"
            }

            override fun perform(uiController: UiController?, view: View) {
                (view as SearchView).setQuery(text, false)
            }
        }
    }
}