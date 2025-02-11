/*
 * Clase que implementa la interface IPersona
 */
package Controladores;

import Modelos.IVeterinarios;
import Modelos.VeterinariosDTO;
import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ignacio
 */
public class VeterinariosDAO implements IVeterinarios {

    private Connection con = null;

    public VeterinariosDAO() {
        con = Conexion.getInstance();
    }

    @Override
    public List<VeterinariosDTO> getAll() throws SQLException {
        List<VeterinariosDTO> lista = new ArrayList<>();

        // Preparamos la consulta de datos mediante un objeto Statement
        // ya que no necesitamos parametrizar la sentencia SQL
        try (Statement st = con.createStatement()) {
            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            ResultSet res = st.executeQuery("select * from veterinarios");
            // Ahora construimos la lista, recorriendo el ResultSet y mapeando los datos
            while (res.next()) {
                VeterinariosDTO v = new VeterinariosDTO();
                // Recogemos los datos de la persona, guardamos en un objeto
                v.setIdnumVet(res.getInt("idnumVet"));
                v.setNif(res.getString("nif"));
                v.setNomVet(res.getString("nomVet"));
                v.setDirVet(res.getString("dirVet"));
                v.setTlfnVet(res.getString("tlfnVet"));
                v.setEmailVet(res.getString("emailVet"));
                //Añadimos el objeto a la lista
                lista.add(v);
            }
        }

        return lista;
    }

    // Este método permite obtener la última id de la tabla
    // Así se pueden insertar nuevos registros sin duplicar claves
    public int getLastInsertedId() throws SQLException {
        int lastId = 0; // Valor predeterminado si no hay registros

        String query = "SELECT MAX(idnumVet) AS idnumVet FROM veterinarios";

        PreparedStatement statement = con.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            lastId = resultSet.getInt("idnumVet");
        }

        return lastId;
    }

    @Override
    public VeterinariosDTO findById(int idnumVet) throws SQLException {

        ResultSet res = null;
        VeterinariosDTO veterinario = new VeterinariosDTO();

        String sql = "select * from veterinarios where idnumVet = ?";

        try (PreparedStatement prest = con.prepareStatement(sql)) {
            // Preparamos la sentencia parametrizada
            prest.setInt(1, idnumVet);

            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            res = prest.executeQuery();

            // Nos posicionamos en el primer registro del Resultset. Sólo debe haber una fila
            // si existe esa pk
            if (res.next()) {
                // Recogemos los datos de la persona, guardamos en un objeto
//                persona.setPk(res.getInt("pk"));
//                persona.setNombre(res.getString("nombre"));
//                persona.setFechaNacimiento(res.getDate("fecha_nac").toLocalDate());
                veterinario.setIdnumVet(res.getInt("idnumVet"));
                veterinario.setNif(res.getString("nif"));
                veterinario.setNomVet(res.getString("nomVet"));
                veterinario.setDirVet(res.getString("dirVet"));
                veterinario.setTlfnVet(res.getString("tlfnVet"));
                veterinario.setEmailVet(res.getString("emailVet"));
                return veterinario;
            }

            return null;
        }
    }

    @Override
    public int insertVeterinarios(VeterinariosDTO veterinario) throws SQLException {

        int numFilas = 0;
        String sql = "insert into veterinarios values (idnumVet, nif, nomVet, dirVet, tlfnVet, emailVet)";

        if (findById(veterinario.getIdnumVet()) != null) {
            // Existe un registro con esa pk
            // No se hace la inserción
            return numFilas;
        } else {
            // Instanciamos el objeto PreparedStatement para inserción
            // de datos. Sentencia parametrizada
            try (PreparedStatement prest = con.prepareStatement(sql)) {

                // Establecemos los parámetros de la sentencia
//                prest.setInt(1, persona.getPk());
//                prest.setString(2, persona.getNombre());
//                prest.setDate(3, Date.valueOf(persona.getFechaNacimiento()));
                prest.setInt(1, veterinario.getIdnumVet());
                prest.setString(2, veterinario.getNif());
                prest.setString(3, veterinario.getNomVet());
                prest.setString(4, veterinario.getDirVet());
                prest.setString(5, veterinario.getTlfnVet());
                prest.setString(6, veterinario.getEmailVet());

                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }

    }

    @Override
    public int insertVeterinarios(List<VeterinariosDTO> lista) throws SQLException {
        int numFilas = 0;

        for (VeterinariosDTO tmp : lista) {
            numFilas += insertVeterinarios(tmp);
        }

        return numFilas;
    }

    @Override
    public int deleteVeterinarios() throws SQLException {

        String sql = "delete from veterinarios";

        int nfilas = 0;

        // Preparamos el borrado de datos  mediante un Statement
        // No hay parámetros en la sentencia SQL
        try (Statement st = con.createStatement()) {
            // Ejecución de la sentencia
            nfilas = st.executeUpdate(sql);
        }

        // El borrado se realizó con éxito, devolvemos filas afectadas
        return nfilas;

    }

    @Override
    public int deleteVeterinarios(int idNumVet) throws SQLException {
        int numFilas = 0;

        String sql = "delete from veterinarios where idnumVet = ?";

        // Sentencia parametrizada
        try (PreparedStatement prest = con.prepareStatement(sql)) {

            // Establecemos los parámetros de la sentencia
            prest.setInt(1, idNumVet);
            // Ejecutamos la sentencia
            numFilas = prest.executeUpdate();
        }
        return numFilas;
    }

    @Override
    public int updateVeterinarios(int idnumVet, VeterinariosDTO nuevosDatos) throws SQLException {

        int numFilas = 0;
        String sql = "update veterinarios set nif = ?, nomVet = ?, dirVet = ?, tlfnVet = ?, emailVet = ? where idnumVet = ?";

        if (findById(idnumVet) == null) {
            // La persona a actualizar no existe
            return numFilas;
        } else {
            // Instanciamos el objeto PreparedStatement para inserción
            // de datos. Sentencia parametrizada
            try (PreparedStatement prest = con.prepareStatement(sql)) {

                // Establecemos los parámetros de la sentencia
//                prest.setString(1, nuevosDatos.getNombre());
//                prest.setDate(2, Date.valueOf(nuevosDatos.getFechaNacimiento()));
//                prest.setInt(3, pk);
                prest.setInt(1, nuevosDatos.getIdnumVet());
                prest.setString(2, nuevosDatos.getNif());
                prest.setString(3, nuevosDatos.getNomVet());
                prest.setString(4, nuevosDatos.getDirVet());
                prest.setString(5, nuevosDatos.getTlfnVet());
                prest.setString(6, nuevosDatos.getEmailVet());

                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }
    }

    
}
