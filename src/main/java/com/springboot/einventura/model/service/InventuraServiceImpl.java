package com.springboot.einventura.model.service;

import com.springboot.einventura.model.DTO.*;
import com.springboot.einventura.model.bean.Artikl;
import com.springboot.einventura.model.bean.Institution;
import com.springboot.einventura.model.bean.Inventura;
import com.springboot.einventura.model.bean.User;
import com.springboot.einventura.model.repository.ArtiklRepository;
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

    @Autowired
    private ArtiklRepository artiklRepository;

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
        model.setStanje(true);

        return inventuraRepository.save(model);
    }

    @Override
    public void deleteById(Integer theId) {
        inventuraRepository.deleteById(theId);
    }

    @Override
    public List<Inventura> getInventurasByUserId(Integer userId) {
        return userRepository.findById(userId)
                .map(User::getInventuras)
                .orElse(List.of());
    }
    @Override
    public List<Inventura> getInventurasByUserIdByStanje(Integer userId) {
         Optional<User> user = userRepository.findById(userId);
         if(user.isEmpty()) {
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventura not found");
         }
         return user.get().getInventuras()
                    .stream()
                    .filter(Inventura::getStanje)
                    .toList();
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
    public List<InventuraListDTO> getAllInventuras() {
        return inventuraRepository.findAll().stream().map(Inventura::toListDTO).toList();

    }
    @Override
    public List<InventuraStanjeDTO> getAllInventurasByStanje() {
        return inventuraRepository.findByStanjeTrue().stream()
                .map(Inventura::toStanjeDTO)
                .toList();
    }

    public void updateArticlePresence(int idArtikl, int idInventura, boolean prisutan) {
        Optional<Inventura> inventura = inventuraRepository.findById(idInventura);
        if (inventura.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventory not found");
        }
        if(inventura.get().getStanje() == false){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Inventory is already finished");
        }

        Optional<Artikl> artikl = artiklRepository.findById(idArtikl);
        if (artikl.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Articles not found");
        }

        if (prisutan){
            if (!inventura.get().getPrisutniArtikli().contains(artikl.get())) {
                inventura.get().getPrisutniArtikli().add(artikl.get());
                inventuraRepository.save(inventura.get());
            }
        }
        else {
            inventura.get().getPrisutniArtikli().remove(artikl.get());
            inventuraRepository.save(inventura.get());
        }
    }

    public void zavrsiInventuru(int idInventura) {
        Optional<Inventura> inventura = inventuraRepository.findById(idInventura);
        if (inventura.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventory not found");
        }
        if(inventura.get().getStanje() == false){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Inventory is already finished");
        }

        List<Integer> prisutniAritkls = inventura.get().getPrisutniArtikli()
                .stream()
                .map(Artikl::getIdArtikl)
                .toList();

        List<Artikl> nePrisutniArtikls = artiklRepository
                .findByProstorijaInstitutionIdInstitution(inventura.get().getInstitution().getIdInstitution())
                .stream()
                .filter((Artikl a) -> !prisutniAritkls.contains(a.getIdArtikl()))
                .toList();

        nePrisutniArtikls.forEach((Artikl a) -> a.setOtpisan(true));
        inventura.get().setStanje(false);
        artiklRepository.saveAll(nePrisutniArtikls);
    }
}
