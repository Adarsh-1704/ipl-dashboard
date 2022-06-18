package com.adarsh.IPLdashboardservice.Controller;

import com.adarsh.IPLdashboardservice.Models.Match;
import com.adarsh.IPLdashboardservice.Repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchController {

    MatchRepository matchRepository;

    @Autowired
    MatchController(MatchRepository matchRepository){
        this.matchRepository=matchRepository;
    }

    @GetMapping("/teamName/{teamName}")
    public List<Match> getMatchByteamName(@PathVariable("teamName") String teamName){

        Pageable pageable = PageRequest.of(0,4);
        return matchRepository.findByTeam1OrTeam2OrderByDateDesc(teamName,teamName, pageable);
    }

}
