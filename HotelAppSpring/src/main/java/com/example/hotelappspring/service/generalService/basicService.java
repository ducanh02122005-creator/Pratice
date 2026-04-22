package com.example.hotelappspring.service.generalService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

public abstract class basicService<Entity, DTO, UPDATE_REQ, ID> {

    protected abstract JpaRepository<Entity, ID> getRepository();

    protected abstract Entity toEntity(DTO dto);

    protected abstract DTO toDto(Entity entity);

    protected abstract void updateRequest(UPDATE_REQ request, Entity entity);

    protected abstract ID getId(Entity entity);

    protected abstract String getPath(); // e.g. "/user/{id}"

    public ResponseEntity<DTO> addEntity(DTO data, UriComponentsBuilder uriBuilder) {
        Entity entity = toEntity(data);

        Entity savedEntity = getRepository().save(entity);

        var uri = uriBuilder
                .path(getPath())
                .buildAndExpand(getId(savedEntity)) // ✅ FIXED
                .toUri();

        return ResponseEntity.created(uri).body(toDto(savedEntity));
    }

    public Iterable<DTO> getAllEntity() {
        return getRepository()
                .findAll()
                .stream()
                .map(this::toDto) // ✅ FIXED
                .toList();
    }

    public ResponseEntity<DTO> getEntityById(ID id) {
        var entity = getRepository().findById(id).orElse(null);

        if (entity == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(toDto(entity));
    }

    public ResponseEntity<DTO> updateEntityById(ID id, UPDATE_REQ request) {
        var entity = getRepository().findById(id).orElse(null);

        if (entity == null) {
            return ResponseEntity.notFound().build();
        }

        updateRequest(request, entity);

        Entity savedEntity = getRepository().save(entity);

        return ResponseEntity.ok(toDto(savedEntity));
    }

    public ResponseEntity<Void> deleteEntityById(ID id) {
        var entity = getRepository().findById(id).orElse(null);

        if (entity == null) {
            return ResponseEntity.notFound().build();
        }

        getRepository().delete(entity);

        return ResponseEntity.noContent().build();
    }
}
