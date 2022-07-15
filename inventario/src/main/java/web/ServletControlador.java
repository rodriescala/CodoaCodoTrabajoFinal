package web;

import data.LibrosDAO;
import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.*;

@WebServlet("/servletControlador")
public class ServletControlador extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException{
        String accion = req.getParameter("accion");
        
        if(accion!=null){
            switch(accion){
                case "eliminar":
                    eliminarLibro(req,res);
                    break;
                case "editar":
                    editarLibro(req, res);
                    break;
                default:
                    accionDefault(req, res);
                    break;
            }
        }else{
            accionDefault(req, res);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req , HttpServletResponse res)throws ServletException, IOException{
        String queryParam = req.getParameter("accion");
        if(queryParam!=null){
            switch(queryParam){
                case "insertar":
                    insertarLibro(req,res);
                    break;
                case "modificar":
                    modificarLibro(req,res);
                    break;
                default:
                    accionDefault(req,res);
                    break;
            }
        }
    }
    
    private void accionDefault(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException{
        List <Biblioteca> libros = new LibrosDAO().findAll();
        libros.forEach(System.out::println);
        HttpSession sesion = req.getSession();
        sesion.setAttribute("libros", libros);
        sesion.setAttribute("cantidadLibros",calcularCopias(libros));
        sesion.setAttribute("precioTotal", calcularPrecio(libros));
        res.sendRedirect("libros.jsp");
    }
    
    private void insertarLibro(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException{
        String nombre = req.getParameter("nombre");
        String autor = req.getParameter("autor");
        int cantpaginas = Integer.parseInt(req.getParameter("cantpaginas"));
        double precio = Double.parseDouble(req.getParameter("precio"));
        int copias = Integer.parseInt(req.getParameter("copias"));
        
        Biblioteca libro = new Biblioteca(nombre, autor, cantpaginas, precio, copias);
        
        int registrosMod = new LibrosDAO().insert(libro);
        
        System.out.println("insertados = " + registrosMod);
        
        accionDefault(req, res);
        
    }
    
        private void eliminarLibro(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException{
        int idlibros = Integer.parseInt(req.getParameter("idlibros"));
        
        int regMod = new LibrosDAO().deleteLibro(idlibros);
        
        System.out.println("SE ELIMINARON: "+ regMod +" REGISTROS");
        
        accionDefault(req, res);
        
     }   
    
    private void editarLibro(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException{
        int idlibros = Integer.parseInt(req.getParameter("idlibros"));
        Biblioteca libro = new LibrosDAO().findById(idlibros);
        req.setAttribute("libro", libro);
        String jspEditar = "/WEB-INF/paginas/operaciones/editar.jsp";
        req.getRequestDispatcher(jspEditar).forward(req, res);
        
    }
        private void modificarLibro(HttpServletRequest req , HttpServletResponse res)throws ServletException, IOException{
        String nombre = req.getParameter("nombre");
        String autor = req.getParameter("autor");
        int cantpaginas = Integer.parseInt(req.getParameter("cantpaginas"));
        double precio = Double.parseDouble(req.getParameter("precio"));
        int copias = Integer.parseInt(req.getParameter("copias"));
        
        int idLibro = Integer.parseInt(req.getParameter("idLibro"));
        
        Biblioteca lib = new Biblioteca(idLibro,nombre,autor,cantpaginas,precio,copias);
        
        int regMod = new LibrosDAO().update(lib);
        
        System.out.println("SE ACTUALIZARON: "+ regMod +" REGISTROS");
        
        accionDefault(req, res);
    }
    

    
    private int calcularCopias(List<Biblioteca> lib){
        int cant=0;
        for (int i = 0; i < lib.size(); i++) {
            cant += lib.get(i).getCopias();
        }
        return cant;
    }
    
    private double calcularPrecio(List<Biblioteca> lib){
        double precio = 0;
        for (int i = 0; i < lib.size(); i++) {
            precio += (lib.get(i).getPrecio() * lib.get(i).getCopias());
        }
        return precio;
    }
}
