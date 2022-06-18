package com.adarsh.IPLdashboardservice.Processor;

import com.adarsh.IPLdashboardservice.Models.Match;
import com.adarsh.IPLdashboardservice.Models.MatchInput;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;

public class MatchProcessor implements ItemProcessor<MatchInput, Match> {
    @Override
    public Match process(MatchInput matchInput) throws Exception {
        Match match = new Match();

        match.setId(matchInput.getId());
        match.setCity(matchInput.getCity());

        LocalDate ld = LocalDate.parse(matchInput.getDate());
        match.setDate(ld);

        match.setManOfTheMatch(matchInput.getPlayer_of_match());
        match.setVenue(matchInput.getVenue());

        match.setTossWinner(matchInput.getToss_winner());
        match.setTossDecision(matchInput.getToss_decision());
        match.setResult(matchInput.getResult());

        match.setUmpire1(matchInput.getUmpire1());
        match.setUmpire2(matchInput.getUmpire2());

        if(matchInput.getToss_decision().equals("bat"))
            if(matchInput.getTeam1().equals(matchInput.getToss_winner())){
                match.setTeam1(matchInput.getTeam1());
                match.setTeam2(matchInput.getTeam2());
            }
            else{
                match.setTeam1(matchInput.getTeam2());
                match.setTeam2(matchInput.getTeam1());
            }
        else {
            if(matchInput.getTeam1().equals(matchInput.getToss_winner())){
                match.setTeam1(matchInput.getTeam2());
                match.setTeam2(matchInput.getTeam1());
            }
            else{
                match.setTeam1(matchInput.getTeam1());
                match.setTeam2(matchInput.getTeam2());
            }
        }

        match.setMatchWinner(matchInput.getWinner());
        match.setResultMargin(matchInput.getResult_margin());

        return match;
    }
}
