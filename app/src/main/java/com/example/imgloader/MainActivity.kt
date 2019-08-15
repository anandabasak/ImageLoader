package com.example.imgloader

import android.content.Context

import android.os.Bundle
import android.view.*

import android.widget.ImageView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import kotlinx.android.synthetic.main.item_layout.*


class MainActivity : AppCompatActivity() {


    private var recyclerView: RecyclerView? = null
    private var toggleButton: ToggleButton? = null

    private val imageList = arrayListOf(
        "https://images.pexels.com/photos/1722183/pexels-photo-1722183.jpeg?dl&fit=crop&crop=entropy&w=1280&h=1920",
        "https://images.pexels.com/photos/1667580/pexels-photo-1667580.jpeg?dl&fit=crop&crop=entropy&w=1280&h=1920",
        "https://images.pexels.com/photos/1470405/pexels-photo-1470405.jpeg?dl&fit=crop&crop=entropy&w=1280&h=853",
        "https://images.pexels.com/photos/1005417/pexels-photo-1005417.jpeg?dl&fit=crop&crop=entropy&w=1280&h=1600",
        "https://images.pexels.com/photos/1294671/pexels-photo-1294671.jpeg?dl&fit=crop&crop=entropy&w=1280&h=1920",
        "https://images.pexels.com/photos/1040893/pexels-photo-1040893.jpeg?dl&fit=crop&crop=entropy&w=1280&h=1919",
        "https://images.pexels.com/photos/1956974/pexels-photo-1956974.jpeg?dl&fit=crop&crop=entropy&w=1280&h=853",
        "https://images.pexels.com/photos/1374064/pexels-photo-1374064.jpeg?dl&fit=crop&crop=entropy&w=1280&h=1706",
        "https://images.pexels.com/photos/1931142/pexels-photo-1931142.jpeg?dl&fit=crop&crop=entropy&w=1280&h=1919",
        "https://images.pexels.com/photos/1295036/pexels-photo-1295036.jpeg?dl&fit=crop&crop=entropy&w=1280&h=1707",
        "https://images.pexels.com/photos/1320684/pexels-photo-1320684.jpeg?dl&fit=crop&crop=entropy&w=1920&h=1440",
        "https://images.pexels.com/photos/1908677/pexels-photo-1908677.jpeg?dl&fit=crop&crop=entropy&w=1280&h=1706"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView = findViewById(R.id.recycler_view)
        toggleButton = findViewById(R.id.toggleButton)

        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.adapter = ImageAdapter(imageList, this)



        toggleButton?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                recyclerView?.layoutManager = GridLayoutManager(this, 2)
            } else {
                recyclerView?.layoutManager = LinearLayoutManager(this)
            }
        }



    }

    /*class RecyclerTouchListener(context: Context, recyclerView: RecyclerView, private val clickListener: ClickListener?) : RecyclerView.OnItemTouchListener {

        private val gestureDetector: GestureDetector

        init {
            gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
                override fun onSingleTapUp(e: MotionEvent): Boolean {
                    return true
                }

                override fun onLongPress(e: MotionEvent) {
                    val child = recyclerView.findChildViewUnder(e.x, e.y)
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child))
                    }
                }
            })
        }

        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {

            val child = rv.findChildViewUnder(e.x, e.y)
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child))
            }
            return false
        }

        override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}

        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

        }
    }*/


    class ImageAdapter(private val items: ArrayList<String>, private val context: Context) : RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false))

        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            ImageLoader.with(context).load(holder.imageView, items[position])
            holder.imageView.setOnClickListener{

                val intent = Intent(context,SingleImage::class.java)
                intent.putExtra("image_url",items[position])
                context.startActivity(intent)
            }
        }


        override fun getItemCount(): Int {
            return items.size
        }



    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView = view.findViewById(R.id.item_imageView) as ImageView



    }
}

/*interface ClickListener {
    fun onClick(view: View, position: Int)

    fun onLongClick(view: View?, position: Int)
}*/
