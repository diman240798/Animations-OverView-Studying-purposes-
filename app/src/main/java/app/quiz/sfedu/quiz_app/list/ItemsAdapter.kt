package app.quiz.sfedu.quiz_app.list

import android.content.Context
import android.content.res.Resources
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import app.quiz.sfedu.quiz_app.R
import app.quiz.sfedu.quiz_app.listeners.ImageRevealer


class ItemsAdapter(
    private val context: Context,
    private val imageRevealer: ImageRevealer
) : RecyclerView.Adapter<ItemHolder>() {


    lateinit var items: List<Item>
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ItemHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_menu_item, parent, false)
        return ItemHolder(view)
    }

    override fun getItemCount(): Int = items.size

    var color = context.resources.getColor(R.color.orange)
    var drawable = R.drawable.ripple_orange

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val testNumber = items[position].testNumber;
        val textView = holder.start_test_tw
        textView.text = testNumber.toString()

        textView.setTextColor(color)
        textView.setBackgroundResource(drawable)

        textView.setOnClickListener {
            // array to save view position
            val originalPos = IntArray(2)
            // write view position to array
            textView.getLocationInWindow(originalPos)

            //or view.getLocationOnScreen(originalPos)
            val cx = originalPos[0] + 60
            val cy = originalPos[1] + 20
            // get the final radius for the clipping circle
            val finalRadius = Math.max(
                Resources.getSystem().getDisplayMetrics().heightPixels,
                Resources.getSystem().getDisplayMetrics().widthPixels
            ).toFloat()

            imageRevealer.revealImage(cx, cy, finalRadius, testNumber)


        }
    }

    fun changeColor(color: Int, drawable: Int) {
        this.color = color
        this.drawable = drawable
        notifyDataSetChanged()
    }

}