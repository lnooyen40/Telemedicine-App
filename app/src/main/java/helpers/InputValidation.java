package helpers;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class InputValidation {
    private Context context;
    private final String TAG = "INPUTVALIDATION";

    public InputValidation(Context context)
    {
        this.context = context;
    }

    public boolean isInputEditTextFilled(EditText inputs,  String message)
    {
        String value = inputs.getText().toString().trim();

        if (value.isEmpty())
        {
            Toast toast = Toast.makeText(context, "You have an empty input text!", Toast.LENGTH_LONG);
            hideKeyboardFrom(inputs);
            return false;
        }else
        {
            Log.e(TAG, "THERE WAS NO INPUT DETECTED ON LOGIN");
        }
        return true;
    }

    // Checks to see if EditText email input is in email format
    public boolean isInputTextAnEmail(EditText inputs, String string)
    {
        String value = inputs.getText().toString().trim();

        if (value.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(value).matches())
        {
            Toast toast = Toast.makeText(context, "THIS IS NOT A VALID EMAIL", Toast.LENGTH_LONG);
            hideKeyboardFrom(inputs);
            return false;
        }else
        {
            Log.e(TAG, "THERE WAS AN ERROR WITH EMAIL.");
        }
        return true;
    }

    public boolean isInputEditTextMatch(EditText input1, EditText input2, String message)
    {
        String value1 = input1.getText().toString().trim();
        String value2 = input2.getText().toString().trim();

        if (!value1.contentEquals(value2))
        {
            Log.e(TAG, message);
            hideKeyboardFrom(input2);
            return false;
        }
        return true;
    }

    private void hideKeyboardFrom(View view)
    {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
}
