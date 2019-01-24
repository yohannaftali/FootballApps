package id.lilule.football.mvp.item

import android.content.Context
import android.widget.ArrayAdapter
import id.lilule.football.mvp.model.League

class LeagueArrayAdapter(context: Context, leagueList: List<League>) :
    ArrayAdapter<League>(
        context,
        android.R.layout.simple_spinner_dropdown_item,
        leagueList
    )