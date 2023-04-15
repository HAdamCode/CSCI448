package com.csci448.hadam.hadam_a3.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.csci448.hadam.hadam_a3.data.AutoCompleteSuggestion
import com.csci448.hadam.hadam_a3.data.TitleVideo

interface IMDBDao {
    @Dao
    interface AutoCompleteSuggestionDao {
        @Query("SELECT * FROM autoCompleteSuggestions WHERE query=:query")
        suspend fun getAutoCompleteSuggestions(query: String): List<AutoCompleteSuggestion>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertAutoCompleteSuggestions(suggestions: List<AutoCompleteSuggestion>)
    }

    @Dao
    interface TitleVideoDao {
        @Query("SELECT * FROM titleVideos WHERE id=:id")
        suspend fun getTitleVideos(id: String): TitleVideo?

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertTitleVideos(video: TitleVideo)
    }
}