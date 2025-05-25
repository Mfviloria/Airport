/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Passenger;

import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author mariafernandaviloriazapata
 */
public class CalculateAge {
        public static int calculateAge(LocalDate birthDate) {
            return Period.between(birthDate, LocalDate.now()).getYears();
        }
}
