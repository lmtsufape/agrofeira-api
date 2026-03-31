package br.com.ecofeira.service

import br.com.ecofeira.domain.entity.Repasse
import br.com.ecofeira.domain.enums.StatusRepasse
import br.com.ecofeira.domain.repository.FeiraComercianteRepository
import br.com.ecofeira.domain.repository.RepasseRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.time.LocalDateTime

@Service
class RepasseService(
    private val repasseRepository: RepasseRepository,
    private val feiraComercianteRepository: FeiraComercianteRepository
) {
    fun listarTodos(): List<Repasse> = repasseRepository.findAll()

    fun listarPorFeira(feiraId: String): List<Repasse> =
        repasseRepository.findByFeiraComercianteEntityFeiraId(feiraId)

    fun listarPorComerciante(comercianteId: String): List<Repasse> =
        repasseRepository.findByFeiraComercianteEntityComercianteId(comercianteId)

    fun listarTotalPorComerciantesNaFeira(feiraId: String): List<Map<String, Any>> =
        feiraComercianteRepository.findByFeiraId(feiraId).map { fc ->
            mapOf(
                "comercianteId" to fc.comerciante.id,
                "comercianteNome" to fc.comerciante.nome,
                "totalVendido" to fc.totalVendido
            )
        }

    @Transactional
    fun registrarRepasse(feiraComercianteId: String, taxaAssociacao: BigDecimal): Repasse {
        val feiraComercianteEntity = feiraComercianteRepository.findById(feiraComercianteId)
            .orElseThrow { RuntimeException("Feira/Comerciante não encontrado") }

        val valorBruto = feiraComercianteEntity.totalVendido
        val valorLiquido = valorBruto.subtract(taxaAssociacao)

        return repasseRepository.save(
            Repasse(
                feiraComercianteEntity = feiraComercianteEntity,
                valorBruto = valorBruto,
                taxaAssociacao = taxaAssociacao,
                valorLiquido = valorLiquido,
                status = StatusRepasse.PAGO,
                repassadoEm = LocalDateTime.now()
            )
        )
    }
}