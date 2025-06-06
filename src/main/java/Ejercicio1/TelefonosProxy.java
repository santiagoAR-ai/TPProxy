package Ejercicio1;

import java.sql.*;
import java.util.*;

public class TelefonosProxy implements Set<Telefono> {
    private Set<Telefono> telefonosReales;
    private boolean cargado = false;
    private int idPersona;
    private Connection conexion;

    public TelefonosProxy(int idPersona, Connection conexion) {
        this.idPersona = idPersona;
        this.conexion = conexion;
    }

    private void cargar() {
        if (!cargado) {
            telefonosReales = new HashSet<>();
            String sql = "SELECT numero FROM telefonos WHERE idpersona = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
                stmt.setInt(1, idPersona);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    telefonosReales.add(new Telefono(rs.getString("numero")));
                }
                cargado = true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Métodos de Set que desencadenan la carga
    @Override public int size() { cargar(); return telefonosReales.size(); }
    @Override public boolean isEmpty() { cargar(); return telefonosReales.isEmpty(); }
    @Override public boolean contains(Object o) { cargar(); return telefonosReales.contains(o); }
    @Override public Iterator<Telefono> iterator() { cargar(); return telefonosReales.iterator(); }
    @Override public Object[] toArray() { cargar(); return telefonosReales.toArray(); }
    @Override public <T> T[] toArray(T[] a) { cargar(); return telefonosReales.toArray(a); }
    @Override public boolean add(Telefono e) { cargar(); return telefonosReales.add(e); }
    @Override public boolean remove(Object o) { cargar(); return telefonosReales.remove(o); }
    @Override public boolean containsAll(Collection<?> c) { cargar(); return telefonosReales.containsAll(c); }
    @Override public boolean addAll(Collection<? extends Telefono> c) { cargar(); return telefonosReales.addAll(c); }
    @Override public boolean retainAll(Collection<?> c) { cargar(); return telefonosReales.retainAll(c); }
    @Override public boolean removeAll(Collection<?> c) { cargar(); return telefonosReales.removeAll(c); }
    @Override public void clear() { cargar(); telefonosReales.clear(); }
}

