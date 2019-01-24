package id.lilule.football.mvp.main

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.PARENT_ID
import androidx.core.content.ContextCompat
import id.lilule.football.R
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.constraint.layout.matchConstraint
import org.jetbrains.anko.design.bottomNavigationView
import org.jetbrains.anko.support.v4.viewPager

class MainActivityUI : AnkoComponent<MainActivity> {

    companion object {

        const val vpMain = R.id.vp_main
        const val nvMain = R.id.nv_main
        const val pbMain = R.id.pb_main
        const val btMatches = R.id.bt_matches
        const val btTeams = R.id.bt_teams
        const val btFavorite = R.id.bt_favorite
    }

    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        constraintLayout {
            viewPager {
                id = vpMain
            }.lparams(width = matchParent, height = matchConstraint) {
                topToTop = PARENT_ID
                leftToLeft = PARENT_ID
                rightToRight = PARENT_ID
                bottomToTop = nvMain
            }
            progressBar {
                id = pbMain
                visibility = View.GONE
            }.lparams(width = wrapContent, height = wrapContent) {
                topToTop = vpMain
                leftToLeft = PARENT_ID
                rightToRight = PARENT_ID
                bottomToTop = nvMain
            }

            bottomNavigationView {
                id = nvMain
                backgroundColor = ContextCompat.getColor(context, R.color.colorPrimaryLight)

                menu.apply {
                    add(0, btMatches, 0, context.getString(R.string.matches))
                        .setIcon(R.drawable.ic_trophy)

                    add(0, btTeams, 1, context.getString(R.string.teams))
                        .setIcon(R.drawable.ic_group)

                    add(0, btFavorite, 2, context.getString(R.string.favorites))
                        .setIcon(R.drawable.ic_stars)

                }
            }.lparams(width = matchParent, height = wrapContent) {
                leftToLeft = PARENT_ID
                rightToRight = PARENT_ID
                bottomToBottom = PARENT_ID
            }
        }
    }
}