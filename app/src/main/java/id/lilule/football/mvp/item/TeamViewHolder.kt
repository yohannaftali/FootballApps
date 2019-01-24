package id.lilule.football.mvp.item

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import id.lilule.football.mvp.model.Team
import id.lilule.football.mvp.team.TeamActivity
import id.lilule.football.util.Constants.OBJECT_TEAM
import org.jetbrains.anko.find

class TeamViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view), View.OnClickListener {

    private var team: Team? = null
    private val tvTeamName: TextView = view.find(TeamItemUI.tvTeamName)
    private val ivTeamBadge: ImageView = view.find(TeamItemUI.ivTeamBadge)

    init {
        view.setOnClickListener(this)
    }

    fun bind(item: Team) {
        if (item.idTeam != null) {
            tvTeamName.text = item.strTeam
            Picasso.get()
                .load(item.strTeamBadge)
                .into(ivTeamBadge)
        }
        this.team = item
    }

    override fun onClick(view: View) {
        if (team != null) {
            val intent = Intent(view.context, TeamActivity::class.java)
            intent.putExtra(OBJECT_TEAM, team)
            view.context.startActivity(intent)
        }
    }
}