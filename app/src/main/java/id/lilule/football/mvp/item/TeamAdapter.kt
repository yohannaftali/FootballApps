package id.lilule.football.mvp.item

import android.view.ViewGroup
import id.lilule.football.mvp.model.Team
import org.jetbrains.anko.AnkoContext

class TeamAdapter(private val teams: List<Team>) : androidx.recyclerview.widget.RecyclerView.Adapter<TeamViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(
            TeamItemUI().createView(
                AnkoContext.create(
                    parent.context,
                    parent
                )
            )
        )
    }

    override fun getItemCount(): Int {
        return teams.size
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bind(teams[position])
    }
}