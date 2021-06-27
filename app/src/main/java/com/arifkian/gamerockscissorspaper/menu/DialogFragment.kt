package com.arifkian.gamerockscissorspaper.menu

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.arifkian.gamerockscissorspaper.R
import com.arifkian.gamerockscissorspaper.data.preference.UserPreference
import com.arifkian.gamerockscissorspaper.databinding.FragmentDialogBinding

class DialogFragment(val value: Int, val flag: Int) : DialogFragment() {

    private lateinit var binding: FragmentDialogBinding
    private lateinit var userPreference: UserPreference
    private lateinit var listener: DialogFragmentList

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDialogBinding.inflate(inflater, container, false)
        dialog?.setCancelable(false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context.let {
            if (it != null) {
                userPreference = UserPreference(it)
            }
        }
        if (value == 1) {
            binding.tvResult.text = String.format(
                getString(
                    R.string.text_winning_condition,
                    context.let {
                        userPreference.userName
                    }
                )
            )
        } else if (value == 0) {
            if(flag == 0){
                binding.tvWinner.text = String.format(
                    getString(
                        R.string.text_winning_condition,
                        "Player 2"
                    )
                )
            }else{
                binding.tvWinner.text = String.format(
                    getString(
                        R.string.text_winning_condition,
                        "CPU"
                    )
                )
            }
        } else {
            binding.tvWinner.setText(R.string.text_draw_condition)
        }
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        binding.btnReturn.setOnClickListener {
            val intent = Intent(view.context, GameMenuActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
        binding.btnReplay.setOnClickListener {
            dismiss()
            if(this::listener.isInitialized){
                listener.onDialogDismiss()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is DialogFragmentList){
            listener = context
        }
    }
}