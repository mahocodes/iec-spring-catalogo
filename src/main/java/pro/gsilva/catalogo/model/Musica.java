package pro.gsilva.catalogo.model;


import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_musica")
public class Musica {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String autor;

    @NotBlank
    private String titulo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", locale = "UTC-03")
    private LocalDate data;

    @NotBlank
    @Lob
    private String letra;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
}
