package io.muth0mi.droid_vcheck

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class DialogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val alert = android.app.AlertDialog.Builder(this)
            .setMessage("This version of Musoni App is outed. Update to get a better user experience.")
            .setNegativeButton("Dismiss") { dialog, _ -> finish() }
            .setPositiveButton("Update") { dialog, _ ->
                try {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")))
                } catch (_: Exception) {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$packageName")))
                }
                finish()
                android.os.Process.killProcess(android.os.Process.myPid())
            }
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setCancelable(false)
        alert.show()
    }
}
