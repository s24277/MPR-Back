package com.pjatk.MPR.service;

import com.pjatk.MPR.AlreadyExists;
import com.pjatk.MPR.model.Cow;
import com.pjatk.MPR.repository.CowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MyCowService {
    private final CowRepository cowRepository;

    @Autowired
    public MyCowService(CowRepository cowRepository) {
        this.cowRepository = cowRepository;
    }

    public Optional<Cow> getCowById(Long id) {
        return cowRepository.findById(id);
    }

    public List<Cow> getAllCows() {
        return (List<Cow>) cowRepository.findAll();
    }

    public void addCow(Cow cow) throws AlreadyExists {
        cowRepository.save(cow);
    }

    public void deleteCowByName(String name) {
        Cow cow = cowRepository.findByName(name);
        if (cow != null) {
            cowRepository.delete(cow);
        }
    }

    public void updateCow(Cow cowToUpdate) {
        cowRepository.save(cowToUpdate);
    }
}
