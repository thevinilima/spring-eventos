package com.vinicius.SpringEventos.control;

import com.vinicius.SpringEventos.model.Convidado;
import com.vinicius.SpringEventos.model.Evento;
import com.vinicius.SpringEventos.repository.ConvidadoRepository;
import com.vinicius.SpringEventos.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;


@Controller
public class EventoController {

    @Autowired
    private EventoRepository er;

    @Autowired
    private ConvidadoRepository cr;

    @RequestMapping(value = "/cadastrar-evento", method = RequestMethod.GET)
    public String form() {
        return "evento/formEvento";
    }

    @RequestMapping(value = "/cadastrar-evento", method = RequestMethod.POST)
    public String form(@Valid Evento evento, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("message", "Preencha todos os campos");
            return "redirect:/cadastrar-evento";
        }

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

    @RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
    public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo) {
        Evento evento = er.findByCodigo(codigo);
        ModelAndView mv = new ModelAndView("evento/detalhesEvento");
        mv.addObject("evento", evento);

        List<Convidado> convidados = cr.findByEvento(evento);
        mv.addObject("convidados", convidados);

        return mv;
    }

    @RequestMapping(value = "/{codigo}", method = RequestMethod.POST)
    public String detalhesEventoPost(
            @PathVariable("codigo") long codigo,
            @Valid Convidado convidado,
            BindingResult result,
            RedirectAttributes attributes
    ) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("message", "Preencha todos os campos");
            return "redirect:/{codigo}";
        }

        Evento evento = er.findByCodigo(codigo);
        convidado.setEvento(evento);
        cr.save(convidado);

        return "redirect:/{codigo}";
    }

    @RequestMapping(value = "/deletar")
    public String deletarEvento(long codigo) {
        Evento evento = er.findByCodigo(codigo);

        List<Convidado> convidados = cr.findByEvento(evento);
        if (convidados.size() > 0) return "redirect:/eventos";

        er.delete(evento);

        return "redirect:/eventos";
    }

    @RequestMapping(value = "/deletarConvidado")
    public String deletarConvidado(long codigo) {
        Convidado convidado = cr.findByCodigo(codigo);
        cr.delete(convidado);

        Evento evento = convidado.getEvento();
        String codigoEvento = evento.getCodigo() + "";

        return "redirect:/" + codigoEvento;
    }
}
