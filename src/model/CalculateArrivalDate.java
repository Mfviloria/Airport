/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author mariafernandaviloriazapata
 */
public class CalculateArrivalDate {
      public static LocalDateTime calculateArrivalDate(LocalDateTime departureDate, int hoursDurationScale, int hoursDurationArrival, int minutesDurationScale, int minutesDurationArrival  ) {
        return departureDate.plusHours(hoursDurationScale).plusHours(hoursDurationArrival).plusMinutes(minutesDurationScale).plusMinutes(minutesDurationArrival);
    }
}
