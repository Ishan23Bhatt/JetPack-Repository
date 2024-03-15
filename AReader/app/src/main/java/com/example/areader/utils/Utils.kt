package com.example.areader.utils


import com.google.firebase.Timestamp
import android.icu.text.DateFormat


fun formatDate(timestamp: Timestamp): String {
    return DateFormat.getDateInstance().format(timestamp.toDate()).toString().split(",")[0] // March 12
}