package com.example.worldskillsshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import com.example.worldskillsshop.databinding.ActivityDialogueOpenBinding

class DialogueOpen : AppCompatActivity() {

    lateinit var binding: ActivityDialogueOpenBinding

    var bar: ActionBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialogue_open)

        binding = ActivityDialogueOpenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bar = supportActionBar
        bar?.setDisplayHomeAsUpEnabled(true)
        bar?.title = ""
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
        }
        return true
    }
}