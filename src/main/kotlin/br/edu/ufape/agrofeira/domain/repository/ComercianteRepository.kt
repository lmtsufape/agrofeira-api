package br.edu.ufape.agrofeira.domain.repository

import br.edu.ufape.agrofeira.domain.entity.Comerciante
import org.springframework.data.jpa.repository.JpaRepository

interface ComercianteRepository : JpaRepository<Comerciante, String> {
    fun findByNomeContainingIgnoreCase(nome: String): List<Comerciante>
}
