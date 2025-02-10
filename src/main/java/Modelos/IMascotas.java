/*
 * Interface que usa el patrón DAO sobre la tabla Persona
 */

package Modelos;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ignacio
 */

public interface IMascotas {
    
    // Método para obtener todos los registros de la tabla
    List<MascotasDTO> getAll() throws SQLException;
    
    // Méodo para obtener un registro a partir de la PK
    MascotasDTO findById(int idnumMasc) throws SQLException;
    
    // Método para insertar un registro
    int insertMascotas (MascotasDTO mascota) throws SQLException;
    
    // Método para insertar varios registros
    int insertMascotas (List<MascotasDTO> lista) throws SQLException;
    
    // Método para borrar una persona
    int deleteMascotas (int idMascota) throws SQLException;
    
    // Método para borrar toda la tabla
    int deleteMascotas() throws SQLException;
    
    // Método para modificar una persona. Se modifica a la persona que tenga esa 'pk'
    // con los nuevos datos que traiga la persona 'nuevosDatos'
    int updateMascotas (int idnumMasc, MascotasDTO nuevosDatos) throws SQLException;
    
    
    //Metodo para obtener una lista de mascotas asociadas a un veterinario por ID
    List<MascotasDTO> getMascotasByVeterinarios (int idnumVet) throws SQLException;
}