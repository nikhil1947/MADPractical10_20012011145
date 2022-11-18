package com.example.madpractical10_20012011145

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.WindowCompat
import com.example.madpractical10_20012011145.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener {
            Log.i(TAG, "onCreate: CLICKED=")
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    Log.i(TAG, "onCreate: HI@@@@=")
                    val data = HttpRequest().makeServiceCall(
                        "https://api.json-generator.com/templates/Ct0cYAQJ6ZGD/data",
                        "0kr4qlh7m3o0lhfhtxc1h8h8p2pttnp2dx1jqg55")
                    Log.i("TAG", "onCreate: data="+data)
                    withContext(Dispatchers.Main) {
                        try {
                            if(data != null)
                                runOnUiThread{getPersonDetailsFromJson(data)}
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
    private fun getPersonDetailsFromJson(sJson: String?) {
        Log.i(TAG, "getPersonDetailsFromJson: "+sJson)
        val personList = ArrayList<Person>()
        try {
            val jsonArray = JSONArray(sJson)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray[i] as JSONObject
                val person = Person(jsonObject)
                personList.add(person)
            }
            Log.i(TAG, "getPersonDetailsFromJson: ")
            binding.listView1.adapter = PersonAdapter(this, personList)
        } catch (ee: JSONException) {
            ee.printStackTrace()
        }
    }
}