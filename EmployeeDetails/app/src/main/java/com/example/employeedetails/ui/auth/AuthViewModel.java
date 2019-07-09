package com.example.employeedetails.ui.auth;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.employeedetails.data.response.Employee;

import java.util.List;


public class AuthViewModel extends ViewModel {

    private AuthRepository authRepository;

    private LiveData<List<Employee>> mEmployee;

    public AuthViewModel() {
        authRepository = new AuthRepository();
        mEmployee = authRepository.getAllEmployee();
    }

    public Employee getEmpDetails(String username) {
        return authRepository.getEmpDetails(username);
    }

    public void updateDetails(Employee employee) {
        authRepository.updateDetails(employee);
    }

    public void login(String userName, String password) {
//        authRepository.login(userName, password);
        this.userName.setValue(userName);
        this.password.setValue(password);
    }

    MutableLiveData<String> userName = new MutableLiveData<>();
    MutableLiveData<String> password = new MutableLiveData<>();

    LiveData<Boolean> loginResponse = Transformations.switchMap(userName, new Function<String, LiveData<Boolean>>() {
        @Override
        public LiveData<Boolean> apply(String input) {

            return authRepository.login(userName.getValue(), password.getValue());
        }
    });
}
