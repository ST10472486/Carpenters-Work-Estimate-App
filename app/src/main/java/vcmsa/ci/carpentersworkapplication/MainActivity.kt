package vcmsa.ci.carpentersworkapplication

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    lateinit var tvHours: EditText
    lateinit var rdbtnDoor: RadioButton
    lateinit var rdbtnFurniture: RadioButton
    lateinit var rdbtnFlooring: RadioButton
    lateinit var chkNails: CheckBox
    lateinit var chkHinges: CheckBox
    lateinit var chkPlanks: CheckBox
    lateinit var btnTotal: Button
    lateinit var tvResult: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvHours = findViewById(R.id.tvHours)
        rdbtnDoor = findViewById(R.id.rdbtnDoor)
        rdbtnFurniture = findViewById(R.id.rdbtnFurniture)
        rdbtnFlooring = findViewById(R.id.rdbtnFlooring)
        chkNails = findViewById(R.id.chkNails)
        chkHinges = findViewById(R.id.chkHinges)
        chkPlanks = findViewById(R.id.chkPlanks)
        btnTotal = findViewById(R.id.btnTotal)
        tvResult = findViewById(R.id.tvResult)

        btnTotal.setOnClickListener { CalcTotal() }



    }

    private fun CalcTotal() {
        val inputHours = tvHours.text.toString()
        if(inputHours.isEmpty()) {
            tvResult.text = "Please enter number of hours"
            return
        }

        val hours = inputHours.toIntOrNull()
        if(hours == null || hours <= 0) {
            tvResult.text = "Invalid number of hours"
            return
        }

            var materialCost = 0

           if(!rdbtnDoor.isChecked && !rdbtnFlooring.isChecked && !rdbtnFurniture.isChecked){
        tvResult.text = "Please select a Task"
               return
           }

            val materialLists = listOf(Pair(chkNails, 20),Pair(chkPlanks, 250),Pair(chkHinges, 45)
            )

            for ((checkbox, cost) in materialLists) {
                if (checkbox.isChecked) {
                    materialCost += cost

                }

            }


            val totalCost = (materialCost * hours)
            tvResult.text = "Total Cost of Task: R $totalCost"
        }


    }
