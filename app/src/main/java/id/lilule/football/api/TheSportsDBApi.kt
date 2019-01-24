package id.lilule.football.api

import android.net.Uri
import id.lilule.football.BuildConfig

object TheSportsDBApi {

    fun getLookupAllTeamsByLeagueId(id: String?): String {
        return Uri.parse(BuildConfig.BASE_URL)
            .buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("lookup_all_teams.php")
            .appendQueryParameter("id", id)
            .build()
            .toString()
    }

    fun getAllLeagues(): String {
        return Uri.parse(BuildConfig.BASE_URL)
            .buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("all_leagues.php")
            .build()
            .toString()
    }

    fun getEventsPastLeague(id: String?): String {
        return Uri.parse(BuildConfig.BASE_URL)
            .buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("eventspastleague.php")
            .appendQueryParameter("id", id)
            .build()
            .toString()
    }

    fun getEventsNextLeague(id: String?): String {
        return Uri.parse(BuildConfig.BASE_URL)
            .buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("eventsnextleague.php")
            .appendQueryParameter("id", id)
            .build()
            .toString()
    }

    fun getLookupTeam(id: String?): String {
        return Uri.parse(BuildConfig.BASE_URL)
            .buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("lookupteam.php")
            .appendQueryParameter("id", id)
            .build()
            .toString()
    }

    fun getSearchEvents(e: String?): String {
        return Uri.parse(BuildConfig.BASE_URL)
            .buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("searchevents.php")
            .appendQueryParameter("e", e)
            .build()
            .toString()
    }

    fun getSearchTeams(t: String?): String {
        return Uri.parse(BuildConfig.BASE_URL)
            .buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("searchteams.php")
            .appendQueryParameter("t", t)
            .build()
            .toString()
    }

    fun getLookupAllPlayersByTeamId(id: String?): String {
        return Uri.parse(BuildConfig.BASE_URL)
            .buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("lookup_all_players.php")
            .appendQueryParameter("id", id)
            .build()
            .toString()
    }
}