/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Passenger;

/**
 *
 * @author mariafernandaviloriazapata
 */
public class GenerateFullPhone {
    public static String generateFullPhone(String countryPhoneCode, String phone) {
        return "+" + countryPhoneCode + " " + phone;
    }
}
