package app.quiz.sfedu.quiz_app.db_model

import io.realm.RealmObject

open class QuestionModel(
    var id: Int,
    var question: String,
    var answerNumber: Int,
    var answers: Array<String>
) : RealmObject()
