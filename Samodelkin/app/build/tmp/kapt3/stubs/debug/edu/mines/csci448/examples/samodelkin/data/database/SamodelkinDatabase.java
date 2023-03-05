package edu.mines.csci448.examples.samodelkin.data.database;

import java.lang.System;

@androidx.room.TypeConverters(value = {edu.mines.csci448.examples.samodelkin.data.database.SamodelkinTypeConverters.class})
@androidx.room.Database(entities = {edu.mines.csci448.examples.samodelkin.data.SamodelkinCharacter.class}, version = 1)
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\'\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005\u00a2\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u00020\u0004X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\b"}, d2 = {"Ledu/mines/csci448/examples/samodelkin/data/database/SamodelkinDatabase;", "Landroidx/room/RoomDatabase;", "()V", "samodelkinDao", "Ledu/mines/csci448/examples/samodelkin/data/database/SamodelkinDao;", "getSamodelkinDao", "()Ledu/mines/csci448/examples/samodelkin/data/database/SamodelkinDao;", "Companion", "app_debug"})
public abstract class SamodelkinDatabase extends androidx.room.RoomDatabase {
    @org.jetbrains.annotations.NotNull()
    public static final edu.mines.csci448.examples.samodelkin.data.database.SamodelkinDatabase.Companion Companion = null;
    @kotlin.jvm.Volatile()
    private static volatile edu.mines.csci448.examples.samodelkin.data.database.SamodelkinDatabase INSTANCE;
    
    public SamodelkinDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract edu.mines.csci448.examples.samodelkin.data.database.SamodelkinDao getSamodelkinDao();
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Ledu/mines/csci448/examples/samodelkin/data/database/SamodelkinDatabase$Companion;", "", "()V", "INSTANCE", "Ledu/mines/csci448/examples/samodelkin/data/database/SamodelkinDatabase;", "getInstance", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final edu.mines.csci448.examples.samodelkin.data.database.SamodelkinDatabase getInstance(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}