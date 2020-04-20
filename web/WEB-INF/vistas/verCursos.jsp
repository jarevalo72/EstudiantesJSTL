<%-- 
    Document   : verCursos
    Created on : 2/11/2019, 11:43:47 AM
    Author     : Ing. Jorge A. Arévalo A.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
        <div class="text-center">
            <div class="jumbotron jumbotron-fluid">
                <h2>Cursos</h2>
                <p>Listado de los cursos habilitados</p>
                <img src="images/LogoHorizontal.jpg" title="ETITC" class="img-thumbnail"/>
            </div>
        </div>
        <div class="text-right">
            <a class="btn btn-primary" href="agregarCurso">
                <i class="fas fa-university"></i> Agregar Curso
            </a>
            <a class="btn btn-danger" href="reporteCursos">
                <i class="far fa-file-pdf"></i> Reporte Cursos
            </a>
        </div>
        <ol class="breadcrumb">
            <li><a href="/EstudiantesJSTL/verEstudiantes">
                    <i class="fas fa-user-friends"></i> Estudiantes
                </a></li>
            <li class="active">Cursos</li>
        </ol>
        <div class="panel panel-default">
            <div class="panel-body">
                <dl class="dl-horizontal">
                    <dt><b>Id</b></dt>
                    <dd><b>Nombre</b></dd>
                    <c:forEach var="c" items="${requestScope.cursos}">
                        <dt>${c.id}</dt>
                        <dd>${c.nombre}</dd>
                    </c:forEach>
                </dl>
            </div>
        </div>