package app.quiz.sfedu.quiz_app.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.transition.TransitionManager
import android.view.View
import app.quiz.sfedu.quiz_app.R
import app.quiz.sfedu.quiz_app.list.Item
import app.quiz.sfedu.quiz_app.list.ItemsAdapter
import kotlinx.android.synthetic.main.menu_activity.*
import android.widget.GridLayout.VERTICAL
import android.widget.ImageView


class MenuActivity : AppCompatActivity() {

    lateinit var itemsApapter: ItemsAdapter
    lateinit var animationStopper: Runnable

    var data: List<Item> = listOf(
        Item(1), Item(2), Item(3),
        Item(4), Item(5), Item(6),
        Item(7), Item(8)
    )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_activity)

        val imageToReveal = findViewById<ImageView>(R.id.menu_image_to_reveal)
        itemsApapter = ItemsAdapter(this, imageToReveal)
        animationStopper = itemsApapter
        itemsApapter.items = data
        var recyclerView = menu_recycler_view as RecyclerView;
        recyclerView.adapter = itemsApapter
        val gridLayoutManager = GridLayoutManager(this, 3, VERTICAL, false)
        recyclerView.layoutManager = gridLayoutManager


        /*val spanCount = 3 // 3 columns
        val spacing = 50 // 50px
        val includeEdge = true
        recyclerView.addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, includeEdge))*/
    }

    fun setLanguage(it : View) {

        val id = it.id;
        var color = R.color.orange
        var drawable = R.drawable.ripple_orange

        when (id) {
            menu_bottom_cpp_iv.id -> {
                menu_image_to_reveal.setImageResource(R.drawable.cpp_header_big)
                color = R.color.orange
                drawable = R.drawable.ripple_orange
            }
            menu_bottom_cpp_java.id -> {
                menu_image_to_reveal.setImageResource(R.drawable.java_header_big)
                color = R.color.red
                drawable = R.drawable.ripple_red

            }
            menu_bottom_cpp_python.id -> {
                menu_image_to_reveal.setImageResource(R.drawable.python_header_big)
                color = R.color.green
                drawable = R.drawable.ripple_green

            }
        }
        TransitionManager.beginDelayedTransition(menu_container)
        menu_sign_place_holder.setContentId(it.id)
        it.postDelayed({
            menu_tool_bar.setBackgroundResource(color)
            val Color = resources.getColor(color)

            window.statusBarColor = Color
            itemsApapter.changeColor(Color, drawable)
        }, 400)
    }

    override fun onBackPressed() {
        animationStopper.run()
        super.onBackPressed()
    }
}
