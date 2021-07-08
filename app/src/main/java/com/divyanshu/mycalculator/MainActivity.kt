package com.divyanshu.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var lastDecimal = false
    var lastNumeric = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun onDigit(view: View){
       tvinput.append((view as Button).text)
        lastNumeric = true
    }

    fun onClr(view: View){
        tvinput.text = ""
        lastNumeric = false
        lastDecimal = false
    }

    fun lastDecimalPoint(view: View){
        if(lastNumeric && !lastDecimal){
            tvinput.append(".")
            lastNumeric = false
            lastDecimal = true
        }
    }
    fun onOperator(view : View){
        if(lastNumeric && !isOperatorAdded(tvinput.text.toString())){
            tvinput.append((view as Button).text)
            lastNumeric = false
            lastDecimal = false
        }
    }
    private fun isOperatorAdded(value : String) : Boolean {
       if(value.startsWith("-")){
        return false
        }
    else{
        if(value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-"))
            return true
       }
        return false
    }
    fun Onequal(view : View){
        if(lastNumeric){
            var tvValue = (tvinput.text).toString()
            var prefix = ""

            try{
                if(tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                if(tvValue.contains("-")) {
                    var splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }
                    tvinput.text = removeLastZero((one.toDouble() - two.toDouble()).toString())
                }

                else if(tvValue.contains("+")){
                    var splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    tvinput.text = removeLastZero((one.toDouble() + two.toDouble()).toString())
                }
                else if(tvValue.contains("*")){
                    var splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    tvinput.text = removeLastZero((one.toDouble() * two.toDouble()).toString())
                }
                else if(tvValue.contains("/")){
                    var splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    tvinput.text = removeLastZero((one.toDouble() / two.toDouble()).toString())
                }
            }
            catch(e:ArithmeticException){
                    e.printStackTrace()
            }
        }
    }
    private fun removeLastZero(value : String) : String{
        if(value.contains(".0")){
         return value.substring(0,value.length-2)
        }
       return value 
    }
}