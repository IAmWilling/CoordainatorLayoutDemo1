package com.zhy.coordainatorlayoutdemo1

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import androidx.core.view.ViewCompat
import kotlin.math.abs

class DependedView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    private var mLastX = 0f
    private var mLastY = 0f
    private var mlSlop = 0

    init {
        mlSlop = ViewConfiguration.get(context).scaledTouchSlop
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let {
            when (it.action) {
                MotionEvent.ACTION_DOWN -> {
                    mLastX = it.x
                    mLastY = it.y
                }
                MotionEvent.ACTION_MOVE -> {
                    var destX = it.x - mLastX
                    var destY = it.y - mLastY
//                    if (abs(destX) > mlSlop || abs(destY) > mlSlop) {
                        ViewCompat.offsetTopAndBottom(this, it.y.toInt())
                        ViewCompat.offsetLeftAndRight(this, it.x.toInt())
//                        x = it.x
//                        y = it.y
//                    }
                    mLastX = it.x
                    mLastY = it.y
                }
            }
        }
        return true
    }

}