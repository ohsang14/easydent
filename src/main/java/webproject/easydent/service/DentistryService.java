package webproject.easydent.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import webproject.easydent.entities.Dentistry;
import webproject.easydent.repositories.DentistryRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DentistryService {

    public final DentistryRepository dentistryRepository;

    public Set<Dentistry> searchByKeyword(String keyword){

        List<Dentistry> clinicNameList = this.dentistryRepository.findByClinicNameContaining(keyword);

        List<Dentistry> categoryList = this.dentistryRepository.findByCategoryContaining(keyword);

        Set<Dentistry> searchSet = new HashSet<>();

        searchSet.addAll(clinicNameList);
        searchSet.addAll(categoryList);
        System.out.println("searchByKeyword :: clinicNameList :  " + clinicNameList);
        System.out.println("searchByKeyword :: categoryList :  " + categoryList);
        return searchSet;
    }
}
