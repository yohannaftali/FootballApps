package id.lilule.football.mvp.player

import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.PARENT_ID
import androidx.core.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.view.ViewManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import id.lilule.football.R
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.constraint.layout.matchConstraint

class PlayerActivityUI : AnkoComponent<PlayerActivity> {

    companion object {
        const val ivPlayerFanart = R.id.iv_player_fanart
        const val tvPlayerWeight = R.id.tv_player_weight
        const val tvPlayerHeight = R.id.tv_player_height
        const val tvPlayerPosition = R.id.tv_player_position
        const val tvPlayerDescription = R.id.tv_player_description
        const val llPropertiesLabel = R.id.ll_properties_label
        const val llPropertiesData = R.id.ll_properties_data
        const val llPlayerPosition = R.id.ll_player_position
        const val svPlayerDetail = R.id.sv_player_detail
        const val pbPlayerDetail = R.id.pb_player_detail
    }

    override fun createView(ui: AnkoContext<PlayerActivity>) = with(ui) {

        constraintLayout {

            imageView {
                id = ivPlayerFanart
                scaleType = ImageView.ScaleType.FIT_XY
                minimumHeight = dip(20)
            }.lparams(width = matchParent, height = wrapContent) {
                topToTop = PARENT_ID
                leftToLeft = PARENT_ID
                rightToRight = PARENT_ID
            }

            textPropertiesLabel(llPropertiesLabel).lparams(width = matchParent, height = wrapContent) {
                topToBottom = ivPlayerFanart
                leftToLeft = PARENT_ID
                rightToRight = PARENT_ID
            }

            textPropertiesData(llPropertiesData).lparams(width = matchParent, height = wrapContent) {
                topToBottom = llPropertiesLabel
                leftToLeft = PARENT_ID
                rightToRight = PARENT_ID
            }

            textPlayerPosition(llPlayerPosition).lparams(width = matchParent, height = wrapContent) {
                topToBottom = llPropertiesData
                leftToLeft = PARENT_ID
                rightToRight = PARENT_ID
            }

            scrollViewDetail(svPlayerDetail).lparams(width = matchParent, height = matchConstraint) {
                topToBottom = llPlayerPosition
                leftToLeft = PARENT_ID
                rightToRight = PARENT_ID
                bottomToBottom = PARENT_ID
            }

            progressBar {
                id = pbPlayerDetail
                visibility = View.GONE
            }.lparams(width = wrapContent, height = wrapContent) {
                topToTop = PARENT_ID
                leftToLeft = PARENT_ID
                rightToRight = PARENT_ID
                bottomToBottom = PARENT_ID
            }
        }
    }

    private fun ViewManager.scrollViewDetail(idScrollView: Int): ScrollView {
        return scrollView {
            id = idScrollView
            textView {
                id = tvPlayerDescription
                text = context.getString(R.string.player_description)
                textSize = 14f
                padding = dip(8)
            }.lparams(width = matchParent, height = wrapContent)
        }
    }

    private fun ViewManager.textPropertiesLabel(idLinearLayout: Int): LinearLayout {
        return linearLayout() {
            id = idLinearLayout
            orientation = LinearLayout.HORIZONTAL
            textView() {
                text = context.getString(R.string.weight_kg)
                textSize = 14f
                textColor = ContextCompat.getColor(context, R.color.colorLightGrey)
                gravity = Gravity.CENTER
                padding = dip(4)
            }.lparams(height = matchParent) {
                weight = 0.5f
            }
            textView {
                text = context.getString(R.string.height_m)
                textSize = 14f
                textColor = ContextCompat.getColor(context, R.color.colorLightGrey)
                gravity = Gravity.CENTER
                padding = dip(4)
            }.lparams(height = matchParent) {
                weight = 0.5f
            }
        }
    }

    private fun ViewManager.textPropertiesData(idLinearLayout: Int): LinearLayout {
        return linearLayout {
            id = idLinearLayout
            orientation = LinearLayout.HORIZONTAL
            textView() {
                id = tvPlayerWeight
                text = context.getString(R.string.weight_kg)
                textSize = 32f
                textColor = ContextCompat.getColor(context, R.color.colorDarkGrey)
                gravity = Gravity.CENTER
                padding = dip(8)
            }.lparams(height = matchParent) {
                weight = 0.5f
            }
            textView {
                id = tvPlayerHeight
                text = context.getString(R.string.height_m)
                textSize = 32f
                textColor = ContextCompat.getColor(context, R.color.colorDarkGrey)
                gravity = Gravity.CENTER
                padding = dip(8)
            }.lparams(height = matchParent) {
                weight = 0.5f
            }
        }
    }

    private fun ViewManager.textPlayerPosition(idLinearLayout: Int): LinearLayout {
        return linearLayout() {
            id = idLinearLayout
            orientation = LinearLayout.VERTICAL
            textView {
                id = tvPlayerPosition
                text = context.getString(R.string.player_position)
                textSize = 24f
                textColor = ContextCompat.getColor(context, R.color.colorPrimaryText)
                gravity = Gravity.CENTER
                padding = dip(8)
            }.lparams(width = matchParent, height = wrapContent)
            separator().lparams(width = matchParent, height = wrapContent)
        }
    }

    private fun ViewManager.separator(): LinearLayout {
        return linearLayout {
            view {
                backgroundColor = ContextCompat.getColor(context, R.color.colorDivider)
            }.lparams(matchParent, dip(1)) {
                topMargin = dip(8)
            }
        }
    }


}