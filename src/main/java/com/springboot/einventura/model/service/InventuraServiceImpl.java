package com.springboot.einventura.model.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.springboot.einventura.model.DTO.*;
import com.springboot.einventura.model.bean.*;
import com.springboot.einventura.model.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public InventuraDetailDTO findByDetailId(Integer idInventura, Integer userId) {
        Optional<Inventura> inventura = inventuraRepository.findById(idInventura);
        if (inventura.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventory not found");
        }

        List<ProstorijaDTO> prostorijaDTOs;

        if (inventura.get().getStanje()) { // true = aktivna
            if (userId != null) { // show only rooms for given user
                prostorijaDTOs = inventuraProstorijaUserRepository.findByInventuraIdInventuraAndUserId(idInventura, userId)
                        .stream()
                        .map(InventuraProstorijaUser::getProstorija)
                        .map(Prostorija::ToDTO)
                        .toList();
            } else {
                prostorijaDTOs = inventura.get().getInstitution().getProstorijas()
                        .stream()
                        .map(Prostorija::ToDTO)
                        .toList();
            }
        } else {
            prostorijaDTOs = inventura.get().getArtiklArchives()
                    .stream()
                    .map(InventuraArtiklArchive::getProstorija)
                    .distinct()
                    .map(Prostorija::ToDTO).toList();
        }

        return InventuraDetailDTO.builder()
                .idInventura(inventura.get().getIdInventura())
                .akademskaGod(inventura.get().getAkademskaGod())
                .datumPocetka(inventura.get().getDatumPocetka())
                .datumZavrsetka(inventura.get().getDatumZavrsetka())
                .naziv(inventura.get().getNaziv())
                .stanje(inventura.get().getStanje())
                .institution(inventura.get().getInstitution().toDTO())
                .prostorije(prostorijaDTOs)
                .build();
    }

    @Override
    public InventuraDetailProstorijaDTO findByDetailProstorijaId(Integer idInventura, Integer idProstorija) {
        Optional<Inventura> inventura = inventuraRepository.findById(idInventura);
        if (inventura.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventory not found");
        }

        Optional<Prostorija> prostorija = prostorijaRepository.findById(idProstorija);
        if (inventura.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prostorija not found");
        }

        List<ArtiklInventuraDTO> artikls;

        if (inventura.get().getStanje()) { // true = aktivna
            artikls = prostorija.get().getArtikls()
                    .stream()
                    .filter(artikl -> !artikl.getOtpisan())
                    .map(artikl -> ArtiklInventuraDTO.builder()
                            .idArtikl(artikl.getIdArtikl())
                            .name(artikl.getName())
                            .prisutan(inventura.get().getPrisutniArtikli().contains(artikl))
                            .build())
                    .toList();

        } else {
            artikls = inventura.get().getArtiklArchives()
                    .stream()
                    .filter(artiklArchive -> artiklArchive.getProstorija().getIdProstorija() == idProstorija)
                    .map(artiklArchive -> ArtiklInventuraDTO.builder()
                            .idArtikl(artiklArchive.getArtikl().getIdArtikl())
                            .name(artiklArchive.getArtikl().getName())
                            .prisutan(artiklArchive.getPristuan())
                            .build())
                    .toList();
        }

        List<UserProstorijaDTO> users = inventuraProstorijaUserRepository.findByInventuraIdInventuraAndProstorijaIdProstorija(idInventura, idProstorija)
                .stream()
                .map(InventuraProstorijaUser::getUser)
                .map(User::toUserProstorijaDTO)
                .toList();

        return InventuraDetailProstorijaDTO.builder()
                .idProstorija(prostorija.get().getIdProstorija())
                .name(prostorija.get().getName())
                .inventuraStanje(inventura.get().getStanje())
                .artikls(artikls)
                .users(users)
                .build();

    }

    @Override
    @Transactional
    public InventuraDTO save(InventuraCreateDTO dto) {
        Inventura model;

        if (dto.getIdInventura() == 0) {
            model = new Inventura();
        } else {
            model = inventuraRepository.findById(dto.getIdInventura())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

            if (model.getStanje() == false) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Inventura je završena");
            }
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

        if (model.getIdInventura() != 0)
            inventuraProstorijaUserRepository.deleteByInventuraIdInventura(model.getIdInventura());

        inventuraProstorijaUserRepository.flush();

        dto.getRoomUserMap().forEach((roomId, users) -> {
            for (UserDTO user : users) {
                Integer id = user.getId();

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

    @Override
    public byte[] generateInventuraPdf(Integer idInventura) {
        Optional<Inventura> inventuraOpt = inventuraRepository.findById(idInventura);
        if (inventuraOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventura not found");
        }
        if (inventuraOpt.get().getStanje()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Inventura nije završena");
        }

        Inventura inventura = inventuraOpt.get();

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter.getInstance(document, out);
            document.open();

            BaseFont baseFont = BaseFont.createFont(
                    InventuraServiceImpl.class.getClassLoader().getResource("fonts/NotoSans-Regular.ttf").toString(),
                    BaseFont.IDENTITY_H,
                    BaseFont.EMBEDDED
            );

            Font boldFont = new Font(baseFont, 12, Font.BOLD);
            Font regularFont = new Font(baseFont, 11, Font.NORMAL);

            // Header - Naziv institucije
            Paragraph header = new Paragraph(inventura.getInstitution().getName(), boldFont);
            header.setAlignment(Element.ALIGN_CENTER);
            document.add(header);

            // Naslov izvješća
            Paragraph title = new Paragraph("Izvješće o obavljenoj inventuri", boldFont);
            title.setSpacingBefore(10);
            title.setSpacingAfter(20);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Datum početka i završetka
            DateTimeFormatter inputFormatter = DateTimeFormatter.ISO_DATE_TIME;
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            String formattedPocetak = ZonedDateTime.parse(inventura.getDatumPocetka(), inputFormatter).format(outputFormatter);
            String formattedZavrsetak = ZonedDateTime.parse(inventura.getDatumZavrsetka(), inputFormatter).format(outputFormatter);

            document.add(new Paragraph("Datum početka: " + formattedPocetak, regularFont));
            document.add(new Paragraph("Datum završetka: " + formattedZavrsetak, regularFont));
            document.add(new Paragraph("Akademska godina: " + inventura.getAkademskaGod(), regularFont));
            document.add(Chunk.NEWLINE);

            // Grupiranje artikala po prostorijama
            Map<Prostorija, List<InventuraArtiklArchive>> artikliPoProstoriji =
                    inventura.getArtiklArchives().stream().collect(Collectors.groupingBy(InventuraArtiklArchive::getProstorija));

            for (Map.Entry<Prostorija, List<InventuraArtiklArchive>> entry : artikliPoProstoriji.entrySet()) {
                Prostorija prostorija = entry.getKey();
                List<InventuraArtiklArchive> artikli = entry.getValue();

                Paragraph prostorijaTitle = new Paragraph("Prostorija: " + prostorija.getName(), boldFont);
                prostorijaTitle.setSpacingBefore(10);
                prostorijaTitle.setSpacingAfter(5);
                document.add(prostorijaTitle);

                String osobeStr = inventura.getInventuraProstorijaUsers().stream()
                        .filter(o -> o.getProstorija().getIdProstorija().equals(prostorija.getIdProstorija()))
                        .map(p -> p.getUser())
                        .map(u -> u.getFirstname() + " " + u.getLastname())
                        .collect(Collectors.joining(", "));
                document.add(new Paragraph("Djelatnici: " + osobeStr, regularFont));

                PdfPTable table = new PdfPTable(4);
                table.setWidthPercentage(100);
                table.setSpacingBefore(5f);
                table.setSpacingAfter(10f);
                table.setWidths(new float[]{1f, 3f, 5f, 3f});

                Stream.of("Rbr", "Inventarska oznaka", "Naziv artikla", "Status")
                        .forEach(headerTitle -> {
                            PdfPCell headerCell = new PdfPCell(new Phrase(headerTitle, boldFont));
                            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                            table.addCell(headerCell);
                        });

                int rbr = 1;
                for (InventuraArtiklArchive artikl : artikli) {
                    table.addCell(new Phrase(String.valueOf(rbr++), regularFont));
                    table.addCell(new Phrase(artikl.getArtikl().getIdArtikl().toString(), regularFont));
                    table.addCell(new Phrase(artikl.getArtikl().getName(), regularFont));
                    String status = artikl.getPristuan() ? "Prisutan" : "Nije prisutan";
                    table.addCell(new Phrase(status, regularFont));
                }

                document.add(table);
            }

            document.close();
            return out.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Greška pri generiranju PDF-a", e);
        }
    }


    public ArtiklDTO updateArticlePresence(int idArtikl, int idInventura, boolean prisutan) {
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

        if (artikl.get().getProstorija().getInstitution().getIdInstitution() != inventura.get().getInstitution().getIdInstitution()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Artikl in wrong institution");
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

        return artikl.get().ToDTO();
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
