package id.lilule.football.mvp.main.teams

import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.PARENT_ID
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import id.lilule.football.R
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.constraint.layout.matchConstraint
import org.jetbrains.anko.recyclerview.v7.recyclerView

class TeamsFragmentUI : AnkoComponent<TeamsFragment> {

    companion object {
        const val pbTeams = R.id.pb_teams
        const val spLeague = R.id.sp_league_teams
        const val rvTeams = R.id.rv_teams
    }

    override fun createView(ui: AnkoContext<TeamsFragment>) = with(ui) {
        constraintLayout {

            spinner {
                id = spLeague
            }.lparams(width = matchParent, height = wrapContent) {
                topToTop = PARENT_ID
                leftToLeft = PARENT_ID
                rightToRight = PARENT_ID
            }

            recyclerView {
                id = rvTeams
                layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
                addItemDecoration(androidx.recyclerview.widget.DividerItemDecoration(context, 1))
            }.lparams(width = matchParent, height = matchConstraint) {
                topToBottom = spLeague
                leftToLeft = PARENT_ID
                rightToRight = PARENT_ID
                bottomToBottom = PARENT_ID
            }

            progressBar {
                id = pbTeams
                visibility = View.GONE
            }.lparams(width = wrapContent, height = wrapContent) {
                topToTop = rvTeams
                leftToLeft = PARENT_ID
                rightToRight = PARENT_ID
                bottomToBottom = PARENT_ID
            }
        }
    }
}
