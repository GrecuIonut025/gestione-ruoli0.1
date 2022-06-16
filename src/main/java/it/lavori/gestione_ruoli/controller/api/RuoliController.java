package it.lavori.gestione_ruoli.controller.api;

import it.lavori.gestione_ruoli.model.Ruoli;
import it.lavori.gestione_ruoli.model.Ruolo;
import it.lavori.gestione_ruoli.service.RuoloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RuoliController {

    Logger logger = LoggerFactory.getLogger(UtenteController.class);

    @Autowired
    RuoloService ruoliService;

    @GetMapping("/ruoli")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Ruolo> getAll() {
        return ruoliService.getAll();
    }

    //
    @GetMapping("/ruolo")
    public Ruolo getById(@RequestParam Ruoli id) {
        return ruoliService.findById(id);

    }


//	}

    @PostMapping("/insertRuolo")
    public Ruolo insert(@RequestBody Ruolo ruolo) {
        return ruoliService.insert(ruolo);
    }

    @DeleteMapping("/deleteRuolo")
    public HttpStatus delete(@RequestParam Ruoli id) {
        ruoliService.delete(id);
        return HttpStatus.OK;
    }

    //
    @PutMapping("/updateRuolo")
    public Ruolo update(@RequestBody Ruolo ruolo) {
        return ruoliService.update(ruolo);
    }

}