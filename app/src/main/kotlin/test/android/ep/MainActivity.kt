package test.android.ep

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.net.URL

internal class MainActivity : AppCompatActivity() {
    private fun onClick(spec: String) {
        if (spec.trim().isEmpty()) {
            showToast("Spec is empty!")
            return
        }
        val url = try {
            URL(spec)
        } catch (e: Throwable) {
            showToast("Spec is wrong! $e")
            return
        }
        // todo
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context: Context = this
        val root = FrameLayout(context).also {
            it.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
            )
        }
        val rows = LinearLayout(context).also {
            it.layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER_VERTICAL,
            )
            it.orientation = LinearLayout.VERTICAL
            root.addView(it)
        }
        TextView(context).also {
            it.layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
            )
            it.text = "spec"
            rows.addView(it)
        }
        val editText = EditText(context).also {
            it.layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
            )
            rows.addView(it)
        }
        Button(context).also {
            it.layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
            )
            it.text = "go"
            it.setOnClickListener {
                onClick(editText.text.toString())
            }
            rows.addView(it)
        }
        setContentView(root)
    }
}
