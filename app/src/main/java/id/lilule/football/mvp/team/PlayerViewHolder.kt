package id.lilule.football.mvp.team

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import id.lilule.football.mvp.model.Player
import id.lilule.football.mvp.player.PlayerActivity
import id.lilule.football.util.Constants.OBJECT_PLAYER
import org.jetbrains.anko.find

class PlayerViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view), View.OnClickListener {

    private var player: Player? = null
    private val tvItemPlayer: TextView = view.find(PlayerItemUI.tvItemPlayer)
    private val tvItemPosition: TextView = view.find(PlayerItemUI.tvItemPosition)
    private val ivItemThumb: ImageView = view.find(PlayerItemUI.ivItemThumb)

    init {
        view.setOnClickListener(this)
    }

    fun bind(item: Player) {
        tvItemPlayer.text = item.strPlayer
        tvItemPosition.text = item.strPosition

        Picasso.get()
            .load(item.strCutout)
            .into(ivItemThumb)
        this.player = item
    }

    override fun onClick(view: View) {
        val intent = Intent(view.context, PlayerActivity::class.java)
        intent.putExtra(OBJECT_PLAYER, player)
        view.context.startActivity(intent)
    }
}