package pro.gsilva.catalogo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.gsilva.catalogo.model.Musica;
import pro.gsilva.catalogo.repository.CatalogoRepository;
import pro.gsilva.catalogo.service.CatalogoService;

import static java.util.Objects.nonNull;

@Service
public class CatalogoServiceImpl implements CatalogoService {

    @Autowired
    private CatalogoRepository catalogoRepository;


    @Override
    public List<Musica> findAll() {
        return catalogoRepository.findAll();
    }

    @Override
    public Musica findById(long id) {
        return catalogoRepository.findById(id).orElse(new Musica());
    }

    @Override
    public void save(Musica musica) {
        if (nonNull(musica) && nonNull(musica.getCategoria())) {
            catalogoRepository.save(musica);
        }
    }

    @Override
    public void excluir(long id) {
        catalogoRepository.deleteById(id);
    }

    @Override
    public List<Musica> findByTitulo(String titulo) {
        String tituloLike = titulo + "%";
        return catalogoRepository.findAllByTituloIsLike(tituloLike);
    }

}