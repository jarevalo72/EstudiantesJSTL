<%-- 
    Document   : agregarCurso
    Created on : 2/11/2019, 11:46:19 AM
    Author     : Ing. Jorge A. Arévalo A.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
        <div class="text-center">
            <div class="jumbotron jumbotron-fluid">
                <h2>Nuevo Curso</h2>
                <img src="images/LogoHorizontal.jpg" title="ETITC" class="img-thumbnail"/>
            </div>
        </div>
        <ol class="breadcrumb">
            <li><a href="/EstudiantesJSTL/verEstudiantes">
                    <i class="fas fa-user-friends"></i> Estudiantes
                </a></li>
            <li><a href="/EstudiantesJSTL/verCursos">
                    <i class="far fa-list-alt"></i> Cursos
                </a></li>
            <li class="active">Nuevo Registro</li>
        </ol>
        <div class="text-left">
            <form action="agregarCurso" method="POST">
            <div class="panel panel-default">
                <div class="panel-heading">Información Curso</div>
                <div class="panel-body">
                    <div class="form-group">
                        <b>Id:</b>
                        <div>
                            <input type="text" class="form-control" name="id" required />
                        </div>
                    </div>
                    <div class="form-group">
                        <b>Nombre:</b>
                        <div>
                        <input type="text" class="form-control" name="nombre" required />
                        </div>
                    </div>
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
