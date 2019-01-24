package id.lilule.football.mvp.item

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import id.lilule.football.mvp.model.Event
import org.jetbrains.anko.AnkoContext

class EventAdapter(private val events: List<Event>) : androidx.recyclerview.widget.RecyclerView.Adapter<EventViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(
            EventItemUI().createView(
                AnkoContext.create(
                    parent.context,
                    parent
                )
            )
        )
    }

    override fun getItemCount(): Int {
        return events.size
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(events[position])
    }
}