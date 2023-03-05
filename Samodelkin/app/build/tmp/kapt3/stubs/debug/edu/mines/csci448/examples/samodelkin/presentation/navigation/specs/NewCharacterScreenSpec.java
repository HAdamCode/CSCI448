package edu.mines.csci448.examples.samodelkin.presentation.navigation.specs;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J(\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0017J*\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0017J%\u0010\u001c\u001a\u00020\u00042\u0016\u0010\u001d\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00040\u001e\"\u0004\u0018\u00010\u0004H\u0016\u00a2\u0006\u0002\u0010\u001fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u0004X\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000eX\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006 "}, d2 = {"Ledu/mines/csci448/examples/samodelkin/presentation/navigation/specs/NewCharacterScreenSpec;", "Ledu/mines/csci448/examples/samodelkin/presentation/navigation/specs/IScreenSpec;", "()V", "LOG_TAG", "", "arguments", "", "Landroidx/navigation/NamedNavArgument;", "getArguments", "()Ljava/util/List;", "route", "getRoute", "()Ljava/lang/String;", "title", "", "getTitle", "()I", "Content", "", "samodelkinViewModel", "Ledu/mines/csci448/examples/samodelkin/presentation/viewmodel/ISamodelkinViewModel;", "navController", "Landroidx/navigation/NavHostController;", "navBackStackEntry", "Landroidx/navigation/NavBackStackEntry;", "context", "Landroid/content/Context;", "TopAppBarActions", "buildRoute", "args", "", "([Ljava/lang/String;)Ljava/lang/String;", "app_debug"})
public final class NewCharacterScreenSpec implements edu.mines.csci448.examples.samodelkin.presentation.navigation.specs.IScreenSpec {
    @org.jetbrains.annotations.NotNull()
    public static final edu.mines.csci448.examples.samodelkin.presentation.navigation.specs.NewCharacterScreenSpec INSTANCE = null;
    private static final java.lang.String LOG_TAG = "448.NewCharacterScreenSpec";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String route = "newCharacter";
    @org.jetbrains.annotations.NotNull()
    private static final java.util.List<androidx.navigation.NamedNavArgument> arguments = null;
    private static final int title = edu.mines.csci448.examples.samodelkin.R.string.app_name;
    
    private NewCharacterScreenSpec() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String getRoute() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.util.List<androidx.navigation.NamedNavArgument> getArguments() {
        return null;
    }
    
    @java.lang.Override()
    public int getTitle() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String buildRoute(@org.jetbrains.annotations.NotNull()
    java.lang.String... args) {
        return null;
    }
    
    @androidx.compose.runtime.Composable()
    @java.lang.Override()
    public void Content(@org.jetbrains.annotations.NotNull()
    edu.mines.csci448.examples.samodelkin.presentation.viewmodel.ISamodelkinViewModel samodelkinViewModel, @org.jetbrains.annotations.NotNull()
    androidx.navigation.NavHostController navController, @org.jetbrains.annotations.NotNull()
    androidx.navigation.NavBackStackEntry navBackStackEntry, @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    @androidx.compose.runtime.Composable()
    @java.lang.Override()
    public void TopAppBarActions(@org.jetbrains.annotations.NotNull()
    edu.mines.csci448.examples.samodelkin.presentation.viewmodel.ISamodelkinViewModel samodelkinViewModel, @org.jetbrains.annotations.NotNull()
    androidx.navigation.NavHostController navController, @org.jetbrains.annotations.Nullable()
    androidx.navigation.NavBackStackEntry navBackStackEntry, @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
}