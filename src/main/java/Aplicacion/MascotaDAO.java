/*
 * Clase que implementa la interface IPersona
 */
package Aplicacion;

import Modelos.IMascota;
import Modelos.MascotaDTO;
import Conexion.Conexion;
import Modelos.MascotaDTO;
import java.sql.Connection;
import java.sql.Date;
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
public class MascotaDAO implements IMascota {

    private Connection con = null;

    public MascotaDAO() {
        con = Conexion.getInstance();
    }

    @Override
    public List<MascotaDTO> getAll() throws SQLException {
        List<MascotaDTO> lista = new ArrayList<>();

        // Preparamos la consulta de datos mediante un objeto Statement
        // ya que no necesitamos parametrizar la sentencia SQL
        try (Statement st = con.createStatement()) {
            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            ResultSet res = st.executeQuery("select * from persona");
            // Ahora construimos la lista, recorriendo el ResultSet y mapeando los datos
            while (res.next()) {
                MascotaDTO v = new MascotaDTO();
                // Recogemos los datos de la persona, guardamos en un objeto
                v.setIdnumMasc(res.getInt("ID Numero Masc"));
                v.setNumChip(res.getInt("Numero chip"));
                v.setNomMasc(res.getString("Nombre"));
                v.setPesoMasc(res.getDouble("0.0"));
                v.setFecnacMasc(res.getDate("Fecha_nac").toLocalDate());
                v.setTipoMasc(res.getString("Tipo"));
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

        String query = "SELECT MAX(idnumMasc) AS idnumMasc FROM mascota";

        PreparedStatement statement = con.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            lastId = resultSet.getInt("idnumVet");
        }

        return lastId;
    }

    @Override
    public MascotaDTO findById(int idnumMasc) throws SQLException {

        ResultSet res = null;
        MascotaDTO mascota = new MascotaDTO();

        String sql = "select * from mascota where pk = ?";

        try (PreparedStatement prest = con.prepareStatement(sql)) {
            // Preparamos la sentencia parametrizada
            prest.setInt(1, idnumMasc);

            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            res = prest.executeQuery();

            // Nos posicionamos en el primer registro del Resultset. Sólo debe haber una fila
            // si existe esa pk
            if (res.next()) {
                // Recogemos los datos de la persona, guardamos en un objeto
//                persona.setPk(res.getInt("pk"));
//                persona.setNombre(res.getString("nombre"));
//                persona.setFechaNacimiento(res.getDate("fecha_nac").toLocalDate());
                mascota.setIdnumMasc(res.getInt("ID Numero Masc"));
                mascota.setNumChip(res.getInt("Numero chip"));
                mascota.setNomMasc(res.getString("Nombre"));
                mascota.setPesoMasc(res.getDouble("0.0"));
                mascota.setFecnacMasc(res.getDate("Fecha_nac").toLocalDate());
                mascota.setTipoMasc(res.getString("Tipo"));
                return mascota;
            }

            return null;
        }
    }

    @Override
    public int insertMascota(MascotaDTO mascota) throws SQLException {

        int numFilas = 0;
        String sql = "insert into mascota values (?,?,?)";

        if (findById(mascota.getIdnumMasc()) != null) {
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
                prest.setInt(1, mascota.getIdnumMasc());
                prest.setInt(2, mascota.getNumChip());
                prest.setString(3, mascota.getNomMasc());
                prest.setDouble(4, mascota.getPesoMasc());
                prest.setDate(5, Date.valueOf(mascota.getFecnacMasc()));
                prest.setString(6, mascota.getTipoMasc());

                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }

    }

    @Override
    public int insertMascota(List<MascotaDTO> lista) throws SQLException {
        int numFilas = 0;

        for (MascotaDTO tmp : lista) {
            numFilas += insertMascota(tmp);
        }

        return numFilas;
    }

    @Override
    public int deleteMascota() throws SQLException {

        String sql = "delete from mascota";

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
    public int deleteMascota(MascotaDTO mascota) throws SQLException {
        int numFilas = 0;

        String sql = "delete from mascota where idnumMasc = ?";

        // Sentencia parametrizada
        try (PreparedStatement prest = con.prepareStatement(sql)) {

            // Establecemos los parámetros de la sentencia
            prest.setInt(1, mascota.getIdnumMasc());
            // Ejecutamos la sentencia
            numFilas = prest.executeUpdate();
        }
        return numFilas;
    }

    @Override
    public int updateMascota(int idnumMasc, MascotaDTO nuevosDatos) throws SQLException {

        int numFilas = 0;
        String sql = "update mascota set numchip = ?, nomMasc = ?, pesoMasc = ?, fecNacMasc = ?, tipoMasc = ? where idnumMasc = ?";

        if (findById(idnumMasc) == null) {
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
                prest.setInt(1, nuevosDatos.getIdnumMasc());
                prest.setInt(2, nuevosDatos.getNumChip());
                prest.setString(3, nuevosDatos.getNomMasc());
                prest.setDouble(4, nuevosDatos.getPesoMasc());
                prest.setDate(5, Date.valueOf(nuevosDatos.getFecnacMasc()));
                prest.setString(6, nuevosDatos.getTipoMasc());

                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }
    }

    @Override
    public List<MascotaDTO> getMascotasByVeterinario(int idnumVet) throws SQLException {
        List<MascotaDTO> lista = new ArrayList <>();
        String sql = "select * from mascota where idnumVet = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idnumVet);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                MascotaDTO m = new MascotaDTO();
                m.setIdnumMasc(rs.getInt("id"));
                m.setNumChip(rs.getInt("numchip"));
                m.setNomMasc(rs.getString("nombre"));
                m.setPesoMasc(rs.getDouble("peso"));
                m.setFecnacMasc(rs.getDate("fecha_nac").toLocalDate());
                m.setTipoMasc(rs.getString("tipo"));
                m.setIdnumVet(rs.getInt("id veterinario"));
                lista.add(m);
            }
        }
        return lista;
    }

    
}

