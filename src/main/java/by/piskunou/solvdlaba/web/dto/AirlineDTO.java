package by.piskunou.solvdlaba.web.dto;

import by.piskunou.solvdlaba.web.dto.groups.OnCreate;
import by.piskunou.solvdlaba.web.dto.groups.OnSearch;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Airline info")
public class AirlineDTO {

    @Schema(description = "The airline's unique identification id")
    @Null(groups = {OnCreate.class, OnSearch.class}, message = "Id should be null")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UUID id;

    @Schema(description = "Full airline's name")
    @NotBlank(groups = OnCreate.class, message = "Name should be not blank")
    @Size(max = 50, groups = {OnCreate.class, OnSearch.class}, message = "Name should be less than 50 characters")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;

    @Schema(description = "The airline's unique IATA(International Air Transport Association) code")
    @Size(max = 2, groups = OnSearch.class, message = "IATA code should be less than 2 characters")
    @Size(min = 2, max = 2, groups = OnCreate.class, message = "IATA code length should be 2")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String iata;

    @Schema(description = "The airline's unique ICAO(International Civil Aviation Organization) code")
    @Size(max = 3, groups = OnSearch.class, message = "ICAO code should be less than 3 characters")
    @Size(min = 3, max = 3, groups = OnCreate.class, message = "ICAO code length should be 3")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String icao;

    @Schema(description = "Airline's callsign. Short name")
    @NotBlank(groups = OnCreate.class, message = "Callsign should be not blank")
    @Size(max = 30, groups = {OnCreate.class, OnSearch.class}, message = "Callsign should be less than 30 characters")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String callsign;

}
