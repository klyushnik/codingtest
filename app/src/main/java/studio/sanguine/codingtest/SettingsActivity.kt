package studio.sanguine.codingtest

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import java.util.*

class SettingsActivity : AppCompatActivity() {

    var currentLocaleStr = ""
    var targetLocaleStr = ""

    lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        preferences = getSharedPreferences("prefs", MODE_PRIVATE)
        currentLocaleStr = preferences.getString("app_language", "en")!!

        updateUiText()

        val setRadioGroup = findViewById<RadioGroup>(R.id.set_redioGroup)

        setRadioGroup.check(
            if(currentLocaleStr == "en") R.id.set_englishRadioButton
            else R.id.set_spanishRadioButton
        )

        setRadioGroup.setOnCheckedChangeListener { radioGroup, i ->
            when(i){
                R.id.set_englishRadioButton -> targetLocaleStr = "en"
                R.id.set_spanishRadioButton -> targetLocaleStr = "es"
            }
            applyConfiguration()
        }
    }

    fun applyConfiguration(){
        with(preferences.edit()){
            putString("app_language", targetLocaleStr)
            commit()
        }
        val config = baseContext.resources.configuration
        config.setLocale(Locale(targetLocaleStr!!))
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
        updateUiText()
    }

    fun updateUiText(){
        supportActionBar?.title = getString(R.string.settings)
        findViewById<RadioButton>(R.id.set_englishRadioButton).setText(getString(R.string.english))
        findViewById<RadioButton>(R.id.set_spanishRadioButton).setText(getString(R.string.spanish))
        findViewById<TextView>(R.id.set_appLanguageLabel).setText(getString(R.string.app_language))
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        applyConfiguration()
        setResult(RESULT_OK)
        super.onBackPressed()
    }
}