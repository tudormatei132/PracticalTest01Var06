package ro.pub.cs.systems.eim.practicaltest01var06

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
class PracticalTest01Var06SecondaryActivity : Activity() {

    private var points : Int = 0
    private lateinit var text : TextView
    private lateinit var button : Button

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical_test01_var06_secondary)


        text = findViewById<TextView>(R.id.textView)
        button = findViewById<Button>(R.id.button)



        val intent = intent
        if (intent != null) {
            var count = intent.getIntExtra("count", -1)
            var field1 = intent.getStringExtra("field1")
            var field2 = intent.getStringExtra("field2")
            var field3 = intent.getStringExtra("field3")

            points = 0
            if ((field1.equals(field2) || field1.equals("*") || field2.equals("*")) && (field2.equals(field3) || field2.equals("*") || field3.equals("*")) && (field1.equals(field3) || field1.equals("*") || field3.equals("*"))) {
                points = 100
                if (count == 1) {
                    points /= 2
                } else if (count == 2) {
                    points /= 10
                } else if (count == 3) {
                    points = 0
                }
                text.setText("Gained $points")
            }

        }

        button.setOnClickListener {
            setResult(RESULT_OK, Intent().apply{
                putExtra("points", points)
            })
            finish()
        }

    }

}