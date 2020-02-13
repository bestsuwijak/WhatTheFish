package buu.informatics.s59160141.whatthefish.fishdex

import buu.informatics.s59160141.whatthefish.models.Fish

interface fishDexView {
    fun setAdapterDataGrid(items: List<Fish>)
}