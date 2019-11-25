package com.example.marvelcomics.utils

import android.content.Context
import android.widget.Toast

fun toast(context: Context, message: CharSequence) =
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()