package com.ngts.test;

import com.ngts.test.entity.Trip;

import java.util.List;

public interface ITripService {

    public List<Trip> getAll();

    public List<Trip> getByOriginDestination(final String origen, final String destiny);
}
