package com.jorgemartinezruiz.gui;

import com.jorgemartinezruiz.datos.Cliente;
import com.jorgemartinezruiz.datos.Empleado;

import java.sql.*;
import java.time.LocalDate;

public class Modelo {


    private Connection conexion;


    //empleado
    Empleado empleado = new Empleado();
    Cliente cliente  = new Cliente();

    public void conectar() throws SQLException {
        conexion = null;
        conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bbddaseguradoratfg", "root", "");

    }

    public boolean clienteExistente(String username, String password) {

        //Instanciamos un objeto para almacenar la consulta SQL
        PreparedStatement sentencia = null;
        try {
            //Guardamos la consulta en un String
            String consulta = "SELECT * FROM clientes where username= ? and password= ?";
            //Preparamos la consulta
            sentencia = conexion.prepareStatement(consulta);
            //Asignamos los String en las diferentes posiciones de la consulta
            sentencia.setString(1, username);
            sentencia.setString(2, password);
            // sentencia.setString(3, tipoUser);
            //Ejecutamos la consulta y guardamos el resultado en un ResultSet
            ResultSet resultado = sentencia.executeQuery();

            if (resultado.next()) {

                cliente.setId(resultado.getString("idcliente"));
                return true;
            }
            return false;
        } catch (SQLException a) {
            a.printStackTrace();
        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (SQLException a) {
                    a.printStackTrace();
                }
            }
        }
        return false;
    }

    public boolean empleadoExistente(String username, String password) {

        //Instanciamos un objeto para almacenar la consulta SQL
        PreparedStatement sentencia = null;
        try {
            //Guardamos la consulta en un String
            String consulta = "SELECT * FROM empleados where username= ? and password= ? and idempleado > 1";
            //Preparamos la consulta
            sentencia = conexion.prepareStatement(consulta);
            //Asignamos los String en las diferentes posiciones de la consulta
            sentencia.setString(1, username);
            sentencia.setString(2, password);

            // sentencia.setString(3, tipoUser);
            //Ejecutamos la consulta y guardamos el resultado en un ResultSet
            ResultSet resultado = sentencia.executeQuery();

            if (resultado.next()) {
                empleado.setId(resultado.getString("idempleado"));
                empleado.setNombre(resultado.getString("nombre"));
                empleado.setApellido(resultado.getString("apellido"));
                empleado.setDni(resultado.getInt("dni"));
                empleado.setTelefono(resultado.getString("telefono"));
                return true;
            }
            return false;
        } catch (SQLException a) {
            a.printStackTrace();
        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (SQLException a) {
                    a.printStackTrace();
                }
            }
        }
        return false;
    }

    public boolean adminExistente(String username, String password) {

        //Instanciamos un objeto para almacenar la consulta SQL
        PreparedStatement sentencia = null;
        try {
            //Guardamos la consulta en un String
            String consulta = "SELECT * FROM empleados where username= ? and password= ? and idempleado = 1";
            //Preparamos la consulta
            sentencia = conexion.prepareStatement(consulta);
            //Asignamos los String en las diferentes posiciones de la consulta
            sentencia.setString(1, username);
            sentencia.setString(2, password);

            // sentencia.setString(3, tipoUser);
            //Ejecutamos la consulta y guardamos el resultado en un ResultSet
            ResultSet resultado = sentencia.executeQuery();

            if (resultado.next()) {

                return true;
            }
            return false;
        } catch (SQLException a) {
            a.printStackTrace();
        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (SQLException a) {
                    a.printStackTrace();
                }
            }
        }
        return false;
    }

    public void nuevoEmpleado(String nombre, String apellido, String dni,
                              int telefono, String username,
                              String password) {

        //Instanciamos un objeto que almacenar la consulta SQL
        PreparedStatement sentencia = null;
        try {
            //Guardamos la consulta en un String
            String consulta = "INSERT INTO empleados (nombre, apellido, dni, telefono, username, password) VALUES (?,?,?,?,?,?)";


            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, nombre);
            sentencia.setString(2, apellido);
            sentencia.setString(3, dni);
            sentencia.setInt(4, telefono);
            sentencia.setString(5, username);
            sentencia.setString(6, password);

            sentencia.executeUpdate();

        } catch (SQLException a) {
            a.printStackTrace();
        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (SQLException a) {
                    a.printStackTrace();
                }
            }
        }
    }

    public void nuevoCliente(String nombre, int edad, boolean x,
                             boolean b, String username,
                             String password) {

        //Instanciamos un objeto que almacenar la consulta SQL
        PreparedStatement sentencia = null;
        try {
            //Guardamos la consulta en un String
            String consulta = "INSERT INTO clientes (nombre, edad, antecedentes, fam_numerosa, username, password) VALUES (?,?,?,?,?,?)";


            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, nombre);
            sentencia.setInt(2, edad);
            sentencia.setBoolean(3, x);
            sentencia.setBoolean(4, b);
            sentencia.setString(5, username);
            sentencia.setString(6, password);

            sentencia.executeUpdate();

        } catch (SQLException a) {
            a.printStackTrace();
        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (SQLException a) {
                    a.printStackTrace();
                }

            }
        }
    }

    public void nuevoServicio(String nombre, Double precio) {

        //Instanciamos un objeto que almacenar la consulta SQL
        PreparedStatement sentencia = null;
        try {
            //Guardamos la consulta en un String
            String consulta = "INSERT INTO servicios (nombre, precio) VALUES (?,?)";

            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, nombre);
            sentencia.setDouble(2, precio);

            sentencia.executeUpdate();

        } catch (SQLException a) {
            a.printStackTrace();
        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (SQLException a) {
                    a.printStackTrace();
                }
            }
        }
    }

    public void realizarVenta(String precio, int idempleado, int idservicio, int idcliente) {

        //Instanciamos un objeto que almacenar la consulta SQL
        PreparedStatement sentencia = null;
        try {
            //Guardamos la consulta en un String
            String consulta = "INSERT INTO ventas (precio, empleados_idempleado,servicios_idservicio,clientes_idcliente, fecha) VALUES (?,?,?,?,?)";
            LocalDate date = LocalDate.now();
            Date fechaa = Date.valueOf(date);

            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, precio);
            sentencia.setInt(2, idempleado);
            sentencia.setInt(3, idservicio);
            sentencia.setInt(4, idcliente);
            sentencia.setDate(5, fechaa);

            sentencia.executeUpdate();

        } catch (SQLException a) {
            a.printStackTrace();
        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (SQLException a) {
                    a.printStackTrace();
                }
            }
        }
    }

    ResultSet consultarCliente() throws SQLException {
        String sentenciaSql = "SELECT concat(idcliente) as 'ID', concat(nombre) as 'Nombre', concat(edad) as 'Edad', " +
                "concat(username) as 'Username' FROM clientes";
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        sentencia = conexion.prepareStatement(sentenciaSql);
        resultado = sentencia.executeQuery();
        return resultado;
    }
    ResultSet consultarVenta() throws SQLException {
        String sentenciaSql = "SELECT concat(idventa) as 'ID', concat(precio) as 'precio', concat(empleados_idempleado) as 'empleado', concat(fecha) as 'fecha'," +
                "concat(servicios_idservicio) as 'servicio', concat(clientes_idcliente) as 'cliente'" +
                " FROM ventas  ";
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        sentencia = conexion.prepareStatement(sentenciaSql);
        resultado = sentencia.executeQuery();
        return resultado;
    }
    ResultSet consultarServicio() throws SQLException {
        String sentenciaSql = "SELECT concat(idservicio) as 'ID', concat(nombre) as 'nombre', concat(precio) as 'precio' " +
                " FROM servicios";
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        sentencia = conexion.prepareStatement(sentenciaSql);
        resultado = sentencia.executeQuery();
        return resultado;
    }
    ResultSet consultarMisServicios(int id) throws SQLException {
        String sentenciaSql = "SELECT idventa, S.nombre, V.precio, V.fecha FROM servicios S, ventas V\n" +
                "WHERE S.idservicio = V.servicios_idservicio and clientes_idcliente = ?";
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        sentencia = conexion.prepareStatement(sentenciaSql);
        sentencia.setInt(1,id);
        resultado = sentencia.executeQuery();
        return resultado;
    }
    ResultSet consultarEmpleado() throws SQLException {
        String sentenciaSql = "SELECT concat(idempleado) as 'ID', concat(nombre) as 'nombre', concat(apellido) as 'apellido', concat(telefono) as 'telefono', concat(dni) as 'dni', concat(username) as 'username' " +
                "FROM empleados";
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        sentencia = conexion.prepareStatement(sentenciaSql);
        resultado = sentencia.executeQuery();
        return resultado;
    }
    public ResultSet edadAscendente() throws SQLException {
        PreparedStatement sentencia = null;

        //Guardamos la consulta en un String

        String consulta = "SELECT concat(idcliente) as 'ID', concat(nombre) as 'Nombre', concat(edad) as 'Edad', " +
                "concat(username) as 'Username' FROM clientes ORDER BY edad ASC";

        sentencia = conexion.prepareStatement(consulta);
        ResultSet resultado = sentencia.executeQuery();

        return resultado;
    }

    public ResultSet edadDescendente() throws SQLException {
        PreparedStatement sentencia = null;

        //Guardamos la consulta en un String

        String consulta = "SELECT concat(idcliente) as 'ID', concat(nombre) as 'Nombre', concat(edad) as 'Edad', " +
                "concat(username) as 'Username' FROM clientes ORDER BY edad DESC";

        sentencia = conexion.prepareStatement(consulta);
        ResultSet resultado = sentencia.executeQuery();

        return resultado;
    }
    public ResultSet precioAscendente() throws SQLException {
        PreparedStatement sentencia = null;

        //Guardamos la consulta en un String

        String consulta = "SELECT concat(idventa) as 'ID', concat(precio) as 'precio', concat(empleados_idempleado) as 'empleado', concat(fecha) as 'fecha', " +
                "concat(servicios_idservicio) as 'servicio', concat(clientes_idcliente) as 'cliente'" +
                " FROM ventas  ORDER BY precio ASC";

        sentencia = conexion.prepareStatement(consulta);
        ResultSet resultado = sentencia.executeQuery();

        return resultado;
    }

    public ResultSet precioDescendente() throws SQLException {
        PreparedStatement sentencia = null;

        //Guardamos la consulta en un String

        String consulta = "SELECT concat(idventa) as 'ID', concat(precio) as 'precio', concat(empleados_idempleado) as 'empleado', concat(fecha) as 'fecha'," +
                "concat(servicios_idservicio) as 'servicio', concat(clientes_idcliente) as 'cliente'" +
                " FROM ventas  ORDER BY precio DESC";

        sentencia = conexion.prepareStatement(consulta);
        ResultSet resultado = sentencia.executeQuery();

        return resultado;
    }

    public int eliminarEmpleado(int id) throws SQLException {
        if (conexion==null)
            return -1;
        if (conexion.isClosed())
            return -2;

        String consulta="DELETE FROM empleados WHERE idempleado =?";
        PreparedStatement sentencia=null;

        sentencia=conexion.prepareStatement(consulta);
        sentencia.setInt(1,id);

        int resultado=sentencia.executeUpdate();

        if (sentencia!=null) {
            sentencia.close();
        }

        return resultado;

    }
    public int eliminarServicio(int id) throws SQLException {
        if (conexion==null)
            return -1;
        if (conexion.isClosed())
            return -2;

        String consulta="DELETE FROM servicios WHERE idservicio=?";
        PreparedStatement sentencia=null;

        sentencia=conexion.prepareStatement(consulta);
        sentencia.setInt(1,id);

        int resultado=sentencia.executeUpdate();

        if (sentencia!=null) {
            sentencia.close();
        }

        return resultado;

    }
    public int eliminarVenta(int id) throws SQLException {
        if (conexion==null)
            return -1;
        if (conexion.isClosed())
            return -2;

        String consulta="DELETE FROM ventas WHERE idventa=?";
        PreparedStatement sentencia=null;

        sentencia=conexion.prepareStatement(consulta);
        sentencia.setInt(1,id);

        int resultado=sentencia.executeUpdate();

        if (sentencia!=null) {
            sentencia.close();
        }

        return resultado;

    }
    public int modificarEmpleado (int id, String nombre, String apellido, int tlf,String dni, String username  ) throws SQLException {
        if (conexion==null)
            return -1;
        if (conexion.isClosed())
            return -2;

        String consulta="UPDATE empleados SET nombre=?, apellido=?,"+
                "dni=?,telefono=?,username=? WHERE idempleado=?";

        PreparedStatement sentencia=null;

        sentencia=conexion.prepareStatement(consulta);

        sentencia.setString(1,nombre);
        sentencia.setString(2,apellido);
        sentencia.setString(3,dni);
        sentencia.setInt(4,tlf);
        sentencia.setString(5,username);
        sentencia.setInt(6,id);

        int resultado=sentencia.executeUpdate();

        if (sentencia!=null) {
            sentencia.close();
        }

        return resultado;

    }
    /**
     * Método para buscar un servicio pasando el nombre del servicio como parametro
     * @return modifica la tabla con los datos pertinentes
     */

    public int modificarServicio (int id, String nombre, double precio) throws SQLException {
        if (conexion==null)
            return -1;
        if (conexion.isClosed())
            return -2;

        String consulta="UPDATE servicios SET nombre=?, precio=? WHERE idservicio=?";

        PreparedStatement sentencia=null;

        sentencia=conexion.prepareStatement(consulta);

        sentencia.setString(1,nombre);
        sentencia.setDouble(2,precio);
        sentencia.setInt(3,id);

        int resultado=sentencia.executeUpdate();

        if (sentencia!=null) {
            sentencia.close();
        }

        return resultado;
    }
    /**
     * Método para buscar un cliente pasando el nombre de usuario del cliente como parametro
     * @return un resultset con los datos buscados por nombre
     */
    ResultSet buscarCliente(String username) throws SQLException {
        String sentenciaSql = "SELECT concat(idcliente) as 'ID', " +
                "concat(username) as 'Username' FROM clientes WHERE username=?";
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        sentencia = conexion.prepareStatement(sentenciaSql);
        sentencia.setString(1,username);
        resultado = sentencia.executeQuery();
        return resultado;
    }
    /**
     * Método para buscar un servicio pasando el nombre del servicio como parametro
     * @return un resultset con los datos buscados por nombre
     */
    ResultSet buscarServicio(String nombre) throws SQLException {
        String sentenciaSql = "SELECT concat(idservicio) as 'ID', " +
                "concat(nombre) as 'Username',concat(precio) as 'Precio' FROM servicios WHERE nombre=?";
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        sentencia = conexion.prepareStatement(sentenciaSql);
        sentencia.setString(1,nombre);
        resultado = sentencia.executeQuery();
        return resultado;
    }

    /**
     * Método para buscar filtrando por nombre en la base de datos
     * @return un resultset con los datos buscados por nombre
     */

    ResultSet buscarPorNombre(String nombre,String idque) throws SQLException {
        String sentenciaSql = "";
        if (idque == "idcliente") {
            sentenciaSql = "SELECT * FROM clientes WHERE nombre = ?";
        }
        if(idque == "idempleado"){
            sentenciaSql = "SELECT * FROM empleados WHERE nombre = ?";
        }
        if(idque == "idservicio"){
            sentenciaSql = "SELECT * FROM servicios WHERE nombre = ?";
        }
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        sentencia = conexion.prepareStatement(sentenciaSql);

        sentencia.setString(1,nombre);
        resultado = sentencia.executeQuery();
        return resultado;
    }
    /**
     * Método para buscar filtrando por id en la base de datos
     * @return un resultset con los datos buscados por id
     */
    ResultSet buscarPorId(int id,  String idque) throws SQLException {
        String sentenciaSql = "";
        if (idque == "idcliente") {
           sentenciaSql = "SELECT * FROM clientes WHERE idcliente = ?";
        }
        if(idque == "idempleado"){
            sentenciaSql = "SELECT * FROM empleados WHERE idempleado = ?";
        }
        if(idque == "idservicio"){
            sentenciaSql = "SELECT * FROM servicios WHERE idservicio = ?";
        }

        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        sentencia = conexion.prepareStatement(sentenciaSql);


        sentencia.setInt(1, id);
        resultado = sentencia.executeQuery();
        return resultado;
    }
}
