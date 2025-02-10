package Vistas;

import Controladores.MascotasDAO;
import Controladores.VeterinariosDAO;
import Modelos.MascotasDTO;
import Modelos.VeterinariosDTO;

import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Programa {

    private static MascotasDAO mascotaDAO = new MascotasDAO();
    private static VeterinariosDAO veterinarioDAO = new VeterinariosDAO();

    public static void main(String[] args) {
        while (true) {
            String menu = "---- Menú ----\n"
                    + "1. Listar mascotas\n"
                    + "2. Listar veterinarios\n"
                    + "3. Agregar mascota\n"
                    + "4. Agregar veterinario\n"
                    + "5. Asignar veterinario a mascota\n"
                    + "6. Eliminar mascota\n"
                    + "7. Eliminar veterinario\n"
                    + "8. Actualizar mascota\n"
                    + "9. Actualizar veterinario\n"
                    + "0. Salir";

            String opcionStr = JOptionPane.showInputDialog(null, menu, "Menú Principal", JOptionPane.INFORMATION_MESSAGE);
            int opcion = Integer.parseInt(opcionStr);

            switch (opcion) {
                case 1:
                    listarMascotas();
                    break;
                case 2:
                    listarVeterinarios();
                    break;
                case 3:
                    agregarMascota();
                    break;
                case 4:
                    agregarVeterinario();
                    break;
                case 5:
                    asignarVeterinario();
                    break;
                case 6:
                    eliminarMascota();
                    break;
                case 7:
                    eliminarVeterinario();
                    break;
                case 8:
                    actualizarMascota();
                    break;
                case 9:
                    actualizarVeterinario();
                    break;
                case 0:
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

    private static void agregarMascota() {
        try {
            String nombre = JOptionPane.showInputDialog("Ingrese nombre de la mascota:");
            String tipo = JOptionPane.showInputDialog("Ingrese tipo de mascota (perro, gato, otros):");
            double peso = Double.parseDouble(JOptionPane.showInputDialog("Ingrese peso de la mascota:"));
            String fechaNacimientoStr = JOptionPane.showInputDialog("Ingrese fecha de nacimiento (yyyy-mm-dd):");
//            java.sql.Date fechaNacimiento = java.sql.Date.valueOf(fechaNacimientoStr);
            LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoStr);

            String numeroChip = JOptionPane.showInputDialog("Ingrese número de chip (alfanumérico):");
            int numChip = Integer.parseInt(numeroChip);

            if (numeroChip == null || numeroChip.trim().isEmpty()) {
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
            String telefonoStr = JOptionPane.showInputDialog("Ingrese teléfono del veterinario:");
            int telefono = Integer.parseInt(telefonoStr);
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
                LocalDate fechaNacimiento = LocalDate.parse(nuevaFechaStr);
                mascota.setNomMasc(nuevoNombre);
                mascota.setPesoMasc(nuevoPeso);
                mascota.setFecnacMasc(fechaNacimiento);
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
                String nuevoTelefonoStr = JOptionPane.showInputDialog("Ingrese nuevo teléfono para el veterinario:");
                int nuevoTelefono = Integer.parseInt(nuevoTelefonoStr);
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
    


