package io.muth0mi.droid_vcheck

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.isSuccessful
import com.google.gson.Gson
import com.google.gson.JsonObject
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
            finish()
        }
    }

    private fun checkIfVersionIsUpToDate(version: String) {
        GlobalScope.launch {
            val (request, response, error) = Fuel.get(
                "https://raw.githubusercontent.com/muth0mi/Droid-Vcheck/master/index.json",
                listOf("version" to version)
            ).response()

            if (response.isSuccessful) {
                val jsonString = response.body().asString("application/json")

                // Parse JSON
                val jsonObject = Gson().fromJson(jsonString, JsonObject::class.java)
                val newestVersion = jsonObject.get("minimum_supported_version").toString().replace("\"", "")

                Log.e("Checking for update", "Newest version -> $newestVersion")

                // Compare versions
                if (version.toFloat() < newestVersion.toFloat()) {
                    startActivity(Intent(applicationContext, DialogActivity::class.java))
                } else
                    runOnUiThread { Toast.makeText(applicationContext, "Application up to date", Toast.LENGTH_SHORT).show() }
            } else Log.e("Checking for update", "Failed $error")
        }
    }

}
