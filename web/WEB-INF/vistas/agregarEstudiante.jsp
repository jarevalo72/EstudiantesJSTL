<%-- 
    Document   : agregarEstudiante
    Created on : 2/11/2019, 11:45:45 AM
    Author     : Ing. Jorge A. Arévalo A.
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
        <c:set var="estudiante" value="${requestScope.estudiante}"/>
        <div class="text-center">
            <div class="jumbotron jumbotron-fluid">
                <h2>Nuevo Estudiante</h2>
                <img src="images/LogoHorizontal.jpg" title="ETITC" class="img-thumbnail"/>
            </div>
        </div>
        <ol class="breadcrumb">
            <li><a href="/EstudiantesJSTL/verEstudiantes">
                    <i class="fas fa-user-friends"></i> Estudiantes
                </a></li>
            <li class="active">
                <c:choose>
                    <c:when test="${estudiante.id != 0}">
                        <c:out value="${estudiante.nombre} ${estudiante.apellido}"/>
                    </c:when>
                    <c:otherwise>
                        Nuevo Registro
                    </c:otherwise>
                </c:choose>
            </li>
        </ol>
        <div class="text-left">
            <form action="registrarEstudiante" method="POST">
            <div class="panel panel-default">
                <div class="panel-heading">Información Personal</div>
                <div class="panel-body">
                    <div class="form-group">
                        <b>Id:</b>
                        <div>
                            <input type="text" class="form-control" name="id" 
                                   value="<c:out value="${estudiante.id}"/>" required />
                        </div>
                    </div>
                    <div class="form-group">
                        <b>Nombre:</b>
                        <div>
                            <input type="text" class="form-control" name="nombre" 
                                   value="<c:out value="${estudiante.nombre}"/>" required />
                        </div>
                    </div>
                    <div class="form-group">
                        <b>Apellido:</b>
                        <div>
                            <input type="text" class="form-control" name="apellido" 
                                   value="<c:out value="${estudiante.apellido}"/>" required />
                        </div>
                    </div>
                    <div class="form-group">
                        <b>Email:</b>
                        <div>
                            <input type="text" class="form-control" name="email" 
                                   value="<c:out value="${estudiante.email}"/>" required />
                        </div>
                    </div>
                    <div class="form-group">
                        <b>Foto:</b>
                        <div>
                            <select name="foto" class="form-control" required>
                                <option value="images/silueta_hombre.jpg" 
                                        <c:if test="${estudiante.foto eq 'images/silueta_hombre.jpg'}">
                                            selected
                                        </c:if>
                                        > Hombre </option>
                                <option value="images/silueta_mujer.jpg" 
                                        <c:if test="${estudiante.foto eq 'images/silueta_mujer.jpg'}">
                                            selected
                                        </c:if>
                                        > Mujer </option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">Cursos</div>
                <div class="panel-body">
                    <ul class="list-group">
                        <c:forEach var="c1" items="${requestScope.cursos}">
                            <li class="list-group-item">
                                <label>
                                    <input type="checkbox" name="cursos"
                                           value="${c1.id}" />
                                    <c:out value="${c1.nombre}"/>
                                </label>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
            <div class="text-right">
                <button type="submit" class="btn btn-primary" value="Guardar" name="submit">
                    <i class="fas fa-save"></i> Guardar
                </button>
            </div>
            <div><br></div>
            </form>
        </div>
