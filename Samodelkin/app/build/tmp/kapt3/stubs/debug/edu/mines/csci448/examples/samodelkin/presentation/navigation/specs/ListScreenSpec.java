package edu.mines.csci448.examples.samodelkin.presentation.navigation.specs;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J0\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0017J*\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0017J%\u0010\u001d\u001a\u00020\t2\u0016\u0010\u001e\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\t0\u001f\"\u0004\u0018\u00010\tH\u0016\u00a2\u0006\u0002\u0010 R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\rX\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006!"}, d2 = {"Ledu/mines/csci448/examples/samodelkin/presentation/navigation/specs/ListScreenSpec;", "Ledu/mines/csci448/examples/samodelkin/presentation/navigation/specs/IScreenSpec;", "()V", "arguments", "", "Landroidx/navigation/NamedNavArgument;", "getArguments", "()Ljava/util/List;", "route", "", "getRoute", "()Ljava/lang/String;", "title", "", "getTitle", "()I", "Content", "", "samodelkinViewModel", "Ledu/mines/csci448/examples/samodelkin/presentation/viewmodel/ISamodelkinViewModel;", "navController", "Landroidx/navigation/NavHostController;", "navBackStackEntry", "Landroidx/navigation/NavBackStackEntry;", "context", "Landroid/content/Context;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "TopAppBarActions", "buildRoute", "args", "", "([Ljava/lang/String;)Ljava/lang/String;", "app_debug"})
public final class ListScreenSpec implements edu.mines.csci448.examples.samodelkin.presentation.navigation.specs.IScreenSpec {
    @org.jetbrains.annotations.NotNull
    public static final edu.mines.csci448.examples.samodelkin.presentation.navigation.specs.ListScreenSpec INSTANCE = null;
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String route = "list";
    @org.jetbrains.annotations.NotNull
    private static final java.util.List<androidx.navigation.NamedNavArgument> arguments = null;
    private static final int title = edu.mines.csci448.examples.samodelkin.R.string.app_name;
    
    private ListScreenSpec() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.lang.String getRoute() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.util.List<androidx.navigation.NamedNavArgument> getArguments() {
        return null;
    }
    
    @java.lang.Override
    public int getTitle() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.lang.String buildRoute(@org.jetbrains.annotations.NotNull
    java.lang.String... args) {
        return null;
    }
    
    @androidx.compose.runtime.Composable
    @java.lang.Override
    public void Content(@org.jetbrains.annotations.NotNull
    edu.mines.csci448.examples.samodelkin.presentation.viewmodel.ISamodelkinViewModel samodelkinViewModel, @org.jetbrains.annotations.NotNull
    androidx.navigation.NavHostController navController, @org.jetbrains.annotations.NotNull
    androidx.navigation.NavBackStackEntry navBackStackEntry, @org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    kotlinx.coroutines.CoroutineScope coroutineScope) {
    }
    
    @androidx.compose.runtime.Composable
    @java.lang.Override
    public void TopAppBarActions(@org.jetbrains.annotations.NotNull
    edu.mines.csci448.examples.samodelkin.presentation.viewmodel.ISamodelkinViewModel samodelkinViewModel, @org.jetbrains.annotations.NotNull
    androidx.navigation.NavHostController navController, @org.jetbrains.annotations.Nullable
    androidx.navigation.NavBackStackEntry navBackStackEntry, @org.jetbrains.annotations.NotNull
    android.content.Context context) {
    }
}