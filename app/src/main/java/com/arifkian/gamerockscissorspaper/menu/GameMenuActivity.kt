package com.arifkian.gamerockscissorspaper.menu

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.arifkian.gamerockscissorspaper.R
import com.arifkian.gamerockscissorspaper.data.constant.Constant
import com.arifkian.gamerockscissorspaper.data.preference.UserPreference
import com.arifkian.gamerockscissorspaper.databinding.ActivityGameMenuBinding
import com.arifkian.gamerockscissorspaper.game.GameActivity
import com.google.android.material.snackbar.Snackbar

class GameMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityGameMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showSnackBar()
        setTextMode()
        onClick()
    }

    private fun showSnackBar() {
        val parentLayout: View = findViewById(android.R.id.content)
        Snackbar.make(
            parentLayout,
            String.format(
                getString(
                    R.string.text_welcome,
                    UserPreference(this).userName
                )
            ),
            Snackbar.LENGTH_LONG
        )
            .setActionTextColor(Color.CYAN)
            .setAction("Tutup") {
                Snackbar.LENGTH_INDEFINITE
            }.show()
        supportActionBar?.hide()
    }

    private fun setTextMode() {
        binding.tvVsPlayer.text = String.format(
            getString(
                R.string.text_user_vs_user,
                UserPreference(this).userName
            )
        )
        binding.tvVsComputer.text = String.format(
            getString(
                R.string.text_user_vs_cpu,
                UserPreference(this).userName
            )
        )
    }

    private fun onClick() {
        binding.ivVsPlayer.setOnClickListener {
            goToMainActivity(0)
        }
        binding.ivVsComputer.setOnClickListener {
            goToMainActivity(1)
        }
    }

    private fun goToMainActivity(playMode: Int) {
        val intent = Intent(this@GameMenuActivity, GameActivity::class.java).apply {
            putExtra(Constant.PLAY_MODE, playMode)
        }
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}
