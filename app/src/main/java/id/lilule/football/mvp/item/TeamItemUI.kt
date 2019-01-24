package id.lilule.football.mvp.item

import androidx.core.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import id.lilule.football.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class TeamItemUI : AnkoComponent<ViewGroup> {

    companion object {
        const val ivTeamBadge = R.id.iv_team_badge
        const val tvTeamName = R.id.tv_team_name
    }

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        cardView {
            lparams(matchParent, wrapContent)
            linearLayout {
                orientation = LinearLayout.HORIZONTAL
                padding = dip(8)
                imageView {
                    id = ivTeamBadge
                }.lparams(wrapContent, matchParent) {
                    width = dip(60)
                    height = dip(60)
                    gravity = Gravity.CENTER
                    margin = dip(4)
                }
                textView {
                    id = tvTeamName
                    textSize = 24f
                    textColor = ContextCompat.getColor(ctx, R.color.colorPrimaryText)
                    gravity = Gravity.CENTER_VERTICAL
                    padding = dip(8)
                }.lparams(width = wrapContent, height = dip(80))

            }.lparams(matchParent, matchParent) {
                setMargins(dip(8), dip(4), dip(8), dip(4))
            }
        }
    }
}