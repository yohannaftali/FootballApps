package id.lilule.football.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import id.lilule.football.mvp.model.Event
import id.lilule.football.mvp.model.Team
import id.lilule.football.util.Constants.DATABASE_FAVORITE
import id.lilule.football.util.Constants.TABLE_FAVORITE_EVENTS
import id.lilule.football.util.Constants.TABLE_FAVORITE_TEAMS
import org.jetbrains.anko.db.*

class DatabaseOpenHelper(context: Context) : ManagedSQLiteOpenHelper(context, DATABASE_FAVORITE, null, 1) {

    companion object {
        private var instance: DatabaseOpenHelper? = null
        @Synchronized
        fun getInstance(context: Context): DatabaseOpenHelper {
            if (instance == null) {
                instance = DatabaseOpenHelper(context.applicationContext)
            }

            return instance as DatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        createTableEvents(db)
        createTableTeams(db)
    }

    private fun createTableEvents(db: SQLiteDatabase?) {
        db?.createTable(
            TABLE_FAVORITE_EVENTS, true,
            Event.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,

            Event.ID_EVENT to TEXT + UNIQUE,
            Event.STR_EVENT to TEXT,
            Event.STR_SPORT to TEXT,
            Event.ID_LEAGUE to TEXT,
            Event.STR_LEAGUE to TEXT,
            Event.DATE_EVENT to TEXT,
            Event.STR_DATE to TEXT,
            Event.STR_TIME to TEXT,

            Event.ID_HOME_TEAM to TEXT,
            Event.STR_HOME_TEAM to TEXT,
            Event.INT_HOME_SCORE to TEXT,
            Event.STR_HOME_FORMATION to TEXT,
            Event.STR_HOME_GOAL_DETAILS to TEXT,
            Event.INT_HOME_SHOTS to TEXT,
            Event.STR_HOME_RED_CARDS to TEXT,
            Event.STR_HOME_YELLOW_CARDS to TEXT,

            Event.STR_HOME_LINEUP_GOALKEEPER to TEXT,
            Event.STR_HOME_LINEUP_DEFENSE to TEXT,
            Event.STR_HOME_LINEUP_MIDFIELD to TEXT,
            Event.STR_HOME_LINEUP_FORWARD to TEXT,
            Event.STR_HOME_LINEUP_SUBSTITUTES to TEXT,

            Event.ID_AWAY_TEAM to TEXT,
            Event.STR_AWAY_TEAM to TEXT,
            Event.INT_AWAY_SCORE to TEXT,
            Event.STR_AWAY_FORMATION to TEXT,
            Event.STR_AWAY_GOAL_DETAILS to TEXT,
            Event.INT_AWAY_SHOTS to TEXT,
            Event.STR_AWAY_RED_CARDS to TEXT,
            Event.STR_AWAY_YELLOW_CARDS to TEXT,

            Event.STR_AWAY_LINEUP_GOALKEEPER to TEXT,
            Event.STR_AWAY_LINEUP_DEFENSE to TEXT,
            Event.STR_AWAY_LINEUP_MIDFIELD to TEXT,
            Event.STR_AWAY_LINEUP_FORWARD to TEXT,
            Event.STR_AWAY_LINEUP_SUBSTITUTES to TEXT
        )
    }

    private fun createTableTeams(db: SQLiteDatabase?) {
        db?.createTable(
            TABLE_FAVORITE_TEAMS, true,
            Team.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,

            Team.ID_TEAM to TEXT + UNIQUE,
            Team.STR_TEAM to TEXT,
            Team.STR_STADIUM to TEXT,
            Team.INT_FORMED_YEAR to TEXT,
            Team.STR_DESCRIPTION_EN to TEXT,
            Team.STR_TEAM_BADGE to TEXT,
            Team.STR_LEAGUE to TEXT,
            Team.STR_ALTERNATE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(TABLE_FAVORITE_EVENTS, true)
        db?.dropTable(TABLE_FAVORITE_TEAMS, true)
    }
}

val Context.database: DatabaseOpenHelper
    get() = DatabaseOpenHelper.getInstance(applicationContext)