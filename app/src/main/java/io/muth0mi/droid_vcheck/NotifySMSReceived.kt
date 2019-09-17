package io.muth0mi.droid_vcheck

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class NotifySMSReceived : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val alert = android.app.AlertDialog.Builder(this)
            .setMessage("This version of Musoni App is outed. Update to get a better user experience.")
            .setNegativeButton("Dismiss") { dialog, _ -> dialog.dismiss() }
            .setPositiveButton("Update", { dialog, which -> })
            .setIcon(android.R.drawable.ic_dialog_alert)
        alert.show()
    }
}
