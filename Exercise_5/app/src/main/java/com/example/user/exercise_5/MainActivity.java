package com.example.user.exercise_5;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Set;

public class MainActivity extends Activity {

    public static final String USER_NAME = "userName";
    public static final String PREFS = "prefs";
    public static final String PASSWORD = "password";
    public static final String USERS = "users";
    private EditText txtUserName, txtPassword;
    private Users users;
    private  User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUserName = findViewById(R.id.txtUserName);
        txtPassword = findViewById(R.id.txtPassword);
        users = Users.getUsers();

        SharedPreferences prefs = prefs = getSharedPreferences(PREFS, MODE_PRIVATE);
        if (prefs.contains(USERS)){
            Set<String> userASString = prefs.getStringSet(USERS, null);
            users.loadUsers(userASString);
        }
        if (prefs.contains(USER_NAME)){
            user = new User(prefs.getString(USER_NAME,null), prefs.getString(PASSWORD, null));
            txtUserName.setText(user.getUserName());
            txtPassword.setText(user.getPassword());
            loginOrSingUp(true);
        }
    }

    public void btnLoginClicked(View view) {
        loginOrSingUp(true);
    }

    public void btnSignUpClicked(View view) {
        loginOrSingUp(false);
    }

    private void loginOrSingUp(boolean isLogin){
        user = getUserFromUI();
        if (user == null)
            return;
        String message = null;
        if (isLogin){
            if (!users.login(user)){
                message = "user name and password wrong";
            }
        }else {
            if (!users.signUp(user)){
                message = "user name is exist";
            }
        }
        if (message != null){
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            return;
        }
        SharedPreferences pref = getSharedPreferences(PREFS, MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString(USER_NAME, user.getUserName()).putString(PASSWORD, user.getPassword());
        if (!isLogin){
            Set<String> usersAsString = users.getUsersAsString();
            edit.putStringSet(USERS, usersAsString);

        }
        edit.commit();
        goToSecondActivity();
    }

    private void goToSecondActivity(){
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(USER_NAME, user.getUserName());
        startActivity(intent);
        finish();
    }
    private User getUserFromUI(){
        String userName = txtUserName.getText().toString();
        String password = txtPassword.getText().toString();
        if (userName.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "you must write something", Toast.LENGTH_SHORT).show();
            return null;
        }
        return new User(userName,password);
    }
}
