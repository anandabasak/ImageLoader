package com.example.imgloader

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.item_image.*

var url = ""
class SingleImage : AppCompatActivity(){

    private val imageLoader1: ImageLoader? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_image)
        url = getIntent().getStringExtra("image_url");
        Toast.makeText(this, url,Toast.LENGTH_LONG).show()


    }
}
