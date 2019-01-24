package id.lilule.football.mvp.event

import android.graphics.Color
import android.graphics.Typeface
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.PARENT_ID
import androidx.core.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.view.ViewManager
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import id.lilule.football.R
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.constraint.layout.matchConstraint

class EventActivityUI : AnkoComponent<EventActivity> {

    companion object {
        const val tvEventDate = R.id.tv_detail_event_date
        const val tvEventTime = R.id.tv_detail_event_time
        const val svDetail = R.id.sv_detail
        const val pbDetail = R.id.pb_detail
        const val llTeamBadge = R.id.ll_detail_team_badge
        const val llTeamName = R.id.ll_detail_team_name
        const val llScore = R.id.ll_detail_score

        const val tvHomeScore = R.id.tv_detail_home_score
        const val tvAwayScore = R.id.tv_detail_away_score

        const val tvHomeTeam = R.id.tv_detail_home_team
        const val tvAwayTeam = R.id.tv_detail_away_team
        const val ivHomeBadge = R.id.tv_detail_home_badge
        const val ivAwayBadge = R.id.tv_detail_away_badge
        const val tvHomeFormation = R.id.tv_detail_home_formation
        const val tvAwayFormation = R.id.tv_detail_away_formation
        const val tvHomeRedCards = R.id.tv_detail_home_red_cards
        const val tvAwayRedCards = R.id.tv_detail_away_red_cards
        const val tvHomeYellowCards = R.id.tv_detail_home_yellow_cards
        const val tvAwayYellowCards = R.id.tv_detail_away_yellow_cards
        const val tvHomeGoalDetails = R.id.tv_detail_home_goal_details
        const val tvAwayGoalDetails = R.id.tv_detail_away_goal_details
        const val tvHomeShots = R.id.tv_detail_home_shots
        const val tvAwayShots = R.id.tv_detail_away_shots

        const val tvHomeLineupGoalKeeper = R.id.tv_detail_home_lineup_goal_keeper
        const val tvAwayLineupGoalKeeper = R.id.tv_detail_away_lineup_goal_keeper
        const val tvHomeLineupDefense = R.id.tv_detail_home_lineup_defense
        const val tvAwayLineupDefense = R.id.tv_detail_away_lineup_defense
        const val tvHomeLineupMidfield = R.id.tv_detail_home_lineup_midfield
        const val tvAwayLineupMidfield = R.id.tv_detail_away_lineup_midfield
        const val tvHomeLineupForward = R.id.tv_detail_home_lineup_forward
        const val tvAwayLineupForward = R.id.tv_detail_away_lineup_forward
        const val tvHomeLineupSubstitutes = R.id.tv_detail_home_lineup_substitutes
        const val tvAwayLineupSubstitutes = R.id.tv_detail_away_lineup_substitutes

    }

    override fun createView(ui: AnkoContext<EventActivity>) = with(ui) {

        constraintLayout {

            layoutTeamBadge(llTeamBadge).lparams(width = matchParent, height = wrapContent) {
                topToTop = PARENT_ID
                leftToLeft = PARENT_ID
                rightToRight = PARENT_ID
            }

            layoutTeamName(llTeamName).lparams(width = matchParent, height = wrapContent) {
                topToBottom = llTeamBadge
                leftToLeft = PARENT_ID
                rightToRight = PARENT_ID
            }

            scrollViewDetail(svDetail).lparams(width = matchParent, height = matchConstraint) {
                topToBottom = llTeamName
                leftToLeft = PARENT_ID
                rightToRight = PARENT_ID
                bottomToBottom = PARENT_ID
            }

            progressBar {
                id = pbDetail
                visibility = View.GONE
            }.lparams(width = wrapContent, height = wrapContent) {
                topToTop = PARENT_ID
                leftToLeft = PARENT_ID
                rightToRight = PARENT_ID
                bottomToBottom = PARENT_ID
            }
        }
    }

    private fun ViewManager.layoutTeamBadge(idLinearLayout: Int): LinearLayout {
        return linearLayout {
            id = idLinearLayout
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
            topPadding = dip(16)
            imageView {
                id = ivHomeBadge
                imageResource = R.drawable.ic_broken_image
            }.lparams(width = wrapContent, height = wrapContent) {
                width = dip(100)
                height = dip(100)
                gravity = Gravity.CENTER
                margin = dip(8)
            }
            linearLayout {
                orientation = LinearLayout.VERTICAL
                gravity = Gravity.CENTER
                textView {
                    id = tvEventDate
                    text = context.getString(R.string.event_date)
                    textSize = 10f
                    textColor = ContextCompat.getColor(context, R.color.colorAccent)
                    gravity = Gravity.CENTER
                    setTypeface(null, Typeface.BOLD)
                    padding = dip(8)
                }.lparams(width = matchParent, height = wrapContent)
                textView {
                    id = tvEventTime
                    text = context.getString(R.string.event_time)
                    textSize = 10f
                    textColor = ContextCompat.getColor(context, R.color.colorRed)
                    gravity = Gravity.CENTER
                    setTypeface(null, Typeface.BOLD)
                    padding = dip(8)
                }.lparams(width = matchParent, height = wrapContent)
                linearLayout {
                    id = llScore
                    gravity = Gravity.CENTER
                    textScore(tvHomeScore)
                    textView {
                        padding = dip(8)
                        textSize = 16f
                        gravity = Gravity.CENTER
                        setTypeface(null, Typeface.BOLD)
                        text = context.getString(R.string.str_vs)
                    }
                    textScore(tvAwayScore)
                }.lparams(width = matchParent, height = wrapContent)
            }
            imageView {
                id = ivAwayBadge
                imageResource = R.drawable.ic_broken_image
            }.lparams(width = wrapContent, height = wrapContent) {
                width = dip(100)
                height = dip(100)
                gravity = Gravity.CENTER
                margin = dip(8)
            }
        }
    }

