package com.adarsh.IPLdashboardservice.Repository;

import com.adarsh.IPLdashboardservice.Models.Match;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match,Long> {

    public List<Match> findByTeam1OrTeam2OrderByDateDesc(String team1, String team2, Pageable pageable);

    public List<Match> getByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(String team1, LocalDate startDate1,LocalDate endDate1,
                                                                              String team2, LocalDate startDate2,LocalDate endDate2);

    @Query("select m from Match m where (m.team1=:teamName or m.team2=:teamName) AND m.date between :startDate AND :endDate order by m.date desc")
    public List<Match> getAllMatchesOfATeamInYear(@Param("teamName")String teamName,
                                                  @Param("startDate")LocalDate startDate,
                                                  @Param("endDate")LocalDate endDate);
}
