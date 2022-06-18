package com.adarsh.IPLdashboardservice.Controller;

import com.adarsh.IPLdashboardservice.Models.Match;
import com.adarsh.IPLdashboardservice.Models.Team;
import com.adarsh.IPLdashboardservice.Repository.MatchRepository;
import com.adarsh.IPLdashboardservice.Repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/team")
@CrossOrigin
public class TeamController {

    TeamRepository teamRepository;
    MatchRepository matchRepository;

    @Autowired
    TeamController(TeamRepository teamRepository, MatchRepository matchRepository){
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    @RequestMapping("/teamName/{teamName}")
    public Team getTeamfromTeamName(@PathVariable String teamName){

        Team team = teamRepository.findByTeamName(teamName);
        Pageable p = PageRequest.of(0,4);
        team.setMatchList(matchRepository.findByTeam1OrTeam2OrderByDateDesc(teamName,teamName,p));
        return team;
    }

    @RequestMapping("/teamName1/{teamName}")
    public Team getTeamfromTeamName1(@PathVariable String teamName){

        Team team = teamRepository.findByTeamName(teamName);
        Pageable p = PageRequest.of(0,6);
        team.setMatchList(matchRepository.findByTeam1OrTeam2OrderByDateDesc(teamName,teamName,p));
        return team;
    }

    @RequestMapping("/totWins")
    public List<Team> getListTeamByTotWins(@RequestParam("totalWins") Long wins){
        return teamRepository.findBytotalWins(wins);
    }

    @RequestMapping("/lessThanWins")
    public List<Team> getListTeamByTotWinslessThan(@RequestParam("lessWins") Long wins){
        return teamRepository.findBytotalWinsLessThanEqualOrderByTotalMatchesDesc(wins);
    }

    @RequestMapping("/findAllTeams")
    public List<Team> getAllTeams(){
        return teamRepository.findAll();
    }

//    @GetMapping("/{teamName}")
//    public List<Match> getMatchInYear(@PathVariable("teamName") String teamName,@RequestParam("year") Integer year){
//        LocalDate startDate = LocalDate.of(year,1,1);
//        LocalDate endDate = LocalDate.of(year+1,1,1);
//        return matchRepository.getByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(teamName,startDate,endDate,teamName,startDate,endDate);
//    }

    @GetMapping("/{teamName}")
    public List<Match> getMatchInYear(@PathVariable("teamName") String teamName,@RequestParam("year") Integer year){
        LocalDate startDate = LocalDate.of(year,1,1);
        LocalDate endDate = LocalDate.of(year+1,1,1);
        return matchRepository.getAllMatchesOfATeamInYear(teamName,startDate,endDate);
    }
}
