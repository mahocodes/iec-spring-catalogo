package pro.gsilva.catalogo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pro.gsilva.catalogo.model.Categoria;
import pro.gsilva.catalogo.service.CategoriaService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @RequestMapping(value="/categorias", method= RequestMethod.GET)
    public ModelAndView getAll(@RequestParam("page") Optional<Integer> page,
                                      @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        ModelAndView mv = new ModelAndView("categorias");
        Page<Categoria> categorias = categoriaService.findAll(PageRequest.of(currentPage - 1, pageSize));
        mv.addObject("categorias", categorias);

        int totalPages = categorias.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            mv.addObject("pageNumbers", pageNumbers);
        }

        return mv;
    }


    @RequestMapping(value="/addCategoria", method=RequestMethod.GET)
    public String getCategoriaForm(Categoria categoria) {
        return "categoriaForm";
    }

    @RequestMapping(value="/addCategoria", method=RequestMethod.POST)
    public ModelAndView save(@Valid @ModelAttribute("categoria") Categoria categoria,
                                        BindingResult result, Model model) {
        if (result.hasErrors()) {
            ModelAndView categoriaForm = new ModelAndView("categoriaForm");
            categoriaForm.addObject("mensagem", "Verifique os errors do formulário");
            return categoriaForm;
        }
        categoriaService.save(categoria);
        return new ModelAndView("redirect:/categorias");
    }

    @RequestMapping(value = "/categorias/edit/{id}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable("id") long id) {
        ModelAndView mv = new ModelAndView("categoriaForm");
        Categoria categoria = categoriaService.findById(id);
        mv.addObject("categoria", categoria);
        return mv;
    }

    @DeleteMapping(value = "/categorias/{id}")
    public String delete(@PathVariable("id") long id) {
        categoriaService.delete(id);
        return "redirect:/categorias";
    }
}
