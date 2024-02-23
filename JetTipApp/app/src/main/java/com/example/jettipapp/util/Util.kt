package com.example.jettipapp.util

fun calculateTotalAmount(totalBillState: Double, tipAmountState: Double, splitCounter: Int): Double {

    return (totalBillState+tipAmountState)/splitCounter

}

fun calculateTipAmount(totalBillState: Double, sliderPosition: Int): Double {
    return if(totalBillState > 1 && totalBillState.toString().isNotEmpty()){
        (totalBillState*sliderPosition)/100
    }
    else{
        0.0
    }

}