package com.chatop.chaTop.controller;

import com.chatop.chaTop.payload.request.PostRentalRequestDto;
import com.chatop.chaTop.payload.response.CreateRentalResponseDto;
import com.chatop.chaTop.payload.response.GetAllRentalsResponseDto;
import com.chatop.chaTop.payload.response.GetRentalResponseDto;
import com.chatop.chaTop.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/rentals")
public class RentalController {

    private final RentalService rentalService;

    public RentalController(final RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping("")
    @Operation(summary = "Get all rentals", description = "Return a complete rentals list")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieve rentals list", content = @Content(schema = @Schema(implementation = GetAllRentalsResponseDto.class), examples = @ExampleObject(value = "{\"rentals\": [" +
                    "        {" +
                    "            \"id\": 9," +
                    "            \"name\": \"Florian\"," +
                    "            \"surface\": 30," +
                    "            \"price\": 40," +
                    "            \"picture\": \"http://res.cloudinary.com/dmpeq0azi/image/upload/v1745068802/file.jpg\"," +
                    "            \"description\": \"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\"," +
                    "            \"ownerId\": 9," +
                    "            \"created_at\": \"2025/04/19\"," +
                    "            \"updated_at\": \"2025/04/19\"" +
                    "        }" +
                    "    ]}"), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Unauthorized request", content = @Content(schema = @Schema())),
    })
    public ResponseEntity<GetAllRentalsResponseDto> getRentals() {
        GetAllRentalsResponseDto rentals = rentalService.getRentals();
        return new ResponseEntity<GetAllRentalsResponseDto>(rentals, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a specific rental by id", description = "Return a rentals per as id")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieve rental", content = @Content(schema = @Schema(implementation = GetAllRentalsResponseDto.class), examples = @ExampleObject(value = "{\"id\": 9," +
                    "    \"name\": \"Florian\"," +
                    "    \"surface\": 30," +
                    "    \"price\": 40," +
                    "    \"picture\": \"http://res.cloudinary.com/dmpeq0azi/image/upload/v1745068802/file.jpg\"," +
                    "    \"description\": \"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\"," +
                    "    \"ownerId\": 9," +
                    "    \"created_at\": \"2025/04/19\"," +
                    "    \"updated_at\": \"2025/04/19\"}"), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Unauthorized request", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Rental not found", content = @Content(schema = @Schema())),
    })
    public ResponseEntity<GetRentalResponseDto> getRentalById(@PathVariable final int id) {
        GetRentalResponseDto rental = rentalService.getRentalById(id);
        return new ResponseEntity<>(rental, HttpStatus.OK);
    }

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Create a new rental")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Rental created !", content = @Content(schema = @Schema(implementation = CreateRentalResponseDto.class), examples = @ExampleObject(value = "{\"message\": \"Rental created !\"}"), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Unauthorized request", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "400", description = "Failed to create rental.", content = @Content(schema = @Schema())),
    })
    public ResponseEntity<CreateRentalResponseDto> addRental(@Parameter(description = "File to upload") @RequestPart(value = "picture") MultipartFile picture, @Valid @ModelAttribute PostRentalRequestDto request, JwtAuthenticationToken token) {
        CreateRentalResponseDto rentalResponse = rentalService.addRental(picture, request, token);
        return new ResponseEntity<>(rentalResponse, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Update existing rental by id")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rental updated !", content = @Content(schema = @Schema(implementation = CreateRentalResponseDto.class), examples = @ExampleObject(value = "{\"message\": \"Rental updated !\"}"), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Unauthorized request", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "400", description = "Failed to update rental.", content = @Content(schema = @Schema())),
    })
    public ResponseEntity<CreateRentalResponseDto> updateRental(@PathVariable int id, @Valid @ModelAttribute PostRentalRequestDto request, JwtAuthenticationToken token) {
        CreateRentalResponseDto rentalResponse = rentalService.updateRental(id, request, token);
        return new ResponseEntity<>(rentalResponse, HttpStatus.OK);
    }
}
