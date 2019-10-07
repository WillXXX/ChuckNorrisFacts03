package com.example.chucknorrisfacts03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.net.URL

class MainActivity : AppCompatActivity() {

    val URL = "https://api.icndb.com/jokes/random"
    var okHttpClient: OkHttpClient = OkHttpClient ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nextBtn.setOnClickListener {
            loadRadomFact()
        }
    }

    private fun loadRadomFact(){
        runOnUiThread{
            progressBar.visibility = View.VISIBLE
        }

        val request: Request = Request.Builder().url(URL).build()
        okHttpClient.newCall(request).enqueue(object: Callback{
            override fun onFailure(call: Call?, e: IOException?) {
                }

            override fun onResponse(call: Call?, response: Response?) {
                val json = response?.body()?.string()
                val txt = (JSONObject(json).getJSONObject("valeu").get("joke").toString())

                runOnUiThread {
                    progressBar.visibility = View.GONE
                    factTV.text = Html.fromHtml(txt)
                }
            }
        })
    }
}








