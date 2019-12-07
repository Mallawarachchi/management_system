package lk.imms.management_system.asset.petition.service;

import lk.imms.management_system.asset.petition.dao.PetitionerDao;
import lk.imms.management_system.asset.petition.entity.Enum.PetitionerType;
import lk.imms.management_system.asset.petition.entity.Petitioner;
import lk.imms.management_system.util.interfaces.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetitionerService implements AbstractService< Petitioner, Long > {
    private final PetitionerDao petitionerDao;

    @Autowired
    public PetitionerService(PetitionerDao petitionerDao) {
        this.petitionerDao = petitionerDao;
    }

    @Override
    public List< Petitioner > findAll() {
        return petitionerDao.findAll();
    }

    @Override
    public Petitioner findById(Long id) {
        return petitionerDao.getOne(id);
    }

    @Override
    public Petitioner persist(Petitioner petitioner) {
        return petitionerDao.save(petitioner);
    }

    @Override
    public boolean delete(Long id) {
        petitionerDao.deleteById(id);
        return true;
    }

    @Override
    public List< Petitioner > search(Petitioner petitioner) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example< Petitioner > petitionerExample = Example.of(petitioner, matcher);
        return petitionerDao.findAll(petitionerExample);
    }

    public Petitioner findByPetitionerType(PetitionerType petitionerType) {
    return petitionerDao.findByPetitionerType(petitionerType);
    }
}
