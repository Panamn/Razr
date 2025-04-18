package com.example.lb40;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import com.google.android.material.materialswitch.MaterialSwitch;
import com.google.android.material.textfield.TextInputLayout;

public class SaveActivity  extends AppCompatActivity {

    public static class Profile{
        public static final String PREFS_NAME = "ProfileData";

        public static final String KEY_USERNAME = "userName";
        public static final String DEF_USERNAME = "defaultUsername";
        public static final String KEY_EMAIL = "userEmail";
        public static final String DEF_EMAIL = "defaultEmail";
        public static final String KEY_PASSWORD = "userPassword";
        public static final String DEF_PASSWORD = "defaultPassword";
    }

    private static final String TOAST_SAVED = "Сохранено";
    private static final String TOAST_LOADED = "Загружено";
    private static final String TOAST_DELETED = "Удалено";

    SharedPreferences sharedPreferences;
    private TextInputLayout userName, userEmail, userPassword;
    private MaterialSwitch switchPrefs;
    private Button saveButton, loadButton, delButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.save_activity);

        userName = findViewById(R.id.nameTextLayout);
        userEmail = findViewById(R.id.emailTextLayout);
        userPassword = findViewById(R.id.passwordTextLayout);
        switchPrefs = findViewById(R.id.DataSwitch);

        initPreferences();

        switchPrefs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                initPreferences();
            }
        });

        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(view -> {
            if(validateInputTexts(userName, userEmail, userPassword)){
                saveData(
                        userName.getEditText().getText().toString(),
                        userEmail.getEditText().getText().toString(),
                        userPassword.getEditText().getText().toString()
                );
                clearFields(userName, userEmail, userPassword);
            }
        });
        loadButton = findViewById(R.id.loadButton);
        loadButton.setOnClickListener(view -> {
            loadData(userName, userEmail, userPassword);
        });
        delButton = findViewById(R.id.delButton);
        delButton.setOnClickListener(view -> {
            deleteData();
        });

    }

    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void clearFields(TextInputLayout... inputFields){
        for(TextInputLayout text : inputFields){
            if(text.getEditText() != null){
                text.getEditText().setText("");
            }
        }
    }

    private boolean validateInputTexts(TextInputLayout... texts){
        boolean isValued = true;

        for(TextInputLayout text: texts){
            if (text.getEditText() == null ||
                TextUtils.isEmpty(text.getEditText().getText().toString())){
                text.setError("Заполните поле");
                isValued = false;
            }else {
                text.setError(null);
            }
        }
        return isValued;
    }

    private void initPreferences(){
        try {
            if(switchPrefs.isChecked()){
                String masterKeyAlies = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
                sharedPreferences = EncryptedSharedPreferences.create(
                        Profile.PREFS_NAME,
                        masterKeyAlies,
                        this,
                        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                );

            }else{
                sharedPreferences = getSharedPreferences(Profile.PREFS_NAME, Context.MODE_PRIVATE);
            }
        }catch (Exception e){
            Log.e("MainActivity", "Error",e);
        }
    }
    private void saveData(String username, String email, String password){
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(Profile.KEY_USERNAME, username);
        editor.putString(Profile.KEY_EMAIL, email);
        editor.putString(Profile.KEY_PASSWORD, password);
        editor.apply();
        showToast(TOAST_SAVED);
    }
    private void loadData(TextInputLayout... texts){
        texts[0].getEditText().setText(sharedPreferences.getString(Profile.KEY_USERNAME, Profile.DEF_USERNAME));
        texts[1].getEditText().setText(sharedPreferences.getString(Profile.KEY_EMAIL, Profile.DEF_EMAIL));
        texts[2].getEditText().setText(sharedPreferences.getString(Profile.KEY_PASSWORD, Profile.DEF_PASSWORD));
        showToast(TOAST_LOADED);
    }
    private void deleteData(TextInputLayout... texts){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        showToast(TOAST_DELETED);
    }

}
