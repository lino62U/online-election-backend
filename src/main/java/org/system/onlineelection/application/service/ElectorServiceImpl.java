package org.system.onlineelection.application.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.system.onlineelection.application.mapper.EntityMapping;
import org.system.onlineelection.application.mapper.VoteDto;
import org.system.onlineelection.domain.model.PoliticalParty;
import org.system.onlineelection.domain.port.input.ElectorServicePort;
import org.system.onlineelection.infrastructure.adapter.entity.ElectorEntity;
import org.system.onlineelection.infrastructure.adapter.entity.PoliticalPartyEntityDto;
import org.system.onlineelection.infrastructure.adapter.entity.ResultEntityDto;
import org.system.onlineelection.infrastructure.adapter.entity.VoteEntity;
import org.system.onlineelection.infrastructure.repository.ElectorRepository;
import org.system.onlineelection.infrastructure.repository.PoliticalPartyRepository;
import org.system.onlineelection.infrastructure.repository.ResultRepository;
import org.system.onlineelection.infrastructure.repository.VoteRepository;

import java.util.ArrayList;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ElectorServiceImpl implements ElectorServicePort {

    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private ResultRepository resultRepository;
    @Autowired
    private ElectorRepository electorRepository;
    @Autowired
    private PoliticalPartyRepository politicalPartyRepository;

    @Autowired
    private final EntityMapping entityMapping;
    @Override
    public void saveVote(VoteDto vote) {

        //check this
        if(!voteRepository.existsById(vote.getIdElector() ) )
        {
            VoteEntity voteEntity = new VoteEntity();

            ElectorEntity electorEntity = electorRepository.findById(vote.getIdElector()).get();
            PoliticalPartyEntityDto politicalPartyEntityDto = politicalPartyRepository.findById(vote.getIdPoliticalParty()).get();

            voteEntity.setElector(electorEntity);
            voteEntity.setPoliticalParty(politicalPartyEntityDto);

            voteRepository.save(voteEntity);

            // Actualizar votos
            ResultEntityDto result = getResultVoteOfPoliticalParty(vote.getIdPoliticalParty());
            result.setNumVotes(result.getNumVotes() + 1);
            resultRepository.save(result);
        }

    }

    @Override
    public ResultEntityDto getResultVoteOfPoliticalParty(Integer idPoliticalParty) {

        // double find
        Optional<ResultEntityDto> data = resultRepository.findById(idPoliticalParty);

        if(data.isPresent())
        {
            return data.get();
        }

        // Primer voto de un partido politico
        ResultEntityDto newResult = new ResultEntityDto();
        PoliticalPartyEntityDto politicalPartyEntityDto = politicalPartyRepository.findById(idPoliticalParty).get();

        newResult.setPoliticalParty(politicalPartyEntityDto);
        newResult.setNumVotes(0);

        return newResult;
    }

    @Override
    public ArrayList<PoliticalParty> getPoliticalParty() {
        Iterable<PoliticalPartyEntityDto> politicalPartiesIterable = politicalPartyRepository.findAll();

        ArrayList<PoliticalParty> politicalPartiesList = new ArrayList<>();

        for (PoliticalPartyEntityDto politicalPartyEntityDto : politicalPartiesIterable) {
            PoliticalParty politicalParty = entityMapping.politicalPartyMapping(politicalPartyEntityDto);
            politicalPartiesList.add(politicalParty);
        }

        return politicalPartiesList;
    }


}
