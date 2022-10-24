package com.musala.controller;

import com.musala.payload.response.CreateMedicationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping
public class Greeting {

    @Operation(summary="Messages From Server")
    @ApiResponses(value={
            @ApiResponse(responseCode="200", description="Messages From Server",
                    content={@Content(mediaType="application/json",
                            schema=@Schema(implementation=String.class))}),
            @ApiResponse(responseCode="4XX", description="Client Error Invalid Data",
                    content=@Content),
            @ApiResponse(responseCode="5XX", description="Server Error Invalid Data",
                    content=@Content)})
    @GetMapping("/message")
    public String greeting(@RequestParam(value = "message") String message){
        return message;
    }
}
