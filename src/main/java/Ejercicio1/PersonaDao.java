package Ejercicio1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class PersonaDao {

    public Persona personaPorId(int id) {
        String sql = "SELECT nombre FROM personas WHERE id = ?";
        try {
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                Set<Telefono> telefonosProxy = new TelefonosProxy(id, conn);
                return new Persona(id, nombre, telefonosProxy);
            } else {
                return null;
            }
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
