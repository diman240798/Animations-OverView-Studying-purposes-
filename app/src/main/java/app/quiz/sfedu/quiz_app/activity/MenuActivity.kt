package app.quiz.sfedu.quiz_app.activity

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import app.quiz.sfedu.quiz_app.R
import app.quiz.sfedu.quiz_app.list.Item
import app.quiz.sfedu.quiz_app.list.ItemsAdapter
import kotlinx.android.synthetic.main.menu_activity.*
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup
import android.widget.GridLayout.VERTICAL
import java.nio.file.Files.size
import android.support.annotation.DimenRes
import android.support.annotation.NonNull
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import android.widget.GridLayout.HORIZONTAL
import app.quiz.sfedu.quiz_app.ui.GridSpacingItemDecoration


class MenuActivity : AppCompatActivity() {

    lateinit var itemsApapter: ItemsAdapter

    var data: List<Item> = listOf(
        Item(1), Item(2), Item(3),
        Item(4), Item(5), Item(6),
        Item(7), Item(8)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_activity)

        itemsApapter = ItemsAdapter(this)
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
}
