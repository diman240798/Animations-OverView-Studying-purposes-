package app.quiz.sfedu.quiz_app.list

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.TextView
import app.quiz.sfedu.quiz_app.R

class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
    val start_test_tw: TextView

    init {
        start_test_tw = view.findViewById(R.id.menu_start_test_tw)
    }

}
