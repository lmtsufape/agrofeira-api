package br.com.ecofeira.domain.repository

import br.com.ecofeira.domain.entity.FeiraComercianteEntity
import org.springframework.data.jpa.repository.JpaRepository

interface FeiraComercianteRepository : JpaRepository<FeiraComercianteEntity, String> {
    fun findByFeiraId(feiraId: String): List<FeiraComercianteEntity>
    fun findByComercianteId(comercianteId: String): List<FeiraComercianteEntity>
    fun findByFeiraIdAndComercianteId(feiraId: String, comercianteId: String): FeiraComercianteEntity?
}