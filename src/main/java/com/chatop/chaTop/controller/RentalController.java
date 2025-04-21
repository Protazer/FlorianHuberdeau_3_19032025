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

/**
 * Controller for handling rental-related operations.
 */
@RestController
@RequestMapping("api/rentals")
public class RentalController {

	private final RentalService rentalService;

	public RentalController(final RentalService rentalService) {
		this.rentalService = rentalService;
	}

	/**
	 * Retrieves all rentals.
	 *
	 * @return ResponseEntity<GetAllRentalsResponseDto> with an array of all stored rentals.
	 */
	@GetMapping("")
	@Operation(summary = "Get all rentals", description = "Return a complete rentals list")
	@SecurityRequirement(name = "Bearer Authentication")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful retrieve rentals list", content = @Content(schema = @Schema(implementation = GetAllRentalsResponseDto.class), examples = @ExampleObject(value = "{\"rentals\": [\n" +
					"  {\n" +
					"\t\"id\": 1,\n" +
					"\t\"name\": \"test house 1\",\n" +
					"\t\"surface\": 432,\n" +
					"\t\"price\": 300,\n" +
					"\t\"picture\": \"https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg\",\n" +
					"\t\"description\": \"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam a lectus eleifend, varius massa ac, mollis tortor. Quisque ipsum nulla, faucibus ac metus a, eleifend efficitur augue. Integer vel pulvinar ipsum. Praesent mollis neque sed sagittis ultricies. Suspendisse congue ligula at justo molestie, eget cursus nulla tincidunt. Pellentesque elementum rhoncus arcu, viverra gravida turpis mattis in. Maecenas tempor elementum lorem vel ultricies. Nam tempus laoreet eros, et viverra libero tincidunt a. Nunc vel nisi vulputate, sodales massa eu, varius erat.\",\n" +
					"\t\"owner_id\": 1,\n" +
					"\t\"created_at\": \"2012/12/02\",\n" +
					"\t\"updated_at\": \"2014/12/02\"  \n" +
					"},\n" +
					"{\n" +
					"\t\"id\": 1,\n" +
					"\t\"name\": \"test house 2\",\n" +
					"\t\"surface\": 154,\n" +
					"\t\"price\": 200,\n" +
					"\t\"picture\": \"https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg\",\n" +
					"\t\"description\": \"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam a lectus eleifend, varius massa ac, mollis tortor. Quisque ipsum nulla, faucibus ac metus a, eleifend efficitur augue. Integer vel pulvinar ipsum. Praesent mollis neque sed sagittis ultricies. Suspendisse congue ligula at justo molestie, eget cursus nulla tincidunt. Pellentesque elementum rhoncus arcu, viverra gravida turpis mattis in. Maecenas tempor elementum lorem vel ultricies. Nam tempus laoreet eros, et viverra libero tincidunt a. Nunc vel nisi vulputate, sodales massa eu, varius erat.\",\n" +
					"\t\"owner_id\": 2,\n" +
					"\t\"created_at\": \"2012/12/02\",\n" +
					"\t\"updated_at\": \"2014/12/02\"  \n" +
					"},{\n" +
					"\t\"id\": 3,\n" +
					"\t\"name\": \"test house 3\",\n" +
					"\t\"surface\": 234,\n" +
					"\t\"price\": 100,\n" +
					"\t\"picture\": \"https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg\",\n" +
					"\t\"description\": \"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam a lectus eleifend, varius massa ac, mollis tortor. Quisque ipsum nulla, faucibus ac metus a, eleifend efficitur augue. Integer vel pulvinar ipsum. Praesent mollis neque sed sagittis ultricies. Suspendisse congue ligula at justo molestie, eget cursus nulla tincidunt. Pellentesque elementum rhoncus arcu, viverra gravida turpis mattis in. Maecenas tempor elementum lorem vel ultricies. Nam tempus laoreet eros, et viverra libero tincidunt a. Nunc vel nisi vulputate, sodales massa eu, varius erat.\",\n" +
					"\t\"owner_id\": 1,\n" +
					"\t\"created_at\": \"2012/12/02\",\n" +
					"\t\"updated_at\": \"2014/12/02\"  \n" +
					"}\n" +
					"  \n" +
					"  ]}"), mediaType = "application/json")),
			@ApiResponse(responseCode = "401", description = "Unauthorized request", content = @Content(schema = @Schema())),
	})
	public ResponseEntity<GetAllRentalsResponseDto> getRentals() {
		GetAllRentalsResponseDto rentals = rentalService.getRentals();
		return new ResponseEntity<GetAllRentalsResponseDto>(rentals, HttpStatus.OK);

	}

	/**
	 * Retrieves information about a specific rental.
	 *
	 * @param id The ID of the rental to retrieve.
	 * @return ResponseEntity<GetRentalResponseDto> with one specific rental content.
	 */
	@GetMapping("/{id}")
	@Operation(summary = "Get a specific rental by id", description = "Return a rentals per as id")
	@SecurityRequirement(name = "Bearer Authentication")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful retrieve rental", content = @Content(schema = @Schema(implementation = GetAllRentalsResponseDto.class), examples = @ExampleObject(value = "{\t\"id\": 1,\n" +
					"\t\"name\": \"dream house\",\n" +
					"\t\"surface\": 24,\n" +
					"\t\"price\": 30,\n" +
					"\t\"picture\": [\"https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg\"],\n" +
					"\t\"description\": \"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam a lectus eleifend, varius massa ac, mollis tortor. Quisque ipsum nulla, faucibus ac metus a, eleifend efficitur augue. Integer vel pulvinar ipsum. Praesent mollis neque sed sagittis ultricies. Suspendisse congue ligula at justo molestie, eget cursus nulla tincidunt. Pellentesque elementum rhoncus arcu, viverra gravida turpis mattis in. Maecenas tempor elementum lorem vel ultricies. Nam tempus laoreet eros, et viverra libero tincidunt a. Nunc vel nisi vulputate, sodales massa eu, varius erat.\",\n" +
					"\t\"owner_id\": 1,\n" +
					"\t\"created_at\": \"2012/12/02\",\n" +
					"\t\"updated_at\": \"2014/12/02\"  }"), mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Rental not found", content = @Content(schema = @Schema(), examples = @ExampleObject(value = "{\"message\": \"Rental not found.\"}"), mediaType = "application/json")),
			@ApiResponse(responseCode = "401", description = "Unauthorized request", content = @Content(schema = @Schema())),
	})
	public ResponseEntity<GetRentalResponseDto> getRentalById(@PathVariable final int id) {
		GetRentalResponseDto rental = rentalService.getRentalById(id);
		return new ResponseEntity<>(rental, HttpStatus.OK);
	}

	/**
	 * Add a new rental store picture on cloudinary server and store details and picture url into database.
	 *
	 * @param picture The multipart file.
	 * @param request The request containing new rental details.
	 * @param token   The JWTAuthenticationToken token.
	 * @return ResponseEntity<CreateRentalResponseDto> containing the success message
	 */
	@PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@Operation(summary = "Create a new rental")
	@SecurityRequirement(name = "Bearer Authentication")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Rental created !", content = @Content(schema = @Schema(implementation = CreateRentalResponseDto.class), examples = @ExampleObject(value = "{\"message\": \"Rental created !\"}"), mediaType = "application/json")),
			@ApiResponse(responseCode = "400", description = "Failed to create rental.", content = @Content(schema = @Schema(), examples = @ExampleObject(value = "{\"message\": \"Failed to create rental.\"}"), mediaType = "application/json")),
			@ApiResponse(responseCode = "401", description = "Unauthorized request", content = @Content(schema = @Schema())),
	})
	public ResponseEntity<CreateRentalResponseDto> addRental(@Parameter(description = "File to upload") @RequestPart(value = "picture") final MultipartFile picture, @Valid @ModelAttribute final PostRentalRequestDto request, final JwtAuthenticationToken token) {
		CreateRentalResponseDto rentalResponse = rentalService.addRental(picture, request, token);
		return new ResponseEntity<>(rentalResponse, HttpStatus.OK);
	}

	/**
	 * Updates information about a specific rental.
	 *
	 * @param id      The ID of the rental to update.
	 * @param request The request containing specific rental to update details.
	 * @param token   The JWTAuthenticationToken token.
	 * @return ResponseEntity<CreateRentalResponseDto> containing the success message.
	 */
	@PutMapping(value = "/{id}")
	@Operation(summary = "Update existing rental by id")
	@SecurityRequirement(name = "Bearer Authentication")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Rental updated !", content = @Content(schema = @Schema(implementation = CreateRentalResponseDto.class), examples = @ExampleObject(value = "{\"message\": \"Rental updated !\"}"), mediaType = "application/json")),
			@ApiResponse(responseCode = "400", description = "Failed to update rental.", content = @Content(schema = @Schema(), examples = @ExampleObject(value = "{\"message\": \"Failed to update rental.\"}"), mediaType = "application/json")),
			@ApiResponse(responseCode = "401", description = "Unauthorized request", content = @Content(schema = @Schema())),
	})
	public ResponseEntity<CreateRentalResponseDto> updateRental(@PathVariable final int id, @Valid @ModelAttribute final PostRentalRequestDto request, final JwtAuthenticationToken token) {
		CreateRentalResponseDto rentalResponse = rentalService.updateRental(id, request, token);
		return new ResponseEntity<>(rentalResponse, HttpStatus.OK);
	}
}
