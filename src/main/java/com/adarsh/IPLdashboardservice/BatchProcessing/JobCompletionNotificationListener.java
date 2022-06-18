package com.adarsh.IPLdashboardservice.BatchProcessing;

import com.adarsh.IPLdashboardservice.Models.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final JdbcTemplate jdbcTemplate;
    private final EntityManager em;

    @Autowired
    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate, EntityManager em) {
        this.jdbcTemplate = jdbcTemplate;
        this.em = em;
    }

    @Override
    @Transactional
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");

//            jdbcTemplate.query("SELECT * FROM match",
//                    (rs, row) -> rs.getString(5)
//            ).forEach(match -> log.info("Found <" + match.toString() + "> in the database."));


            Map<String,Long> winsMap = new HashMap<>();
            //Total Wins
            List<Object[]> winsList = em.createQuery("Select m.matchWinner,count(*) from Match m group by m.matchWinner",Object[].class).getResultList();
            for(int i=0;i<winsList.size();i++){
                Object[] data = winsList.get(i);
                String teamName = (String)data[0];
                Long count = (Long)data[1];

                winsMap.put(teamName,count);
            }

            Map<String,Team> teamMap = new HashMap<>();

            //Total team 1
            List<Object[]> team1List = em.createQuery("Select m.team1,count(*) from Match m group by m.team1",Object[].class).getResultList();
            for(int i=0;i<team1List.size();i++){
                Object[] data1 = team1List.get(i);
                String team1Name = (String) data1[0];
                Long team1Count = (Long) data1[1];

                Team team = new Team();
                team.setTeamName(team1Name);
                team.setTotalMatches(team1Count);
                team.setTotalWins(winsMap.get(team1Name));

                teamMap.put(team1Name,team);
            }

            //Total team 2
            List<Object[]> team2List = em.createQuery("Select m.team2,count(*) from Match m group by m.team2",Object[].class).getResultList();
            for(int i=0;i<team2List.size();i++){
                Object[] data2 = team2List.get(i);
                String team2Name = (String) data2[0];
                Long team2Count = (Long) data2[1];

                if(teamMap.containsKey(team2Name)){
                    Team t = teamMap.get(team2Name);
                    t.setTotalMatches(t.getTotalMatches()+team2Count);
                }
                else {
                    Team team = new Team();
                    team.setTeamName(team2Name);
                    team.setTotalMatches(team2Count);
                    team.setTotalWins(winsMap.get(team2Name));

                    teamMap.put(team2Name,team);
                }
            }

            List<Team> list = new ArrayList<>(teamMap.values());

            for(int i=0;i<teamMap.size();i++){
                em.persist(list.get(i));
            }
//            for(int j=0;j<teamMap.size();j++){
//                System.out.println(list.get(j));
//            }

        }
    }
}