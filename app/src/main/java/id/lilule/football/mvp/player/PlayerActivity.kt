package id.lilule.football.mvp.player

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import id.lilule.football.R
import id.lilule.football.mvp.model.Player
import id.lilule.football.util.Constants.OBJECT_PLAYER
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView


class PlayerActivity : AppCompatActivity() {

    private lateinit var player: Player
    private val ui = PlayerActivityUI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUI()
        setupData()
    }

    private fun setupUI() {
        PlayerActivityUI().setContentView(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupData() {
        val intent = intent
        val player: Player = intent.getParcelableExtra(OBJECT_PLAYER)
        if (!player.idPlayer.isNullOrEmpty()) {
            this.player = player

            find<TextView>(ui.tvPlayerWeight).text = player.strWeight
            find<TextView>(ui.tvPlayerHeight).text = player.strHeight
            find<TextView>(ui.tvPlayerPosition).text = player.strPosition
            find<TextView>(ui.tvPlayerDescription).text = player.strDescriptionEN

            val ivTeamBadgeDetail = find<ImageView>(ui.ivPlayerFanart)

            Picasso.get()
                .load(player.strFanart1)
                .into(ivTeamBadgeDetail)

            supportActionBar?.title = player.strPlayer
            supportActionBar?.subtitle = player.strTeam
        } else {
            supportActionBar?.title = getString(R.string.error_no_data)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}