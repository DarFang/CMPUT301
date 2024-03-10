package com.example.simpleparadox.listycity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.app.Activity;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class MainActivityTest {
    private Solo solo;
    @Rule

    public ActivityTestRule<MainActivity> rule =
            new ActivityTestRule<>(MainActivity.class, true, true);

    @Before
    public void setUp() throws Exception{
        solo = new Solo(InstrumentationRegistry.getInstrumentation(), rule.getActivity());
    }
    @Test
    public void start () throws Exception{
        Activity activity = rule.getActivity();
    }
    @Test
    public void checkList(){
        solo.assertCurrentActivity("Wrong Activity",MainActivity.class);
        solo.clickOnButton("ADD CITY");
        solo.enterText((EditText)solo.getView(R.id.editText_name), "Edmonton");
        solo.clickOnButton("CONFIRM");
        solo.clearEditText((EditText)solo.getView(R.id.editText_name));
        assertTrue(solo.waitForText("Edmonton", 1, 2000));
        solo.clickOnButton("CLEAR ALL");
        assertFalse(solo.waitForText("Edmonton"));
    }

    @Test
    public void checkCityListItem(){
        solo.assertCurrentActivity("Wrong Activity",MainActivity.class);
        solo.clickOnButton("ADD CITY");
        solo.enterText((EditText)solo.getView(R.id.editText_name), "Edmonton");
        solo.clickOnButton("CONFIRM");
        solo.clearEditText((EditText)solo.getView(R.id.editText_name));
        assertTrue(solo.waitForText("Edmonton", 1, 2000));

        MainActivity activity = (MainActivity) solo.getCurrentActivity();
        final ListView cityList = activity.cityList;
        String city = (String) cityList.getItemAtPosition(0);
        assertEquals("Edmonton", city);


    }
    @Test
    public void checkSelectedCity(){
        solo.assertCurrentActivity("Wrong Activity",MainActivity.class);
        solo.clickOnButton("ADD CITY");
        solo.enterText((EditText)solo.getView(R.id.editText_name), "Edmonton");
        solo.clickOnButton("CONFIRM");
        solo.clickOnText("Edmonton");
        solo.assertCurrentActivity("Wrong Activity",showActivity.class);
        assertTrue(solo.waitForText("Edmonton", 1, 2000));
        solo.clickOnButton("Back");
        solo.assertCurrentActivity("Wrong Activity",MainActivity.class);
    }
    @After
    public void tearDown(){
        solo.finishOpenedActivities();
    }
}
