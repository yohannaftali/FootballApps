package id.lilule.football.mvp.team

import android.graphics.Typeface
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.PARENT_ID
import com.google.android.material.tabs.TabLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.Gravity
import android.view.View
import android.view.ViewManager
import android.widget.LinearLayout
import android.widget.ScrollView
import id.lilule.football.R
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.constraint.layout.matchConstraint
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.themedTabLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView

class TeamActivityUI : AnkoComponent<TeamActivity> {

    companion object {
        const val ivTeamBadgeDetail = R.id.iv_team_badge_detail
        const val svTeamOverview = R.id.sv_team_overview
        const val svTeamPlayers = R.id.sv_team_players
        const val pbTeam = R.id.pb_team
        const val llTeamDetail = R.id.ll_team_detail
        const val abTeam = R.id.ab_team
        const val tlTeam = R.id.tl_team
        const val tvTeam = R.id.tv_team
        const val tvFormedYear = R.id.tv_formed_year
        const val tvStadium = R.id.tv_stadium
        const val tvDescriptionEN = R.id.tv_description_en
        const val rvPlayers = R.id.rv_players
    }

    override fun createView(ui: AnkoContext<TeamActivity>) = with(ui) {

        constraintLayout {

            layoutTeamBadge(llTeamDetail).lparams(width = matchParent, height = wrapContent) {
                topToTop = PARENT_ID
                leftToLeft = PARENT_ID
                rightToRight = PARENT_ID
            }

            appBarLayout {
                id = abTeam
                themedTabLayout(R.style.ThemeOverlay_AppCompat_Dark) {
                    id = tlTeam
                    lparams(width = matchParent, height = wrapContent)
                    {
                        tabGravity = Gravity.CENTER
                        tabMode = TabLayout.MODE_FIXED
                    }
                }
            }.lparams(width = matchParent, height = wrapContent) {
                topToBottom = llTeamDetail
                rightToRight = PARENT_ID
                leftToLeft = PARENT_ID
            }

            scrollViewOverview(svTeamOverview).lparams(width = matchParent, height = wrapContent) {
                topToBottom = abTeam
                leftToLeft = PARENT_ID
                rightToRight = PARENT_ID
            }.lparams(width = matchParent, height = matchConstraint) {
                topToBottom = llTeamDetail
                rightToRight = PARENT_ID
                leftToLeft = PARENT_ID
                bottomToBottom = PARENT_ID
            }

            scrollViewPlayers(svTeamPlayers).lparams(width = matchParent, height = wrapContent) {
                topToBottom = svTeamOverview
                leftToLeft = PARENT_ID
                rightToRight = PARENT_ID
                bottomToBottom = PARENT_ID
            }.lparams(width = matchParent, height = matchConstraint) {
                topToBottom = abTeam
                rightToRight = PARENT_ID
                leftToLeft = PARENT_ID
                bottomToBottom = PARENT_ID
            }

            progressBar {
                id = pbTeam
                visibility = View.GONE
            }.lparams(width = wrapContent, height = wrapContent) {
                topToTop = abTeam
                leftToLeft = PARENT_ID
                rightToRight = PARENT_ID
                bottomToBottom = PARENT_ID
            }
        }
    }

    private fun ViewManager.layoutTeamBadge(idLinearLayout: Int): LinearLayout {
        return linearLayout {
            id = idLinearLayout
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            topPadding = dip(16)
            backgroundColor = ContextCompat.getColor(context, R.color.colorPrimary)
            imageView {
                id = ivTeamBadgeDetail
                imageResource = R.drawable.ic_broken_image
            }.lparams(width = matchParent, height = wrapContent) {
                width = dip(100)
                height = dip(100)
                gravity = Gravity.CENTER
                margin = dip(8)
            }

            textView {
                id = tvTeam
                text = context.getString(R.string.str_team)
                textSize = 24f
                textColor = ContextCompat.getColor(context, R.color.colorWhite)
                setTypeface(null, Typeface.BOLD)
                padding = dip(4)
                gravity = Gravity.CENTER
            }.lparams(width = matchParent, height = wrapContent)

            textView {
                id = tvFormedYear
                text = context.getString(R.string.str_team)
                textSize = 12f
                textColor = ContextCompat.getColor(context, R.color.colorRed)
                setTypeface(null, Typeface.BOLD)
                gravity = Gravity.CENTER
                padding = dip(4)
            }.lparams(width = matchParent, height = wrapContent)

            textView {
                id = tvStadium
                text = context.getString(R.string.str_team)
                textSize = 16f
                textColor = ContextCompat.getColor(context, R.color.colorWhite)
                setTypeface(null, Typeface.BOLD)
                gravity = Gravity.CENTER
                padding = dip(8)
            }.lparams(width = matchParent, height = wrapContent)
        }
    }

    private fun ViewManager.scrollViewOverview(idScrollView: Int): ScrollView {
        return scrollView {
            id = idScrollView
            visibility = View.VISIBLE
            textView {
                id = tvDescriptionEN
            }
        }
    }

    private fun ViewManager.scrollViewPlayers(idScrollView: Int): ScrollView {
        return scrollView {
            id = idScrollView
            visibility = View.GONE
            recyclerView {
                id = rvPlayers
                layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
                addItemDecoration(androidx.recyclerview.widget.DividerItemDecoration(context, 1))
            }
        }
    }
}