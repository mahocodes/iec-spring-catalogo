package pro.gsilva.catalogo.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pro.gsilva.catalogo.model.Categoria;
import pro.gsilva.catalogo.repository.CategoriaRepository;
import pro.gsilva.catalogo.service.CategoriaService;

import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.length;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public Page<Categoria> findAll(Pageable pageable) {
        return categoriaRepository.findAll(pageable);
    }

    @Override
    public Categoria findById(Long id) {
        return categoriaRepository.findById(id).orElse(new Categoria());
    }

    @Override
    public void save(Categoria categoria) {
        if (isCategoriaNomeValido(categoria)) {
            categoriaRepository.save(categoria);
        } else {
            throw new RuntimeException("Nome da categoria deve ser válido (até no max. 30 carac.)");
        }
    }

    private boolean isCategoriaNomeValido(Categoria categoria) {
        return nonNull(categoria.getNome()) && isBlank(categoria.getNome()) || length(categoria.getNome()) <= 30;
    }

    @Override
    public void delete(Long id) {
        categoriaRepository.deleteById(id);
    }
}
