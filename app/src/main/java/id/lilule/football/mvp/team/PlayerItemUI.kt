package id.lilule.football.mvp.team

import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.PARENT_ID
import androidx.core.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import id.lilule.football.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.constraint.layout.matchConstraint

class PlayerItemUI : AnkoComponent<ViewGroup> {

    companion object {
        const val ivItemThumb = R.id.iv_item_thumb
        const val tvItemPlayer = R.id.tv_item_player
        const val tvItemPosition = R.id.tv_item_position
    }

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        cardView {
            lparams(matchParent, wrapContent)
            constraintLayout {
                imageView {
                    id = ivItemThumb
                    scaleType = ImageView.ScaleType.FIT_XY
                    padding = dip(8)
                }.lparams(width = dip(80), height = dip(80)) {
                    topToTop = PARENT_ID
                    leftToLeft = PARENT_ID
                    bottomToBottom = PARENT_ID
                }
                textView {
                    id = tvItemPlayer
                    textSize = 18f
                    textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                    gravity = Gravity.CENTER_VERTICAL
                    padding = dip(8)
                }.lparams(width = matchConstraint, height = dip(80)) {
                    topToTop = PARENT_ID
                    leftToRight = ivItemThumb
                    bottomToBottom = PARENT_ID
                    rightToLeft = tvItemPosition
                }
                textView {
                    id = tvItemPosition
                    textColor = ContextCompat.getColor(ctx, R.color.colorAccent)
                    textSize = 12f
                    gravity = Gravity.CENTER_VERTICAL
                    padding = dip(8)

                }.lparams(width = wrapContent, height = dip(80)) {
                    topToTop = PARENT_ID
                    rightToRight = PARENT_ID
                    bottomToBottom = PARENT_ID
                }

            }.lparams(matchParent, matchParent) {
                setMargins(dip(8), dip(4), dip(8), dip(4))
            }
        }
    }
}