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
        setClickEvent()
    }

    private fun setClickEvent() {
        binding.flActionPlayerBatu.setOnClickListener {
            gamePlay(0, Random.nextInt(0, 3))
            Log.d(TAG, "setClickEvent user choice: Rock")
        }
        binding.flActionPlayerGunting.setOnClickListener {
            gamePlay(1, Random.nextInt(0, 3))
            Log.d(TAG, "setClickEvent user choice: Papper")
        }
        binding.flActionPlayerKertas.setOnClickListener {
            gamePlay(2, Random.nextInt(0, 3))
            Log.d(TAG, "setClickEvent user choice: Scissor")
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
        setSelectComp(compMechanic)
    }

    private fun setSelectPlayer(playerMechanic: Int) {
        when (GameMechanic.formInt(playerMechanic)) {
            GameMechanic.ROCK -> {
                binding.flActionPlayerBatu.setBackgroundResource(R.drawable.cover)
                binding.flActionPlayerGunting.setBackgroundResource(0)
                binding.flActionPlayerKertas.setBackgroundResource(0)
            }
            GameMechanic.SCISSOR -> {
                binding.flActionPlayerBatu.setBackgroundResource(0)
                binding.flActionPlayerGunting.setBackgroundResource(R.drawable.cover)
                binding.flActionPlayerKertas.setBackgroundResource(0)
            }
            GameMechanic.PAPER -> {
                binding.flActionPlayerBatu.setBackgroundResource(0)
                binding.flActionPlayerGunting.setBackgroundResource(0)
                binding.flActionPlayerKertas.setBackgroundResource(R.drawable.cover)
            }
            else -> {
                binding.flActionPlayerBatu.setBackgroundResource(0)
                binding.flActionPlayerGunting.setBackgroundResource(0)
                binding.flActionPlayerKertas.setBackgroundResource(0)
            }
        }
    }

    private fun setSelectComp(compMechanic: Int) {
        when (GameMechanic.formInt(compMechanic)) {
            GameMechanic.ROCK -> {
                binding.flActionComBatu.setBackgroundResource(R.drawable.cover)
                binding.flActionComGunting.setBackgroundResource(0)
                binding.flActionComKertas.setBackgroundResource(0)
            }
            GameMechanic.SCISSOR -> {
                binding.flActionComBatu.setBackgroundResource(0)
                binding.flActionComGunting.setBackgroundResource(R.drawable.cover)
                binding.flActionComKertas.setBackgroundResource(0)
            }
            GameMechanic.PAPER -> {
                binding.flActionComBatu.setBackgroundResource(0)
                binding.flActionComGunting.setBackgroundResource(0)
                binding.flActionComKertas.setBackgroundResource(R.drawable.cover)
            }
            else -> {
                binding.flActionComBatu.setBackgroundResource(0)
                binding.flActionComGunting.setBackgroundResource(0)
                binding.flActionComKertas.setBackgroundResource(0)
            }
        }
    }

    private fun resetGame() {
        setSelectPlayer(-1)
        setSelectComp(-1)
        binding.ivImageVs.setImageResource(R.drawable.vs)
    }
}