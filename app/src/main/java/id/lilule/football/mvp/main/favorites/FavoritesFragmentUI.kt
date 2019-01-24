package id.lilule.football.mvp.main.favorites

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.PARENT_ID
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.GRAVITY_CENTER
import id.lilule.football.R
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.constraint.layout.matchConstraint
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.themedTabLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView


class FavoritesFragmentUI : AnkoComponent<FavoritesFragment> {

    companion object {
        const val tlFavorites = R.id.tl_favorites
        const val abFavorites = R.id.ab_favorites
        const val pbFavorites = R.id.pb_favorites
        const val rvFavoriteEvent = R.id.rv_favorite_event
        const val rvFavoriteTeam = R.id.rv_favorite_team
    }

    override fun createView(ui: AnkoContext<FavoritesFragment>) = with(ui) {
        constraintLayout {
            appBarLayout {
                id = abFavorites
                themedTabLayout(R.style.ThemeOverlay_AppCompat_Dark) {
                    id = tlFavorites
                    lparams(width = matchParent, height = wrapContent)
                    {
                        tabGravity = GRAVITY_CENTER
                        tabMode = TabLayout.MODE_FIXED
                    }
                }
            }.lparams(width = matchParent, height = wrapContent) {
                topToTop = PARENT_ID
                rightToRight = PARENT_ID
                leftToLeft = PARENT_ID
            }

            recyclerView {
                id = rvFavoriteEvent
                layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
                addItemDecoration(androidx.recyclerview.widget.DividerItemDecoration(context, 1))
                visibility = View.VISIBLE
            }.lparams(width = matchParent, height = matchConstraint) {
                topToBottom = abFavorites
                leftToLeft = PARENT_ID
                rightToRight = PARENT_ID
                bottomToBottom = PARENT_ID
            }

            recyclerView {
                id = rvFavoriteTeam
                layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
                addItemDecoration(androidx.recyclerview.widget.DividerItemDecoration(context, 1))
                visibility = View.VISIBLE
            }.lparams(width = matchParent, height = matchConstraint) {
                topToBottom = abFavorites
                leftToLeft = PARENT_ID
                rightToRight = PARENT_ID
                bottomToBottom = PARENT_ID
            }

            progressBar {
                id = pbFavorites
                visibility = View.GONE
            }.lparams(width = wrapContent, height = wrapContent) {
                topToBottom = abFavorites
                leftToLeft = PARENT_ID
                rightToRight = PARENT_ID
                bottomToBottom = PARENT_ID
            }
        }
    }
}
