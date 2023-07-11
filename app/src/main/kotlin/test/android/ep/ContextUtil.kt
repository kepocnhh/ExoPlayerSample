package test.android.ep

import android.content.Context
import android.widget.Toast

internal fun Context.showToast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}
