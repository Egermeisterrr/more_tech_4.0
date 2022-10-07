package com.example.moretech40android.presentation.fragments.insides

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class InsightsItemDecoration(
    private val spaceSizeVertical: Float,
    private val spaceSizeHorizontal: Float
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        parent.adapter?.let { adapter ->
            outRect.top = when (parent.getChildAdapterPosition(view)) {
                RecyclerView.NO_POSITION,
                adapter.itemCount - 1 -> 0
                else -> spaceSizeVertical.toInt()
            }
            outRect.left = spaceSizeHorizontal.toInt()
            outRect.right = spaceSizeHorizontal.toInt()
            outRect.bottom = spaceSizeVertical.toInt()
        }
    }
}