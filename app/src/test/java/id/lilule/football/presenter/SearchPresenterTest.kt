package id.lilule.football.presenter


import com.google.gson.Gson
import id.lilule.football.api.ApiRepository
import id.lilule.football.api.TheSportsDBApi
import id.lilule.football.mvp.search.SearchPresenter
import id.lilule.football.mvp.search.SearchActivityView
import id.lilule.football.mvp.model.*
import id.lilule.football.mvp.team.TeamPresenter
import id.lilule.football.mvp.team.TeamView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class SearchPresenterTest {

    private lateinit var presenter: SearchPresenter

    @Mock
    private lateinit var view: SearchActivityView

    @Mock
    lateinit var apiRepository: ApiRepository

    @Mock
    lateinit var sportsDBApi: TheSportsDBApi

    @Mock
    private lateinit var gson: Gson

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = SearchPresenter(view, apiRepository, gson)
    }

    @Test
    fun getSearchEvents() {
        val data: MutableList<Event> = mutableListOf()
        val response = SearchEventsResponse(data)

        doAsync {
            Mockito.`when`(
                gson.fromJson(
                    apiRepository.doRequest(sportsDBApi.getAllLeagues()), SearchEventsResponse::class.java
                )
            ).thenReturn(response)
            uiThread {
                presenter.getSearchEvents("Arsenal_vs_Chelsea")
                verify(view).showLoading()
                verify(view).showSearchEventResult(response.event)
                verify(view).hideLoading()
            }

        }
    }

    @Test
    fun getSearchTeams() {
        val data: MutableList<Team> = mutableListOf()
        val response = TeamResponse(data)

        doAsync {
            Mockito.`when`(
                gson.fromJson(
                    apiRepository.doRequest(sportsDBApi.getAllLeagues()), TeamResponse::class.java
                )
            ).thenReturn(response)
            uiThread {
                presenter.getSearchTeams("Arsenal")
                verify(view).showLoading()
                verify(view).showSearchTeamsResult(response.teams)
                verify(view).hideLoading()
            }

        }
    }
}