package app.quiz.sfedu.quiz_app.activity

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.transition.TransitionManager
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.TranslateAnimation
import android.widget.GridLayout.VERTICAL
import android.widget.ImageView
import app.quiz.sfedu.quiz_app.R
import app.quiz.sfedu.quiz_app.list.Item
import app.quiz.sfedu.quiz_app.list.ItemsAdapter
import app.quiz.sfedu.quiz_app.listeners.ImageRevealer
import kotlinx.android.synthetic.main.menu_activity.*


class MenuActivity : AppCompatActivity(), ImageRevealer {

    // Views
    private lateinit var imageToReveal: ImageView
    lateinit var itemsApapter: ItemsAdapter

    private var anim: Animator? = null

    var data: List<Item> = listOf(
        Item(1), Item(2), Item(3),
        Item(4), Item(5), Item(6),
        Item(7), Item(8)
    )
    var color = R.color.orange
    private var Color: Int = 0
    lateinit var imageViews: ArrayList<ImageView>

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppThemeNoActionBar)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_activity)

        Color = resources.getColor(R.color.orange_status)
        menu_sign_place_holder.setContentId(R.id.menu_bottom_cpp_iv)


        imageToReveal = findViewById<ImageView>(R.id.menu_image_to_reveal)
        itemsApapter = ItemsAdapter(this, this)
        itemsApapter.items = data
        var recyclerView = menu_recycler_view as RecyclerView;
        recyclerView.adapter = itemsApapter
        val gridLayoutManager = GridLayoutManager(this, 3, VERTICAL, false)
        recyclerView.layoutManager = gridLayoutManager

        imageViews = arrayListOf<ImageView>(menu_bottom_java_iv, menu_bottom_cpp_iv, menu_bottom_python_iv)

        /*val spanCount = 3 // 3 columns
        val spacing = 50 // 50px
        val includeEdge = true
        recyclerView.addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, includeEdge))*/
    }

    private var id: Int = R.id.menu_bottom_cpp_iv

    fun setLanguage(it: View) {

        id = it.id;
        var drawable = R.drawable.ripple_orange
        var colorStatus = R.color.orange_status


        when (id) {
            menu_bottom_cpp_iv.id -> {
                val orange = R.color.orange
                if (color == orange)
                    return
                menu_image_to_reveal.setImageResource(R.drawable.cpp_header_big)
                color = orange
                drawable = R.drawable.ripple_orange
                colorStatus = R.color.orange_status
            }
            menu_bottom_java_iv.id -> {
                val red = R.color.red
                if (color == red)
                    return
                menu_image_to_reveal.setImageResource(R.drawable.java_header_big)
                color = red
                drawable = R.drawable.ripple_red
                colorStatus = R.color.red_status
            }
            menu_bottom_python_iv.id -> {
                val green = R.color.green
                if (color == green)
                    return
                menu_image_to_reveal.setImageResource(R.drawable.python_header_big)
                color = green
                drawable = R.drawable.ripple_green
                colorStatus = R.color.green_status
            }
        }
        TransitionManager.beginDelayedTransition(menu_container)
        menu_sign_place_holder.setContentId(it.id)
        it.postDelayed({
            menu_tool_bar.setBackgroundResource(color)
            Color = resources.getColor(colorStatus)

            window.statusBarColor = Color
            itemsApapter.changeColor(Color, drawable)
        }, 400)
    }

    override fun onBackPressed() {
        if (anim != null)
            return
        super.onBackPressed()
    }

    private var defaultYforToolBar: Float = 0F
    private var defaultYBottom_IV: Float = 0F

    override fun revealImage(cx: Int, cy: Int, finalRadius: Float, testNumber: Int) {
        if (anim != null)
            return

        anim = ViewAnimationUtils.createCircularReveal(imageToReveal, cx, cy, 0f, finalRadius)
        anim?.duration = 2000


        anim?.addListener(
            object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {

                }

                override fun onAnimationEnd(animation: Animator?) {
                    val intent = Intent(this@MenuActivity, TestActivity::class.java)
                    intent.putExtra("number", testNumber)
                    intent.putExtra("color", Color)
                    startActivity(intent)
                    imageToReveal.postDelayed({
                        imageToReveal.visibility = View.GONE
                        val translateAnimationRestore = TranslateAnimation(
                            0F, 0F,
                            defaultYforToolBar - menu_tool_bar.height, defaultYforToolBar
                        )
                        translateAnimationRestore.duration = 1000
                        translateAnimationRestore.fillAfter = true

                        enableImageViews()
                        menu_tool_bar.startAnimation(translateAnimationRestore)
                        findViewById<ImageView>(id).startAnimation(translateAnimationRestore)
                    }, 600)

                    anim = null
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

        defaultYforToolBar = menu_tool_bar.y
        defaultYBottom_IV = menu_sign_place_holder.y

        val translateAnimation = TranslateAnimation(
            0F, 0F,
            defaultYforToolBar, defaultYforToolBar - menu_tool_bar.height
        )
        translateAnimation.duration = 2000
        translateAnimation.fillAfter = true

        disableImageViews()
        menu_tool_bar.startAnimation(translateAnimation)
        findViewById<ImageView>(id).startAnimation(translateAnimation)

    }


    fun disableImageViews() {
        var viewsToAnimate = ArrayList<ImageView>()

        for (view in imageViews) {
            view.isEnabled = false
            if (view.id != id) {
                viewsToAnimate.add(view)
            }
        }

        val firstView = viewsToAnimate[0]
        defaultYBottom_IV = firstView.y
        val finalTranslation = defaultYBottom_IV - firstView.height

        val translateAnimation = TranslateAnimation(
            0F, 0F, firstView.translationY, firstView.translationY + firstView.height
        )
        translateAnimation.duration = 1000
        translateAnimation.fillAfter = true

        for (view in viewsToAnimate) {
            view.startAnimation(translateAnimation)
        }
    }

    fun enableImageViews() {
        var viewsToAnimate = ArrayList<ImageView>()

        for (view in imageViews) {
            view.isEnabled = true
            if (view.id != id) {
                viewsToAnimate.add(view)
            }
        }


        val firstView = viewsToAnimate[0]

        val translateAnimation = TranslateAnimation(
            0F, 0F, firstView.translationY + firstView.height, firstView.translationY
        )
        translateAnimation.duration = 1000
        translateAnimation.fillAfter = true


        for (view in viewsToAnimate) {
            view.startAnimation(translateAnimation)
        }
    }
}