package br.com.ecofeira.domain.repository

import br.com.ecofeira.domain.entity.Comerciante
import org.springframework.data.jpa.repository.JpaRepository

interface ComercianteRepository : JpaRepository<Comerciante, String> {
    fun findByNomeContainingIgnoreCase(nome: String): List<Comerciante>
}