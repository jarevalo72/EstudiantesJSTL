<%-- 
    Document   : verEstudiante
    Created on : 26/04/2019, 01:09:41 PM
    Author     : Jorge A
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
        <c:set var="estudiante" value="${requestScope.estudiante}"/>
        <div class="text-center">
            <div class="jumbotron jumbotron-fluid">
                <h2>Estudiante</h2>
                <img src="images/LogoHorizontal.jpg" title="ETITC" class="img-thumbnail"/>
            </div>
        </div>
        <div class="text-right">
            <form action="reporteEstudiante" method="POST">
                <input type="hidden" value="${estudiante.getId()}" name="id"/>
                <button type="submit" class="btn btn-danger" 
                        title="Reporte">
                    <i class="far fa-file-pdf"></i> Reporte Estudiante
                </button>
            </form>
        </div>
        <ol class="breadcrumb">
            <li><a href="/EstudiantesJSTL/verEstudiantes">
                    <i class="fas fa-user-friends"></i> Estudiantes
                </a></li>
            <li class="active">
                <c:out value="${estudiante.getNombre()} ${estudiante.getApellido()}"/>
            </li>
        </ol>
        <div class="panel panel-default">
            <div class="panel-heading">Información Personal</div>
            <div class="panel-body">
                <dl class="dl-horizontal">
                    <dt>Identificación</dt>
                    <dd><c:out value="${estudiante.id}"/></dd>
                    <dt>Nombre</dt>
                    <dd><c:out value="${estudiante.nombre}"/></dd>
                    <dt>Apellido</dt>
                    <dd><c:out value="${estudiante.apellido}"/></dd>
                    <dt>Email</dt>
                    <dd><c:out value="${estudiante.email}"/></dd>
                    <dt>Foto</dt>
                    <dd><img src="${estudiante.foto}" width="150" 
                             height="150" alt="foto" class="img-thumbnail" /></dd>
                </dl>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">Cursos</div>
            <div class="panel-body">
                <ul class="list-group">
                    <c:forEach var="c" items="${estudiante.cursos}">
                        <li class="list-group-item"><c:out value="${c.nombre}"/></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
