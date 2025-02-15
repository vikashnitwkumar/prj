package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.dtos.BookingUpdateRequestDto;
import org.example.evaluations.evaluation.models.Booking;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @GetMapping("{id}")
    public ResponseEntity<?> getBookingById(@PathVariable("id") Long bookingId) {
        Booking booking = new Booking();
        booking.setId(bookingId);
        return ResponseEntity.ok(booking);
    }

    @GetMapping("/{guest}/{date}")
    public ResponseEntity<?> getBookingByGuestNameAndDate(@PathVariable("guest") String guestName,
                                                          @PathVariable("date") String dateString) {
        try {
            Date date = parseDate(dateString);

            Booking booking = new Booking();
            booking.setGuestName(guestName);
            booking.setDate(date);

            return ResponseEntity.ok(booking);
        } catch (ParseException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid date format. Use YYYY-MM-DD"));
        }
    }

    @GetMapping("/{date}")
    public ResponseEntity<?> listBookingsOfParticularDate(@PathVariable String dateString) {
        try {
            Date date = parseDate(dateString);

            List<Booking> bookings = new ArrayList<>();
            Booking booking = new Booking();
            booking.setDate(date);
            bookings.add(booking);

            return ResponseEntity.ok(bookings);
        } catch (ParseException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid date format. Use YYYY-MM-DD"));
        }
    }

    @PutMapping("/{bookingId}")
    public ResponseEntity<?> updateBooking(@PathVariable Long bookingId, @RequestBody BookingUpdateRequestDto requestDto) {
        try {
            Date date = parseDate(requestDto.getDate());

            Booking booking = new Booking();
            booking.setId(bookingId);
            booking.setGuestName(requestDto.getGuestName());
            booking.setDate(date);

            return ResponseEntity.ok(booking);
        } catch (ParseException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid date format. Use YYYY-MM-DD"));
        }
    }

    private Date parseDate(String dateString) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        simpleDateFormat.setLenient(false); // Ensures strict parsing
        return simpleDateFormat.parse(dateString);
    }
}
