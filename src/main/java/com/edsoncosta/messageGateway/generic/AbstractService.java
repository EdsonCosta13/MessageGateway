package com.edsoncosta.messageGateway.generic;

import java.util.List;
import java.util.Optional;

/******************************
 * Created by: Edson Costa
 * Date: 08/07/2024
 * Time: 12:14 AM
 ******************************/

public interface AbstractService<E, ID, RESPONSE_DTO> {

    RESPONSE_DTO mapToResponse(E e);
    List<RESPONSE_DTO> listAll();
    Optional<RESPONSE_DTO> getByID(ID id);
    RESPONSE_DTO save(E entity);
    RESPONSE_DTO update(E entity, ID id);
    void delete(ID id);

}
