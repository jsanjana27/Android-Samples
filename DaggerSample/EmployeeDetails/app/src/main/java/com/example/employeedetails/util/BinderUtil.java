package com.example.employeedetails.util;

import android.app.DatePickerDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class BinderUtil {

    /**
     * Two way data binding for Edit text error hint
     *
     * @param view           {@link TextInputLayout} object
     * @param bindableString {@link ObservableField<String>} object
     */
    @BindingAdapter({"app:error"})
    public static void bindEditErrorText(TextInputLayout view, final ObservableField<String> bindableString) {

        view.setError(bindableString == null ? null : bindableString.get());
        // If we don't have error value just hide the space for the error message
        if (TextUtils.isEmpty(bindableString.get())) {
            view.setErrorEnabled(false);
        } else {
            view.setErrorEnabled(true);
        }
    }

    @BindingAdapter({"bind:date"})
    public static void bindSelectDate(final TextInputEditText text, final ObservableField<String> bindableString) {
        text.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                Toast.makeText(text.getContext(), "date", Toast.LENGTH_SHORT).show();
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    try {
                        showDatePicker(text, bindableString);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
                return false;
            }
        });
    }

    @BindingAdapter({"bind:updateDate"})
    public static void bindUpdateDate(final TextInputEditText text, final ObservableField<String> bindableString) {
        if (TextUtils.isEmpty(bindableString.get())) {
            text.setText("");
            return;
        }
        text.setText(bindableString.get());
    }

    public static void showDatePicker(final TextInputEditText editText, final ObservableField<String> bindableString) throws ParseException {
        String dateString = bindableString.get();
        Log.d("Date", "showDatePicker: " +dateString);

        Calendar calendar = Calendar.getInstance();
        if (!TextUtils.isEmpty(dateString)) {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
            calendar.setTime(date);
        }


        DatePickerDialog datePickerDialog =
                new DatePickerDialog(editText.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar cal = new GregorianCalendar(year, month, dayOfMonth);
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        String date = df.format(cal.getTime());
                        Log.d("dsf", "onDateSet: " + date);
                        bindableString.set(date);
                        bindableString.notifyChange();
//                        editText.setText(date);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}


