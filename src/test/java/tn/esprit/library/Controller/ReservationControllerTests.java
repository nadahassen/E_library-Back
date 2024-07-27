package tn.esprit.library.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.library.controller.ReservationController;
import tn.esprit.library.entities.Reservation;
import tn.esprit.library.services.IReservationService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ReservationControllerTests {

    private MockMvc mockMvc;

    @Mock
    private IReservationService reservationService;

    @InjectMocks
    private ReservationController reservationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reservationController).build();
    }

    @Test
    void testAddReservation() throws Exception {
        Reservation reservation = new Reservation(1L, new Date(), new Date(), null, null, null);
        when(reservationService.addReservation(any(Reservation.class))).thenReturn(reservation);

        mockMvc.perform(post("/reservation/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"reservation_date\":\"2024-07-26T00:00:00.000+00:00\",\"return_date\":\"2024-08-26T00:00:00.000+00:00\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id_reservation").value(1L));

        verify(reservationService, times(1)).addReservation(any(Reservation.class));
    }

    @Test
    void testGetAllReservations() throws Exception {
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation(1L, new Date(), new Date(), null, null, null));
        when(reservationService.getAllReservations()).thenReturn(reservations);

        mockMvc.perform(get("/reservation/getall"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id_reservation").value(1L));

        verify(reservationService, times(1)).getAllReservations();
    }

    @Test
    void testGetReservationsByBook() throws Exception {
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation(1L, new Date(), new Date(), null, null, null));
        when(reservationService.getReservationsByBook(anyLong())).thenReturn(reservations);

        mockMvc.perform(get("/reservation/getByBook/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id_reservation").value(1L));

        verify(reservationService, times(1)).getReservationsByBook(anyLong());
    }

    @Test
    void testGetReservationsByUser() throws Exception {
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation(1L, new Date(), new Date(), null, null, null));
        when(reservationService.getReservationsByUser(anyLong())).thenReturn(reservations);

        mockMvc.perform(get("/reservation/getByUser/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id_reservation").value(1L));

        verify(reservationService, times(1)).getReservationsByUser(anyLong());
    }

    @Test
    void testGetReservation() throws Exception {
        Reservation reservation = new Reservation(1L, new Date(), new Date(), null, null, null);
        when(reservationService.getReservationById(anyLong())).thenReturn(reservation);

        mockMvc.perform(get("/reservation/get/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id_reservation").value(1L));

        verify(reservationService, times(1)).getReservationById(anyLong());
    }

    @Test
    void testDeleteReservation() throws Exception {
        doNothing().when(reservationService).deleteReservation(anyLong());

        mockMvc.perform(delete("/reservation/delete/1"))
                .andExpect(status().isOk());

        verify(reservationService, times(1)).deleteReservation(anyLong());
    }
}
