package com.example.streetlamp.tp1;

import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;

import com.v41.tp1.R;
import com.v41.tp1.viewcontroler.View;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class interfaceTests
{
    private UiDevice device;

    /**
     * This is to restart the activity before every test method.
     */
    @Rule
    public ActivityTestRule<View> view = new ActivityTestRule<>(View.class, true, true);

    @Test
    public void testValidFormula() throws Exception
    {
        onView(withId(R.id.editText_userFormulaInput)).perform(typeText("H2O"));

        onView(withId(R.id.button_calculate)).perform(click());

        onView(withId(R.id.textView_confirmationMessage)).check(matches(withText("The chemical formula is valid.")));

        onView(withId(R.id.editText_userFormulaInput)).check(matches(withText("H2O")));
    }

    @Test
    public void testInvalidFormulaNull() throws Exception
    {
        onView(withId(R.id.editText_userFormulaInput)).perform(typeText(""));

        onView(withId(R.id.button_calculate)).perform(click());

        onView(withId(R.id.textView_confirmationMessage)).check(matches(withText("Erreur: formule de longueur nulle.")));
    }

    @Test
    public void testInvalidFormulaStartWithNb() throws Exception
    {
        onView(withId(R.id.editText_userFormulaInput)).perform(typeText("2HCBO"));

        onView(withId(R.id.button_calculate)).perform(click());

        onView(withId(R.id.textView_confirmationMessage)).check(matches(withText("Erreur: la formule commence par un chiffre.")));
    }

    @Test
    public void testInvalidFormulaNotAnAtom() throws Exception
    {
        onView(withId(R.id.editText_userFormulaInput)).perform(typeText("Hh"));

        onView(withId(R.id.button_calculate)).perform(click());

        onView(withId(R.id.textView_confirmationMessage)).check(matches(withText("Erreur: ceci n'est pas un atome existant.")));
    }

    @Test
    public void testInvalidFormulaMisplacedMultiplier() throws Exception
    {
        onView(withId(R.id.editText_userFormulaInput)).perform(typeText("HZn(2H)4"));

        onView(withId(R.id.button_calculate)).perform(click());

        onView(withId(R.id.textView_confirmationMessage)).check(matches(withText("Erreur: un multiplicateur doit suivre un élément ou une parenthèse fermante.")));
    }

    @Test
    public void testInvalidFormulaMultiplierTooSmall() throws Exception
    {
        onView(withId(R.id.editText_userFormulaInput)).perform(typeText("HZn(H)1"));

        onView(withId(R.id.button_calculate)).perform(click());

        onView(withId(R.id.textView_confirmationMessage)).check(matches(withText("Erreur: Un multiplicateur doit être au moins de 2.")));
    }

    @Test
    public void testInvalidFormulaExtraZeros() throws Exception
    {
        onView(withId(R.id.editText_userFormulaInput)).perform(typeText("OB02"));

        onView(withId(R.id.button_calculate)).perform(click());

        onView(withId(R.id.textView_confirmationMessage)).check(matches(withText("Erreur: les zéros en trop ne sont pas permis devant un nombre.")));
    }

    @Test
    public void testInvalidFormulaEmptyBrackets() throws Exception
    {
        onView(withId(R.id.editText_userFormulaInput)).perform(typeText("BeSi(Bi())4"));

        onView(withId(R.id.button_calculate)).perform(click());

        onView(withId(R.id.textView_confirmationMessage)).check(matches(withText("Erreur: les parenthèses vides sont interdites.")));
    }

    @Test
    public void testInvalidFormulaMissingOpeningBracket() throws Exception
    {
        onView(withId(R.id.editText_userFormulaInput)).perform(typeText("(Mo2Mn4)5Rh2)2"));

        onView(withId(R.id.button_calculate)).perform(click());

        onView(withId(R.id.textView_confirmationMessage)).check(matches(withText("Erreur: Parenthèse fermante sans parenthèse ouvrante.")));
    }

    @Test
    public void testInvalidFormulaMissingClosingBracket() throws Exception
    {
        onView(withId(R.id.editText_userFormulaInput)).perform(typeText("((HTc7Os3)4PbSn2"));

        onView(withId(R.id.button_calculate)).perform(click());

        onView(withId(R.id.textView_confirmationMessage)).check(matches(withText("Erreur: Parenthèse ouvrante sans parenthèse fermante.")));
    }

    @Test
    public void testInvalidFormulInvalidCharacter() throws Exception
    {
        onView(withId(R.id.editText_userFormulaInput)).perform(typeText("ReOs3(Ir2Pt3)4Pdh"));

        onView(withId(R.id.button_calculate)).perform(click());

        onView(withId(R.id.textView_confirmationMessage)).check(matches(withText("Erreur: caractère invalide (y compris une minuscule qui ne suit pas une majuscule).")));
    }

    @Test
    public void testInvalidFormulaMultiplierTooBig() throws Exception
    {
        onView(withId(R.id.editText_userFormulaInput)).perform(typeText("Ta123456789123456"));

        onView(withId(R.id.button_calculate)).perform(click());

        onView(withId(R.id.textView_confirmationMessage)).check(matches(withText("Erreur: Le multiplicateur est trop gros.")));
    }

    @Test
    public void testButtonClear() throws Exception
    {
        onView(withId(R.id.editText_userFormulaInput)).perform(typeText("H2O"));

        onView(withId(R.id.button_calculate)).perform(click());

        onView(withId(R.id.button_clean)).perform(click());

        onView(withId(R.id.textView_confirmationMessage)).check(matches(withText("Please enter a chemical formula: ")));

        onView(withId(R.id.editText_userFormulaInput)).check(matches(withText("")));
    }

    @Test
    public void testEditTextClick() throws Exception
    {
        onView(withId(R.id.textView_confirmationMessage)).check(matches(withText("Welcome to the TP1 prototype program.")));

        onView(withId(R.id.editText_userFormulaInput)).perform(click());

        onView(withId(R.id.editText_userFormulaInput)).perform(typeText("H"));

        onView(withId(R.id.textView_confirmationMessage)).check(matches(withText("Please enter a chemical formula: ")));
    }

    @Test
    public void testAcitivtyRotationSavedData() throws Exception
    {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        onView(withId(R.id.editText_userFormulaInput)).perform(typeText("H2O"));

        onView(withId(R.id.button_calculate)).perform(click());

        onView(withId(R.id.textView_confirmationMessage)).check(matches(withText("The chemical formula is valid.")));

        onView(withId(R.id.editText_userFormulaInput)).check(matches(withText("H2O")));

        device.setOrientationLeft();

        Thread.sleep(100);

        onView(withId(R.id.textView_confirmationMessage)).check(matches(withText("The chemical formula is valid.")));

        onView(withId(R.id.editText_userFormulaInput)).check(matches(withText("H2O")));

        device.setOrientationNatural();

        Thread.sleep(100);

        onView(withId(R.id.textView_confirmationMessage)).check(matches(withText("The chemical formula is valid.")));

        onView(withId(R.id.editText_userFormulaInput)).check(matches(withText("H2O")));
    }
}
