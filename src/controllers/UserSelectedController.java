/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import controllers.utils.Response;
import controllers.utils.Status;


/**
 *
 * @author mariafernandaviloriazapata
 */
public class UserSelectedController {
    public static Response userSelected(boolean userSelected){
        if (userSelected){
            return new Response("To view or add flights, please select your user ID first from the box labeled 'Select your user' below.", Status.OK);
        } else if (!userSelected){
            return null;
        }
        return new Response("ID verified successfully!", Status.OK);
        
    }
}
