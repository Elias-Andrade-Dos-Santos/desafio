package com.desafio.desafio.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.desafio.model.HierarquiaEntity;
import com.desafio.desafio.repository.HierarquiaRepository;

@Service
public class HierarquiaService {

    @Autowired
    private HierarquiaRepository hierarquiaRepository;

    public HierarquiaEntity createHierarquia(String name) {
        var node = new HierarquiaEntity(name);
        return hierarquiaRepository.save(node);
    }

    public Optional<HierarquiaEntity> getHierarquia(Long id) {
        return hierarquiaRepository.findById(id);
    }

    public HierarquiaEntity addChild(Long parentId, String childName) {
        HierarquiaEntity parent = hierarquiaRepository.findById(parentId)
            .orElseThrow(() -> new RuntimeException("Parent node not found"));
            HierarquiaEntity child = new HierarquiaEntity(childName);
        parent.addChild(child);
        return hierarquiaRepository.save(parent);
    }

    public void deleteHierarquia(Long id) {
        hierarquiaRepository.deleteById(id);
    }
}
