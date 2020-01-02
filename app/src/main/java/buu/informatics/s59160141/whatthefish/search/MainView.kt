package buu.informatics.s59160141.whatthefish.search

import buu.informatics.s59160141.whatthefish.models.User

interface MainView {
    fun setAdapterData(items: List<User>)
}