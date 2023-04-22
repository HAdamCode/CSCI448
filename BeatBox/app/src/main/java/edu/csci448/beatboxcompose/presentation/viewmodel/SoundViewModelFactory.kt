package edu.csci448.beatboxcompose.presentation.viewmodel

import android.content.res.AssetManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.csci448.beatboxcompose.util.BeatBox

class SoundViewModelFactory(private val assets: AssetManager) : ViewModelProvider.NewInstanceFactory() {
    fun getViewModelClassname() = SoundViewModel::class.java

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom( getViewModelClassname() )) {
            return modelClass
                .getConstructor( BeatBox::class.java )
                .newInstance( BeatBox(assets) )
        } else {
            throw IllegalArgumentException("Unknown ViewModel")
        }
    }
}