package coml.anwesh.uiprojects.pausetoplaystepview

/**
 * Created by anweshmishra on 17/12/18.
 */

import android.view.View
import android.view.MotionEvent
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.Color
import android.app.Activity
import android.content.Context

val nodes : Int = 5
val color : Int = Color.parseColor("#283593")
val scGap : Float = 0.05f
val scDiv : Double = 0.51
val strokeFactor : Int = 90
val sizeFactor : Float = 2.6f
val lines : Int = 2

fun Int.getInverse() : Float = 1f / this

fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.getInverse(), Math.max(0f, this - i * n.getInverse())) * n

fun Float.scaleFactor() : Float = Math.floor(this / scDiv).toFloat()

fun Float.mirrorValue(a : Int, b : Int) : Float = (1 - scaleFactor()) * a.getInverse() + scaleFactor() * b.getInverse()

fun Float.updateScale(dir : Float, a : Int, b : Int) : Float = mirrorValue(a, b) * dir * scGap

fun Canvas.drawPTPNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val gap : Float = w / (nodes + 1)
    val size : Float = gap / sizeFactor
    val xGap : Float = size * Math.sqrt(3.0).toFloat()
    val sc1 : Float = scale.divideScale(0, 2)
    val sc2 : Float = scale.divideScale(1, 2)
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    paint.strokeCap = Paint.Cap.ROUND
    paint.color = color
    save()
    translate(gap * (i + 1), h / 2)
    rotate(-90f * sc2)
    drawLine(-xGap/2, -size, -xGap/2, size, paint)
    for (j in 0..(lines - 1)) {
        val sf : Float = 1f - 2 * j
        val sc : Float = sc1.divideScale(j, lines)
        save()
        translate(xGap/2, 0f)
        rotate(30f * sc * sf)
        drawLine(0f, 0f, 0f, size * sf, paint)
        restore()
    }
    restore()
}