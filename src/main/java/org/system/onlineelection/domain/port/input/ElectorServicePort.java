package org.system.onlineelection.domain.port.input;



import org.system.onlineelection.application.mapper.VoteDto;
import org.system.onlineelection.domain.model.PoliticalParty;
import org.system.onlineelection.infrastructure.adapter.entity.ResultEntityDto;

import java.util.ArrayList;

public interface ElectorServicePort {
    void saveVote(VoteDto vote);
    ResultEntityDto getResultVoteOfPoliticalParty(Integer idPoliticalParty);
    ArrayList<PoliticalParty> getPoliticalParty();

}
