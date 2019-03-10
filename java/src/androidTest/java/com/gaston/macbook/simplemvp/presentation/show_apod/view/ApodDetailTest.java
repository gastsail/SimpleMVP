package com.gaston.macbook.simplemvp.presentation.show_apod.view;

import com.gaston.macbook.simplemvp.R;
import com.gaston.macbook.simplemvp.presentation.MainOptions;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest
public class ApodDetailTest {

    @Rule
    public ActivityTestRule<MainOptions> mNotesActivityTestRule =
            new ActivityTestRule<>(MainOptions.class);

    @Test
    public void clickISSImageView_opensYoutubePlayer() throws Exception {
        onView(withId(R.id.imageview_mainoptions_iss)).perform(click());
        onView(withId(R.id.isslive_youtube_player)).check(matches(isDisplayed()));
    }

}