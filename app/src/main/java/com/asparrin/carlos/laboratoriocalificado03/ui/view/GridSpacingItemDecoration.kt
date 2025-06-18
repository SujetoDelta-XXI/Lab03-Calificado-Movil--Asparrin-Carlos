package com.asparrin.carlos.laboratoriocalificado03.ui.view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridSpacingItemDecoration(
    private val spanCount: Int,
    private val spacing: Int,
    private val includeEdge: Boolean
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view) // ítem posición
        val column = position % spanCount                 // columna

        if (includeEdge) {
            outRect.left   = spacing - column * spacing / spanCount
            outRect.right  = (column + 1) * spacing / spanCount
            if (position < spanCount) { // primera fila
                outRect.top = spacing
            }
            outRect.bottom = spacing // espacio abajo
        } else {
            outRect.left  = column * spacing / spanCount
            outRect.right = spacing - (column + 1) * spacing / spanCount
            if (position >= spanCount) {
                outRect.top = spacing // espacio arriba
            }
        }
    }
}