    private fun ViewManager.layoutTeamName(idLinearLayout: Int): LinearLayout {
        return linearLayout {
            id = idLinearLayout
            orientation = LinearLayout.HORIZONTAL
            textView {
                id = tvHomeTeam
                text = context.getString(R.string.str_team)
                textSize = 18f
                textColor = ContextCompat.getColor(context, R.color.colorPrimary)
                setTypeface(null, Typeface.BOLD)
                padding = dip(8)
                gravity = Gravity.CENTER
            }.lparams(width = 0, height = wrapContent)
            {
                weight = 0.5f
            }
            textView {
                id = tvAwayTeam
                text = context.getString(R.string.str_team)
                textSize = 18f
                textColor = ContextCompat.getColor(context, R.color.colorPrimary)
                setTypeface(null, Typeface.BOLD)
                padding = dip(8)
                gravity = Gravity.CENTER
            }.lparams(width = 0, height = wrapContent) {
                weight = 0.5f
            }
        }
    }

    private fun ViewManager.scrollViewDetail(idScrollView: Int): ScrollView {
        return scrollView {
            id = idScrollView
            linearLayout {
                orientation = LinearLayout.VERTICAL
                separator()
                layoutEventInfo(
                    context.getString(R.string.formation),
                    tvHomeFormation,
                    tvAwayFormation
                )
                separator()
                layoutEventInfo(
                    context.getString(R.string.goals),
                    tvHomeGoalDetails,
                    tvAwayGoalDetails
                )
                layoutEventInfo(
                    context.getString(R.string.shots),
                    tvHomeShots,
                    tvAwayShots
                )
                separator()
                layoutEventInfo(
                    context.getString(R.string.red_cards),
                    tvHomeRedCards,
                    tvAwayRedCards, R.color.colorRed
                )
                layoutEventInfo(
                    context.getString(R.string.yellow_cards),
                    tvHomeYellowCards,
                    tvAwayYellowCards,
                    R.color.colorYellow
                )
                separator()
                textSubTitle(context.getString(R.string.lineups))
                layoutEventInfo(
                    context.getString(R.string.goal_keeper),
                    tvHomeLineupGoalKeeper,
                    tvAwayLineupGoalKeeper
                )
                layoutEventInfo(
                    context.getString(R.string.defense),
                    tvHomeLineupDefense,
                    tvAwayLineupDefense
                )
                layoutEventInfo(
                    context.getString(R.string.midfield),
                    tvHomeLineupMidfield,
                    tvAwayLineupMidfield
                )
                layoutEventInfo(
                    context.getString(R.string.forward),
                    tvHomeLineupForward,
                    tvAwayLineupForward
                )
                layoutEventInfo(
                    context.getString(R.string.substitutes),
                    tvHomeLineupSubstitutes,
                    tvAwayLineupSubstitutes
                )
                padding = dip(8)
            }.lparams(width = matchParent, height = wrapContent)
        }
    }

    private fun ViewManager.layoutEventInfo(
        title: String?,
        idHomeInfo: Int,
        idAwayInfo: Int,
        color: Int? = null
    ): LinearLayout {
        return linearLayout {
            topPadding = dip(8)

            textView {
                id = idHomeInfo
                rightPadding = dip(8)
                text = context.getString(R.string.str_info)
            }.lparams(matchParent, wrapContent, 1f)

            textCenter(title, color)

            textView {
                id = idAwayInfo
                gravity = Gravity.END
                leftPadding = dip(8)
                text = context.getString(R.string.str_info)
            }.lparams(matchParent, wrapContent, 1f)
        }
    }

    private fun ViewManager.textScore(idScore: Int): TextView {
        return textView {
            id = idScore
            text = context.getString(R.string.int_score)
            textSize = 36f
            gravity = Gravity.CENTER
            setTypeface(null, Typeface.BOLD)
            padding = dip(8)
        }
    }

    private fun ViewManager.textCenter(title: String?, color: Int? = null): TextView {
        return textView {
            val resColor = color ?: R.color.colorPrimary
            gravity = Gravity.CENTER
            leftPadding = dip(8)
            rightPadding = dip(8)

            textColor = ContextCompat.getColor(context, resColor)
            text = title
        }
    }

    private fun ViewManager.textSubTitle(subTitle: String?): TextView {
        return textView {
            topPadding = dip(8)
            gravity = Gravity.CENTER
            textSize = 18f
            setTypeface(null, Typeface.BOLD)
            text = subTitle
        }
    }

    private fun ViewManager.separator(): LinearLayout {
        return linearLayout {
            view {
                backgroundColor = Color.LTGRAY
            }.lparams(matchParent, dip(1)) {
                topMargin = dip(8)
            }
        }
    }
}