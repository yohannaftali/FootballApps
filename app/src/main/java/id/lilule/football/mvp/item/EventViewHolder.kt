package id.lilule.football.mvp.item

import android.content.Intent
import android.provider.CalendarContract
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.lilule.football.mvp.event.EventActivity
import id.lilule.football.mvp.model.Event
import id.lilule.football.util.Constants.OBJECT_EVENT
import id.lilule.football.util.Date.dateIsoGMTToLong
import id.lilule.football.util.Date.timeGMTToLocale
import id.lilule.football.util.Date.timeInMillis
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk27.coroutines.onClick


class EventViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

    private var event: Event? = null
    private val tvEventDate: TextView = view.find(EventItemUI.tvEventDate)
    private val tvEventTime: TextView = view.find(EventItemUI.tvEventTime)
    private val tvHomeName: TextView = view.find(EventItemUI.tvHomeName)
    private val tvHomeScore: TextView = view.find(EventItemUI.tvHomeScore)
    private val tvAwayName: TextView = view.find(EventItemUI.tvAwayName)
    private val tvAwayScore: TextView = view.find(EventItemUI.tvAwayScore)
    private val btAddToCalendar: TextView = view.find(EventItemUI.btAddToCalendar)
    private var currentView: View? = null

    init {
        view.setOnClickListener(this)
        currentView = view
    }

    fun bind(item: Event) {
        if (item.idEvent != null) {
            tvEventDate.text = dateIsoGMTToLong(item.dateEvent, item.strTime)
            tvEventTime.text = timeGMTToLocale(item.strTime)
            tvHomeName.text = item.strHomeTeam
            tvHomeScore.text = item.intHomeScore
            tvAwayName.text = item.strAwayTeam
            tvAwayScore.text = item.intAwayScore
        }
        this.event = item
        val currentTime = System.currentTimeMillis()
        val time = timeInMillis(event?.dateEvent, event?.strTime)
        val isPastEvent = if (time == null || time < currentTime) {
            btAddToCalendar.alpha = 0.1f
            true
        } else {
            btAddToCalendar.alpha = 1f
            false
        }
        btAddToCalendar.onClick {
            if (!isPastEvent) {
                addToCalendar(currentView)
            }
        }
    }

    override fun onClick(view: View) {
        if (event != null) {
            val intent = Intent(view.context, EventActivity::class.java)
            intent.putExtra(OBJECT_EVENT, event)
            view.context.startActivity(intent)
        }
    }

    private fun addToCalendar(view: View?) {
        val time = timeInMillis(event?.dateEvent, event?.strTime)
        if (time != null) {
            val intent = Intent(Intent.ACTION_INSERT)
            intent.data = CalendarContract.Events.CONTENT_URI
            intent.type = "vnd.android.cursor.item/event"

            val endTime = time + 60 * 60 * 1000

            intent.putExtra(CalendarContract.Events.TITLE, event?.strEvent)
            intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, time)
            intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime)

            view?.context?.startActivity(intent)
        }
    }
}