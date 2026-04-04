package br.edu.ufape.agrofeira.dto

import br.edu.ufape.agrofeira.domain.entity.ZonaEntrega
import java.math.BigDecimal

data class ZonaEntregaDTO(
    val id: String,
    val bairro: String,
    val regiao: String?,
    val taxa: BigDecimal,
) {
    companion object {
        fun from(zona: ZonaEntrega) =
            ZonaEntregaDTO(
                id = zona.id,
                bairro = zona.bairro,
                regiao = zona.regiao,
                taxa = zona.taxa,
            )
    }
}
