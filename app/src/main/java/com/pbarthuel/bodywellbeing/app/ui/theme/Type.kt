package com.pbarthuel.bodywellbeing.app.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.pbarthuel.bodywellbeing.R

private val GothamBold = FontFamily(Font(R.font.gotham_bold))
private val GothamBook = FontFamily(Font(R.font.gotham_book))
private val GothamMedium = FontFamily(Font(R.font.gotham_medium))
private val GothamRoundedBook = FontFamily(Font(R.font.gotham_rounded_book))
private val GothamRoundedMedium = FontFamily(Font(R.font.gotham_rounded_medium))
private val Glyph = FontFamily(Font(R.font.hm_icon))

private val body1 = TextStyle(
    fontFamily = GothamBook,
    fontSize = 16.sp,
    lineHeight = 24.sp
)

private val body2 = TextStyle(
    fontFamily = GothamBook,
    fontSize = 14.sp,
    lineHeight = 20.sp
)

private val data2 = TextStyle(
    fontFamily = GothamRoundedBook,
    fontSize = 48.sp,
    lineHeight = 60.sp
)

private val data3 = TextStyle(
    fontFamily = GothamRoundedBook,
    fontSize = 36.sp,
    lineHeight = 48.sp
)

private val data4 = TextStyle(
    fontFamily = GothamRoundedBook,
    fontSize = 28.sp,
    lineHeight = 36.sp
)

private val data5 = TextStyle(
    fontFamily = GothamRoundedBook,
    fontSize = 24.sp,
    lineHeight = 28.sp
)

private val data6 = TextStyle(
    fontFamily = GothamRoundedMedium,
    fontSize = 18.sp,
    lineHeight = 32.sp
)

private val data7 = TextStyle(
    fontFamily = GothamMedium,
    fontSize = 16.sp,
    lineHeight = 24.sp
)

private val header1 = TextStyle(
    fontFamily = GothamMedium,
    fontSize = 32.sp,
    lineHeight = 36.sp
)

private val header2 = TextStyle(
    fontFamily = GothamMedium,
    fontSize = 22.sp,
    lineHeight = 26.sp
)

private val header3 = TextStyle(
    fontFamily = GothamMedium,
    fontSize = 18.sp,
    lineHeight = 22.sp
)

private val header4 = TextStyle(
    fontFamily = GothamMedium,
    fontSize = 16.sp,
    lineHeight = 20.sp
)

private val header_eyebrow = TextStyle(
    fontFamily = GothamBold,
    fontSize = 12.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.5.sp
)

private val button1 = TextStyle(
    fontFamily = GothamMedium,
    fontSize = 16.sp,
    lineHeight = 20.sp
)

private val button2 = TextStyle(
    fontFamily = GothamMedium,
    fontSize = 12.sp,
    lineHeight = 16.sp
)

private val detail1 = TextStyle(
    fontFamily = GothamBook,
    fontSize = 14.sp,
    lineHeight = 20.sp
)

private val detail2 = TextStyle(
    fontFamily = GothamBook,
    fontSize = 12.sp,
    lineHeight = 16.sp
)

private val glyph = TextStyle(
    fontFamily = Glyph,
)

fun typeList() = BodyWellBeingTypeList(
    glyph = glyph,
    header1 = header1,
    header2 = header2,
    header3 = header3,
    header4 = header4,
    header_eyebrow = header_eyebrow,
    body1 = body1,
    body2 = body2,
    detail1 = detail1,
    detail2 = detail2,
    button1 = button1,
    button2 = button2,
    data2 = data2,
    data3 = data3,
    data4 = data4,
    data5 = data5,
    data6 = data6,
    data7 = data7,
)
