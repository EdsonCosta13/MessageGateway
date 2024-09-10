package com.edsoncosta.messageGateway.generic;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class AbstractServiceImpl<E, ID, RESPONSE_DTO> implements AbstractService<E, ID, RESPONSE_DTO>{
    private final JpaRepository<E, ID> repository;

    public AbstractServiceImpl(JpaRepository<E, ID> repository) {
        this.repository = repository;
    }

    protected JpaRepository<E, ID> getRepository() {
        return repository;
    }

    public abstract RESPONSE_DTO mapToResponse(E e);

    public List<RESPONSE_DTO> listAll(){
        return this.repository.findAll().stream().map(this::mapToResponse).toList();
    }

    public Optional<RESPONSE_DTO> getByID(ID id){
        return this.repository.findById(id).map(this::mapToResponse);
    }

    public RESPONSE_DTO save(E entity){
        if (entity == null)
            throw new IllegalArgumentException("DTO or entity is null");
        try {
            return mapToResponse(this.repository.save(entity));
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateKeyException("Chave duplicada: " + ex.getMessage());
        }
    }

    public RESPONSE_DTO update(E entity, ID id){
        if (!this.repository.existsById(id)){
            throw new EntityNotFoundException("Entidade não encontrada!");
        }
        return this.save(entity);
//        return this.update(entity,id);
    }

    public void delete(ID id){
        this.repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Entidade que pretende remover não existe!")
        );
        this.repository.deleteById(id);
    }
}
