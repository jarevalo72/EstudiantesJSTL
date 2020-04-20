<%-- 
    Document   : verEstudiantes
    Created on : 2/11/2019, 11:43:05 AM
    Author     : Ing. Jorge A. Arévalo A.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
        <div class="text-center">
            <div class="jumbotron jumbotron-fluid">
                <h2>Estudiantes</h2>
                <p>Listado de los estudiantes registrados</p>
                <img src="images/LogoHorizontal.jpg" title="ETITC" class="img-thumbnail"/>
            </div>
        </div>
        <div class="text-right">
            <a class="btn btn-primary" href="agregarEstudiante">
                <i class="fas fa-user-plus"></i> Agregar Estudiante
            </a>
            <a class="btn btn-primary" href="verCursos">
                <i class="far fa-list-alt"></i> Ver Cursos
            </a>
            <a class="btn btn-danger" href="reporteEstudiantes">
                <i class="far fa-file-pdf"></i> Reporte Estudiantes
            </a>
        </div>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th style="width:100px">Id</th>
                    <th style="width:150px">Nombre</th>
                    <th style="width:150px">Apellido</th>
                    <th style="width:150px">Email</th>
                    <th style="width:60px">Foto</th>
                    <th style="width:200px">Curso 1</th>
                    <th style="width:200px">Curso 2</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="e" items="${requestScope.estudiantes}">
                    <tr>
                        <td><c:out value="${e.id}"/></td>
                        <td><c:out value="${e.nombre}"/></td>
                        <td><c:out value="${e.apellido}"/></td>
                        <td><c:out value="${e.email}"/></td>
                        <td><img src="${e.foto}" width="40" height="40" 
                                 alt="foto ${e.id}" class="img-thumbnail"/></td>
                        <c:forEach var="c" items="${e.cursos}">
                            <td><c:out value="${c.nombre}"/></td>
                        </c:forEach>
                        <td>
                            <form action="verEstudiante" method="POST">
                                <input type="hidden" value="${e.id}" name="id"/>
                                <button type="submit" class="btn btn-info" 
                                        title="Ver">
                                    <i class="fas fa-eye"></i> Ver
                                </button>
                            </form>
                        </td>
                        <td>
                            <form action="editarEstudiante" method="POST">
                                <input type="hidden" value="${e.id}" name="id"/>
                                <button type="submit" class="btn btn-default" 
                                        title="Editar">
                                    <i class="fas fa-user-edit"></i> Editar
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
