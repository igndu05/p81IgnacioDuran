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

public interface IMascota {
    
    // Método para obtener todos los registros de la tabla
    List<MascotaDTO> getAll() throws SQLException;
    
    // Méodo para obtener un registro a partir de la PK
    MascotaDTO findById(int idnumMasc) throws SQLException;
    
    // Método para insertar un registro
    int insertMascota (MascotaDTO mascota) throws SQLException;
    
    // Método para insertar varios registros
    int insertMascota (List<MascotaDTO> lista) throws SQLException;
    
    // Método para borrar una persona
    int deleteMascota (MascotaDTO p) throws SQLException;
    
    // Método para borrar toda la tabla
    int deleteMascota() throws SQLException;
    
    // Método para modificar una persona. Se modifica a la persona que tenga esa 'pk'
    // con los nuevos datos que traiga la persona 'nuevosDatos'
    int updateMascota (int idnumMasc, MascotaDTO nuevosDatos) throws SQLException;
    
}