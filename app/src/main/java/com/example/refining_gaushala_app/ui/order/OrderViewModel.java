package com.example.refining_gaushala_app.ui.order;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.refining_gaushala_app.models.Request;

import java.util.ArrayList;
import java.util.List;

public class OrderViewModel extends ViewModel {

    private final MutableLiveData<List<Request>> acceptedRequests;

    public OrderViewModel() {
        acceptedRequests = new MutableLiveData<>(new ArrayList<>()); // Initialize with an empty list
    }

    public LiveData<List<Request>> getAcceptedRequests() {
        return acceptedRequests;
    }

    public void addRequest(Request request) {
        List<Request> currentRequests = acceptedRequests.getValue();
        if (currentRequests != null) {
            currentRequests.add(request);
            acceptedRequests.setValue(currentRequests);
        }
    }

    public void removeRequest(Request request) {
        List<Request> currentRequests = acceptedRequests.getValue();
        if (currentRequests != null) {
            currentRequests.remove(request);
            acceptedRequests.setValue(currentRequests);
        }
    }
}
