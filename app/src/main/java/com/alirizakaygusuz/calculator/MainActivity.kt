package com.alirizakaygusuz.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.alirizakaygusuz.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var calculate: Calculate


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        calculate = Calculate()


    }

    fun clickBtnNumbers(view: View) {
        when (view) {
            binding.btn0 -> {
                calculate.input += "0"

            }
            binding.btn1 -> {
                calculate.input += "1"

            }
            binding.btn2 -> {
                calculate.input += "2"

            }
            binding.btn3 -> {
                calculate.input += "3"

            }
            binding.btn4 -> {
                calculate.input += "4"

            }
            binding.btn5 -> {
                calculate.input += "5"

            }
            binding.btn6 -> {
                calculate.input += "6"

            }
            binding.btn7 -> {
                calculate.input += "7"

            }
            binding.btn8 -> {
                calculate.input += "8"

            }
            binding.btn9 -> {
                calculate.input += "9"

            }
            binding.btnDot -> {
                if (calculate.isOperator) {
                    if (calculate.input[calculate.input.length - 1].toString() != calculate.operator && calculate.input.length >= 1) {
                        calculate.input += "."
                    }
                } else {
                    calculate.input += "."
                }
            }
        }

        binding.txtOperation.text = calculate.input

    }


    fun click_btnC(view: View) {
        binding.txtOperation.text = ""
        binding.txtResult.text = "0"
        calculate.input = ""
        calculate.isOperator = false
    }


    fun clickBtnDelete(view: View) {
        var tmp = ""

        Log.i("Operator:", calculate.input[calculate.input.length - 1].toString())
        if (calculate.input[calculate.input.length - 1] == calculate.operator[0]) {
            calculate.isOperator = false

        }

        for (i in 0..(calculate.input.length - 2)) {
            tmp += calculate.input[i]
        }

        calculate.input = tmp
        binding.txtOperation.text = calculate.input

    }

    fun clickBtnOperator(view: View) {
        if (calculate.isOperator) {
            if (calculate.input[calculate.input.length - 1] == calculate.operator[0]) {
                Toast.makeText(this, "Please Do Not Enter(% , + , - , / ,*)", Toast.LENGTH_SHORT)
                    .show()

            } else {
                clickBtnEquals(view)
                operatorFinder(view)

            }

        } else {
            calculate.number1 = calculate.input.toFloat()
            operatorFinder(view)
        }


    }

    fun operatorFinder(view: View) {
        Log.i("Selam", "As Reis")
        when (view) {
            binding.btnPlus -> {
                calculate.operator = "+"
                calculate.input += "+"
            }
            binding.btnMinus -> {
                calculate.operator = "-"
                calculate.input += "-"
            }
            binding.btnDivided -> {
                calculate.operator = "/"
                calculate.input += "/"
            }
            binding.btnMultiply -> {
                calculate.operator = "*"
                calculate.input += "*"
            }
            binding.btnMod -> {
                calculate.operator = "%"
                calculate.input += "%"

            }
        }

        calculate.necessaryLength = calculate.input.length

        Log.i("Length::", calculate.necessaryLength.toString())


        calculate.isOperator = true

        binding.txtOperation.text = calculate.input
    }


    fun clickBtnEquals(view: View) {
        if (calculate.isOperator) {

            if (calculate.input.substring(calculate.necessaryLength).isNullOrEmpty()) {
                Toast.makeText(this, "Please Enter Number!!!", Toast.LENGTH_SHORT).show()
            } else {
                calculate.number2 = calculate.input.substring(calculate.necessaryLength).toFloat()


                Log.i("Number2:", calculate.number2.toString())

                when (calculate.operator) {
                    "+" -> {
                        calculate.result = calculate.number1 + calculate.number2
                    }
                    "-" -> {
                        calculate.result = calculate.number1 - calculate.number2

                    }
                    "/" -> {
                        calculate.result = calculate.number1 / calculate.number2

                    }
                    "*" -> {
                        calculate.result = calculate.number1 * calculate.number2

                    }
                    "%" -> {
                        calculate.result = calculate.number1 % calculate.number2


                    }
                }
                binding.txtOperation.text = calculate.result.toString()
                binding.txtResult.text = calculate.result.toString()
                calculate.input = calculate.result.toString()
                calculate.number1 = calculate.result

                calculate.isOperator = false
            }
        }
    }


}