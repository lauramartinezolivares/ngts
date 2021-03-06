package com.ngts.test;

import com.ngts.test.entity.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/ngts")
public class TripController {

    @Autowired
    private TripService TripService;

    @GetMapping()
    public List<Trip> getAll(){
        return TripService.getAll();
    }

   @GetMapping(path = "cheapest/{origin}/{destination}")
    public List<Trip> getByOriginDestination(@PathVariable String origin, @PathVariable  String destination){
        return TripService.getByOriginDestination(origin, destination);
    }



}
