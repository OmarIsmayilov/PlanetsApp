package com.omarismayilov.recycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.omarismayilov.recycleview.databinding.ActivityDetailBinding
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    private lateinit var actionBar : ActionBar

    private lateinit var planetList : ArrayList<modelItem>
    private var positionClicked : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar = supportActionBar!!
        actionBar.setDisplayShowHomeEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)

        planetList = intent.getSerializableExtra("planets") as ArrayList<modelItem>
        positionClicked = intent.getIntExtra("position",0)

        binding.planetnameTv.text = planetList[positionClicked].title
        binding.infoTv.text = planetList[positionClicked].info
        binding.planetimageIm.setImageResource(planetList[positionClicked].image)


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }


}