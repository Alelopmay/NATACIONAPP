package org.example.Domain;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SwimmerTest {

    @Test
    void calculateCategory() {
        Swimmer swimmer = new Swimmer();

        // Prueba para una edad de 5 años
        String category1 = swimmer.calculateCategory(5);
        assertEquals("Pre-Benjamin", category1);

        // Prueba para una edad de 7 años
        String category2 = swimmer.calculateCategory(7);
        assertEquals("Benjamin", category2);

        // Prueba para una edad de 9 años
        String category3 = swimmer.calculateCategory(9);
        assertEquals("Alevin", category3);

        // Prueba para una edad de 11 años
        String category4 = swimmer.calculateCategory(11);
        assertEquals("Infantil", category4);

        // Prueba para una edad de 13 años
        String category5 = swimmer.calculateCategory(13);
        assertEquals("Juvenil", category5);

        // Prueba para una edad de 16 años
        String category6 = swimmer.calculateCategory(16);
        assertEquals("Junior", category6);

        // Prueba para una edad de 18 años
        String category7 = swimmer.calculateCategory(18);
        assertEquals("Senior", category7);
    }
}
