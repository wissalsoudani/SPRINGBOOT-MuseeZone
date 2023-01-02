package tn.esprit.exam.Controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tn.esprit.exam.Entities.*;
import tn.esprit.exam.Repositories.MuseeREPO;
import tn.esprit.exam.Repositories.OeuvreArtREPO;
import tn.esprit.exam.Repositories.PersonnelREPO;
import tn.esprit.exam.Repositories.ZoneREPO;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("rest")
public class Controller {

    @Autowired
    MuseeREPO museeREPO;
    @Autowired
    OeuvreArtREPO oeuvreArtREPO;
    @Autowired
    PersonnelREPO personnelREPO;
    @Autowired
    ZoneREPO zoneREPO;

    @PostMapping("/addMusee")
    public Musee ajouterMusee(@RequestBody Musee musee){
        return museeREPO.save(musee);}

//ManytoOne
    @PostMapping("/addZoneAmusee/{idMusee}")
    public Zone ajouterZoneEtAffecterAMusee (@RequestBody Zone zone, @PathVariable Long idMusee) {
        Musee musee = museeREPO.findById(idMusee).orElse(null);
        if (musee != null && zone != null) {
            zone.setMusee(musee);
        } zoneREPO.save(zone);
        return zone;
    }
    
//ManytoOne
    @PostMapping("/addOeuvreArtAzone/{idZone}")
    public OeuvreArt ajouterOeuvreArtEtAffecterAZone(@RequestBody OeuvreArt oeuvreArt, @PathVariable Long idZone) {
        Zone zone = zoneREPO.findById(idZone).orElse(null);
        if (zone != null && oeuvreArt != null) {
            oeuvreArt.setZones(zone);
        }
        oeuvreArtREPO.save(oeuvreArt);
        return (oeuvreArt);
    }

    @Transactional
    @PostMapping("/addpersonnelAzone/{idZone}/{idGardien}/{Directeur_id}")
    public Personnel affecterPersonnelsAZone(@PathVariable Long idZone, @PathVariable Long idGardien, @PathVariable Long Directeur_id) throws Exception {
        Zone zone = zoneREPO.findById(idZone).orElse(null);
        Personnel gardien = personnelREPO.findById(idGardien).orElse(null);
        Personnel directeur = personnelREPO.findById(Directeur_id).orElse(null);
        if (zone != null &&  gardien != null && directeur != null) {

            gardien.setZone(zone);
            personnelREPO.save(gardien);
            zoneREPO.save(zone);

            zone.setDIRECTEUR(directeur);
            directeur.setZonedirecteur(zone);
            personnelREPO.save(directeur);
            zoneREPO.save(zone);
        }

        return gardien;
    }

    @GetMapping("/titreTableauParMuseeEtDirection/{idMusee}/{direction}")
    public List<String> titreTableauParMuseeEtDirection(@PathVariable Long idMusee,@PathVariable Direction direction) {

        Musee museum = museeREPO.findById(idMusee).orElse(null);
        List<Zone> directorZones = museum.getZones().stream()
                .filter(z -> z.getDirection().equals(direction))
                .collect(Collectors.toList());

        List<OeuvreArt> directorArtworks = directorZones.stream()
                .flatMap(z -> z.getOeuvreArts().stream())
                .collect(Collectors.toList());

        return directorArtworks.stream()
                .map(OeuvreArt::getTitreTableau)
                .collect(Collectors.toList());
    }

    @Scheduled(fixedRate = 60000)
    public void desaffecterDirecteursZoneInactif () {
        List<Zone> inactiveZones = zoneREPO.findByActif(false);
        for (Zone zone : inactiveZones) {
            Personnel D = zone.getDIRECTEUR();
            zone.setDIRECTEUR(null);
            zoneREPO.save(zone);
        }
    }

}
