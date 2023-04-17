package edu.mines.csci448.examples.samodelkin.util.api;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\f\u001a\u00020\rR\u0019\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00048F\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Ledu/mines/csci448/examples/samodelkin/util/api/SamodelkinFetchr;", "", "()V", "characterState", "Lkotlinx/coroutines/flow/StateFlow;", "Ledu/mines/csci448/examples/samodelkin/data/SamodelkinCharacter;", "getCharacterState", "()Lkotlinx/coroutines/flow/StateFlow;", "mCharacterState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "samodelkinApiService", "Ledu/mines/csci448/examples/samodelkin/util/api/SamodelkinApiService;", "getCharacter", "", "Companion", "app_debug"})
public final class SamodelkinFetchr {
    @org.jetbrains.annotations.NotNull
    public static final edu.mines.csci448.examples.samodelkin.util.api.SamodelkinFetchr.Companion Companion = null;
    private static final java.lang.String LOG_TAG = "448.Fetchr";
    private final edu.mines.csci448.examples.samodelkin.util.api.SamodelkinApiService samodelkinApiService = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<edu.mines.csci448.examples.samodelkin.data.SamodelkinCharacter> mCharacterState = null;
    
    public SamodelkinFetchr() {
        super();
    }
    
    public final void getCharacter() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<edu.mines.csci448.examples.samodelkin.data.SamodelkinCharacter> getCharacterState() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Ledu/mines/csci448/examples/samodelkin/util/api/SamodelkinFetchr$Companion;", "", "()V", "LOG_TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}