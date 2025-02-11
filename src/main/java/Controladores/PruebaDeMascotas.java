/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Conexion.Conexion;
import Modelos.MascotasDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ignacio
 */
public class PruebaDeMascotas {
    public static void main(String[] args) {
        
        Connection con = Conexion.getInstance();
        MascotasDAO mascota = new MascotasDAO();
        List<MascotasDTO> listaMascotas = new ArrayList<>();
        
        listaMascotas.add(new MascotasDTO(1, 1, "1", "Pepe", 25.00, new Date(2015, 10, 30), "Perro"));
        listaMascotas.add(new MascotasDTO(2, 1, "2", "Pepe", 12.28, new Date(2015, 10, 30), "Gato"));
        listaMascotas.add(new MascotasDTO(3, 1, "3", "Jesus", 30.00, new Date(2015, 10, 30), "Loro"));
    
        try {
            
            System.out.println("Nº mascotas insertadas --> " + mascota.insertMascotas(listaMascotas));
            System.out.println("-----------------------------------------");
            System.out.println("Comprobamos en una nueva lista que se recogen los datos desde la tabla.");
            List<MascotasDTO> nuevaLista = mascota.getAll();
            System.out.println("-------- Lista con datos recogidos desde la B.D -------------");
            nuevaLista.forEach(System.out::println);
            System.out.println("-----------------------------------------");
            System.out.println("Persona con primary key 1: ");
            System.out.println(mascota.findById(1));
            System.out.println("-----------------------------------------");
            System.out.println("Se va a borrar la persona con pk 3");
            System.out.println("Nº personas borradas - >" + 
                    mascota.deleteMascotas(3));
            System.out.println("-----------------------------------------");
            nuevaLista = mascota.getAll();
            System.out.println("-------- Lista con datos recogidos desde la B.D despues de borrar una mascota -------------");
            nuevaLista.forEach(System.out::println);
        } catch (SQLException sqle) {
            System.out.println("No se ha podido realizar la operación:");
            System.out.println(sqle.getMessage());
        }
        System.out.println("-------- Lista original --------------------");
        listaMascotas.forEach(System.out::println);
    }
}
