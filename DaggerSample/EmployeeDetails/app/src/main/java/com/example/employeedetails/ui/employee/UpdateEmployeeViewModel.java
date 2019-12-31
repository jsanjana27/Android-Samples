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

public class UpdateEmployeeViewModel extends ViewModel {

    public ObservableField<String> firstName = new ObservableField<String>();
    public ObservableField<String> firstNameError = new ObservableField<String>();
    public ObservableField<String> lastName = new ObservableField<String>();
    public ObservableField<String> lastNameError = new ObservableField<String>();
    public ObservableField<String> userName = new ObservableField<String>();
    public ObservableField<String> userNameError = new ObservableField<String>();
    public ObservableField<String> company = new ObservableField<String>();
    public ObservableField<String> team = new ObservableField<String>();
    public ObservableField<String> manager = new ObservableField<String>();
    public ObservableField<String> phone = new ObservableField<String>();
    public ObservableField<String> phoneError = new ObservableField<String>();
    public ObservableField<String> role = new ObservableField<String>();
    public ObservableField<String> userId = new ObservableField<String>();

    String id= null;


    private EmployeeRepository employeeRepository = new EmployeeRepository();

    MutableLiveData<Employee> employee = new MutableLiveData<>();
    LiveData<Employee> updateResponse = Transformations.switchMap(employee, new Function<Employee, LiveData<Employee>>() {
        @Override
        public LiveData<Employee> apply(Employee input) {
            Log.d("e", "apply: "+ employee);
            return employeeRepository.updateDetails(input);
        }
    });

    public LiveData<Boolean> deleteResponse = Transformations.switchMap(employee, new Function<Employee, LiveData<Boolean>>() {
        @Override
        public LiveData<Boolean> apply(Employee input) {
            return employeeRepository.deleteById(input.getUserId());
        }
    });

    public LiveData<Employee> getEmployeeById(String id) {
        this.id = id;

        return employeeRepository.getEmployeeById(id);
    }

    public LiveData<Boolean> deleteById(String id) {
        return employeeRepository.deleteById(id);
    }

    public void onUpdate() {
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

        if (TextUtils.isEmpty(phone.get())) {
            phoneError.set("Please enter contact number");
            return;
        }
        phoneError.set(null);


        Employee employee = new Employee();
        employee.setFirstName(firstName.get());
        employee.setLastName(lastName.get());
        employee.setUsername(userName.get());
        employee.setCompany(company.get());
        employee.setTeam(team.get());
        employee.setManager(manager.get());
        employee.setContactNo(phone.get());
        employee.setRole(role.get());
        employee.setPrimaryId(Long.parseLong(userId.get()));
        employee.setUserId(id);

        Log.d("MM   ", "onUpdate: " + employee.getLastName());
        this.employee.setValue(employee);
    }

    public void onDelete() {
        Log.d("clicked", "onDelete: ");
    }

    public void updateEmployee(Employee employee) {
        firstName.set(employee.getFirstName());
        lastName.set(employee.getLastName());
        userName.set(employee.getUsername());
        userId.set(employee.getPrimaryId()+ "");
        company.set(employee.getCompany());
        team.set(employee.getTeam());
        role.set(employee.getRole());
        manager.set(employee.getManager());
        phone.set(employee.getContactNo());
    }
}
