package com.ngts.test;

import com.ngts.test.entity.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class TripService implements ITripService {

    @Autowired
    private ITripRepository tripserviceRepository;

    public List<Trip> getAll() {
        List<Trip> trips = new ArrayList<>();
        tripserviceRepository.findAll().forEach(trip -> trips.add(trip));

        return trips;
    }

    public List<Trip> getByOriginDestination(String origin, String destination) {

        List<Trip> trips = getAll();

        checkIfExist(trips, origin, destination);

        List<Trip> direct = new ArrayList<>();
        List<Trip> indirect = new ArrayList<>();

        /*
        * Check if the given origin and destination exist
        */
        List<Trip> destinationList = new ArrayList<>();
        trips.stream().forEach(trip -> {
            if (trip.getDestination().equalsIgnoreCase(destination)) {
                destinationList.add(trip);
            }
        });


        /*trips.stream().forEach(trip -> {
            //comprovo si origen destí directe
            if (trip.getOrigin().equalsIgnoreCase(origin) && trip.getDestination().equalsIgnoreCase(destination)) {
                direct.add(trip);
            }
            //comprovo si l'origen no té destí directe
            if (trip.getOrigin().equalsIgnoreCase(origin) && !trip.getDestination().equalsIgnoreCase(destination)) {
                indirect.add(trip);
            }
        });*/

        /*
        * Loop through the list of trips to get the route
        */
        List<String> finalList = new ArrayList<>();
        for (Trip trip : trips) {
            String origen = trip.getOrigin();
            if (trip.getOrigin().equalsIgnoreCase(origin)) {
                String desti = trip.getDestination();
                if (desti.equalsIgnoreCase(destination)) {
                    finalList.add("Direct -> " +  origen +" "+ desti );
                } else {
                    getroute(desti, destination, trips);
                }
            }
        }
            return null;
        }

        private void getroute(String origen, String destination, List<Trip> trips) {
        List<String> list = new ArrayList<>();
            for (Trip trip : trips) {
                if (origen.equalsIgnoreCase(trip.getOrigin())) {
                    String newDesti = trip.getDestination();
                    if (newDesti.equalsIgnoreCase(destination)) {
                        list.add(newDesti);
                    } else {
                        getroute(newDesti, destination, trips);


                    }

                }
            }
        }

        private boolean checkIfExist (List < Trip > trips, String origin, String destination){
            List<String> origins = new ArrayList<>();
            List<String> destinations = new ArrayList<>();
            trips.stream().forEach(trip -> origins.add(trip.getOrigin()));
            trips.stream().forEach(trip -> destinations.add(trip.getDestination()));

            List<String> finalOrigins = new ArrayList<>(clearDuplicates(origins));
            List<String> finalDestinations = new ArrayList<>(clearDuplicates(destinations));

            if (!finalOrigins.contains(origin)) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "entity not found"
                );
            } else if (!finalDestinations.contains(destination)) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "entity not found"
                );
            }
            return true;
        }

        private List<String> clearDuplicates (List < String > list) {
            Set<String> listCleared = new LinkedHashSet<>(list);
            list.clear();
            list.addAll(listCleared);
            return list;
        }

}