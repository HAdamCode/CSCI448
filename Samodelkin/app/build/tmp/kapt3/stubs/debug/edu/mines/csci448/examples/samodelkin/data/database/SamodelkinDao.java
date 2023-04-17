package edu.mines.csci448.examples.samodelkin.data.database;

import java.lang.System;

@androidx.room.Dao
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u001b\u0010\u0007\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\fH\'\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000e"}, d2 = {"Ledu/mines/csci448/examples/samodelkin/data/database/SamodelkinDao;", "", "addCharacter", "", "samodelkinCharacter", "Ledu/mines/csci448/examples/samodelkin/data/SamodelkinCharacter;", "deleteCrime", "getCharacterById", "id", "Ljava/util/UUID;", "(Ljava/util/UUID;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCharacters", "Lkotlinx/coroutines/flow/Flow;", "", "app_debug"})
public abstract interface SamodelkinDao {
    
    @androidx.room.Insert
    public abstract void addCharacter(@org.jetbrains.annotations.NotNull
    edu.mines.csci448.examples.samodelkin.data.SamodelkinCharacter samodelkinCharacter);
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM character")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<edu.mines.csci448.examples.samodelkin.data.SamodelkinCharacter>> getCharacters();
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "SELECT * FROM character WHERE id=(:id)")
    public abstract java.lang.Object getCharacterById(@org.jetbrains.annotations.NotNull
    java.util.UUID id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super edu.mines.csci448.examples.samodelkin.data.SamodelkinCharacter> continuation);
    
    @androidx.room.Delete
    public abstract void deleteCrime(@org.jetbrains.annotations.NotNull
    edu.mines.csci448.examples.samodelkin.data.SamodelkinCharacter samodelkinCharacter);
}