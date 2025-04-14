package com.springboot.einventura.model.service;

import com.springboot.einventura.model.DTO.*;
import com.springboot.einventura.model.bean.*;
import com.springboot.einventura.model.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

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

    @Autowired
    private ProstorijaRepository prostorijaRepository;

    @Autowired
    private ProstorijaService prostorijaService;

    @Autowired
    private InventuraProstorijaUserRepository inventuraProstorijaUserRepository;

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
    public ZavrsenaInventuraDTO findByZavsenaId(Integer idInventura) {
        Optional<Inventura> inventura = inventuraRepository.findById(idInventura);
        if (inventura.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventory not found");
        }

        List<ProstorijaDTO> prostorijaDTOs = inventura.get().getArtiklArchives()
                .stream()
                .map(InventuraArtiklArchive::getProstorija)
                .distinct()
                .map(Prostorija::ToDTO).toList();

        return ZavrsenaInventuraDTO.builder()
                .idInventura(inventura.get().getIdInventura())
                .akademskaGod(inventura.get().getAkademskaGod())
                .datumPocetka(inventura.get().getDatumPocetka())
                .datumZavrsetka(inventura.get().getDatumZavrsetka())
                .naziv(inventura.get().getNaziv())
                .institution(inventura.get().getInstitution().toDTO())
                .prostorije(prostorijaDTOs)
                .build();
    }

    @Override
    public ZavrsenaInventraProstorijaDTO findByProstorijaId(Integer idInventura, Integer idProstorija) {
        Optional<Inventura> inventura = inventuraRepository.findById(idInventura);
        if (inventura.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventory not found");
        }

        Optional<Prostorija> prostorija = prostorijaRepository.findById(idProstorija);
        if (inventura.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventory not found");
        }

        List<ArtiklInventuraDTO> artikls = inventura.get().getArtiklArchives()
                .stream()
                .filter(artiklArchive -> artiklArchive.getProstorija().getIdProstorija() == idProstorija)
                .map(artiklArchive -> ArtiklInventuraDTO.builder()
                        .idArtikl(artiklArchive.getArtikl().getIdArtikl())
                        .name(artiklArchive.getArtikl().getName())
                        .prisutan(artiklArchive.getPristuan())
                        .build())
                .toList();

        List<UserProstorijaDTO> users = inventuraProstorijaUserRepository.findByInventuraIdInventuraAndProstorijaIdProstorija(idInventura, idProstorija)
                .stream()
                .map(InventuraProstorijaUser::getUser)
                .map(User::toUserProstorijaDTO)
                .toList();

        return ZavrsenaInventraProstorijaDTO.builder()
                .idProstorija(prostorija.get().getIdProstorija())
                .name(prostorija.get().getName())
                .artikls(artikls)
                .users(users)
                .build();

    }

    @Override
    @Transactional
    public InventuraDTO save(Probno dto) {
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
        model.setDatumPocetka(dto.getDatumPocetka());
        model.setDatumZavrsetka(dto.getDatumZavrsetka());
        model.setAkademskaGod(dto.getAkademskaGod());
        model.setNaziv(dto.getNaziv());
        model.setStanje(true);

        inventuraRepository.save(model);

        dto.getRoomUserMap().forEach((roomId, userIds) -> {
            for (User userId : userIds) {
                Integer id = userId.getId();

                InventuraProstorijaUser inventuraProstorijaUser = InventuraProstorijaUser.builder()
                        .inventura(model)
                        .prostorija(Prostorija.builder().idProstorija(roomId).build())
                        .user(User.builder().id(id).build())
                        .build();

                inventuraProstorijaUserRepository.save(inventuraProstorijaUser);
            }
        });

        return model.toDTO();
    }

    @Override
    public void deleteById(Integer theId) {
        inventuraRepository.deleteById(theId);
    }

    @Override
    public List<Inventura> getInventurasByUserId(Integer userId) {
        List<InventuraProstorijaUser> inventuraProstorijaUsers = inventuraProstorijaUserRepository.findByUserId(userId);
        return inventuraProstorijaUsers
                .stream()
                .map(InventuraProstorijaUser::getInventura)
                .distinct()
                .toList();
    }

    @Override
    public List<Inventura> getInventurasByUserIdByStanje(Integer userId) {
        List<InventuraProstorijaUser> inventuraProstorijaUsers = inventuraProstorijaUserRepository.findByUserId(userId);
        return inventuraProstorijaUsers
                .stream()
                .map(InventuraProstorijaUser::getInventura)
                .distinct()
                .filter(Inventura::getStanje)
                .toList();
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
        if (inventura.get().getStanje() == false) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Inventory is already finished");
        }

        Optional<Artikl> artikl = artiklRepository.findById(idArtikl);
        if (artikl.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Articles not found");
        }

        if (prisutan) {
            if (!inventura.get().getPrisutniArtikli().contains(artikl.get())) {
                inventura.get().getPrisutniArtikli().add(artikl.get());
                inventuraRepository.save(inventura.get());
            }
        } else {
            inventura.get().getPrisutniArtikli().remove(artikl.get());
            inventuraRepository.save(inventura.get());
        }
    }

    public void zavrsiInventuru(int idInventura) {
        Optional<Inventura> inventura = inventuraRepository.findById(idInventura);
        if (inventura.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventory not found");
        }
        if (inventura.get().getStanje() == false) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Inventory is already finished");
        }

        List<Integer> prisutniAritkls = inventura.get().getPrisutniArtikli()
                .stream()
                .map(Artikl::getIdArtikl)
                .toList();

        List<Artikl> allArtikls = artiklRepository
                .findByProstorijaInstitutionIdInstitutionAndOtpisanFalse(inventura.get().getInstitution().getIdInstitution());

        for (Artikl artikl : allArtikls) {
            Boolean prisutan = prisutniAritkls.contains(artikl.getIdArtikl());

            if (!prisutan) {
                artikl.setOtpisan(true);
            }

            InventuraArtiklArchive artiklArchive = InventuraArtiklArchive.builder()
                    .inventura(inventura.get())
                    .artikl(artikl)
                    .prostorija(artikl.getProstorija())
                    .pristuan(prisutan)
                    .build();

            inventura.get().getArtiklArchives().add(artiklArchive);
        }

        inventura.get().getPrisutniArtikli().clear();
        inventura.get().setStanje(false);

        artiklRepository.saveAll(allArtikls);
        inventuraRepository.save(inventura.get());
    }
}
