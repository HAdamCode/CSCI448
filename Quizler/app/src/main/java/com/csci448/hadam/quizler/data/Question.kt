package com.csci448.hadam.quizler.data

import androidx.annotation.StringRes
import androidx.compose.ui.res.stringResource
import com.csci448.hadam.quizler.R

data class Question(@StringRes val questionTextId: Int, val correctChoiceNumber: Int, val choice1Id: String = stringResource(
    id = R.string.label_true
), val choice2Id: String)
