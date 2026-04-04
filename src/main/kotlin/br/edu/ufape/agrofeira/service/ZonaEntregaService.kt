package br.edu.ufape.agrofeira.service

import br.edu.ufape.agrofeira.domain.entity.ZonaEntrega
import br.edu.ufape.agrofeira.domain.repository.ZonaEntregaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ZonaEntregaService(
    private val zonaEntregaRepository: ZonaEntregaRepository,
) {
    fun listarTodas(): List<ZonaEntrega> = zonaEntregaRepository.findAll()

    fun buscarPorId(id: String): ZonaEntrega =
        zonaEntregaRepository.findById(id).orElseThrow { RuntimeException("Zona de entrega não encontrada") }

    fun buscarPorBairro(bairro: String): ZonaEntrega? = zonaEntregaRepository.findByBairroIgnoreCase(bairro)

    @Transactional
    fun criar(zonaEntrega: ZonaEntrega): ZonaEntrega = zonaEntregaRepository.save(zonaEntrega)

    @Transactional
    fun atualizar(
        id: String,
        zonaEntrega: ZonaEntrega,
    ): ZonaEntrega {
        val existente = buscarPorId(id)
        return zonaEntregaRepository.save(
            existente.copy(
                bairro = zonaEntrega.bairro,
                regiao = zonaEntrega.regiao,
                taxa = zonaEntrega.taxa,
            ),
        )
    }
}
