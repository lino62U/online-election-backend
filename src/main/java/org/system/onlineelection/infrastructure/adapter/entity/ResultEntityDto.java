package org.system.onlineelection.infrastructure.adapter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name="resultado")
public class ResultEntityDto {

    @Id
    @OneToOne
    @JoinColumn(name = "id_partido")
    private PoliticalPartyEntityDto politicalParty;

    private Integer numVotes;

}
