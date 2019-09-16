package io.muth0mi.droid_vcheck

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        val spnVersion = findViewById<Button>(R.id.btn_emulate)
        val btnProceed = findViewById<Button>(R.id.btn_emulate)


        // Check version in coroutine and proceed to application
        btnProceed.setOnClickListener {
            checkIfVersionIsUpToDate()
            // Proceed to application
            startActivity(Intent(this, Application::class.java))
        }
    }

    private fun checkIfVersionIsUpToDate() {
        GlobalScope.launch {

            Thread.sleep(5000)
            Log.e("Global scope couroutine", "I'm Done")
        }
    }
}
