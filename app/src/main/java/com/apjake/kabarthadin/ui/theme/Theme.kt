package com.apjake.kabarthadin.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = PrimaryDarkColor,
    primaryVariant = PrimaryDarkColor,
    secondary = SecondaryDarkColor,
    background = BackgroundDarkColor,
    surface = SurfaceDarkColor,
    onPrimary = OnPrimaryDarkColor,
    onSurface = OnSurfaceDarkColor
)


private val LightColorPalette = lightColors(
    primary = PrimaryColor,
    primaryVariant = PrimaryColor,
    secondary = SecondaryColor,
    background = BackgroundColor,
    surface = SurfaceColor,
    onPrimary = OnPrimaryColor,
    onSurface = OnSurfaceColor
)

@Composable
fun KaBarThaDinTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}