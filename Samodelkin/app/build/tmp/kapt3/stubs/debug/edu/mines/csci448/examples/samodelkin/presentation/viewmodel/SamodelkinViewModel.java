package edu.mines.csci448.examples.samodelkin.presentation.viewmodel;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0007\u0018\u0000 \u001a2\u00020\u00012\u00020\u0002:\u0001\u001aB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\tH\u0016J\u0010\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\tH\u0016J\u0010\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u0011H\u0016R \u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u00078VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u00078VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000bR\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Ledu/mines/csci448/examples/samodelkin/presentation/viewmodel/SamodelkinViewModel;", "Landroidx/lifecycle/ViewModel;", "Ledu/mines/csci448/examples/samodelkin/presentation/viewmodel/ISamodelkinViewModel;", "samodelkinRepo", "Ledu/mines/csci448/examples/samodelkin/data/SamodelkinRepo;", "(Ledu/mines/csci448/examples/samodelkin/data/SamodelkinRepo;)V", "characterListState", "Lkotlinx/coroutines/flow/StateFlow;", "", "Ledu/mines/csci448/examples/samodelkin/data/SamodelkinCharacter;", "getCharacterListState", "()Lkotlinx/coroutines/flow/StateFlow;", "currentCharacterState", "getCurrentCharacterState", "mCharacters", "Lkotlinx/coroutines/flow/MutableStateFlow;", "mCurrentCharacterIdState", "Ljava/util/UUID;", "mCurrentCharacterState", "addCharacter", "", "characterToAdd", "deleteCharacter", "characterToDelete", "loadCharacterByUUID", "uuid", "Companion", "app_debug"})
public final class SamodelkinViewModel extends androidx.lifecycle.ViewModel implements edu.mines.csci448.examples.samodelkin.presentation.viewmodel.ISamodelkinViewModel {
    private final edu.mines.csci448.examples.samodelkin.data.SamodelkinRepo samodelkinRepo = null;
    @org.jetbrains.annotations.NotNull
    public static final edu.mines.csci448.examples.samodelkin.presentation.viewmodel.SamodelkinViewModel.Companion Companion = null;
    private static final java.lang.String LOG_TAG = "448.SamodelkinViewModel";
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<edu.mines.csci448.examples.samodelkin.data.SamodelkinCharacter>> mCharacters = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<edu.mines.csci448.examples.samodelkin.data.SamodelkinCharacter> mCurrentCharacterState = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.UUID> mCurrentCharacterIdState = null;
    
    public SamodelkinViewModel(@org.jetbrains.annotations.NotNull
    edu.mines.csci448.examples.samodelkin.data.SamodelkinRepo samodelkinRepo) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.StateFlow<java.util.List<edu.mines.csci448.examples.samodelkin.data.SamodelkinCharacter>> getCharacterListState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.StateFlow<edu.mines.csci448.examples.samodelkin.data.SamodelkinCharacter> getCurrentCharacterState() {
        return null;
    }
    
    /**
     * Loads a character by id into currentCharacterState, if it exists.  If id is not found
     * in list of characters, then sets currentCharacterState to null.
     * @param uuid id to use for character lookup
     */
    @java.lang.Override
    public void loadCharacterByUUID(@org.jetbrains.annotations.NotNull
    java.util.UUID uuid) {
    }
    
    /**
     * Adds the given character to the list of characters.
     * @param characterToAdd character to add to the list
     */
    @java.lang.Override
    public void addCharacter(@org.jetbrains.annotations.NotNull
    edu.mines.csci448.examples.samodelkin.data.SamodelkinCharacter characterToAdd) {
    }
    
    /**
     * Deletes corresponding character from the list of characters, if it exists in the list.
     * Matches characters by id.  If character is not found in the list, does nothing.
     * @param characterToDelete character to delete from list
     */
    @java.lang.Override
    public void deleteCharacter(@org.jetbrains.annotations.NotNull
    edu.mines.csci448.examples.samodelkin.data.SamodelkinCharacter characterToDelete) {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Ledu/mines/csci448/examples/samodelkin/presentation/viewmodel/SamodelkinViewModel$Companion;", "", "()V", "LOG_TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}