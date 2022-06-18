package com.adarsh.IPLdashboardservice.Repository;

import com.adarsh.IPLdashboardservice.Models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team,Long> {

    public Team findByTeamName(String name);

    public List<Team> findBytotalWins(long wins);

    public List<Team> findBytotalWinsLessThanEqualOrderByTotalMatchesDesc(long lessWin);

}