package com.csci448.hadam.quizler.data

import androidx.annotation.StringRes

data class Question(@StringRes val questionTextId: Int, val isTrue: Boolean) {
}
