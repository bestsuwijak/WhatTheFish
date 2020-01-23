package buu.informatics.s59160141.whatthefish.search

import buu.informatics.s59160141.whatthefish.models.Fish

interface MainView {
    fun setAdapterData(items: List<Fish>)
}