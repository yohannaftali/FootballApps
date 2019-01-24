package id.lilule.football.mvp.main.matches

import android.view.Gravity
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.PARENT_ID
import com.google.android.material.tabs.TabLayout
import id.lilule.football.R
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.constraint.layout.matchConstraint
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.themedTabLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MatchesFragmentUI : AnkoComponent<MatchesFragment> {

    companion object {
        const val tlMatches = R.id.tl_matches
        const val abMatches = R.id.ab_matches
        const val pbMatches = R.id.pb_matches
        const val spLeague = R.id.sp_league
        const val rvEvents = R.id.rv_events
    }

    override fun createView(ui: AnkoContext<MatchesFragment>) = with(ui) {
        constraintLayout {
            appBarLayout {
                id = abMatches
                themedTabLayout(R.style.ThemeOverlay_AppCompat_Dark) {
                    id = tlMatches
                    lparams(width = matchParent, height = wrapContent)
                    {
                        tabGravity = Gravity.CENTER
                        tabMode = TabLayout.MODE_FIXED
                    }
                }
            }.lparams(width = matchParent, height = wrapContent) {
                topToTop = PARENT_ID
                rightToRight = PARENT_ID
                leftToLeft = PARENT_ID
            }

            spinner {
                id = spLeague
            }.lparams(width = matchParent, height = wrapContent) {
                topToBottom = abMatches
                leftToLeft = PARENT_ID
                rightToRight = PARENT_ID
            }

            recyclerView {
                id = rvEvents
                layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
                addItemDecoration(androidx.recyclerview.widget.DividerItemDecoration(context, 1))
            }.lparams(width = matchParent, height = matchConstraint) {
                topToBottom = spLeague
                leftToLeft = PARENT_ID
                rightToRight = PARENT_ID
                bottomToBottom = PARENT_ID
            }

            progressBar {
                id = pbMatches
                visibility = View.GONE
            }.lparams(width = wrapContent, height = wrapContent) {
                topToTop = rvEvents
                leftToLeft = PARENT_ID
                rightToRight = PARENT_ID
                bottomToBottom = PARENT_ID
            }
        }
    }
}
