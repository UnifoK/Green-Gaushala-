package com.example.refining_gaushala_app.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.refining_gaushala_app.models.Request;

import java.util.ArrayList;
import java.util.List;

public class GalleryViewModel extends ViewModel {

    private final MutableLiveData<List<Request>> mRequests;
    private final MutableLiveData<String> mText;

    public GalleryViewModel() {
        mRequests = new MutableLiveData<>(new ArrayList<>());
        mText = new MutableLiveData<>();
        mText.setValue("This is Manufacturer fragment");
    }

    // Method to get the list of requests
    public LiveData<List<Request>> getRequests() {
        return mRequests;
    }

    // Method to add a new request
    public void addRequest(Request request) {
        List<Request> currentRequests = mRequests.getValue();
        if (currentRequests != null) {
            currentRequests.add(request);
            mRequests.setValue(currentRequests);
        }
    }

    // Method to get the text (if still needed)
    public LiveData<String> getText() {
        return mText;
    }
}
