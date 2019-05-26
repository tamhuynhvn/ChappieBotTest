package test.tamhuynh.com.chappiebot.view.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import test.tamhuynh.com.chappiebot.R
import test.tamhuynh.com.chappiebot.service.model.Item

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val fragment = ProjectListFragment()

            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, fragment, TAG_OF_PROJECT_LIST_FRAGMENT)
                    .commit()
        }
    }

    fun show(item: Item) {
        val projectFragment = ProjectFragment.forProject(item.document_id)

        supportFragmentManager
                .beginTransaction()
                .addToBackStack("project")
                .replace(R.id.fragment_container, projectFragment, null)
                .commit()
    }
}
