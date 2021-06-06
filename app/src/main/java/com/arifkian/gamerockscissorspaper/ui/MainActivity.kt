package com.arifkian.gamerockscissorspaper.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.arifkian.gamerockscissorspaper.R
import com.arifkian.gamerockscissorspaper.databinding.ActivityMainBinding
import com.arifkian.gamerockscissorspaper.mechanic.GameMechanic
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setClick()
    }

    private fun setClick() {
        binding.flActionPlayerRock.setOnClickListener {
            gamePlay(0, Random.nextInt(0, 3))
            Log.d(TAG, "setClick player choice: Rock")
        }
        binding.flActionPlayerScissor.setOnClickListener {
            gamePlay(1, Random.nextInt(0, 3))
            Log.d(TAG, "setClick player choice: Papper")
        }
        binding.flActionPlayerPapper.setOnClickListener {
            gamePlay(2, Random.nextInt(0, 3))
            Log.d(TAG, "setClick player choice: Scissor")
        }
        binding.ibReset.setOnClickListener {
            resetGame()
        }
    }

    private fun gamePlay(playerMechanic: Int, compMechanic: Int) {
        if ((playerMechanic.plus(1)).rem(3) == compMechanic) {
            Log.d(TAG, "setClickEvent user won")
            binding.ivImageVs.setImageResource(R.drawable.playerwin)
        } else if (playerMechanic.equals(compMechanic)) {
            Log.d(TAG, "setClickEvent draw")
            binding.ivImageVs.setImageResource(R.drawable.draw)
        } else {
            Log.d(TAG, "setClickEvent Computer won")
            binding.ivImageVs.setImageResource(R.drawable.computerwin)
        }
        setSelectPlayer(playerMechanic)
        setSelectComputer(compMechanic)
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
        binding.ivImageVs.setImageResource(R.drawable.vs)
    }
}