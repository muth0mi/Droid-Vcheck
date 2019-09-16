package io.muth0mi.droid_vcheck

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.isSuccessful
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        val spnVersion = findViewById<Spinner>(R.id.spn_versions)
        val btnProceed = findViewById<Button>(R.id.btn_emulate)


        // Check version in coroutine and proceed to application
        btnProceed.setOnClickListener {
            checkIfVersionIsUpToDate(spnVersion.selectedItem.toString())
            // Proceed to application
            startActivity(Intent(this, Application::class.java))
        }
    }

    private fun checkIfVersionIsUpToDate(version: String) {
        GlobalScope.launch {
            val (request, response, error) = Fuel.get(
                "https://raw.githubusercontent.com/muth0mi/Droid-Vcheck/master/index.json",
                listOf("version" to version)
            ).response()

            if (response.isSuccessful) {
                Log.e("Checking for update", response.body().asString("application/json"))
            } else Log.e("Checking for update", "Failed " + error.toString())

        }
    }
}
