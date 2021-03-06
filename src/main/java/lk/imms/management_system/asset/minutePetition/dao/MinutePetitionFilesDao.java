package lk.imms.management_system.asset.minutePetition.dao;

import lk.imms.management_system.asset.minutePetition.entity.MinutePetition;
import lk.imms.management_system.asset.minutePetition.entity.MinutePetitionFiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MinutePetitionFilesDao extends JpaRepository< MinutePetitionFiles, Long > {
    List< MinutePetitionFiles > findByMinutePetitionOrderByIdDesc(MinutePetition minutePetition);

    MinutePetitionFiles findByName(String filename);

    MinutePetitionFiles findByNewName(String filename);

    MinutePetitionFiles findByNewId(String filename);
}
