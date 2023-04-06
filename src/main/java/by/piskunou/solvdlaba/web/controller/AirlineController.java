package by.piskunou.solvdlaba.web.controller;

import by.piskunou.solvdlaba.domain.AirlineAggregate;
import by.piskunou.solvdlaba.service.AirlineCommandService;
import by.piskunou.solvdlaba.service.AirlineQueryService;
import by.piskunou.solvdlaba.web.dto.AirlineDTO;
import by.piskunou.solvdlaba.web.dto.groups.OnCreate;
import by.piskunou.solvdlaba.web.mapper.AirlineMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/airlines")
@RequiredArgsConstructor
@Tag(name = "Airlines", description = "Admin's methods for work with airlines")
public class AirlineController {

    private final AirlineCommandService commandService;
    private final AirlineQueryService queryService;
    private final AirlineMapper mapper;

    @GetMapping
    @Operation(summary = "Information about all airlines")
    public List<AirlineDTO> findAll() {
        return mapper.toDTO( queryService.findAll() );
    }

    @GetMapping("/{id}")
    @Operation(summary = "Information about certain airlines by id")
    @Parameter(name = "id", description = "The airline unique identification number")
    public AirlineDTO findById(@PathVariable UUID id) {
        return mapper.toDTO( queryService.findById(id) );
    }

    @GetMapping("/search")
    @Operation(summary = "Search for airlines")
    @Parameter(name = "inquiry", description = "Search airline(s) with fields like in the inquiry")
    public List<AirlineDTO> search(String inquiry) {
        return mapper.toDTO( queryService.search(inquiry) );
    }

    @GetMapping("/exists/{id}")
    @Operation(summary = "Check if an airline exists")
    @Parameter(name = "id", description = "The airline unique identification number")
    public boolean existsById(@PathVariable UUID id) {
        return queryService.isExistsById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create airline")
    @Parameter(name = "dto", description = "Created airline")
    public void create(@RequestBody @Validated(OnCreate.class) AirlineDTO dto) {
        AirlineAggregate airline = mapper.toEntity(dto);
        commandService.create(airline);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update airline by id")
    @Parameters({
            @Parameter(name = "id", description = "The airline's unique identification number"),
            @Parameter(name = "dto", description = "Updated airline")
    })
    public void updateById(@PathVariable UUID id, @RequestBody @Validated(OnCreate.class) AirlineDTO dto) {
        AirlineAggregate airline = mapper.toEntity(dto);
        commandService.updateById(id, airline);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remove airline by id")
    @Parameter(name = "id", description = "The airline's unique identification number")
    public void removeById(@PathVariable UUID id) {
        commandService.removeById(id);
    }

}
