package com.zhy.coordainatorlayoutdemo1

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.widget.NestedScrollView

class SimpleTitleBehavior(context: Context, attributeSet: AttributeSet) :
    CoordinatorLayout.Behavior<View>(context, attributeSet) {
    private var destY = 0f
    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        return dependency is NestedScrollView
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        println("yumi y = ${dependency.y}")
        if(destY == 0f){
            destY = dependency.y - child.height
        }
        var dy = dependency.y - child.height
        dy = if(dy < 0f) 0f else dy
        val y: Float = -(dy / destY) * child.height
        child.translationY = y
        child.animationMatrix
        return true
    }

}