package app.quiz.sfedu.quiz_app.fragment

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import app.quiz.sfedu.quiz_app.R
import app.quiz.sfedu.quiz_app.db_model.QuestionModel
import kotlinx.android.synthetic.main.test_layout.*

class TestFragment() : Fragment(), View.OnClickListener {


    // Views
    // Question
    private lateinit var questionUI: TextView
    // answers
    private lateinit var answer_1: TextView
    private lateinit var answer_2: TextView
    private lateinit var answer_3: TextView
    private lateinit var answer_4: TextView

    // Fields
    private val tagKey: Int = R.string.tag_key
    private var rightAnswer: Int = 0
    private var question: QuestionModel? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = LayoutInflater.from(context).inflate(R.layout.test_layout, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        this.questionUI = view.findViewById<TextView>(R.id.test_question_tw)
        answer_1 = view.findViewById<TextView>(R.id.test_answer_1_tw)
        answer_2 = view.findViewById<TextView>(R.id.test_answer_2_tw)
        answer_3 = view.findViewById<TextView>(R.id.test_answer_3_tw)
        answer_4 = view.findViewById<TextView>(R.id.test_answer_4_tw)

    }

    override fun onResume() {
        super.onResume()
        configureViews()
        if (question != null)
            setData(question)

    }

    private fun configureViews() {
        answer_1.setTag(tagKey, 1)
        answer_1.setOnClickListener(this)
        answer_2.setTag(tagKey, 2)
        answer_2.setOnClickListener(this)
        answer_3.setTag(tagKey, 3)
        answer_3.setOnClickListener(this)
        answer_4.setTag(tagKey, 4)
        answer_4.setOnClickListener(this)
    }

    fun setData(question: QuestionModel?) {
        if (question != null) {
            rightAnswer = question.answerNumber
            this.questionUI.text = question.question


            val animation = AnimationUtils.loadAnimation(context, R.anim.fade_in_quick)

            questionUI.startAnimation(animation)
            questionUI.animation.setAnimationListener(
                object : Animation.AnimationListener {
                    override fun onAnimationRepeat(animation: Animation?) {
                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        val animation = AnimationUtils.loadAnimation(context, R.anim.fade_in_quick)

                        answer_1.text = question.answers[0]
                        answer_2.text = question.answers[1]
                        answer_3.text = question.answers[2]
                        answer_4.text = question.answers[3]

                        answer_1.startAnimation(animation)
                        answer_2.startAnimation(animation)
                        answer_3.startAnimation(animation)
                        answer_4.startAnimation(animation)
                    }

                    override fun onAnimationStart(animation: Animation?) {

                    }
                }
            )

        }
    }

    override fun onClick(v: View?) {
        val tag = v?.getTag(tagKey) as Int

        if (tag == rightAnswer) {
            setTextColor(tag, Color.GREEN)
        } else {
            setTextColor(tag, Color.RED)
            setTextColor(rightAnswer, Color.GREEN)
        }
        disableClicks()

    }

    private fun disableClicks() {
        answer_1.isEnabled = false
        answer_2.isEnabled = false
        answer_3.isEnabled = false
        answer_4.isEnabled = false
    }

    private fun enableClicks() {
        answer_1.isEnabled = true
        answer_2.isEnabled = true
        answer_3.isEnabled = true
        answer_4.isEnabled = true
    }

    private fun setTextColor(tag: Int, color: Int) {
        when (tag) {
            1 -> test_answer_1_tw.setTextColor(color)
            2 -> test_answer_2_tw.setTextColor(color)
            3 -> test_answer_3_tw.setTextColor(color)
            4 -> test_answer_4_tw.setTextColor(color)
        }
    }

    fun setQuestion(question: QuestionModel) {
        this.question = question
        setData(question)
    }
}
