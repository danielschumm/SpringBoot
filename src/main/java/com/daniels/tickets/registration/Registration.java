package com.daniels.tickets.registration;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

public record Registration(Integer id, @NotNull(message = "Product id is required") Integer productId, String ticketCode, @NotBlank(message = "Attendeename is required")String attendeeName) {

}
