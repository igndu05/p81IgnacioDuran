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

public interface IVeterinarios {
    
    // Método para obtener todos los registros de la tabla
    List<VeterinariosDTO> getAll() throws SQLException;
    
    // Méodo para obtener un registro a partir de la PK
    VeterinariosDTO findById(int idnumVet) throws SQLException;
    
    // Método para insertar un registro
    int insertVeterinarios (VeterinariosDTO veterinario) throws SQLException;
    
    // Método para insertar varios registros
    int insertVeterinarios (List<VeterinariosDTO> lista) throws SQLException;
    
    // Método para borrar una persona
    int deleteVeterinarios (VeterinariosDTO p) throws SQLException;
    
    // Método para borrar toda la tabla
    int deleteVeterinarios() throws SQLException;
    
    // Método para modificar una persona. Se modifica a la persona que tenga esa 'pk'
    // con los nuevos datos que traiga la persona 'nuevosDatos'
    int updateVeterinarios (int idnumVet, VeterinariosDTO nuevosDatos) throws SQLException;
    
}
