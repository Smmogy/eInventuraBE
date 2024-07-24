package com.springboot.einventura.model.service;

import com.springboot.einventura.model.DTO.InventuraDTO;
import com.springboot.einventura.model.DTO.InventuraDetailDTO;
import com.springboot.einventura.model.bean.Institution;
import com.springboot.einventura.model.bean.Inventura;
import com.springboot.einventura.model.bean.User;
import com.springboot.einventura.model.repository.InstitutionRepository;
import com.springboot.einventura.model.repository.InventuraRepository;
import com.springboot.einventura.model.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InventuraServiceImpl implements InventuraService {
    @Autowired
    private InventuraRepository inventuraRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InstitutionRepository institutionRepository;

    @Override
    public List<Inventura> findAll() {
        return inventuraRepository.findAll();
    }

    @Override
    public Optional<InventuraDTO> findById(Integer theId) {
        return inventuraRepository.findById(theId).map(Inventura::toDTO);
    }
    @Override
    public Optional<InventuraDetailDTO> findByDetailId(Integer theId) {
        return inventuraRepository.findById(theId).map(Inventura::toDetailDTO);
    }

    @Override
    public Inventura save(InventuraDTO dto) {
        Inventura model;

        if (dto.getIdInventura() == 0) {
            model = new Inventura();
        } else {
            model = inventuraRepository.findById(dto.getIdInventura())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        }

        Optional<Institution> institution = institutionRepository.findById(dto.getInstitutionId());
        if (institution.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Institution not found");
        }
        model.setInstitution(institution.get());

        List<User> users = userRepository.findAllById(dto.getUsersIds());
        if (users.size() != dto.getUsersIds().size()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Some users not found");
        }
        model.setUsers(users);
        model.setDatumPocetka(dto.getDatumPocetka());
        model.setDatumZavrsetka(dto.getDatumZavrsetka());
        model.setAkademskaGod(dto.getAkademskaGod());
        model.setNaziv(dto.getNaziv());

        return inventuraRepository.save(model);
    }

    @Override
    public void deleteById(Integer theId) {
        inventuraRepository.deleteById(theId);
    }

    @Override
    public Optional<List<Inventura>> findAllByStanje(Integer stanje) {
        return inventuraRepository.findByStanje(stanje);
    }

    @Override
    public List<Inventura> getInventurasByUserId(Integer userId) {
        return userRepository.findById(userId)
                .map(User::getInventuras)
                .orElse(List.of());
    }

    @Override
    public void addUsersToInventura(Integer inventuraId, List<Integer> userIds) {
        Inventura inventura = inventuraRepository.findById(inventuraId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventura not found"));

        List<User> users = userRepository.findAllById(userIds);
        if (users.size() != userIds.size()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Some users not found");
        }
        inventura.getUsers().addAll(users);
        inventuraRepository.save(inventura);
    }

    @Override
    public List<InventuraDTO> getAllInventuras() {
        return inventuraRepository.findAll().stream().map(Inventura::toDTO).toList();
    }
}
