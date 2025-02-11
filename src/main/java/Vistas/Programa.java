package Vistas;

import Controladores.MascotasDAO;
import Controladores.VeterinariosDAO;
import Modelos.MascotasDTO;
import Modelos.VeterinariosDTO;

import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Programa {

    private static MascotasDAO mascotaDAO = new MascotasDAO();
    private static VeterinariosDAO veterinarioDAO = new VeterinariosDAO();

    public static void main(String[] args) {
        while (true) {
            String menu = "---- Menú ----\n"
                    + "1.- Ver datos de 1 mascota\n"
                    + "2.- Ver datos de 1 veterinario\n"
                    + "3. Listar mascotas\n"
                    + "4. Listar veterinarios\n"
                    + "5. Agregar mascota\n"
                    + "6. Agregar veterinario\n"
                    + "7. Asignar veterinario a mascota\n"
                    + "8. Eliminar mascota\n"
                    + "9. Eliminar veterinario\n"
                    + "10. Actualizar mascota\n"
                    + "11. Actualizar veterinario\n"
                    + "12. Salir";

            String opcionStr = JOptionPane.showInputDialog(null, menu, "Menú Principal", JOptionPane.INFORMATION_MESSAGE);
            int opcion = Integer.parseInt(opcionStr);

            switch (opcion) {
                case 1:
                    listarUnaMascota();
                    break;
                case 2:
                    listarUnVeterinario();
                    break;
                case 3:
                    listarMascotas();
                    break;
                case 4:
                    listarVeterinarios();
                    break;
                case 5:
                    agregarMascota();
                    break;
                case 6:
                    agregarVeterinario();
                    break;
                case 7:
                    asignarVeterinario();
                    break;
                case 8:
                    eliminarMascota();
                    break;
                case 9:
                    eliminarVeterinario();
                    break;
                case 10:
                    actualizarMascota();
                    break;
                case 11:
                    actualizarVeterinario();
                    break;
                case 12:
                    JOptionPane.showMessageDialog(null, "Saliendo del programa...");
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        }
    }

    // Listar todas las mascotas
    private static void listarMascotas() {
        try {
            List<MascotasDTO> mascotas = mascotaDAO.getAll();
            StringBuilder message = new StringBuilder("Mascotas:\n");
            for (MascotasDTO m : mascotas) {
                message.append(m.getIdnumMasc()).append(": ").append(m.getNomMasc()).append(" (").append(m.getTipoMasc()).append(")\n");
            }
            JOptionPane.showMessageDialog(null, message.toString());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar las mascotas: " + e.getMessage());
        }
    }
    
    // Listar 1 mascota
    private static void listarUnaMascota() {
        try {
            int buscar = Integer.parseInt(JOptionPane.showInputDialog(null, "Inserte el ID de la mascota a buscar: "));
            MascotasDTO m = mascotaDAO.findById(buscar);
            StringBuilder message = new StringBuilder("Mascota con ID " + buscar + ": ");
            message.append(m.getNomMasc()).append(" - ").append(m.getTipoMasc()).append("\n");
            JOptionPane.showMessageDialog(null, message.toString());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar la mascota: " + e.getMessage());
        }
    }
    
    // Listar todos los veterinarios
    private static void listarVeterinarios() {
        try {
            List<VeterinariosDTO> veterinarios = veterinarioDAO.getAll();
            StringBuilder message = new StringBuilder("Veterinarios:\n");
            for (VeterinariosDTO v : veterinarios) {
                message.append(v.getIdnumVet()).append(": ").append(v.getNomVet()).append("\n");
            }
            JOptionPane.showMessageDialog(null, message.toString());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar los veterinarios: " + e.getMessage());
        }
    }
        
    // Listar 1 veterinario
    private static void listarUnVeterinario() {
        try {
            int buscar = Integer.parseInt(JOptionPane.showInputDialog(null, "Inserte el ID del veterinario a buscar: "));
            VeterinariosDTO v = veterinarioDAO.findById(buscar);
            StringBuilder message = new StringBuilder("Veterinario con ID " + buscar + ": ");
            message.append(v.getNomVet()).append(" - ").append(v.getNif()).append(" - ").append(v.getTlfnVet()).append("\n");
            JOptionPane.showMessageDialog(null, message.toString());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar la mascota: " + e.getMessage());
        }
    }

    private static void agregarMascota() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la mascota: "));
            String nombre = JOptionPane.showInputDialog("Ingrese nombre de la mascota:");
            String tipo = JOptionPane.showInputDialog("Ingrese tipo de mascota (perro, gato, otros):");
            double peso = Double.parseDouble(JOptionPane.showInputDialog("Ingrese peso de la mascota:"));
            String fechaNacimientoStr = JOptionPane.showInputDialog("Ingrese fecha de nacimiento (yyyy-mm-dd):");
            java.sql.Date fechaNacimiento = java.sql.Date.valueOf(fechaNacimientoStr);
            

            String numChip = JOptionPane.showInputDialog("Ingrese número de chip (alfanumérico):");
            

            if (numChip == null || numChip.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El número de chip no puede estar vacío.");
                return;
            }

            String idVeterinarioStr = JOptionPane.showInputDialog("Ingrese el ID del veterinario (deje vacío si no tiene):");
            Integer idVeterinario = null;

            // Verificar si se ingresó un ID veterinario
            if (idVeterinarioStr != null && !idVeterinarioStr.trim().isEmpty()) {
                idVeterinario = Integer.parseInt(idVeterinarioStr);
            }

            // Crear objeto de Mascota
            MascotasDTO nuevaMascota = new MascotasDTO();
            nuevaMascota.setIdnumMasc(id);
            nuevaMascota.setNomMasc(nombre);
            nuevaMascota.setTipoMasc(tipo);
            nuevaMascota.setPesoMasc(peso);
            nuevaMascota.setFecnacMasc(fechaNacimiento);
            nuevaMascota.setNumChip(numChip);
            nuevaMascota.setIdnumVet(idVeterinario);

            // Inserción de la mascota en la base de datos
            int filasInsertadas = mascotaDAO.insertMascotas(nuevaMascota);
            if (filasInsertadas > 0) {
                JOptionPane.showMessageDialog(null, "Mascota agregada exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo agregar la mascota.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar la mascota: " + e.getMessage());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El ID veterinario debe ser un número entero.");
        }
    }

    // Agregar un nuevo veterinario
    private static void agregarVeterinario() {
        try {
            String nif = JOptionPane.showInputDialog("Ingrese NIF del veterinario:");
            String nombre = JOptionPane.showInputDialog("Ingrese nombre del veterinario:");
            String direccion = JOptionPane.showInputDialog("Ingrese dirección del veterinario:");
            String telefono = JOptionPane.showInputDialog("Ingrese teléfono del veterinario:");
            String email = JOptionPane.showInputDialog("Ingrese email del veterinario:");

            VeterinariosDTO nuevoVeterinario = new VeterinariosDTO();
            nuevoVeterinario.setNif(nif);
            nuevoVeterinario.setNomVet(nombre);
            nuevoVeterinario.setDirVet(direccion);
            nuevoVeterinario.setTlfnVet(telefono);
            nuevoVeterinario.setEmailVet(email);

            int filasInsertadas = veterinarioDAO.insertVeterinarios(nuevoVeterinario);
            if (filasInsertadas > 0) {
                JOptionPane.showMessageDialog(null, "Veterinario agregado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo agregar el veterinario.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar el veterinario: " + e.getMessage());
        }
    }

    // Asignar un veterinario a una mascota
    private static void asignarVeterinario() {
        try {
            int idMascota = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la mascota a asignar:"));
            int idVeterinario = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del veterinario a asignar:"));
            MascotasDTO mascota = mascotaDAO.findById(idMascota);
            if (mascota != null) {
                mascota.setIdnumVet(idVeterinario);
                mascotaDAO.updateMascotas(idMascota, mascota);
                JOptionPane.showMessageDialog(null, "Veterinario asignado a la mascota.");
            } else {
                JOptionPane.showMessageDialog(null, "La mascota no existe.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al asignar veterinario: " + e.getMessage());
        }
    }

    // Eliminar una mascota
    private static void eliminarMascota() {
        try {
            int idMascota = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la mascota a eliminar:"));
            int filasEliminadas = mascotaDAO.deleteMascotas(idMascota);
            if (filasEliminadas > 0) {
                JOptionPane.showMessageDialog(null, "Mascota eliminada exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar la mascota.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar la mascota: " + e.getMessage());
        }
    }

    // Eliminar un veterinario
    private static void eliminarVeterinario() {
        try {
            int idVeterinario = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del veterinario a eliminar:"));
            int filasEliminadas = veterinarioDAO.deleteVeterinarios(idVeterinario);
            if (filasEliminadas > 0) {
                JOptionPane.showMessageDialog(null, "Veterinario eliminado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar el veterinario.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el veterinario: " + e.getMessage());
        }
    }

    // Actualizar una mascota
    private static void actualizarMascota() {
        try {
            int idMascota = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la mascota a actualizar:"));
            MascotasDTO mascota = mascotaDAO.findById(idMascota);
            if (mascota != null) {
                String nuevoNombre = JOptionPane.showInputDialog("Ingrese nuevo nombre para la mascota:");
                double nuevoPeso = Double.parseDouble(JOptionPane.showInputDialog("Ingrese nuevo peso para la mascota:"));
                String nuevaFechaStr = JOptionPane.showInputDialog("Ingrese nueva fecha de nacimiento (yyyy-mm-dd):");                
                java.sql.Date nuevaFecha = java.sql.Date.valueOf(nuevaFechaStr);
                mascota.setNomMasc(nuevoNombre);
                mascota.setPesoMasc(nuevoPeso);
                mascota.setFecnacMasc(nuevaFecha);
                mascotaDAO.updateMascotas(idMascota, mascota);
                JOptionPane.showMessageDialog(null, "Mascota actualizada exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "La mascota no existe.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar la mascota: " + e.getMessage());
        }
    }

    // Actualizar un veterinario
    private static void actualizarVeterinario() {
        try {
            int idVeterinario = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del veterinario a actualizar:"));
            VeterinariosDTO veterinario = veterinarioDAO.findById(idVeterinario);
            if (veterinario != null) {
                String nuevoNombre = JOptionPane.showInputDialog("Ingrese nuevo nombre para el veterinario:");
                String nuevaDireccion = JOptionPane.showInputDialog("Ingrese nueva dirección para el veterinario:");
                String nuevoTelefono = JOptionPane.showInputDialog("Ingrese nuevo teléfono para el veterinario:");                
                String nuevoEmail = JOptionPane.showInputDialog("Ingrese nuevo email para el veterinario:");
                veterinario.setNomVet(nuevoNombre);
                veterinario.setDirVet(nuevaDireccion);
                veterinario.setTlfnVet(nuevoTelefono);
                veterinario.setEmailVet(nuevoEmail);
                veterinarioDAO.updateVeterinarios(idVeterinario, veterinario);
                JOptionPane.showMessageDialog(null, "Veterinario actualizado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "El veterinario no existe.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el veterinario: " + e.getMessage());
        }
    }
}
    


