package ifsp.edu.br.minguini.matheus.trabalhoFinal2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import ifsp.edu.br.minguini.matheus.trabalhoFinal2.fragment.MainFragment
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getString(R.string.app_name)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setFragment(MainFragment())
    }

    private fun setFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }
}

