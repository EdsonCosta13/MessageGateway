package com.edsoncosta.messageGateway.generic;



import com.edsoncosta.messageGateway.generic.dto.GenericValueRequestDTO;
import com.edsoncosta.messageGateway.utils.http.HttpResponseController;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/******************************
 * Created by: Edson Costa
 * Date: 08/07/2024
 * Time: 12:14 AM
 ******************************/

public abstract class AbstractController<E, ID, REQUEST_DTO extends GenericValueRequestDTO<E>, RESPONSE_DTO> extends HttpResponseController {

    private final AbstractService<E, ID, RESPONSE_DTO> service;

    protected                                                                                                                                                                                                                                                                                                                                 AbstractController(AbstractService<E, ID, RESPONSE_DTO> service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity getAll(){
        return success(this.service.listAll(), "Dados carregados!");
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable ID id){

        return success(this.service.getByID(id).orElseThrow(
                () -> new EntityNotFoundException("Entidade não encontrada!")
        ), "Entidade encotrada!");
    }

    @PostMapping
    public ResponseEntity create(@RequestBody REQUEST_DTO dto){
        return created(this.service.save(dto.mapToEntity()), "Entidade criada com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable ID id, @RequestBody REQUEST_DTO dto){
        return created(this.service.update(dto.mapToEntity(), id), "Entidade editada com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable ID id){
        this.service.delete(id);
        return successNoContent("Entidade eliminada!");
    }

    public AbstractService<E, ID, RESPONSE_DTO> getService() {
        return service;
    }
}
