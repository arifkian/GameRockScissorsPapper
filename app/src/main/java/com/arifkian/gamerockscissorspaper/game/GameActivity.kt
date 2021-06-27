package com.arifkian.gamerockscissorspaper.game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.arifkian.gamerockscissorspaper.R
import com.arifkian.gamerockscissorspaper.data.constant.Constant
import com.arifkian.gamerockscissorspaper.data.preference.UserPreference
import com.arifkian.gamerockscissorspaper.databinding.ActivityMainBinding
import com.arifkian.gamerockscissorspaper.mechanic.GameMechanic
import com.arifkian.gamerockscissorspaper.menu.DialogFragment
import com.arifkian.gamerockscissorspaper.menu.DialogFragmentList
import kotlin.random.Random

class GameActivity : AppCompatActivity() , DialogFragmentList {

    private lateinit var binding: ActivityMainBinding
    private var playMode: Int? = null
    private var player2: Int? = null
    private var player1: Int? = null
    private var flag: Int = -1
    private val TAG = GameActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        start()
        onResetClick()
    }

    private fun start() {
        playMode = intent.getIntExtra(Constant.PLAY_MODE, 0)
        binding.ivImagePlayer.setText(UserPreference(this).userName)

        if (playMode == 0) {
            flag = 0
            binding.ivImageCom.setText(getString(R.string.text_enemy_player2))
            onPlayerOneClick()
            onPlayerTwoClick()
        } else {
            flag = 1
            binding.ivImageCom.setText(getString(R.string.text_enemy_cpu))
            onPlayerOneClick()
        }
    }

    private fun onResetClick() {
        binding.ibReset.setOnClickListener {
            resetGame()
        }
    }


    private fun onPlayerOneClick() {
        var random = 0
        binding.flActionPlayerRock.setOnClickListener {
            player1 = 0
            setSelectPlayer(0)
            showToastFromPlayer1Choice(player1!!)
            if (playMode != 0) {
                random = Random.nextInt(0, 3)
                gamePlay(player1!!, random)
            }
        }
        binding.flActionPlayerScissor.setOnClickListener {
            player1 = 1
            setSelectPlayer(1)
            showToastFromPlayer1Choice(player1!!)
            if (playMode != 0) {
                random = Random.nextInt(0, 3)
                gamePlay(player1!!, random)
            }
        }
        binding.flActionPlayerPapper.setOnClickListener {
            player1 = 2
            showToastFromPlayer1Choice(player1!!)
            setSelectPlayer(2)
            if (playMode != 0) {
                random = Random.nextInt(0, 3)
                gamePlay(player1!!, random)
            }
        }
    }
    private fun onPlayerTwoClick() {
        binding.flActionComRock.setOnClickListener {
            player2 = 0
            gamePlay(player1!!, player2!!)
        }
        binding.flActionComScissor.setOnClickListener {
            player2 = 1
            gamePlay(player1!!, player2!!)
        }
        binding.flActionComPapper.setOnClickListener {
            player2 = 2
            gamePlay(player1!!, player2!!)
        }
    }

    private fun gamePlay(playerOne: Int, playerTwo: Int) {
        if ((playerOne.plus(1)).rem(3) == playerTwo) {
            Log.d(TAG, "setClickEvent Computer won")
            binding.ivImageVs.setImageResource(R.drawable.playerwin)
            DialogFragment(1, flag).show(supportFragmentManager, null)
        } else if (playerOne.equals(playerTwo)) {
            Log.d(TAG, "setClickEvent draw")
            binding.ivImageVs.setImageResource(R.drawable.draw)
            DialogFragment(3, flag).show(supportFragmentManager, null)
        } else {
            Log.d(TAG, "setClickEvent User won")
            binding.ivImageVs.setImageResource(R.drawable.computerwin)
            DialogFragment(0, flag).show(supportFragmentManager, null)
        }
        setSelectPlayer(playerOne)
        setSelectComputer(playerTwo)
        if (playMode != 0) {
            showToastFromPlayer2Choice(getString(R.string.text_player_cpu), playerTwo)
        } else {
            showToastFromPlayer2Choice(
                getString(R.string.text_player_player2),
                playerTwo
            )
        }
    }

    private fun showToastFromPlayer1Choice(choice: Int) {
        if (choice == 0) {
            Toast.makeText(
                this,
                String.format(
                    getString(R.string.text_toast_choice),
                    UserPreference(this).userName,
                    getString(R.string.text_shape_rock)

                ), Toast.LENGTH_SHORT
            ).show()
        } else if (choice == 1) {
            Toast.makeText(
                this,
                String.format(
                    getString(R.string.text_toast_choice),
                    UserPreference(this).userName,
                    getString(R.string.text_shape_paper)
                ), Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(
                this,
                String.format(
                    getString(R.string.text_toast_choice),
                    UserPreference(this).userName,
                    getString(R.string.text_shape_scissor)
                ), Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun showToastFromPlayer2Choice(player: String, choice: Int) {
        if (choice == 0) {
            Toast.makeText(
                this,
                String.format(
                    getString(R.string.text_toast_choice),
                    player,
                    getString(R.string.text_shape_rock)
                ), Toast.LENGTH_SHORT
            ).show()
        } else if (choice == 1) {
            Toast.makeText(
                this,
                String.format(
                    getString(R.string.text_toast_choice),
                    player,
                    getString(R.string.text_shape_paper)
                ), Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(
                this,
                String.format(
                    getString(R.string.text_toast_choice),
                    player,
                    getString(R.string.text_shape_scissor)
                ), Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun setSelectPlayer(playerMechanic: Int) {
        when (GameMechanic.formInt(playerMechanic)) {
            GameMechanic.ROCK -> {
                binding.flActionPlayerRock.setBackgroundResource(R.drawable.cover)
                binding.flActionPlayerScissor.setBackgroundResource(0)
                binding.flActionPlayerPapper.setBackgroundResource(0)
            }
            GameMechanic.SCISSOR -> {
                binding.flActionPlayerRock.setBackgroundResource(0)
                binding.flActionPlayerScissor.setBackgroundResource(R.drawable.cover)
                binding.flActionPlayerPapper.setBackgroundResource(0)
            }
            GameMechanic.PAPER -> {
                binding.flActionPlayerRock.setBackgroundResource(0)
                binding.flActionPlayerScissor.setBackgroundResource(0)
                binding.flActionPlayerPapper.setBackgroundResource(R.drawable.cover)
            }
            else -> {
                binding.flActionPlayerRock.setBackgroundResource(0)
                binding.flActionPlayerScissor.setBackgroundResource(0)
                binding.flActionPlayerPapper.setBackgroundResource(0)
            }
        }
    }

    private fun setSelectComputer(compMechanic: Int) {
        when (GameMechanic.formInt(compMechanic)) {
            GameMechanic.ROCK -> {
                binding.flActionComRock.setBackgroundResource(R.drawable.cover)
                binding.flActionComScissor.setBackgroundResource(0)
                binding.flActionComPapper.setBackgroundResource(0)
            }
            GameMechanic.SCISSOR -> {
                binding.flActionComRock.setBackgroundResource(0)
                binding.flActionComScissor.setBackgroundResource(R.drawable.cover)
                binding.flActionComPapper.setBackgroundResource(0)
            }
            GameMechanic.PAPER -> {
                binding.flActionComRock.setBackgroundResource(0)
                binding.flActionComScissor.setBackgroundResource(0)
                binding.flActionComPapper.setBackgroundResource(R.drawable.cover)
            }
            else -> {
                binding.flActionComRock.setBackgroundResource(0)
                binding.flActionComScissor.setBackgroundResource(0)
                binding.flActionComPapper.setBackgroundResource(0)
            }
        }
    }

    private fun resetGame() {
        setSelectPlayer(-1)
        setSelectComputer(-1)
        player1 = null
        player2 = null
        binding.ivImageVs.setImageResource(R.drawable.vs)
    }

    override fun onDialogDismiss() {
        resetGame()
    }
}