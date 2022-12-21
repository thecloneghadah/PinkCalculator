package com.example.pinkcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var canAddOperator = false
    var canAddComa = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun clearAllAction(view: View) {
        topText.text = ""
        bottomText.text = ""
        canAddComa = true
        canAddOperator = false
    }

    fun backSpaceAction(view: View) {
        val length = topText.text.length
        if(length > 0){
            var newText = topText.text.subSequence(0, length -1 )
            if(topText.text.last() == '.' ){
                canAddComa = true
            }
            if(!(topText.text.last().isDigit()) && topText.text.last() != '.'){
                canAddOperator = true
            }
            topText.text = newText
        }
    }

    fun addNumberAction(view :View){
        val length = topText.text.length
        if(view is Button){
            if(view.text == "."){
                if(canAddComa){
                    if(length == 0 || length == null ){
                        topText.append("0.")
                    }else if(!(topText.text.last().isDigit())){
                        topText.append("0.")
                    }
                    else{
                        topText.append(view.text)
                    }
                    canAddComa = false
                    canAddOperator = true
                }
            }else{
                topText.append(view.text)
                canAddOperator = true
            }
        }
    }

    fun addOperatorAction(view : View){
        if(view is Button){
            if(canAddOperator){
                topText.append(view.text)
                canAddOperator = false
                canAddComa = true
            }
        }
    }

    fun equalAction(view :View){
        var list = turnToList()
        if(list.isEmpty()){
            bottomText.text=""
        }else{
            list = multiplyAndDevide(list)
            list = subtractAndSum(list)
            if(list.size==1){
                bottomText.text=list[0].toString()
            }else{
                bottomText.text="ERROR"
            }
        }
    }

    private fun multiplyAndDevide(passedList:MutableList<Any>):MutableList<Any>{
        var OgList = passedList
        var newList = mutableListOf<Any>()
        var i = 0
        var size=OgList.size
        while(i<size-1){
            when(OgList[i]){
                'X'->{
                    var prevNum = OgList[i-1].toString().toFloat()
                    var nextNum = OgList[i+1].toString().toFloat()
                    OgList[i+1] = prevNum*nextNum
                    i++
                }
                '/'->{
                    var prevNum = OgList[i-1].toString().toFloat()
                    var nextNum = OgList[i+1].toString().toFloat()
                    OgList[i+1] = prevNum/nextNum
                    i++
                }
                else ->{
                    if(OgList[i+1]!='X' && OgList[i+1]!='/'){
                        newList.add(OgList[i])
                    }
                    i++
                }
            }
        }
        newList.add(OgList[i])
        return newList
    }

    private fun subtractAndSum(passedList:MutableList<Any>):MutableList<Any>{
        var OgList = passedList
        var newList = mutableListOf<Any>()
        var i = 0
        var size=OgList.size
        while(i<size-1){
            when(OgList[i]){
                '-'->{
                    var prevNum = OgList[i-1].toString().toFloat()
                    var nextNum = OgList[i+1].toString().toFloat()
                    OgList[i+1] = prevNum-nextNum
                    i++
                }
                '+'->{
                    var prevNum = OgList[i-1].toString().toFloat()
                    var nextNum = OgList[i+1].toString().toFloat()
                    OgList[i+1] = prevNum+nextNum
                    i++
                }
                else ->{
                    if(OgList[i+1]!='-' && OgList[i+1]!='+'){
                        newList.add(OgList[i])
                    }
                    i++
                }
            }
        }
        newList.add(OgList[i])
        return newList
    }




    private fun turnToList(): MutableList<Any> {
        var list = mutableListOf<Any>()
        val expresion = topText.text.toString()
        var currentDigit = ""

        for(i in expresion){
            if(i.isDigit() || i=='.')
            {
                currentDigit+=i
            }else{
                list.add(currentDigit)
                currentDigit = ""
                list.add(i)
            }
        }
        if(currentDigit != ""){
            list.add(currentDigit)

        }

        return list

    }


}