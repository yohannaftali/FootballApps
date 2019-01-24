package id.lilule.football

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import id.lilule.football.mvp.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testRecyclerViewBehaviour() {
        // check whether RecyclerView is displayed
        onView(withId(R.id.rv_events)).check(matches(isDisplayed()))
        Thread.sleep(5000)

        // simulate perform scroll
        onView(withId(R.id.rv_events)).perform(
            RecyclerViewActions.scrollToPosition<androidx.recyclerview.widget.RecyclerView.ViewHolder>(
                10
            )
        )
        Thread.sleep(5000)

        // simulate perform click
        onView(withId(R.id.rv_events)).perform(
            RecyclerViewActions.actionOnItemAtPosition<androidx.recyclerview.widget.RecyclerView.ViewHolder>(
                10,
                click()
            )
        )
        Thread.sleep(5000)
    }

    @Test
    fun testBottomNavigationViewBehaviour() {
        // check whether BottomNavigationView is displayed
        onView(withId(R.id.nv_main)).check(matches(isDisplayed()))
        Thread.sleep(5000)

        // simulate perform click on button Past Event
        onView(withId(R.id.bt_teams)).perform(click())
        Thread.sleep(5000)

        // simulate perform click on button Next Event
        onView(withId(R.id.bt_favorite)).perform(click())
        Thread.sleep(5000)

        // simulate perform click on button Favorite
        onView(withId(R.id.bt_matches)).perform(click())
        Thread.sleep(5000)
    }

    @Test
    fun testAddEventToFavoriteBehaviour() {
        // check whether SpinnerLayout is displayed
        onView(withId(R.id.sp_league)).check(matches(isDisplayed()))
        Thread.sleep(5000)

        // simulate perform click Spinner
        onView(withId(R.id.sp_league)).perform(click())

        // simulate perform click Spinner Item with Label Spanish La Liga
        onView(withText("Spanish La Liga")).perform(click())
        Thread.sleep(5000)

        // check whether RecyclerView Item with label Sociedad is displayed
        onView(withText("Sociedad")).check(matches(isDisplayed()))
        Thread.sleep(5000)

        // simulate perform click RecyclerView Item with Label Sociedad
        onView(withText("Sociedad")).perform(click())
        Thread.sleep(5000)

        // simulate perform press back
        pressBack()
        Thread.sleep(5000)

        // check whether BottomNavigationMain is displayed
        onView(withId(R.id.nv_main)).check(matches(isDisplayed()))
        Thread.sleep(5000)

        // simulate perform click button favorite on BottomNavigationView
        onView(withId(R.id.bt_favorite)).perform(click())
        Thread.sleep(5000)

        // Expected result Event Sociedad vs Espanol already added to Favorite
        // Before running make sure tha Event Sociedad not added to Favorite
    }

    @Test
    fun testBottomNavigationViewStressTest() {
        // check whether BottomNavigationView is displayed
        onView(withId(R.id.nv_main)).check(matches(isDisplayed()))

        for (i in 1..100) {
            onView(withId(R.id.bt_teams)).perform(click())
            onView(withId(R.id.bt_favorite)).perform(click())
            onView(withId(R.id.bt_matches)).perform(click())
        }
    }
}
