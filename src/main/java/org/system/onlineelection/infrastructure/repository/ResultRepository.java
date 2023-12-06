package org.system.onlineelection.infrastructure.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.system.onlineelection.infrastructure.adapter.entity.PoliticalPartyEntityDto;
import org.system.onlineelection.infrastructure.adapter.entity.ResultEntityDto;


@Repository
public interface ResultRepository extends CrudRepository<ResultEntityDto, PoliticalPartyEntityDto> {
    //test second parameter
}
