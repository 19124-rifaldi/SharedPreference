package com.binar.sharedpreference

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.binar.sharedpreference.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val sharedPrefile = "kotlinsharedpreference"
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreference : SharedPreferences =
            this.getSharedPreferences(sharedPrefile, Context.MODE_PRIVATE)
            //
        binding.btnSave.setOnClickListener{
            val id : Int = Integer.parseInt(binding.etInputId.text.toString())
            val name : String = binding.etInputName.text.toString()
            val editor : SharedPreferences.Editor = sharedPreference.edit()
            editor.putInt("id_key",id)
            editor.putString("name_key",name)
            editor.apply()
            Toast.makeText(this,"data saved",Toast.LENGTH_LONG).show()
        }

        binding.btnView.setOnClickListener{
            val sharedIdValue = sharedPreference.getInt("id_key",0)
            val sharedNameValue = sharedPreference.getString("name_key","defaultname")

            if (sharedIdValue.equals(0) && sharedNameValue.equals("defaultname")){
                binding.tvShowName.text = ("default name : ${sharedNameValue}")
                binding.tvShowId.text = ("default id : ${sharedIdValue}")
                Toast.makeText(this,"Data view kosong",Toast.LENGTH_LONG).show()
            }else{
                binding.tvShowId.text = sharedIdValue.toString()
                binding.tvShowName.text = sharedNameValue.toString()
                Toast.makeText(this,"Data view ditampilkan",Toast.LENGTH_LONG).show()
            }
        }
        binding.btnClear.setOnClickListener{
            val editor = sharedPreference.edit()

            editor.clear()
            editor.apply()
            binding.tvShowName.setText("")
            binding.tvShowId.setText("")
            Toast.makeText(this,"Data view dihapus",Toast.LENGTH_LONG).show()
        }

    }
}