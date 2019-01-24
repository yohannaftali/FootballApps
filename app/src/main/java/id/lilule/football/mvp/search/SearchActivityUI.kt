package id.lilule.football.mvp.search

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.PARENT_ID
import id.lilule.football.R
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.constraint.layout.matchConstraint
import org.jetbrains.anko.recyclerview.v7.recyclerView

class SearchActivityUI : AnkoComponent<SearchActivity> {

    companion object {
        const val rvSearch = R.id.rv_search
        const val pbSearch = R.id.pb_search
    }

    override fun createView(ui: AnkoContext<SearchActivity>) = with(ui) {
        constraintLayout {

            recyclerView {
                id = rvSearch
                layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
                addItemDecoration(androidx.recyclerview.widget.DividerItemDecoration(context, 1))
            }.lparams(width = matchParent, height = matchConstraint) {
                topToTop = PARENT_ID
                leftToLeft = PARENT_ID
                rightToRight = PARENT_ID
                bottomToBottom = PARENT_ID
            }
            progressBar {
                id = pbSearch
                visibility = View.GONE
            }.lparams(width = wrapContent, height = wrapContent) {
                topToTop = PARENT_ID
                leftToLeft = PARENT_ID
                rightToRight = PARENT_ID
                bottomToBottom = PARENT_ID
            }
        }
    }
}