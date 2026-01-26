package com.daniels.tickets.registration;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;

@Document("registrations")
public record Registration(
    @Id String id,
    @NotNull(message = "Product id is required") Integer productId,
    String ticketCode,
    @NotBlank(message = "Attendeename is required")String attendeeName) {

}
