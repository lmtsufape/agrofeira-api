package br.com.ecofeira.domain.repository

import br.com.ecofeira.domain.entity.ZonaEntrega
import org.springframework.data.jpa.repository.JpaRepository

interface ZonaEntregaRepository : JpaRepository<ZonaEntrega, String> {
    fun findByBairroIgnoreCase(bairro: String): ZonaEntrega?
}