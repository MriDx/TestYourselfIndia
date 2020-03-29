package com.mridx.testyourselfindia.helper;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.core.app.ActivityOptionsCompat;

import com.google.android.material.radiobutton.MaterialRadioButton;
import com.google.android.material.textfield.TextInputLayout;
import com.mridx.testyourselfindia.R;
import com.mridx.testyourselfindia.activity.MainUI;
import com.mridx.testyourselfindia.activity.QuestionUI;
import com.mridx.testyourselfindia.dataholder.PatientDetails;

import java.util.Locale;

public class MainUIHelper {

    private Context context;
    private MainUI mainUi;
    private TextInputLayout textInputLayout;
    private RadioGroup radioGroup;
    private int language_code = 0;
    public static PatientDetails patientDetails;

    public MainUIHelper(Context context) {
        this.context = context;
        mainUi = ((MainUI) context);
    }


    public void assignListener() {
        mainUi.proceedBtn.setOnClickListener(v -> proceed());
        mainUi.nameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (textInputLayout != null) {
                    textInputLayout.setError(null);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                checkForButton();
            }
        });
        mainUi.phoneField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (textInputLayout != null) {
                    textInputLayout.setError(null);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                checkForButton();
            }
        });
        mainUi.pinField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (textInputLayout != null) {
                    textInputLayout.setError(null);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                checkForButton();
                if (s.toString().length() == 6) {
                    hideKeyboard(mainUi.pinField);
                }
            }
        });
        mainUi.placeGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (radioGroup != null) {
                ((MaterialRadioButton) radioGroup.getChildAt(0)).setError(null);
            }
            checkForButton();
        });
        mainUi.languageGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == mainUi.languageGroup.getChildAt(0).getId()) {
                this.language_code = 0;
                //((MaterialRadioButton)mainUi.languageGroup.getChildAt(1)).setChecked(false);
                //((MaterialRadioButton)mainUi.languageGroup.getChildAt(0)).setChecked(true);
                return;
            }
            this.language_code = 1;
            //((MaterialRadioButton)mainUi.languageGroup.getChildAt(1)).setChecked(true);
            //((MaterialRadioButton)mainUi.languageGroup.getChildAt(0)).setChecked(false);
        });
        ((MaterialRadioButton) mainUi.languageGroup.getChildAt(0)).setChecked(true);
    }

    private void hideKeyboard(View v) {
        try {
            InputMethodManager imm = (InputMethodManager) mainUi.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        } catch (Exception ignored) {
        }
    }

    private void proceed() {
        if (valid()) {
            //Toast.makeText(context, "Proceed", Toast.LENGTH_SHORT).show();
            //startQuestions();
            setupLocale(language_code);
        }
    }

    private void startQuestions(String locale_name) {
        setLocale(locale_name);
        //Bundle bundle = generatePatientDetails();
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(mainUi, ((TextInputLayout) mainUi.nameField.getParent().getParent()), "patientName");
        Intent intent = new Intent(context, QuestionUI.class);
        //intent.putExtra("LANGUAGE_CODE", language_code);
        intent.putExtras(generatePatientDetails());
        mainUi.startActivity(intent, optionsCompat.toBundle());
    }

    private Bundle generatePatientDetails() {
        patientDetails = new PatientDetails(getName(), getPhone(), getPin(), getPlace(), getLanguage());
        Bundle bundle = new Bundle();
        bundle.putSerializable("PATIENT_DETAILS", patientDetails);
        return bundle;
    }

    private void setupLocale(int language_code) {
        switch (language_code) {
            case 0:
                //setLocale("en");
                startQuestions("en");
                break;
            case 1:
                //setLocale("as");
                startQuestions("as");
                break;
        }
    }

    private void setLocale(String locale_name) {
        Locale locale = new Locale(locale_name);
        Resources resources = mainUi.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        resources.updateConfiguration(configuration, displayMetrics);
        /*Intent refresh = new Intent(this, QuestionUI.class);
        startActivity(refresh);*/
    }

    private void checkForButton() {
        if (validForBtn()) {
            mainUi.proceedBtn.setCardBackgroundColor(mainUi.getResources().getColor(R.color.gray));
            //mainUi.proceedBtn.setEnabled(true);
            mainUi.proceedBtn.setAlpha(1);
            return;
        }
        mainUi.proceedBtn.setCardBackgroundColor(mainUi.getResources().getColor(R.color.disabled));
        //mainUi.proceedBtn.setEnabled(false);
        mainUi.proceedBtn.setAlpha(0.3f);
    }

    private boolean validForBtn() {
        if (getName().length() == 0) {
            return false;
        } else if (getPhone().length() == 0 || getPhone().length() <= 9) {
            return false;
        } else if (getPin().length() == 0 || getPin().length() <= 5) {
            return false;
        } else if (getPlace().length() == 0) {
            return false;
        } else {
            return true;
        }
    }

    private boolean valid() {
        if (getName().length() == 0) {
            textInputLayout = ((TextInputLayout) mainUi.nameField.getParent().getParent());
            textInputLayout.setError("Enter Full Name");
            textInputLayout.requestFocus();
            return false;
        } else if (getPhone().length() == 0 || getPhone().length() <= 9) {
            textInputLayout = ((TextInputLayout) mainUi.phoneField.getParent().getParent());
            textInputLayout.setError("Phone number must be 10 digit");
            textInputLayout.requestFocus();
            return false;
        } else if (getPin().length() == 0 || getPin().length() <= 5) {
            textInputLayout = ((TextInputLayout) mainUi.pinField.getParent().getParent());
            textInputLayout.setError("Pincode must be 6 digit");
            textInputLayout.requestFocus();
            return false;
        } else if (getPlace().length() == 0) {
            radioGroup = mainUi.placeGroup;
            ((MaterialRadioButton) radioGroup.getChildAt(0)).setError("Select an option");
            radioGroup.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private String getName() {
        return mainUi.nameField.getText().toString().trim();
    }

    private String getPhone() {
        return mainUi.phoneField.getText().toString().trim();
    }

    private String getPin() {
        return mainUi.pinField.getText().toString().trim();
    }

    private String getPlace() {
        if (mainUi.placeGroup.getCheckedRadioButtonId() != -1) {
            return ((MaterialRadioButton) mainUi.findViewById(mainUi.placeGroup.getCheckedRadioButtonId())).getText().toString().trim();
        }
        return "";
    }

    private String getLanguage() {
        if (mainUi.languageGroup.getCheckedRadioButtonId() != -1) {
            return ((MaterialRadioButton) mainUi.findViewById(mainUi.languageGroup.getCheckedRadioButtonId())).getText().toString().trim();
        }
        return "";
    }
}
