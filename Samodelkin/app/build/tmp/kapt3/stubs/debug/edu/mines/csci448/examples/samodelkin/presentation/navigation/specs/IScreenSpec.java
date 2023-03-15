package edu.mines.csci448.examples.samodelkin.presentation.navigation.specs;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u0000 !2\u00020\u0001:\u0001!J0\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\'J*\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\'J*\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0003J%\u0010\u001d\u001a\u00020\b2\u0016\u0010\u001e\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\b0\u001f\"\u0004\u0018\u00010\bH&\u00a2\u0006\u0002\u0010 R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\bX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0012\u0010\u000b\u001a\u00020\fX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000e\u0082\u0001\u0003\"#$\u00a8\u0006%"}, d2 = {"Ledu/mines/csci448/examples/samodelkin/presentation/navigation/specs/IScreenSpec;", "", "arguments", "", "Landroidx/navigation/NamedNavArgument;", "getArguments", "()Ljava/util/List;", "route", "", "getRoute", "()Ljava/lang/String;", "title", "", "getTitle", "()I", "Content", "", "samodelkinViewModel", "Ledu/mines/csci448/examples/samodelkin/presentation/viewmodel/ISamodelkinViewModel;", "navController", "Landroidx/navigation/NavHostController;", "navBackStackEntry", "Landroidx/navigation/NavBackStackEntry;", "context", "Landroid/content/Context;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "TopAppBarActions", "TopAppBarContent", "buildRoute", "args", "", "([Ljava/lang/String;)Ljava/lang/String;", "Companion", "Ledu/mines/csci448/examples/samodelkin/presentation/navigation/specs/DetailScreenSpec;", "Ledu/mines/csci448/examples/samodelkin/presentation/navigation/specs/ListScreenSpec;", "Ledu/mines/csci448/examples/samodelkin/presentation/navigation/specs/NewCharacterScreenSpec;", "app_debug"})
public abstract interface IScreenSpec {
    @org.jetbrains.annotations.NotNull()
    public static final edu.mines.csci448.examples.samodelkin.presentation.navigation.specs.IScreenSpec.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String root = "samodelkin";
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.lang.String getRoute();
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.List<androidx.navigation.NamedNavArgument> getArguments();
    
    public abstract int getTitle();
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.lang.String buildRoute(@org.jetbrains.annotations.NotNull()
    java.lang.String... args);
    
    @androidx.compose.runtime.Composable()
    public abstract void Content(@org.jetbrains.annotations.NotNull()
    edu.mines.csci448.examples.samodelkin.presentation.viewmodel.ISamodelkinViewModel samodelkinViewModel, @org.jetbrains.annotations.NotNull()
    androidx.navigation.NavHostController navController, @org.jetbrains.annotations.NotNull()
    androidx.navigation.NavBackStackEntry navBackStackEntry, @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineScope coroutineScope);
    
    @androidx.compose.runtime.Composable()
    public abstract void TopAppBarActions(@org.jetbrains.annotations.NotNull()
    edu.mines.csci448.examples.samodelkin.presentation.viewmodel.ISamodelkinViewModel samodelkinViewModel, @org.jetbrains.annotations.NotNull()
    androidx.navigation.NavHostController navController, @org.jetbrains.annotations.Nullable()
    androidx.navigation.NavBackStackEntry navBackStackEntry, @org.jetbrains.annotations.NotNull()
    android.content.Context context);
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 3)
    public final class DefaultImpls {
        
        @androidx.compose.runtime.Composable()
        @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
        private static void TopAppBarContent(edu.mines.csci448.examples.samodelkin.presentation.navigation.specs.IScreenSpec $this, edu.mines.csci448.examples.samodelkin.presentation.viewmodel.ISamodelkinViewModel samodelkinViewModel, androidx.navigation.NavHostController navController, androidx.navigation.NavBackStackEntry navBackStackEntry, android.content.Context context) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J*\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R!\u0010\u0005\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0018"}, d2 = {"Ledu/mines/csci448/examples/samodelkin/presentation/navigation/specs/IScreenSpec$Companion;", "", "()V", "LOG_TAG", "", "allScreens", "", "Ledu/mines/csci448/examples/samodelkin/presentation/navigation/specs/IScreenSpec;", "getAllScreens", "()Ljava/util/Map;", "root", "startDestination", "getStartDestination", "()Ljava/lang/String;", "TopBar", "", "samodelkinViewModel", "Ledu/mines/csci448/examples/samodelkin/presentation/viewmodel/ISamodelkinViewModel;", "navController", "Landroidx/navigation/NavHostController;", "navBackStackEntry", "Landroidx/navigation/NavBackStackEntry;", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        private static final java.lang.String LOG_TAG = "448.IScreenSpec";
        @org.jetbrains.annotations.NotNull()
        private static final java.util.Map<java.lang.String, edu.mines.csci448.examples.samodelkin.presentation.navigation.specs.IScreenSpec> allScreens = null;
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String root = "samodelkin";
        @org.jetbrains.annotations.NotNull()
        private static final java.lang.String startDestination = null;
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.Map<java.lang.String, edu.mines.csci448.examples.samodelkin.presentation.navigation.specs.IScreenSpec> getAllScreens() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getStartDestination() {
            return null;
        }
        
        @androidx.compose.runtime.Composable()
        public final void TopBar(@org.jetbrains.annotations.NotNull()
        edu.mines.csci448.examples.samodelkin.presentation.viewmodel.ISamodelkinViewModel samodelkinViewModel, @org.jetbrains.annotations.NotNull()
        androidx.navigation.NavHostController navController, @org.jetbrains.annotations.Nullable()
        androidx.navigation.NavBackStackEntry navBackStackEntry, @org.jetbrains.annotations.NotNull()
        android.content.Context context) {
        }
    }
}