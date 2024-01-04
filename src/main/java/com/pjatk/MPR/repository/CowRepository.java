package com.pjatk.MPR.repository;

import com.pjatk.MPR.model.Cow;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CowRepository extends CrudRepository<Cow,Long> {
    Cow findByName(String name);
}
