package app.quiz.sfedu.quiz_app.application

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class Application : Application(), Realm.Transaction {
    override fun onCreate() {
        super.onCreate()
        val config = RealmConfiguration.Builder()
            .name("myrealm.realm")
            .initialData(this)
            .build()

        //Realm.init(applicationContext)
        //Realm.setDefaultConfiguration(config)

    }

    override fun execute(realm: Realm) {

    }
}
