package pro.gsilva.catalogo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pro.gsilva.catalogo.model.Categoria;

public interface CategoriaService {
    Page<Categoria> findAll(Pageable pageable);

    Categoria findById(Long id);

    void save(Categoria categoria);

    void delete(Long id);
}
