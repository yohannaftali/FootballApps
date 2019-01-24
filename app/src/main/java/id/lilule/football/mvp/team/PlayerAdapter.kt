package id.lilule.football.mvp.team

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import id.lilule.football.mvp.model.Player
import org.jetbrains.anko.AnkoContext

class PlayerAdapter(private val player: List<Player>) : androidx.recyclerview.widget.RecyclerView.Adapter<PlayerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(
            PlayerItemUI().createView(
                AnkoContext.create(
                    parent.context,
                    parent
                )
            )
        )
    }

    override fun getItemCount(): Int {
        return player.size
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bind(player[position])
    }
}