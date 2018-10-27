package app.quiz.sfedu.quiz_app.db_model

import io.realm.RealmObject

open class TestModel (
    open var id: Int,
    var questions: Array<QuestionModel>
) : RealmObject() {}