package com.example.employeedetails.ui.auth;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.employeedetails.data.model.Employee;

import java.util.List;


public class AuthViewModel extends ViewModel {

    MutableLiveData<String> userName = new MutableLiveData<>();
    MutableLiveData<String> password = new MutableLiveData<>();
    private AuthRepository authRepository = new AuthRepository();
    LiveData<Employee> loginResponse = Transformations.switchMap(userName, new Function<String, LiveData<Employee>>() {
        @Override
        public LiveData<Employee> apply(String input) {

            return authRepository.login(userName.getValue(), password.getValue());
        }
    });
    private LiveData<List<Employee>> mEmployee;

//    public AuthViewModel() {
//        authRepository = new AuthRepository();
//        mEmployee = authRepository.getAllEmployee();
//    }
//
//    public LiveData<Employee> getEmpDetails(long id) {
//        return authRepository.getEmpDetails(id);
//    }
//
//    public void updateDetails(Employee employee) {
//        authRepository.updateDetails(employee);
//    }

    public void login(String userName, String password) {
        this.password.setValue(password);
        this.userName.setValue(userName);
    }
}
