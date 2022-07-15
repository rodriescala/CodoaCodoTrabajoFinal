<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="es_AR"/>
<section id="libros">
    <div class="container">
        <div class="row">
            <div class="col">
                <div class="card">
                    <div class="card-header bg-dark text-white text-center">
                        <h4>Listado de Libros</h4>
                    </div>
                    <table class="table table-striped table-white">
                        <thead class="thead-dark">
                            <tr>
                                <th>#</th>
                                <th>Nombre</th>
                                <th>Autor</th>
                                <th>Cantidad de Paginas</th>
                                <th>Precio</th>
                                <th>Copias</th>
                                <th>Accion</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="libro" items="${libros}" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${libro.nombre}</td>
                                    <td>${libro.autor}</td>
                                    <td>${libro.cantpaginas}</td>
                                    <td><fmt:formatNumber value="${libro.precio}" type="currency"/></td>
                                    <td>${libro.copias}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/servletControlador?accion=editar&idlibros=${libro.idlibros}" class="btn btn-secondary">
                                            <i class="fas fa-angle-double-right"></i>
                                            Editar
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        
        
        <div class="card-group">
            
                <div class="card text-center bg-light text-dark mb-3">
                    <div class="card-body">
                        <h3>Cantidad de libros</h3>
                        <h4 class="display-inline">${cantidadLibros}</h4>
                    </div>
                </div>
                    
                <div class="card text-center bg-dark text-white mb-3">
                    <div class="card-body">
                        <h3>Precio Total de Libros</h3>
                        <h4 class="display-inline"><fmt:formatNumber value="${precioTotal}" type="currency"/></h4>
                    </div>
                </div>
            </div>
        </div>                  
</section>
<jsp:include page="/WEB-INF/paginas/operaciones/agregarLibro.jsp"/>