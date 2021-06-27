package com.arifkian.gamerockscissorspaper.intro

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arifkian.gamerockscissorspaper.R
import com.arifkian.gamerockscissorspaper.data.preference.UserPreference
import com.arifkian.gamerockscissorspaper.databinding.FragmentPlayerNameFromBinding
import com.arifkian.gamerockscissorspaper.menu.GameMenuActivity

class PlayerNameFormFragment : Fragment() {

    private lateinit var binding : FragmentPlayerNameFromBinding
    private lateinit var userPreference: UserPreference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPlayerNameFromBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //todo
        prefilledCurrentName()
    }

    private fun prefilledCurrentName() {
        context?.let{
            userPreference = UserPreference(it)
            binding.etPlayerName.setText(userPreference.userName.orEmpty())
        }
    }private fun isFormFilled():Boolean {
        val name = binding.etPlayerName.text.toString()
        var isFormValid = true

        if(name.isEmpty()){
            isFormValid = false
            Toast.makeText(context, R.string.text_error_toast_name_empty, Toast.LENGTH_SHORT).show()
        }
        return isFormValid
    }

    fun navigateToMenuGame(){
        if(isFormFilled()){
            userPreference.userName= binding.etPlayerName.text.toString()
            context?.startActivity(Intent(context, GameMenuActivity::class.java))
        }
    }

}