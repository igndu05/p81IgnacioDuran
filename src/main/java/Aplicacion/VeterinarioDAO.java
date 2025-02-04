/*
 * Clase que implementa la interface IPersona
 */
package Aplicacion;

import Modelos.IVeterinario;
import Modelos.VeterinarioDTO;
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
public class VeterinarioDAO implements IVeterinario {

    private Connection con = null;

    public VeterinarioDAO() {
        con = Conexion.getInstance();
    }

    @Override
    public List<VeterinarioDTO> getAll() throws SQLException {
        List<VeterinarioDTO> lista = new ArrayList<>();

        // Preparamos la consulta de datos mediante un objeto Statement
        // ya que no necesitamos parametrizar la sentencia SQL
        try (Statement st = con.createStatement()) {
            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            ResultSet res = st.executeQuery("select * from persona");
            // Ahora construimos la lista, recorriendo el ResultSet y mapeando los datos
            while (res.next()) {
                VeterinarioDTO v = new VeterinarioDTO();
                // Recogemos los datos de la persona, guardamos en un objeto
                v.setIdnumVet(res.getInt("ID Numero Vet"));
                v.setNif(res.getString("NIF"));
                v.setNomVet(res.getString("Nombre"));
                v.setDirVet(res.getString("Direccion"));
                v.setTlfnVet(res.getInt("Telefono"));
                v.setEmailVet(res.getString("Email"));
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

        String query = "SELECT MAX(idnumVet) AS idnumVet FROM veterinario";

        PreparedStatement statement = con.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            lastId = resultSet.getInt("idnumVet");
        }

        return lastId;
    }

    @Override
    public VeterinarioDTO findById(int idnumVet) throws SQLException {

        ResultSet res = null;
        VeterinarioDTO veterinario = new VeterinarioDTO();

        String sql = "select * from veterinario where pk = ?";

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
                veterinario.setIdnumVet(res.getInt("ID Numero Vet"));
                veterinario.setNif(res.getString("NIF"));
                veterinario.setNomVet(res.getString("Nombre"));
                veterinario.setDirVet(res.getString("Direccion"));
                veterinario.setTlfnVet(res.getInt("Telefono"));
                veterinario.setEmailVet(res.getString("Email"));
                return veterinario;
            }

            return null;
        }
    }

    @Override
    public int insertVeterinario(VeterinarioDTO veterinario) throws SQLException {

        int numFilas = 0;
        String sql = "insert into veterinario values (?,?,?)";

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
                prest.setInt(5, veterinario.getTlfnVet());
                prest.setString(6, veterinario.getEmailVet());

                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }

    }

    @Override
    public int insertVeterinario(List<VeterinarioDTO> lista) throws SQLException {
        int numFilas = 0;

        for (VeterinarioDTO tmp : lista) {
            numFilas += insertVeterinario(tmp);
        }

        return numFilas;
    }

    @Override
    public int deleteVeterinario() throws SQLException {

        String sql = "delete from veterinario";

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
    public int deleteVeterinario(VeterinarioDTO veterinario) throws SQLException {
        int numFilas = 0;

        String sql = "delete from veterinario where pk = ?";

        // Sentencia parametrizada
        try (PreparedStatement prest = con.prepareStatement(sql)) {

            // Establecemos los parámetros de la sentencia
            prest.setInt(1, veterinario.getIdnumVet());
            // Ejecutamos la sentencia
            numFilas = prest.executeUpdate();
        }
        return numFilas;
    }

    @Override
    public int updateVeterinario(int idnumVet, VeterinarioDTO nuevosDatos) throws SQLException {

        int numFilas = 0;
        String sql = "update veterinario set nif = ?, nomVet = ?, dirVet = ?, tlfnVet = ?, emailVet = ? where idnumVet = ?";

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
                prest.setInt(5, nuevosDatos.getTlfnVet());
                prest.setString(6, nuevosDatos.getEmailVet());

                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }
    }

    
}
