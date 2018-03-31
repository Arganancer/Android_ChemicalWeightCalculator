package com.v41.tp1.viewcontroler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.v41.tp1.R;
import com.v41.tp1.modele.portal.ModelPortal;
import com.v41.tp1.viewcontroler.portal.ViewPortal;

import java.io.InputStream;

/**
 * This class represents both the functional and visual aspects of the interface
 * through which the user will interact with the program.
 */
public class View extends AppCompatActivity implements ViewPortal
{
    /**
     * The theme radioGroup.
     */
    RadioGroup radioGroupTheme = null;

    /**
     * The calculate button.
     */
    Button buttonCalculate = null;

    /**
     * The clear button.
     */
    Button buttonClear = null;

    /**
     * The formula editText. Will contain the formula entered by the user.
     */
    EditText editTextUserFormulaInput = null;

    /**
     * The confirmation textView. Will contain a variety of messages to the user, such
     * as aa welcome message, instructions, and messages detailing the results of
     * the validation step.
     */
    TextView textViewConfirmationMessage = null;

    /**
     * The webView. Will contain the formatted output from the user's validated formula.
     */
    WebView webViewFormattedResults = null;

    /**
     * The mimetype of a webpage. Used when loading data into the webView.
     */
    String mimeType = "text/html";

    /**
     * The encoding type of a webpage. Used when loading data into the webView.
     */
    String encoding = "utf-8";

    /**
     * Array of strings each containing a different message to be displayed to the user.
     * The first item in this Array starts blank, and is reserved for messages to the user
     * detailing the results of the validations done by the Controller.
     */
    protected String[] interfaceMessages;

    /**
     * String containing the content of the webView. Whenever the webView is updated, this string
     * is also updated.
     */
    protected String webViewContent;

    /**
     * This event is called when the app is first opened. In it we call all initialization sequences
     * for the app.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InputStream inputStream = this.getResources().openRawResource(R.raw.periodictable);
        Controller.INSTANCE.initialise(this, inputStream);

        radioGroupTheme = (RadioGroup)findViewById(R.id.radioGroup_theme);
        buttonCalculate = (Button)findViewById(R.id.button_calculate);
        buttonClear = (Button)findViewById(R.id.button_clean);
        editTextUserFormulaInput = (EditText)findViewById(R.id.editText_userFormulaInput);
        textViewConfirmationMessage = (TextView)findViewById(R.id.textView_confirmationMessage);
        webViewFormattedResults = (WebView)findViewById(R.id.webView_formattedResults);

        interfaceMessages = new String[4];
        interfaceMessages[0] = ""; // No message when the program starts.
        interfaceMessages[1] = "Welcome to the TP1 prototype program.";
        interfaceMessages[2] = "Please enter a chemical formula: ";
        interfaceMessages[3] = "Goodbye!";

        webViewContent = "";

        textViewConfirmationMessage.setText(interfaceMessages[1]);

        /**
         * Anonymous class to set a listener on the calculate button.
         */
        buttonCalculate.setOnClickListener(new android.view.View.OnClickListener()
        {
            /**
             * This method is called when the calculate button is pressed.
             * It is assumed that by pressing this button the user has finished typing his formula,
             * therefore the softkeyboard is hidden.
             *
             * @param view The main activity.
             */
            @Override
            public void onClick(android.view.View view)
            {
                webViewContent = "";
                webViewFormattedResults.loadUrl("about:blank");
                /**
                 * Keyboard hiding method taken from here:
                 *      @link https://stackoverflow.com/questions/1109022/close-hide-the-android-soft-keyboard
                 */
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);

                interfaceMessages[0] = Controller.INSTANCE.processUserInput(editTextUserFormulaInput.getText().toString());
                textViewConfirmationMessage.setText(interfaceMessages[0]);
            }
        });

        /**
         * Anonymous class to set a listener on the clear button.
         */
        buttonClear.setOnClickListener(new android.view.View.OnClickListener()
        {
            /**
             * This method is called when the clear button is pressed.
             * Clears all information from the main activity (essentially resets the app to its default state).
             *
             * @param view The main activity.
             */
            @Override
            public void onClick(android.view.View view)
            {
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                editTextUserFormulaInput.setText("");
                webViewContent = "";
                webViewFormattedResults.loadUrl("about:blank");
                textViewConfirmationMessage.setText(interfaceMessages[2]);
            }
        });

        /**
         * Anonymous class to set a listener on the formula editText.
         */
        editTextUserFormulaInput.setOnClickListener(new android.view.View.OnClickListener()
        {
            /**
             * This method is called when the editText widget is pressed.
             *
             * @param view The main activity.
             */
            @Override
            public void onClick(android.view.View view)
            {
                if(textViewConfirmationMessage.getText().toString().equals(interfaceMessages[1]))
                {
                    textViewConfirmationMessage.setText(interfaceMessages[2]);
                }
            }
        });
        /**
         * Anonymous class to set a listener on the theme radioGroup.
         */
        radioGroupTheme.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            /**
             * This method is called when the radioButton is changed.
             *
             * @param radioGroup The theme radioGroup.
             * @param themeId The id of the currently selected radioButton.
             */
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int themeId)
            {
                RadioButton radioButton = findViewById(themeId);
                int index = radioGroup.indexOfChild(radioButton);
                Controller.INSTANCE.changeTheme(index);

            }
        });
    }

    /**
     * This method is called when the is about the close or restart.
     *
     * @param savedInstanceState The bundle that will contain the information that needs to be stored.
     */
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("savedEditText", editTextUserFormulaInput.getText().toString());
    }

    /**
     * This method is called when the app is restored.
     *
     * @param savedInstanceState The bundle containing the information to restore.
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        webViewFormattedResults.loadUrl("about:blank");

        editTextUserFormulaInput.setText(savedInstanceState.getString("savedEditText"));
        interfaceMessages[0] = Controller.INSTANCE.processUserInput(editTextUserFormulaInput.getText().toString());
        textViewConfirmationMessage.setText(interfaceMessages[0]);
    }

    /**
     * This method is called by the model to inform the view that the result of the chemical
     * formula calculation is complete. When called, the view requests the information
     * from the model, then prints those results to the console.
     *
     * @param modelPortal This interface allows the view to request the results of the chemical
     *                    calculation from the model.
     *      @see ModelPortal
     */
    @Override
    public void notify(ModelPortal modelPortal)
    {
        webViewFormattedResults.loadUrl("about:blank");
        webViewContent = modelPortal.getInformationAboutChemicalComposition();
        webViewFormattedResults.loadData(
                webViewContent,
                mimeType,
                encoding);
    }
}
