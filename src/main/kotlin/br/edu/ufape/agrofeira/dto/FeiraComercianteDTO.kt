package br.edu.ufape.agrofeira.dto

import br.edu.ufape.agrofeira.domain.entity.FeiraComercianteEntity
import java.math.BigDecimal

data class FeiraComercianteDTO(
    val id: String,
    val comercianteId: String,
    val comercianteNome: String,
    val totalVendido: BigDecimal,
) {
    companion object {
        fun from(fc: FeiraComercianteEntity) =
            FeiraComercianteDTO(
                id = fc.id,
                comercianteId = fc.comerciante.id,
                comercianteNome = fc.comerciante.nome,
                totalVendido = fc.totalVendido,
            )
    }
}
