package org.training360.finalexam.teams;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeamRepository extends JpaRepository<Team, Long> {


    @Query("select t from Team t join fetch t.players where t.id=:id")
    Team findTeamByIdWithPLayers(long id);

}
