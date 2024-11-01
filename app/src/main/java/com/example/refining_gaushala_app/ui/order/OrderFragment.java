package com.example.refining_gaushala_app.ui.order;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.refining_gaushala_app.R;
import com.example.refining_gaushala_app.models.Request;

import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends Fragment implements AcceptedRequestAdapter.OnRequestCompleteListener {

    private RecyclerView recyclerViewOrders;
    private AcceptedRequestAdapter adapter;
    private List<Request> acceptedRequests;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        recyclerViewOrders = view.findViewById(R.id.recyclerViewOrders); // Ensure this ID is in fragment_order.xml
        acceptedRequests = new ArrayList<>();

        // Load accepted requests (this should be replaced with actual data fetching)
        loadAcceptedRequests();

        // Set up RecyclerView
        recyclerViewOrders.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AcceptedRequestAdapter(acceptedRequests, this);
        recyclerViewOrders.setAdapter(adapter);

        return view;
    }

    private void loadAcceptedRequests() {
        // Replace this with the actual method to fetch accepted requests
        acceptedRequests.add(new Request("Bioplant A", 500, "01/11/2024", "Fresh")); // Added dung type
        acceptedRequests.add(new Request("Bioplant B", 300, "02/11/2024", "Dry")); // Added dung type
    }

    @Override
    public void onRequestComplete(Request request) {
        // Remove the request from the list and notify adapter
        acceptedRequests.remove(request);
        adapter.notifyDataSetChanged(); // Refresh the RecyclerView
    }
}
