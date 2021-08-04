package com.ngts.test;

import com.ngts.test.entity.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TripService implements ITripService {

    @Autowired
    private ITripRepository tripserviceRepository;

    public List<Trip> getAllTrip()
    {
        List<Trip> trips = new ArrayList<>();
        tripserviceRepository.findAll().forEach(trip -> trips.add(trip));
        return trips;
    }
}
