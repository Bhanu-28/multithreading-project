package com.bank.accounts.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatusCode;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(
		name = "ErrorResponse",
		description = "Schema to hold Error response information"
)
@Data @AllArgsConstructor
public class ErrorResponseDto {
	
	@Schema(
			description = "API Path Invoked by client"
	)
	private String apiPath;
	@Schema(
			description = "Error code representing the error happened"
	)
	private HttpStatusCode errorCode;
	@Schema(
			description = "Error Message representing the error happened"
	)
	private String errorMessage;
	@Schema(
			description = "Time representing when the error happened"
	)
	private LocalDateTime errorTime;
	
	

}
