package com.vinicius.SpringEventos.control;

import com.vinicius.SpringEventos.model.Evento;
import com.vinicius.SpringEventos.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class EventoController {

    @Autowired
    private EventoRepository er;
    
    @RequestMapping(value = "/cadastrar-evento", method = RequestMethod.GET)
    public String form() {
        return "evento/formEvento";
    }

    @RequestMapping(value = "/cadastrar-evento", method = RequestMethod.POST)
    public String form(Evento evento) {
        er.save(evento);

        return "redirect:/cadastrar-evento";
    }

    @RequestMapping("/eventos")
    public ModelAndView listaEventos() {
        ModelAndView mv = new ModelAndView("index");
        Iterable<Evento> eventos = er.findAll();
        mv.addObject("eventos", eventos);

        return mv;
    }

    @RequestMapping("/{codigo}")
    public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo) {
        Evento evento = er.findByCodigo(codigo);
        ModelAndView mv = new ModelAndView("evento/detalhesEvento");
        mv.addObject("evento", evento);

        return mv;
    }
}
