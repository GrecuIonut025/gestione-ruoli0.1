package it.lavori.gestione_ruoli.controller.api;

import it.lavori.gestione_ruoli.dto.UtenteDto;
import it.lavori.gestione_ruoli.model.Utente;
import it.lavori.gestione_ruoli.service.UtenteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UtenteController {

    Logger logger = LoggerFactory.getLogger(UtenteController.class);

    @Autowired
    UtenteService utenteService;

//	@GetMapping("/utenti/{pageNo}/{pageSize}")
//	@ResponseStatus(value = HttpStatus.OK)
//	public List<UtenteDto>getPaginated(@PathVariable int pageNo,@PathVariable int pagesize){
//		return utenteService.findPaginated(pageNo, pagesize);
//	}

    @GetMapping("/utenti")
    @ResponseStatus(value = HttpStatus.OK)
    public Page<UtenteDto> getAllFilteredByNomeAndCognome(@RequestParam(defaultValue = "") String nomeFilter,
                                                          @RequestParam(defaultValue = "") String cognomeFilter,
                                                          @RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "30") int size) {


                return utenteService.findByFirstNameLikeAndLastNameLike(nomeFilter, cognomeFilter, page, size);
    }



    //
    @GetMapping("/utente")
    public UtenteDto getById(@RequestParam Long codice) {
        try {
            return utenteService.getById(codice);
        } catch (ChangeSetPersister.NotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }




 /*   @GetMapping("/utente")
    public ResponseEntity<UtenteDto> getById(@RequestParam Long codice) {
        UtenteDto utente= utenteService.getById(codice)
       .orElseThrow(()->new ResourceNotFoundException("The user with id"+codice+" was NOT Found!"));
        return ResponseEntity.ok().body(utente);

        }
*/
    


    @GetMapping("/getUtenteByNome")
    public ResponseEntity<?> getUtenteByNome(Pageable page, @RequestParam String nome) {
        Page<UtenteDto> utente = (Page<UtenteDto>) utenteService.getUtenteByNome(page, nome);
        if (utente.hasContent())
            return new ResponseEntity<>(utente.getContent(), HttpStatus.OK);
        else
            return new ResponseEntity<>("Nessun utente trovato", HttpStatus.BAD_REQUEST);
    }

//	}

    @PostMapping("/insertUtente")
    @ResponseStatus(value = HttpStatus.CREATED)
    public UtenteDto insert(@RequestBody Utente utente) {
        return utenteService.insert(utente);

    }

    @DeleteMapping("/deleteUtente")
    public HttpStatus delete(@RequestParam Long codice) {
    utenteService.delete(codice);
        return HttpStatus.OK;
    }

    //
    @PutMapping("/updateUtente")
    public UtenteDto update(@RequestBody Utente utente) {
        return utenteService.update(utente);
    }



}
