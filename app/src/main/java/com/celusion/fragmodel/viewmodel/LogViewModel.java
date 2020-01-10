package com.celusion.fragmodel.viewmodel;

import android.app.Application;
import android.text.TextUtils;

import com.celusion.fragmodel.model.User;

import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.PropertyChangeRegistry;
import androidx.lifecycle.ViewModel;

public class LogViewModel extends ViewModel implements Observable {
    private User user;
    private PropertyChangeRegistry thisCallback;

    public static final ObservableField<String>userEmail = new ObservableField<>();
    public static final ObservableField<String>userpassword = new ObservableField<>();
    public ObservableBoolean isValid = new ObservableBoolean();

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
        super();
        Observable.OnPropertyChangedCallback callback = new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (sender == userEmail && sender==userpassword) {
                    isEmailValid = isValidEmail(userEmail.get());
                    isPasswordValid=isValidPassword(userpassword.get());
                }
                isValid.set(isEmailValid);
                isValid.set(isPasswordValid);
            }
        };

        userEmail.addOnPropertyChangedCallback(callback);
        userpassword.addOnPropertyChangedCallback(callback);

    }




    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }

//    public void notifyPropertyChanged(int id) {
//        synchronized (this) {
//            if (thisCallback == null) {
//                return;
//            }
//        }
//        thisCallback.notifyCallbacks(this, id, null);
//    }

    public static boolean isValidPassword(String value) {
        Pattern pattern = Pattern.compile("^[0-9a-zA-Z@$#%]{6,20}$");
        Boolean validation = pattern.matcher(value).matches();
        return validation;
    }

    public static boolean isValidEmail(String value) {
        if (value == null)
            return false;
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
