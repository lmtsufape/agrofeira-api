package br.edu.ufape.agrofeira.domain.repository

import br.edu.ufape.agrofeira.domain.entity.ZonaEntrega
import org.springframework.data.jpa.repository.JpaRepository

interface ZonaEntregaRepository : JpaRepository<ZonaEntrega, String> {
    fun findByBairroIgnoreCase(bairro: String): ZonaEntrega?
}
