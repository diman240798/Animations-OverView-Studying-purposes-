package app.quiz.sfedu.quiz_app.list

import android.animation.Animator
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import android.widget.ImageView
import app.quiz.sfedu.quiz_app.R
import app.quiz.sfedu.quiz_app.activity.TestActivity


class ItemsAdapter(
    private val context: Context,
    private val imageToReveal: ImageView
) : RecyclerView.Adapter<ItemHolder>(), Runnable {


    lateinit var items: List<Item>

    override fun run() {
        anim?.removeAllListeners()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ItemHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_menu_item, parent, false)
        return ItemHolder(view)
    }

    override fun getItemCount(): Int = items.size


    private var anim: Animator? = null

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val testNumber = items[position].testNumber;
        val textView = holder.start_test_tw
        textView.text = testNumber.toString();
        textView.setOnClickListener {
            val originalPos = IntArray(2)
            textView.getLocationInWindow(originalPos)
            //or view.getLocationOnScreen(originalPos)
            val cx = originalPos[0] + 60
            val cy = originalPos[1] + 20
            val startRadius = 0
            // get the final radius for the clipping circle
            val finalRadius = Math.max(Resources.getSystem().getDisplayMetrics().heightPixels,
                Resources.getSystem().getDisplayMetrics().widthPixels).toFloat()


            anim = ViewAnimationUtils.createCircularReveal(imageToReveal, cx, cy, 0f, finalRadius)
            anim?.duration = 2000


            anim?.addListener(
                object : Animator.AnimatorListener {
                    override fun onAnimationRepeat(animation: Animator?) {

                    }

                    override fun onAnimationEnd(animation: Animator?) {
                        val intent = Intent(context, TestActivity::class.java)
                        intent.putExtra("number", testNumber)
                        context.startActivity(intent)
                        imageToReveal.postDelayed({ imageToReveal.visibility = View.GONE }, 600)

                    }

                    override fun onAnimationCancel(animation: Animator?) {
                    }

                    override fun onAnimationStart(animation: Animator?) {
                    }

                }
            )
            imageToReveal.postDelayed({
                imageToReveal.visibility = View.VISIBLE
                anim?.start()
            }, 280)

        }
    }

}
