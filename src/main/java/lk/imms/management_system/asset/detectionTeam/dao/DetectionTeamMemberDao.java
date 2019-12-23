package lk.imms.management_system.asset.detectionTeam.dao;

import lk.imms.management_system.asset.detectionTeam.entity.DetectionTeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetectionTeamMemberDao extends JpaRepository< DetectionTeamMember, Long > {
}