package com.example.employeedetails.ui.employee;

import android.text.TextUtils;
import android.util.Log;

import androidx.arch.core.util.Function;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.employeedetails.data.model.Employee;
import com.example.employeedetails.data.model.Task;

import java.util.List;

public class AddEmployeeViewModel extends ViewModel {

    public ObservableField<String> firstName = new ObservableField<String>();
    public ObservableField<String> firstNameError = new ObservableField<String>();
    public ObservableField<String> lastName = new ObservableField<String>();
    public ObservableField<String> lastNameError = new ObservableField<String>();
    public ObservableField<String> userName = new ObservableField<String>();
    public ObservableField<String> userNameError = new ObservableField<String>();
    public ObservableField<String> password = new ObservableField<String>();
    public ObservableField<String> passwordError = new ObservableField<String>();
    public ObservableField<String> company = new ObservableField<String>();
    public ObservableField<String> team = new ObservableField<String>();
    public ObservableField<String> manager = new ObservableField<String>();
    public ObservableField<String> phone = new ObservableField<String>();
    public ObservableField<String> phoneError = new ObservableField<String>();
    public ObservableField<String> role = new ObservableField<String>();
    public ObservableField<String> userId = new ObservableField<String>();
    public ObservableField<String> userIdError = new ObservableField<String>();

    MutableLiveData<Employee> employee = new MutableLiveData<>();
    private EmployeeRepository employeeRepository = new EmployeeRepository();
    LiveData<Employee> registerResponse = Transformations.switchMap(employee, new Function<Employee, LiveData<Employee>>() {
        @Override
        public LiveData<Employee> apply(Employee input) {
            return employeeRepository.register(input);
        }
    });

    public void register(Employee employee) {
        this.employee.setValue(employee);
    }


    public void onButtonClicked() {
        if (TextUtils.isEmpty(firstName.get())) {
            firstNameError.set("Please enter first name");

            return;
        }
        firstNameError.set(null);
        if (TextUtils.isEmpty(lastName.get())) {
            lastNameError.set("Please enter last name");
            return;
        }
        lastNameError.set(null);

        if (TextUtils.isEmpty(userName.get())) {
            userNameError.set("Please enter user name");
            return;
        }
        userNameError.set(null);

        if (TextUtils.isEmpty(password.get())) {
            passwordError.set("Please enter password");
            return;
        }
        passwordError.set(null);

        if (TextUtils.isEmpty(phone.get())) {
            phoneError.set("Please enter contact number");
            return;
        }
        phoneError.set(null);

        if (TextUtils.isEmpty(userId.get())) {
            userIdError.set("Please enter userId");
            return;
        }
        userIdError.set(null);

        Employee employee = new Employee();
        employee.setFirstName(firstName.get());
        employee.setLastName(lastName.get());
        employee.setUsername(userName.get());
        employee.setPassword(password.get());
        employee.setCompany(company.get());
        employee.setTeam(team.get());
        employee.setManager(manager.get());
        employee.setContactNo(phone.get());
        employee.setRole(role.get());
        employee.setPrimaryId(Long.parseLong(userId.get()));
        employee.setActive(true);

        this.employee.setValue(employee);

    }

}
