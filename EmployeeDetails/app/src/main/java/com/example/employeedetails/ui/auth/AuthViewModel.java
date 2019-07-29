package com.example.employeedetails.ui.auth;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.employeedetails.data.response.Employee;

import java.util.List;


public class AuthViewModel extends ViewModel {

    MutableLiveData<String> userName = new MutableLiveData<>();
    MutableLiveData<String> password = new MutableLiveData<>();
    private AuthRepository authRepository;
    LiveData<Boolean> loginResponse = Transformations.switchMap(userName, new Function<String, LiveData<Boolean>>() {
        @Override
        public LiveData<Boolean> apply(String input) {

            return authRepository.login(userName.getValue(), password.getValue());
        }
    });
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
        this.userName.setValue(userName);
        this.password.setValue(password);
    }

    MutableLiveData<Employee> employee = new MutableLiveData<>();
    LiveData<Employee> registerResponse = Transformations.switchMap(employee, new Function<Employee, LiveData<Employee>>() {
        @Override
        public LiveData<Employee> apply(Employee input) {
            return authRepository.register(input);
        }
    });

    public void register(Employee employee) {
        this.employee.setValue(employee);
    }

}
