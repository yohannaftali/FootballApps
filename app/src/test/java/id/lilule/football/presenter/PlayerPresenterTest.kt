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

class PlayerPresenterTest {

    private lateinit var presenter: TeamPresenter

    @Mock
    private lateinit var view: TeamView

    @Mock
    lateinit var apiRepository: ApiRepository

    @Mock
    lateinit var sportsDBApi: TheSportsDBApi

    @Mock
    private lateinit var gson: Gson

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = TeamPresenter(view, apiRepository, gson)
    }

    @Test
    fun posGetPlayers() {
        val data: MutableList<Player> = mutableListOf()
        val response = PlayerResponse(data)

        doAsync {
            Mockito.`when`(
                gson.fromJson(
                    apiRepository.doRequest(sportsDBApi.getAllLeagues()), PlayerResponse::class.java
                )
            ).thenReturn(response)
            uiThread {
                presenter.getPlayers("133604")
                verify(view).showLoading()
                verify(view).showPlayer(response)
                verify(view).hideLoading()
            }

        }
    }
}