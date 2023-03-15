package edu.mines.csci448.examples.samodelkin.util.api;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\'\u00a8\u0006\u0006"}, d2 = {"Ledu/mines/csci448/examples/samodelkin/util/api/SamodelkinApiService;", "", "getCharacter", "Lretrofit2/Call;", "Ledu/mines/csci448/examples/samodelkin/data/SamodelkinCharacter;", "Companion", "app_debug"})
public abstract interface SamodelkinApiService {
    @org.jetbrains.annotations.NotNull()
    public static final edu.mines.csci448.examples.samodelkin.util.api.SamodelkinApiService.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BASE_API_URL = "https://cs-courses.mines.edu";
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "csci448/samodelkin/")
    public abstract retrofit2.Call<edu.mines.csci448.examples.samodelkin.data.SamodelkinCharacter> getCharacter();
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Ledu/mines/csci448/examples/samodelkin/util/api/SamodelkinApiService$Companion;", "", "()V", "BASE_API_URL", "", "app_debug"})
    public static final class Companion {
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String BASE_API_URL = "https://cs-courses.mines.edu";
        
        private Companion() {
            super();
        }
    }
}