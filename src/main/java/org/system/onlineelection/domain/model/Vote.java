package org.system.onlineelection.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Vote {

    private Elector elector;
    private PoliticalParty politicalParty;
    private Date date;

}
