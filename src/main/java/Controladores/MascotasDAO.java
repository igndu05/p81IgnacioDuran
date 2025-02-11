/*
 * Clase que implementa la interface IPersona
 */
package Controladores;

import Modelos.IMascotas;
import Modelos.MascotasDTO;
import Conexion.Conexion;
import Modelos.MascotasDTO;
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
public class MascotasDAO implements IMascotas {

    private Connection con = null;

    public MascotasDAO() {
        con = Conexion.getInstance();
    }

    @Override
    public List<MascotasDTO> getAll() throws SQLException {
        List<MascotasDTO> lista = new ArrayList<>();

        // Preparamos la consulta de datos mediante un objeto Statement
        // ya que no necesitamos parametrizar la sentencia SQL
        try (Statement st = con.createStatement()) {
            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            ResultSet res = st.executeQuery("select * from mascotas");
            // Ahora construimos la lista, recorriendo el ResultSet y mapeando los datos
            while (res.next()) {
                MascotasDTO m = new MascotasDTO();
                // Recogemos los datos de la persona, guardamos en un objeto
                m.setIdnumMasc(res.getInt("idnumMasc"));
                m.setIdnumVet(res.getInt("idnumVet"));
                m.setNumChip(res.getString("numchip"));
                m.setNomMasc(res.getString("nomMasc"));
                m.setPesoMasc(res.getDouble("pesoMasc"));
                m.setFecnacMasc(res.getDate("fecNacMasc"));
                m.setTipoMasc(res.getString("tipoMasc"));                
                //Añadimos el objeto a la lista
                lista.add(m);
            }
        }

        return lista;
    }

    // Este método permite obtener la última id de la tabla
    // Así se pueden insertar nuevos registros sin duplicar claves
    public int getLastInsertedId() throws SQLException {
        int lastId = 0; // Valor predeterminado si no hay registros

        String query = "SELECT MAX(idnumMasc) AS idnumMasc FROM mascotas";

        PreparedStatement statement = con.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            lastId = resultSet.getInt("idnumMasc");
        }

        return lastId;
    }

    @Override
    public MascotasDTO findById(int idnumMasc) throws SQLException {

        ResultSet res = null;
        MascotasDTO mascota = new MascotasDTO();

        String sql = "select * from mascotas where idnumMasc = ?";

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
//                persona.setFechaNacimiento(res.getDate("fecha_nac").toLocalDate())
                mascota.setIdnumMasc(res.getInt("idnumMasc"));
                mascota.setIdnumVet(res.getInt("idnumVet"));
                mascota.setNumChip(res.getString("numChip"));
                mascota.setNomMasc(res.getString("nomMasc"));
                mascota.setPesoMasc(res.getDouble("pesoMasc"));
                mascota.setFecnacMasc(res.getDate("fecnacMasc"));
                mascota.setTipoMasc(res.getString("tipoMasc"));
                return mascota;
            }

            return null;
        }
    }

    @Override
    public int insertMascotas(MascotasDTO mascota) throws SQLException {

        int numFilas = 0;
        String sql = "insert into mascotas values (idnumMasc, idnumVet, numChip, nomMasc, pesoMasc, fecnacMasc, tipoMasc)";

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
                prest.setInt(2, mascota.getIdnumVet());
                prest.setString(3, mascota.getNumChip());
                prest.setString(4, mascota.getNomMasc());
                prest.setDouble(5, mascota.getPesoMasc());
                
                if (mascota.getFecnacMasc()!= null) {
                java.sql.Date sqlDate = new java.sql.Date(mascota.getFecnacMasc().getTime()); // Convertir a java.sql.Date
                prest.setDate(6, sqlDate);  // Asignamos la fecha en el formato correcto para SQL
                } else {
                prest.setNull(6, java.sql.Types.DATE);  // Si la fecha es null, asignamos NULL a la base de datos
                }
                
                prest.setString(7, mascota.getTipoMasc());

                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }

    }

    @Override
    public int insertMascotas(List<MascotasDTO> lista) throws SQLException {
        int numFilas = 0;

        for (MascotasDTO tmp : lista) {
            numFilas += insertMascotas(tmp);
        }

        return numFilas;
    }

    @Override
    public int deleteMascotas() throws SQLException {

        String sql = "delete from mascotas";

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
    public int deleteMascotas(int idMascota) throws SQLException {
        int numFilas = 0;

        String sql = "delete from mascotas where idnumMasc = ?";

        // Sentencia parametrizada
        try (PreparedStatement prest = con.prepareStatement(sql)) {

            // Establecemos los parámetros de la sentencia
            prest.setInt(1, idMascota);
            // Ejecutamos la sentencia
            numFilas = prest.executeUpdate();
        }
        return numFilas;
    }

    @Override
    public int updateMascotas(int idnumMasc, MascotasDTO nuevosDatos) throws SQLException {

        int numFilas = 0;
        String sql = "update mascotas set numchip = ?, nomMasc = ?, pesoMasc = ?, fecNacMasc = ?, tipoMasc = ? where idnumMasc = ?";

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
                prest.setString(2, nuevosDatos.getNumChip());
                prest.setString(3, nuevosDatos.getNomMasc());
                prest.setDouble(4, nuevosDatos.getPesoMasc());
                
                if (nuevosDatos.getFecnacMasc()!= null) {
                java.sql.Date sqlDate = new java.sql.Date(nuevosDatos.getFecnacMasc().getTime());
                prest.setDate(5, sqlDate);  // Usamos el constructor de java.sql.Date que toma un long
                } else {
                prest.setNull(5, java.sql.Types.DATE);  // Si la fecha es nula, asignamos null
                }
                
                prest.setString(6, nuevosDatos.getTipoMasc());

                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }
    }

    @Override
    public List<MascotasDTO> getMascotasByVeterinarios(int idnumVet) throws SQLException {
        List<MascotasDTO> lista = new ArrayList <>();
        String sql = "select * from mascotas where idnumVet = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idnumVet);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                MascotasDTO m = new MascotasDTO();
                m.setIdnumMasc(rs.getInt("id"));
                m.setNumChip(rs.getString("numchip"));
                m.setNomMasc(rs.getString("nombre"));
                m.setPesoMasc(rs.getDouble("peso"));
                m.setFecnacMasc(rs.getDate("fecha_nac"));
                m.setTipoMasc(rs.getString("tipo"));
                m.setIdnumVet(rs.getInt("id veterinario"));
                lista.add(m);
            }
        }
        return lista;
    }

    
}

