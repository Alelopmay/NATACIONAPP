package org.example.Util;

import org.example.Domain.Swimmer;
import org.example.dao.SwimmerDAO;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    /**
     *
     * @param name Nombre de el nadador
     * @return el nombre validado de manera correcta
     */

    public static boolean ValidName(String name) {
        // Expresión regular para validar un nombre: solo letras y espacios, con una longitud mínima de 2 caracteres
        String regex = "^[A-Za-zñÑáéíóúÁÉÍÓÚüÜ]+$";
        ;

        return name.matches(regex);
    }

    /**
     *
     * @param last_name apellido del deportista
     * @return el apellido validad de manera correcta
     */
    public static boolean validLast_name(String last_name) {
        // Expresión regular para validar un apellido: solo letras y espacios, con una longitud mínima de 2 caracteres
        String regex = "^[A-Za-zñÑáéíóúÁÉÍÓÚüÜ]+(\\s[A-Za-zñÑáéíóúÁÉÍÓÚüÜ]+)*$";

        return last_name.matches(regex);
    }

    /**
     *
     * @param age edad para validar
     * @return devuelve la edad validad pora que no se pueda poner unad edad menor a 1 o mayor a 100
     */
    public static boolean validAge(int age) {
        // Verificar que la edad no sea mayor a 100
        return age >= 1 && age <= 100;
    }

    /**
     * Comprueba si una cadena de tiempo tiene un formato correcto
     * @param timeString cadena de tiempo a comprobar
     * @return devuelve el tiempo validado que se ha puesto en el formato correcto
     */
    public static boolean validateTimeFormat(String timeString) {
        // Expresión regular para validar el formato de tiempo: 00:00:00
        String regex = "^([01]\\d|2[0-3]):([0-5]\\d):([0-5]\\d)$";

        return timeString.matches(regex);
    }







}
