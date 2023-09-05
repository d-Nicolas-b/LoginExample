package com.example.loginformularyexample.ui.login

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import com.example.loginformularyexample.IlogicApp
import com.example.loginformularyexample.R
import com.example.loginformularyexample.databinding.ActivityEnviromentBinding
import com.google.gson.Gson
import com.google.gson.JsonObject
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class EnvironmentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEnviromentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            binding = ActivityEnviromentBinding.inflate(layoutInflater)

            binding.btConfirm.setOnClickListener {
                onClickButton()
            }

            binding.etAccessCode.setOnEditorActionListener { _, i, keyEvent ->
                if (i == EditorInfo.IME_ACTION_DONE || (keyEvent.keyCode == KeyEvent.KEYCODE_ENTER && keyEvent.action == KeyEvent.ACTION_UP)) {
                    onClickButton()
                    true
                } else {
                    false
                }
            }

            setContentView(binding.root)

            val fileInputStream = openFileInput("app.config")
            val inputReader = InputStreamReader(fileInputStream)
            val output = inputReader.readText()

            val appConfig: JsonObject = Gson().fromJson(output, JsonObject::class.java)

            // Data is displayed in the TextView
            binding.etTag.setText(appConfig.get("serviceLabel").asString)
            binding.etAccessCode.setText(appConfig.get("serviceKey").asString)
        } catch (ex: Exception) {
            ex.printStackTrace()
            Log.e("YourTag", "Error: ${ex.message}")
        }
    }

    private fun saveConfiguration(accessCode: String, serviceLabel: String) {
        // Create a JsonObject with properties
        val appConfig = JsonObject().apply {
            addProperty("serviceKey", accessCode)
            addProperty("serviceLabel", serviceLabel)
        }

        // Open file for writing and use resources safely with "use"
        openFileOutput("app.config", Context.MODE_PRIVATE).use { fileOutputStream ->
            OutputStreamWriter(fileOutputStream).use { outputWriter ->
                // Write the JsonObject as a String to the file
                outputWriter.write(appConfig.toString())
            }
        }
    }

    private fun onClickButton() {
        try {
            val accessCode = binding.etAccessCode.text.toString()
            val serviceLabel = binding.etTag.text.toString()

            if (accessCode.isEmpty()) {
                showToast("Por favor digite el codigo de acceso")
            } else {
                saveConfiguration(accessCode, serviceLabel)
                showToast("File saved successfully!")
                finish()
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            Log.e("YourTag", "Error: ${ex.message}")
        }
    }

    private fun showToast(message: String) {
        // Show a Toast message using the application context
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }
}