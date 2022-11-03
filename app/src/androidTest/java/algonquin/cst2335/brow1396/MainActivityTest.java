package algonquin.cst2335.brow1396;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    /**
     *This function verifies the app detects when a password doesn't meet
     *the requirements when a user enters only digits
     */
    @Test
    public void mainActivityTest() {

        ViewInteraction appCompatEditText = onView(withId(R.id.editText));
        appCompatEditText.perform(replaceText("12345"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(withId(R.id.button));
        materialButton.perform(click());

        ViewInteraction textView = onView( withId(R.id.textView) );  textView.check(matches(withText("You shall not pass!")));
    }

    /**
     *This function verifies the app detects when a password doesn't meet
     *the requirements when the password entered is missing an uppercase letter
     */
    @Test
    public void testFindMissingUpperCase() {

        //find view
        ViewInteraction appCompatEditText = onView(withId(R.id.editText));
        //type in password1234#$*
        appCompatEditText.perform(replaceText("password1234#$*"));

        //find the button
        ViewInteraction materialButton = onView(withId(R.id.button) );
        //click the button
        materialButton.perform(click());

        //find the text view
        ViewInteraction textView = onView(withId(R.id.textView));
        //check the text view
        textView.check(matches(withText("You shall not pass!")));
    }

    /**
     *This function verifies the app detects when a password doesn't meet
     *the requirements when the password entered is missing a lowercase letter
     */
    @Test
    public void testFindMissingLowerCase(){

        //find view
        ViewInteraction appCompatEditText = onView(withId(R.id.editText));
        //type in password1234#$*
        appCompatEditText.perform(replaceText("PASSWORD1234#$*"));

        //find the button
        ViewInteraction materialButton = onView(withId(R.id.button) );
        //click the button
        materialButton.perform(click());

        //find the text view
        ViewInteraction textView = onView(withId(R.id.textView));
        //check the text view
        textView.check(matches(withText("You shall not pass!")));
    }

    /**
     *This function verifies the app detects when a password doesn't meet
     *the requirements when the password entered is missing a digit
     */
    @Test
    public void testFindMissingDigit(){

        //find view
        ViewInteraction appCompatEditText = onView(withId(R.id.editText));
        //type in password1234#$*
        appCompatEditText.perform(replaceText("Password#$*"));

        //find the button
        ViewInteraction materialButton = onView(withId(R.id.button) );
        //click the button
        materialButton.perform(click());

        //find the text view
        ViewInteraction textView = onView(withId(R.id.textView));
        //check the text view
        textView.check(matches(withText("You shall not pass!")));
    }

    /**
     *This function verifies the app detects when a password doesn't meet
     *the requirements when the password entered is missing a special character
     */
    @Test
    public void testFindMissingSpecialChar(){

        //find view
        ViewInteraction appCompatEditText = onView(withId(R.id.editText));
        //type in password1234#$*
        appCompatEditText.perform(replaceText("Password123"));

        //find the button
        ViewInteraction materialButton = onView(withId(R.id.button) );
        //click the button
        materialButton.perform(click());

        //find the text view
        ViewInteraction textView = onView(withId(R.id.textView));
        //check the text view
        textView.check(matches(withText("You shall not pass!")));
    }

    /**
     *This function verifies the app detects when a password meet all
     *the requirements when the password entered meets all the requirements
     */
    @Test
    public void testMeetsAllRequirements(){

        //find view
        ViewInteraction appCompatEditText = onView(withId(R.id.editText));
        //type in password1234#$*
        appCompatEditText.perform(replaceText("Password123#$*"));

        //find the button
        ViewInteraction materialButton = onView(withId(R.id.button) );
        //click the button
        materialButton.perform(click());

        //find the text view
        ViewInteraction textView = onView(withId(R.id.textView));
        //check the text view
        textView.check(matches(withText("Your password is complex enough")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
