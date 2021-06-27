package com.arifkian.gamerockscissorspaper.intro

import android.graphics.Color
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.arifkian.gamerockscissorspaper.R
import com.github.appintro.AppIntro2
import com.github.appintro.AppIntroFragment


class AppIntroActivity : AppIntro2() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        isSkipButtonEnabled = false
        // Make sure you don't call setContentView!

        // Call addSlide passing your Fragments.
        // You can use AppIntroFragment to use a pre-built fragment
        addSlide(
            AppIntroFragment.newInstance(
                description = getString(R.string.Deskription_1),
                backgroundColor = ContextCompat.getColor(this, R.color.bg_color),
                imageDrawable = R.drawable.landing_page1
            )
        )
        addSlide(
            AppIntroFragment.newInstance(
                description = getString(R.string.Deskription_2),
                backgroundColor = ContextCompat.getColor(this, R.color.bg_color),
                imageDrawable = R.drawable.landing_page2
            )
        )
        addSlide(PlayerNameFormFragment())
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        // Decide what to do when the user clicks on "Skip"
        finish()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        // Decide what to do when the user clicks on "Done"
        if (currentFragment is PlayerNameFormFragment) {
            currentFragment.navigateToMenuGame()
        }
    }
}