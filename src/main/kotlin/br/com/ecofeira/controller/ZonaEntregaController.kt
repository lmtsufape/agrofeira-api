package br.com.ecofeira.controller

import br.com.ecofeira.domain.entity.ZonaEntrega
import br.com.ecofeira.dto.ZonaEntregaDTO
import br.com.ecofeira.service.ZonaEntregaService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/zonas-entrega")
class ZonaEntregaController(
    private val zonaEntregaService: ZonaEntregaService
) {
    @GetMapping
    fun listarTodas() = ResponseEntity.ok(
        zonaEntregaService.listarTodas().map { ZonaEntregaDTO.from(it) }
    )

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: String) =
        ResponseEntity.ok(ZonaEntregaDTO.from(zonaEntregaService.buscarPorId(id)))

    @PostMapping
    fun criar(@RequestBody zonaEntrega: ZonaEntrega) =
        ResponseEntity.ok(ZonaEntregaDTO.from(zonaEntregaService.criar(zonaEntrega)))

    @PutMapping("/{id}")
    fun atualizar(@PathVariable id: String, @RequestBody zonaEntrega: ZonaEntrega) =
        ResponseEntity.ok(ZonaEntregaDTO.from(zonaEntregaService.atualizar(id, zonaEntrega)))
}