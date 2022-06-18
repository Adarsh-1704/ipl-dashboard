package com.adarsh.IPLdashboardservice.Models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="team")
@ToString
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public String teamName;
    public Long totalMatches;
    public Long totalWins;

    @Transient
    public List<Match> matchList;

}