package com.zhy.coordainatorlayoutdemo1

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.marginBottom
import java.lang.RuntimeException

/**
 * recyclerview behavior
 */
class RecyclerBehavior(context: Context, attributeSet: AttributeSet) :
    CoordinatorLayout.Behavior<View>(context, attributeSet) {
    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        //与textView关联
        return dependency is TextView
    }

    override fun onMeasureChild(
        parent: CoordinatorLayout,
        child: View,
        parentWidthMeasureSpec: Int,
        widthUsed: Int,
        parentHeightMeasureSpec: Int,
        heightUsed: Int
    ): Boolean {
        var height = View.MeasureSpec.getSize(parentHeightMeasureSpec);
        var lp = child.layoutParams
        lp.height = height
        child.layoutParams = lp;
        return false
    }

    override fun onLayoutChild(
        parent: CoordinatorLayout,
        child: View,
        layoutDirection: Int
    ): Boolean {
        //获取当前view所依赖的view列表
        var listView = parent.getDependencies(child)
        //寻找第一个依赖的view 可以有多个view但此情况根据需求而定
        var dependencyView = findFirstDependencyView(listView)
        var lp = dependencyView.layoutParams as ViewGroup.MarginLayoutParams
        var top = dependencyView.bottom + lp.bottomMargin
        var left = 0
        var right = child.measuredWidth
        var bottom = child.measuredHeight
        child.offsetLeftAndRight(top)
        child.offsetLeftAndRight(bottom)
        return false
    }

    private fun findFirstDependencyView(listView: List<View>): View {
        if (listView.isNullOrEmpty()) {
            throw RuntimeException("View必须有一个对应依赖")
        }
        return listView[0]
    }

}