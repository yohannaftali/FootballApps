package id.lilule.football.mvp.item

import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.ViewManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.PARENT_ID
import androidx.core.content.ContextCompat
import id.lilule.football.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.constraint.layout.constraintLayout

class EventItemUI : AnkoComponent<ViewGroup> {

    companion object {
        const val tvEventDate = R.id.tv_event_date
        const val tvEventTime = R.id.tv_event_time
        const val tvHomeName = R.id.tv_home_name
        const val tvHomeScore = R.id.tv_home_score
        const val tvAwayName = R.id.tv_away_name
        const val tvAwayScore = R.id.tv_away_score
        const val llCenter = R.id.ll_event_item_center
        const val btAddToCalendar = R.id.bt_add_to_calendar
    }

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        cardView {
            lparams(matchParent, wrapContent)
            constraintLayout {
                textDate(tvEventDate).lparams(width = matchParent, height = wrapContent) {
                    topToTop = PARENT_ID
                    leftToLeft = PARENT_ID
                    rightToRight = PARENT_ID
                }
                textTime(tvEventTime).lparams(width = matchParent, height = wrapContent) {
                    topToBottom = tvEventDate
                    leftToLeft = PARENT_ID
                    rightToRight = PARENT_ID
                }

                layoutCenter(llCenter).lparams(width = matchParent, height = wrapContent) {
                    topToBottom = tvEventTime
                    leftToLeft = PARENT_ID
                    bottomToBottom = PARENT_ID
                    rightToRight = PARENT_ID
                }

                buttonNotification(btAddToCalendar).lparams(width = dip(20), height = dip(20)) {
                    topToTop = tvEventDate
                    rightToRight = tvEventDate
                    margin = dip(8)
                }
            }.lparams(width = matchParent, height = wrapContent)
        }
    }

    private fun ViewManager.layoutCenter(idLayout: Int): LinearLayout {
        return linearLayout {
            id = idLayout
            gravity = Gravity.CENTER_VERTICAL
            textTeam(tvHomeName).lparams(matchParent, wrapContent, 1f)
            linearLayout {
                gravity = Gravity.CENTER_VERTICAL
                textScore(tvHomeScore)
                textLabel(context.getString(R.string.str_vs))
                textScore(tvAwayScore)
            }
            textTeam(tvAwayName).lparams(matchParent, wrapContent, 1f)
        }
    }

    private fun ViewManager.textTeam(idTextView: Int): TextView {
        return textView {
            id = idTextView
            text = context.getString(R.string.str_team)
            textSize = 18f
            gravity = Gravity.CENTER
        }
    }

    private fun ViewManager.textScore(idTextView: Int): TextView {
        return textView {
            id = idTextView
            text = context.getString(R.string.int_score)
            textSize = 20f
            textColor = ContextCompat.getColor(context, R.color.colorAccent)
            setTypeface(null, Typeface.BOLD)
            gravity = Gravity.CENTER
            padding = dip(8)
        }
    }

    private fun ViewManager.textDate(idTextView: Int): TextView {
        return textView {
            id = idTextView
            textSize = 14f
            textColor = ContextCompat.getColor(context, R.color.colorPrimary)
            gravity = Gravity.CENTER
        }
    }

    private fun ViewManager.textTime(idTextView: Int): TextView {
        return textView {
            id = idTextView
            textSize = 12f
            textColor = ContextCompat.getColor(context, R.color.colorPrimary)
            gravity = Gravity.CENTER
        }
    }

    private fun ViewManager.textLabel(label: String): TextView {
        return textView {
            text = label
            textSize = 12f
            textColor = ContextCompat.getColor(context, R.color.colorAccent)
            gravity = Gravity.CENTER
        }
    }

    private fun ViewManager.buttonNotification(idImage: Int): Button {
        return button {
            id = idImage
            background = ContextCompat.getDrawable(context, R.drawable.ic_notifications_active)
        }
    }
}