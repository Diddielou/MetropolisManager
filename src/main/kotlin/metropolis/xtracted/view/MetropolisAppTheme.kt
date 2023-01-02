package metropolis.xtracted.view

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.sp

val Roboto = FontFamily(
    Font(resource = "/fonts/roboto_regular.ttf", FontWeight.Normal),
    Font(resource = "/fonts/roboto_thin.ttf", FontWeight.Thin),
    Font(resource = "/fonts/roboto_bold.ttf", FontWeight.Bold),
    Font(resource = "/fonts/roboto_medium.ttf", FontWeight.Medium)
)
val GoogleSans = FontFamily(
    Font(resource = "/fonts/googlesans_regular.ttf", FontWeight.Normal),
    Font(resource = "/fonts/googlesans_bold.ttf", FontWeight.Bold),
    Font(resource = "/fonts/googlesans_medium.ttf", FontWeight.Medium)
)

val LightColorPalette = lightColors(
    primary          = Color(0xFFb44360),
    primaryVariant   = Color(0xFF283594),
    secondary        = Color(0xFF283594),
    secondaryVariant = Color(0xFF283594),
    error            = Color(0xFFB00020),
    surface          = Color(0xFFFAFAFA)
)

val DarkColorPalette = darkColors(
    primary          =  Color(0xFFad1457), // Pink800
    primaryVariant   =  Color(0xFF78002e), // Dark
    secondary        =  Color(0xFFffeb3b), // Yellow500
    secondaryVariant =  Color(0xFFffff72), // Light
)

val typography = Typography(
    defaultFontFamily = Roboto,
    h1 = TextStyle(
        fontFamily = GoogleSans,
        fontSize = 80.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 112.sp, // 117
        letterSpacing = (-1.5).sp
    ),
    h2 = TextStyle(
        fontFamily = GoogleSans,
        fontSize = 60.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 73.sp,
        letterSpacing = (-0.5).sp
    ),
    h3 = TextStyle(
        fontFamily = GoogleSans,
        fontSize = 48.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 59.sp
    ),
    h4 = TextStyle(
        fontFamily = GoogleSans,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 37.sp
    ),
    h5 = TextStyle(
        fontFamily = GoogleSans,
        fontSize = 24.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 29.sp
    ),
    h6 = TextStyle(
        fontFamily = GoogleSans,
        fontSize = 20.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.15.sp,
        lineHeight = 24.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = Roboto,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = Roboto,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 24.sp,
        letterSpacing = 0.1.sp
    ),
    body1 = TextStyle(
        fontFamily = Roboto,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 16.sp,
        letterSpacing = 0.1.sp
    ),
    body2 = TextStyle(
        fontFamily = Roboto,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    button = TextStyle(
        fontFamily = Roboto,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 16.sp,
        letterSpacing = 1.25.sp
    ),
    caption = TextStyle(
        fontFamily = Roboto,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),
    overline = TextStyle(
        fontFamily = Roboto,
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 16.sp,
        letterSpacing = 1.sp
    )
)
    /*
    h4 = TextStyle(fontFamily = GoogleSans,
    fontWeight = FontWeight.Bold),
    h5 = TextStyle(fontFamily    = GoogleSans,
        fontWeight    = FontWeight.SemiBold
    ),
    h6 = TextStyle(
        fontFamily    = Roboto,
        fontWeight    = FontWeight.Normal
    ),
    caption =  TextStyle(
        fontFamily    = Roboto,
        fontSize      = 10.sp,
        fontWeight    = FontWeight.Normal,
        lineHeight    = 10.sp,
        letterSpacing = 0.1.sp
    )
     */

@Composable
fun ExpensesAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit){
    val colors = if (darkTheme){
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        content = content
    )
}