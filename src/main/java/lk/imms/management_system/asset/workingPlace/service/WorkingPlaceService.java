package lk.imms.management_system.asset.workingPlace.service;

import lk.imms.management_system.asset.commonAsset.service.CommonCodeService;
import lk.imms.management_system.asset.workingPlace.dao.WorkingPlaceDao;
import lk.imms.management_system.asset.workingPlace.entity.WorkingPlace;
import lk.imms.management_system.util.interfaces.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig( cacheNames = {"workingPlace"} ) // tells Spring where to store cache for this class
public class WorkingPlaceService implements AbstractService< WorkingPlace, Long > {
    private final WorkingPlaceDao workingPlaceDao;
    private final CommonCodeService commonCodeService;

    @Autowired
    public WorkingPlaceService(WorkingPlaceDao workingPlaceDao, CommonCodeService commonCodeService) {
        this.workingPlaceDao = workingPlaceDao;
        this.commonCodeService = commonCodeService;
    }


    @Override
    @Cacheable
    public List< WorkingPlace > findAll() {
        return workingPlaceDao.findAll();
    }

    @Override
    @Cacheable
    public WorkingPlace findById(Long id) {
        return workingPlaceDao.getOne(id);
    }

    @Override
    @Caching( evict = {@CacheEvict( value = "workingPlace", allEntries = true )},
            put = {@CachePut( value = "workingPlace", key = "#workingPlace.id" )} )
    public WorkingPlace persist(WorkingPlace workingPlace) {
        workingPlace.setCode(workingPlace.getCode().toUpperCase());
        workingPlace.setLandOne(commonCodeService.commonMobileNumberLengthValidator(workingPlace.getEmailOne()));
        workingPlace.setLandTwo(commonCodeService.commonMobileNumberLengthValidator(workingPlace.getEmailTwo()));
        workingPlace.setLandThree(commonCodeService.commonMobileNumberLengthValidator(workingPlace.getLandThree()));
        workingPlace.setLandFour(commonCodeService.commonMobileNumberLengthValidator(workingPlace.getLandFour()));
        workingPlace.setFaxNumber(commonCodeService.commonMobileNumberLengthValidator(workingPlace.getFaxNumber()));
        return workingPlaceDao.save(workingPlace);
    }

    @Override
    @CacheEvict( allEntries = true )
    public boolean delete(Long id) {
        workingPlaceDao.deleteById(id);
        return true;
    }

    @Override
    @Cacheable
    public List< WorkingPlace > search(WorkingPlace workingPlace) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example< WorkingPlace > workingPlaceExample = Example.of(workingPlace, matcher);
        return workingPlaceDao.findAll(workingPlaceExample);
    }

}
