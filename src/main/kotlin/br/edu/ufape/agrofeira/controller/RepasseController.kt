package br.edu.ufape.agrofeira.controller

import br.edu.ufape.agrofeira.dto.RepasseDTO
import br.edu.ufape.agrofeira.service.RepasseService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal

data class RegistrarRepasseRequest(
    val feiraComercianteId: String,
    val taxaAssociacao: BigDecimal = BigDecimal.ZERO,
)

@RestController
@RequestMapping("/api/repasses")
class RepasseController(
    private val repasseService: RepasseService,
) {
    @GetMapping
    fun listarTodos() =
        ResponseEntity.ok(
            repasseService.listarTodos().map { RepasseDTO.from(it) },
        )

    @GetMapping("/feira/{feiraId}")
    fun listarPorFeira(
        @PathVariable feiraId: String,
    ) = ResponseEntity.ok(repasseService.listarPorFeira(feiraId).map { RepasseDTO.from(it) })

    @GetMapping("/comerciante/{comercianteId}")
    fun listarPorComerciante(
        @PathVariable comercianteId: String,
    ) = ResponseEntity.ok(repasseService.listarPorComerciante(comercianteId).map { RepasseDTO.from(it) })

    @GetMapping("/feira/{feiraId}/totais")
    fun listarTotaisPorFeira(
        @PathVariable feiraId: String,
    ) = ResponseEntity.ok(repasseService.listarTotalPorComerciantesNaFeira(feiraId))

    @PostMapping
    fun registrarRepasse(
        @RequestBody request: RegistrarRepasseRequest,
    ) = ResponseEntity.ok(
        RepasseDTO.from(
            repasseService.registrarRepasse(
                request.feiraComercianteId,
                request.taxaAssociacao,
            ),
        ),
    )
}
