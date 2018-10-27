package app.quiz.sfedu.quiz_app.list

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import app.quiz.sfedu.quiz_app.R
import app.quiz.sfedu.quiz_app.activity.TestActivity

class ItemsAdapter(private val context: Context) : RecyclerView.Adapter<ItemHolder>() {
    lateinit var items: List<Item>

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ItemHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_menu_item, parent, false)
        return ItemHolder(view)
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val testNumber = items[position].testNumber;
        holder.start_test_tw.text = testNumber.toString();
        holder.start_test_tw.setOnClickListener {
            val intent = Intent(context, TestActivity::class.java)
            intent.putExtra("number", testNumber)
            context.startActivity(intent)
        }
    }

}
