package com.celusion.fragmodel.viewmodel;

import android.app.Application;
import android.text.TextUtils;
import android.widget.Toast;

import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

public class LogViewModel extends AndroidBaseViewModel  {

    public static final ObservableField<String>userEmail = new ObservableField<>();
    public static final ObservableField<String>userpassword = new ObservableField<>();
    public ObservableBoolean isValid = new ObservableBoolean();
//    public MutableLiveData<Boolean> isLoginClicked = new MutableLiveData<>();
    private static boolean isEmailValid;
    private static boolean isPasswordValid;



    public void setUserEmail(String email) {
      userEmail.set(email);
    }

    @Bindable
    public String getUserEmail() {
        return userEmail.get();
    }

    @Bindable
    public String getUserPassword() {
        return userpassword.get();
    }

    public void setUserPassword(String password) {
      userpassword.set(password);
    }

    public LogViewModel(@NonNull Application application) {
        super(application);
        Observable.OnPropertyChangedCallback callback = new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (sender == userEmail  && sender==userpassword) {
                    isEmailValid = isValidEmail(userEmail.get());
                    isPasswordValid = isValidPassword(userpassword.get());
                }
                    if(!isEmailValid && !isPasswordValid) {
                        Toast.makeText(getApplication(),"Invalid Email or Password",Toast.LENGTH_LONG).show();
                    }
                    else {
                        isValid.set(isEmailValid && isPasswordValid);
                    }
            }
        };

        userEmail.addOnPropertyChangedCallback(callback);
        userpassword.addOnPropertyChangedCallback(callback);

    }

//    public void onLoginClicked(){
//        isLoginClicked.setValue(true);
//    }

    public static boolean isValidPassword(String value) {
        Pattern pattern = Pattern.compile("^[0-9a-zA-Z@$#%]{6,20}$");
        Boolean validation = pattern.matcher(value).matches();
        return validation;
    }

    public static boolean isValidEmail(String value) {
        // Pattern pattern = Pattern.compile("^([0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9})$");
        Pattern pattern = Pattern.compile("^(?!.{51})([A-Za-z0-9])+([A-Za-z0-9._-])+@([A-Za-z0-9._-])+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Boolean validation = pattern.matcher(value).matches();
        return isEmpty(value) && validation;
    }

    public static boolean isEmpty(String data) {
        if (TextUtils.isEmpty(data)) {
            return false;
        } else {
            String inputData = data.trim();
            if (inputData.equalsIgnoreCase("null")) {
                return false;
            } else if (inputData.equalsIgnoreCase("\"\"")) {
                return false;
            }
        }
        return true;
    }
}
