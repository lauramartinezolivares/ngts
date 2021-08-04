package com.ngts.test;

import com.ngts.test.entity.Trip;
import org.springframework.data.repository.CrudRepository;

public interface ITripRepository extends CrudRepository<Trip, Long> {
}
