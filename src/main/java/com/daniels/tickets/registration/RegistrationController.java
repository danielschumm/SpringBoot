package com.daniels.tickets.registration;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.NoSuchElementException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping(path = "/registrations")
public class RegistrationController{

    private final RegistrationRepository registrationRepository;

    public RegistrationController(RegistrationRepository registrationRepository){
        this.registrationRepository = registrationRepository;
    }
    @PostMapping
    public Registration create(@RequestBody @Valid Registration registration) {
        return registrationRepository.create(registration);
    }
    @GetMapping(path = "/{ticketCode}")
    public Registration get(@PathVariable("ticketCode") String ticketCode) {
        return registrationRepository. findByTicketCode(ticketCode)
                .orElseThrow(() -> new NoSuchElementException( "Registration with ticket code " + ticketCode + " not found"));
    }
    @PutMapping(path = "/{ticketCode}")
    public Registration update(@PathVariable String ticketCode, @RequestBody @Valid Registration registration) {
            registration = new Registration(
                registration.id(),
                registration.productId(),
                ticketCode,
                registration.attendeeName()
            );

        return registrationRepository.update(registration)
            .orElseThrow(() -> new NoSuchElementException(
                    "Registration with ticket code " + ticketCode + " not found"));
}
    @DeleteMapping(path = "/{ticketCode}")
    public void delete(@PathVariable("ticketCode") String ticketCode) {
        registrationRepository.deleteByTicketCode(ticketCode);
    }
}
