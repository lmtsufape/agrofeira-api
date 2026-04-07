package br.edu.ufape.agrofeira.controller

import br.edu.ufape.agrofeira.domain.entity.Feira
import br.edu.ufape.agrofeira.domain.enums.StatusFeira
import br.edu.ufape.agrofeira.dto.FeiraComercianteDTO
import br.edu.ufape.agrofeira.dto.FeiraDTO
import br.edu.ufape.agrofeira.service.FeiraService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

data class CriarFeiraRequest(
    val feira: Feira,
    val comercianteIds: List<String>,
    val itemIds: List<String>,
)

@RestController
@RequestMapping("/api/feiras")
class FeiraController(
    private val feiraService: FeiraService,
) {
    @GetMapping
    fun listarTodas() =
        ResponseEntity.ok(
            feiraService.listarTodas().map { FeiraDTO.from(it) },
        )

    @GetMapping("/{id}")
    fun buscarPorId(
        @PathVariable id: String,
    ) = ResponseEntity.ok(FeiraDTO.from(feiraService.buscarPorId(id)))

    @PostMapping
    fun criar(
        @RequestBody request: CriarFeiraRequest,
    ) = ResponseEntity.ok(
        FeiraDTO.from(
            feiraService.criar(request.feira, request.comercianteIds, request.itemIds),
        ),
    )

    @PatchMapping("/{id}/status")
    fun atualizarStatus(
        @PathVariable id: String,
        @RequestBody status: StatusFeira,
    ) = ResponseEntity.ok(FeiraDTO.from(feiraService.atualizarStatus(id, status)))

    @GetMapping("/{feiraId}/comerciante/{comercianteId}")
    fun buscarFeiraComerciantePorFeiraEComerciante(
        @PathVariable feiraId: String,
        @PathVariable comercianteId: String,
    ) = ResponseEntity.ok(
        feiraService
            .buscarFeiraComerciantePorFeiraEComerciante(feiraId, comercianteId)
            ?.let { FeiraComercianteDTO.from(it) }
            ?: throw RuntimeException("Relação Feira/Comerciante não encontrada"),
    )
}
