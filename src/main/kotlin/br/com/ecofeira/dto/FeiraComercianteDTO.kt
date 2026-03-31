package br.com.ecofeira.dto

import br.com.ecofeira.domain.entity.FeiraComercianteEntity
import java.math.BigDecimal

data class FeiraComercianteDTO(
    val id: String,
    val comercianteId: String,
    val comercianteNome: String,
    val totalVendido: BigDecimal
) {
    companion object {
        fun from(fc: FeiraComercianteEntity) = FeiraComercianteDTO(
            id = fc.id,
            comercianteId = fc.comerciante.id,
            comercianteNome = fc.comerciante.nome,
            totalVendido = fc.totalVendido
        )
    }
}