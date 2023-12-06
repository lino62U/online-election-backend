package org.system.onlineelection.infrastructure.adapter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "partido_politico")
public class PoliticalPartyEntityDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String namePoliticalParty;

    @OneToOne(mappedBy = "politicalParty")
    private CandidateEntity candidate;

    @OneToMany(mappedBy = "politicalParty")
    private List<VoteEntity> votes;
}
