package data;

import static data.Conexion.*;
import java.sql.*;
import java.util.*;
import model.Biblioteca;

public class LibrosDAO {
    private static final String SQL_CREATE="INSERT INTO libros(nombre, autor, cantpaginas, precio, copias) VALUES(?, ?, ?, ?, ?)";
    private static final String SQL_READ="SELECT * FROM libros";
    private static final String SQL_READ_BY_ID= "SELECT * FROM libros WHERE idlibros= ?";
    private static final String SQL_UPDATE_PRECIO="UPDATE libros SET precio = ? WHERE idlibros = ?";
    private static final String SQL_UPDATE_COPIAS="UPDATE libros SET copias = ? WHERE idlibros = ?";
    private static final String SQL_UPDATE="UPDATE libros SET nombre = ?, autor = ?, cantpaginas = ?, precio = ?,copias = ? WHERE idlibros = ?";
    private static final String SQL_DELETE="DELETE FROM libros WHERE idlibros = ?";
    
    
    public List<Biblioteca> findAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Biblioteca libro ;
        List<Biblioteca> libros = new ArrayList();

        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_READ);
            rs = stmt.executeQuery();
            while (rs.next()) {
                
                int idlibros = rs.getInt(1);
                String nombre = rs.getString(2);
                String autor = rs.getString(3);
                int cantpaginas = rs.getInt(4);
                double precio = rs.getDouble(5);
                int copias = rs.getInt(6);

                libro = new Biblioteca(idlibros, nombre, autor,cantpaginas,precio,copias);

                libros.add(libro);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(rs);
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }

        return libros;
    }
    
    public Biblioteca findById(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Biblioteca libro = null;
        
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_READ_BY_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                int idlibros = rs.getInt(1);
                String nombre = rs.getString(2);
                String autor = rs.getString(3);
                int cantpaginas = rs.getInt(4);
                double precio = rs.getDouble(5);
                int copias = rs.getInt(6);

                libro = new Biblioteca(idlibros, nombre, autor,cantpaginas,precio,copias);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(rs);
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return libro;
    }
    
    
    
    public int insert(Biblioteca libro){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_CREATE);
            stmt.setString(1, libro.getNombre());
            stmt.setString(2, libro.getAutor());
            stmt.setInt(3, libro.getCantpaginas());
            stmt.setDouble(4, libro.getPrecio());
            stmt.setInt(5, libro.getCopias());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    
    public int updatePrecio(Biblioteca libro){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_UPDATE_PRECIO);
            stmt.setDouble(1, libro.getPrecio());
            stmt.setInt(2, libro.getIdlibros());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    
    public int updateCopias(Biblioteca libro){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_UPDATE_COPIAS);
            stmt.setInt(1, libro.getCopias());
            stmt.setInt(2, libro.getIdlibros());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    
    public int update(Biblioteca libro){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, libro.getNombre());
            stmt.setString(2, libro.getAutor());
            stmt.setInt(3, libro.getCantpaginas());
            stmt.setDouble(4, libro.getPrecio());
            stmt.setInt(5, libro.getCopias());
            stmt.setInt(6, libro.getIdlibros());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    
    public int deleteLibro(int id){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, id);
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
}
