package org.system.onlineelection.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Result {

    private PoliticalParty politicalParty;
    private Integer numVotes;

}
