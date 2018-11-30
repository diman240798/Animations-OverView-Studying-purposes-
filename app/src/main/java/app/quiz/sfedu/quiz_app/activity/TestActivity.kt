package app.quiz.sfedu.quiz_app.activity

import android.os.Bundle
import android.support.constraint.motion.MotionLayout
import android.support.v7.app.AppCompatActivity
import app.quiz.sfedu.quiz_app.R
import app.quiz.sfedu.quiz_app.db_model.QuestionModel
import app.quiz.sfedu.quiz_app.db_model.TestModel
import app.quiz.sfedu.quiz_app.fragment.TestFragment
import kotlinx.android.synthetic.main.test_activity.*

class TestActivity : AppCompatActivity() {
    private lateinit var testFragment: TestFragment

    private lateinit var tests: List<TestModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_activity)

        val extras = intent.extras
        val number = extras.getInt("number")
        val color = extras.getInt("color")

        window.statusBarColor = color



        testFragment = TestFragment()
        testFragment.setColor(color)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.test_root_lay, testFragment)
            .setCustomAnimations(android.R.animator.fade_out, android.R.animator.fade_in)
            .commit();

        val motionLayout = testActivityMotionContainer as MotionLayout
        motionLayout.setTransitionListener(
            object: MotionLayout.TransitionListener {
                override fun onTransitionChange(p0: MotionLayout?, startId: Int, endId: Int, progress: Float) {

                }

                override fun onTransitionCompleted(p0: MotionLayout?, currentId: Int) {
                    if(currentId == R.id.ending_set) {
                        // load fake Data
                        var questionsFake = arrayOf(
                            QuestionModel(
                                1, "Are you dumb?",
                                1, arrayOf("yes", "yes", "yes", "yes")
                            ),
                            QuestionModel(
                                2, "Are you Smart?",
                                1, arrayOf("no", "no", "no", "no")
                            ),
                            QuestionModel(
                                1, "Are you dumb?",
                                1, arrayOf("yes", "yes", "yes", "yes")
                            ),
                            QuestionModel(
                                1, "Are you dumb?",
                                1, arrayOf("yes", "yes", "yes", "yes")
                            )
                        )
                        tests = listOf<TestModel>(TestModel(1, questionsFake))
                        var test = tests.get(0)
                        val questions = test.questions
                        var question = questions[0]
                        testFragment.setQuestion(question)

                    }
                }
            }
        )
        motionLayout.transitionToEnd()



    }

    override fun onResume() {
        super.onResume()
        test_back_bt.setOnClickListener {
            testFragment.setQuestion(tests.get(0).questions[0])
        }

        test_next_bt.setOnClickListener {
            testFragment.setQuestion(tests.get(0).questions[1])
        }

    }

}
