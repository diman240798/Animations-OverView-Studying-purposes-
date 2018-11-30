package app.quiz.sfedu.quiz_app.fragment

import android.content.res.ColorStateList
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
    private var color: Int = 0
    var hashMap = mutableMapOf<Int, Boolean>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = LayoutInflater.from(context).inflate(R.layout.test_layout, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        configureViews()
    }

    private fun initViews(view: View) {
        this.questionUI = view.findViewById<TextView>(R.id.test_question_tw)
        answer_1 = view.findViewById<TextView>(R.id.test_answer_1_tw)
        answer_2 = view.findViewById<TextView>(R.id.test_answer_2_tw)
        answer_3 = view.findViewById<TextView>(R.id.test_answer_3_tw)
        answer_4 = view.findViewById<TextView>(R.id.test_answer_4_tw)

        view.findViewById<View>(R.id.divider1).setBackgroundColor(color)
        view.findViewById<View>(R.id.divider2).setBackgroundColor(color)
        view.findViewById<View>(R.id.divider3).setBackgroundColor(color)
        view.findViewById<View>(R.id.divider4).setBackgroundColor(color)

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

                        questionUI.postDelayed({
                            val questionWasAnswered = hashMap.get(question.id)
                            setDefaultColors()
                            if (questionWasAnswered != null && questionWasAnswered) {
                                disableClicks()
                            } else
                                enableClicks()
                        }, 300)

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

    private var textColors: ColorStateList? = null

    override fun onClick(v: View?) {
        val tag = v?.getTag(tagKey) as Int
        hashMap.put(question?.id!!, true)
        textColors = answer_1.textColors
        question!!.chosenAnswer = tag
        setAnswersColors(tag)
        disableClicks()
    }

    private fun setAnswersColors(tag: Int) {
        if (tag == rightAnswer) {
            setTextColor(tag, Color.GREEN)
        } else {
            setTextColor(tag, Color.RED)
            setTextColor(rightAnswer, Color.GREEN)
        }
    }

    private fun disableClicks() {
        answer_1.isEnabled = false
        answer_2.isEnabled = false
        answer_3.isEnabled = false
        answer_4.isEnabled = false

        var chosenId = question!!.chosenAnswer
        setAnswersColors(chosenId)


    }

    private fun enableClicks() {
        answer_1.isEnabled = true
        answer_2.isEnabled = true
        answer_3.isEnabled = true
        answer_4.isEnabled = true

        setDefaultColors()
    }

    private fun setDefaultColors() {
        if (textColors != null) {
            answer_1.setTextColor(textColors)
            answer_2.setTextColor(textColors)
            answer_3.setTextColor(textColors)
            answer_4.setTextColor(textColors)
        }
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

    fun setColor(color: Int) {
        this.color = color
    }

}
