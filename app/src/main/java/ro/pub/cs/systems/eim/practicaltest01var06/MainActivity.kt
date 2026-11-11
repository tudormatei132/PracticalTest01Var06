package ro.pub.cs.systems.eim.practicaltest01var06

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var textNo1 : EditText
    private lateinit var textNo2 : EditText
    private lateinit var textNo3 : EditText
    private lateinit var checkBox : CheckBox
    private lateinit var checkBox2 : CheckBox
    private lateinit var checkBox3 : CheckBox
    private lateinit var buttonPlay : Button
    private lateinit var buttonRandom : Button

    private val set : String = "123*"
    private var field1 : String = ""
    private var field2 : String = ""
    private var field3 : String = ""

    private var points : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practical_test01_var06_main)


        textNo1 = findViewById<EditText>(R.id.text_no1)
        textNo2 = findViewById<EditText>(R.id.text_no2)
        textNo3 = findViewById<EditText>(R.id.text_no3)

        checkBox = findViewById<CheckBox>(R.id.checkBox)
        checkBox2 = findViewById<CheckBox>(R.id.checkBox2)
        checkBox3 = findViewById<CheckBox>(R.id.checkBox3)

        buttonPlay = findViewById<Button>(R.id.buttonPlay)
        buttonRandom = findViewById<Button>(R.id.buttonRandom)

        buttonRandom.setOnClickListener {

            if (!checkBox.isChecked)
                textNo1.setText(field1)
            if (!checkBox2.isChecked)
                textNo2.setText(field2)
            if (!checkBox3.isChecked)
                textNo3.setText(field3)

            field1 = set.random().toString()
            field2 = set.random().toString()
            field3 = set.random().toString()

            Toast.makeText(this, "Random: $field1, $field2, $field3", Toast.LENGTH_SHORT).show()

        }

        buttonPlay.setOnClickListener {
            var count = 0
            if (checkBox.isChecked)
                count++
            if (checkBox2.isChecked)
                count++
            if (checkBox3.isChecked)
                count++


            val intent = Intent(this,
                PracticalTest01Var06SecondaryActivity::class.java).apply {
                putExtra("count", count)
                putExtra("field1", textNo1.text.toString())
                putExtra("field2", textNo2.text.toString())
                putExtra("field3", textNo3.text.toString())

            }

            startActivityForResult(intent, Constants.ACTIVITY_RQ)
        }


    }

    override fun onResume() {
        super.onResume()
        field1 = set.random().toString()
        field2 = set.random().toString()
        field3 = set.random().toString()
        Toast.makeText(this, "Random: $field1, $field2, $field3", Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.ACTIVITY_RQ) {
            if (resultCode == RESULT_OK) {
                points += data!!.getIntExtra("points", 0)
                Log.d("PUNCTE", points.toString())
            }
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("points", points)
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        points = savedInstanceState.getInt("points", 0)
        Log.d("PUNCTE_SALVATE", points.toString())
    }




}