package br.edu.ifmg.escola_2026.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.java.Log;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "usuario")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Log

public class Usuario {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String nome;
    private String telefone;
    private String email;
    private String senha;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant criadoEm;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant atualizadoEm;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuario_perfil",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_perfil")
    )

    @ElementCollection
    private Set<Perfil> perfis = new HashSet<Perfil>();

    public void addRole(Perfil perfil){
        this.perfis.add(perfil);
    }

    public boolean hasRole(Perfil perfil){
        return this.perfis.contains(perfil);
    }

    @PrePersist
    public void prePersist(){
        this.criadoEm = Instant.now();
    }

    @PreUpdate
    public void preUpdate(){
        this.atualizadoEm = Instant.now();
    }
}
