package edu.mines.csci448.examples.samodelkin.presentation.viewmodel;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0005H&J\u0010\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u0005H&J\u0010\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H&R\u001e\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\u0007\u00a8\u0006\u0012"}, d2 = {"Ledu/mines/csci448/examples/samodelkin/presentation/viewmodel/ISamodelkinViewModel;", "", "characterListState", "Lkotlinx/coroutines/flow/StateFlow;", "", "Ledu/mines/csci448/examples/samodelkin/data/SamodelkinCharacter;", "getCharacterListState", "()Lkotlinx/coroutines/flow/StateFlow;", "currentCharacterState", "getCurrentCharacterState", "addCharacter", "", "characterToAdd", "deleteCharacter", "characterToDelete", "loadCharacterByUUID", "uuid", "Ljava/util/UUID;", "app_debug"})
public abstract interface ISamodelkinViewModel {
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.StateFlow<java.util.List<edu.mines.csci448.examples.samodelkin.data.SamodelkinCharacter>> getCharacterListState();
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.StateFlow<edu.mines.csci448.examples.samodelkin.data.SamodelkinCharacter> getCurrentCharacterState();
    
    public abstract void loadCharacterByUUID(@org.jetbrains.annotations.NotNull()
    java.util.UUID uuid);
    
    public abstract void addCharacter(@org.jetbrains.annotations.NotNull()
    edu.mines.csci448.examples.samodelkin.data.SamodelkinCharacter characterToAdd);
    
    public abstract void deleteCharacter(@org.jetbrains.annotations.NotNull()
    edu.mines.csci448.examples.samodelkin.data.SamodelkinCharacter characterToDelete);
}