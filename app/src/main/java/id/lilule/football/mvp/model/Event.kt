package id.lilule.football.mvp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event(

    val id: Long?,
    val idEvent: String?,
    val strEvent: String?,
    val strSport: String?,
    val idLeague: String?,
    val strLeague: String?,
    val dateEvent: String?,
    val strDate: String?,
    val strTime: String?,

    // home team
    val idHomeTeam: String?,
    val strHomeTeam: String?,
    val intHomeScore: String?,
    val strHomeFormation: String?,
    val strHomeGoalDetails: String?,
    val intHomeShots: String?,
    val strHomeRedCards: String?,
    val strHomeYellowCards: String?,

    val strHomeLineupGoalkeeper: String?,
    val strHomeLineupDefense: String?,
    val strHomeLineupMidfield: String?,
    val strHomeLineupForward: String?,
    val strHomeLineupSubstitutes: String?,

    // away team
    val idAwayTeam: String?,
    val strAwayTeam: String?,
    val intAwayScore: String?,
    val strAwayFormation: String?,
    val strAwayGoalDetails: String?,
    val intAwayShots: String?,
    val strAwayRedCards: String?,
    val strAwayYellowCards: String?,

    val strAwayLineupGoalkeeper: String?,
    val strAwayLineupDefense: String?,
    val strAwayLineupMidfield: String?,
    val strAwayLineupForward: String?,
    val strAwayLineupSubstitutes: String?

) : Parcelable {
    companion object {
        const val ID: String = "id"
        const val ID_EVENT: String = "idEvent"
        const val STR_EVENT: String = "strEvent"
        const val STR_SPORT: String = "strSport"
        const val ID_LEAGUE: String = "idLeague"
        const val STR_LEAGUE: String = "strLeague"
        const val DATE_EVENT: String = "dateEvent"
        const val STR_DATE: String = "strDate"
        const val STR_TIME: String = "strTime"

        // home team
        const val ID_HOME_TEAM: String = "idHomeTeam"
        const val STR_HOME_TEAM: String = "strHomeTeam"
        const val INT_HOME_SCORE: String = "intHomeScore"
        const val STR_HOME_FORMATION: String = "strHomeFormation"
        const val STR_HOME_GOAL_DETAILS: String = "strHomeGoalDetails"
        const val INT_HOME_SHOTS: String = "intHomeShots"
        const val STR_HOME_RED_CARDS: String = "strHomeRedCards"
        const val STR_HOME_YELLOW_CARDS: String = "strHomeYellowCards"

        const val STR_HOME_LINEUP_GOALKEEPER: String = "strHomeLineupGoalkeeper"
        const val STR_HOME_LINEUP_DEFENSE: String = "strHomeLineupDefense"
        const val STR_HOME_LINEUP_MIDFIELD: String = "strHomeLineupMidfield"
        const val STR_HOME_LINEUP_FORWARD: String = "strHomeLineupForward"
        const val STR_HOME_LINEUP_SUBSTITUTES: String = "strHomeLineupSubstitutes"

        // away team
        const val ID_AWAY_TEAM: String = "idAwayTeam"
        const val STR_AWAY_TEAM: String = "strAwayTeam"
        const val INT_AWAY_SCORE: String = "intAwayScore"
        const val STR_AWAY_FORMATION: String = "strAwayFormation"
        const val STR_AWAY_GOAL_DETAILS: String = "strAwayGoalDetails"
        const val INT_AWAY_SHOTS: String = "intAwayShots"
        const val STR_AWAY_RED_CARDS: String = "strAwayRedCards"
        const val STR_AWAY_YELLOW_CARDS: String = "strAwayYellowCards"

        const val STR_AWAY_LINEUP_GOALKEEPER: String = "strAwayLineupGoalkeeper"
        const val STR_AWAY_LINEUP_DEFENSE: String = "strAwayLineupDefense"
        const val STR_AWAY_LINEUP_MIDFIELD: String = "strAwayLineupMidfield"
        const val STR_AWAY_LINEUP_FORWARD: String = "strAwayLineupForward"
        const val STR_AWAY_LINEUP_SUBSTITUTES: String = "strAwayLineupSubstitutes"
    }
}