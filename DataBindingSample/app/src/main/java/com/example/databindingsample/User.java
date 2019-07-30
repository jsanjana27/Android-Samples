package com.example.databindingsample;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;

public class User {
    public final ObservableField<String> firstName = new ObservableField<>();
    public final ObservableField<String> lastName = new ObservableField<>();
    public final ObservableInt age = new ObservableInt();

}
