package com.example.tiki_assignment.model;

import java.util.ArrayList;

public interface LoadKeyWordListener {

    void onLoadKeyWordSuccess(ArrayList<String> arrayList);
    void onLoadKeyWordFailure();
}
