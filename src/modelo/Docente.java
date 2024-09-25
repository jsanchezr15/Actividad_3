/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Juanjo SR
 */
public class Docente extends Persona{
    private int id_docente;
    private String cod_docente;
    private double salario;
    private String f_ing_lab;
    private String f_ing_reg;
    Conexion cn;
    
    public int getId_docente() {
        return id_docente;
    }

    public void setId_docente(int id_docente) {
        this.id_docente = id_docente;
    }

    public String getCod_docente() {
        return cod_docente;
    }

    public void setCod_docente(String cod_docente) {
        this.cod_docente = cod_docente;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getF_ing_lab() {
        return f_ing_lab;
    }

    public void setF_ing_lab(String f_ing_lab) {
        this.f_ing_lab = f_ing_lab;
    }

    public String getF_ing_reg() {
        return f_ing_reg;
    }

    public void setF_ing_reg(String f_ing_reg) {
        this.f_ing_reg = f_ing_reg;
    }
     
    public DefaultTableModel leer(){
        DefaultTableModel tabla = new DefaultTableModel();
        try{
            cn = new Conexion();
            cn.abrir_conexion();
            String query;
            query = "select id_docente as id_docente,nit,cod_docente,salario,f_ingreso_laborar,f_ingreso_registro,nombres,apellidos,direccion,telefono,fecha_nacimiento from docente;";
            ResultSet consulta =  cn.conexionBD.createStatement().executeQuery(query);
            
            String encabezado[] = {"ID_DOCENTE","NIT","COD_DOCENTE","NOMBRES","APELLIDOS","DIRECCION","TELEFONO","FECHA_NACIMIENTO","SALARIO","F_INGRESO_LABORAR","F_INGRESO_REGISTRO"};
            tabla.setColumnIdentifiers(encabezado);
            
            String datos[] = new String[11];
            while(consulta.next()){
                datos[0] = consulta.getString("ID_DOCENTE");
                datos[1] = consulta.getString("NIT");
                datos[2] = consulta.getString("COD_DOCENTE");
                datos[3] = consulta.getString("NOMBRES");
                datos[4] = consulta.getString("APELLIDOS");
                datos[5] = consulta.getString("DIRECCION");
                datos[6] = consulta.getString("TELEFONO");
                datos[7] = consulta.getString("FECHA_NACIMIENTO");
                datos[8] = consulta.getString("SALARIO");
                datos[9] = consulta.getString("F_INGRESO_LABORAR");
                datos[10] = consulta.getString("F_INGRESO_REGISTRO");
                tabla.addRow(datos);
            }
            cn.cerrar_conexion();
            
        }catch(SQLException ex){
            cn.cerrar_conexion();
            System.out.println("Error..."+ex.getMessage());
        }
        return tabla;
    }
    
    @Override
    public void crear(){
        try{
            PreparedStatement parametro;
            String query = "insert into docente (nit,cod_docente,salario,f_ingreso_laborar,f_ingreso_registro,nombres,apellidos,direccion,telefono,fecha_nacimiento) values (?,?,?,?,?,?,?,?,?,?)";
            cn = new Conexion();
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, this.getNit());
            parametro.setString(2, this.getCod_docente());
            parametro.setDouble(3, this.getSalario());
            parametro.setString(4, this.getF_ing_lab());
            parametro.setString(5, this.getF_ing_reg());
            parametro.setString(6, this.getNombre());
            parametro.setString(7, this.getApellido());
            parametro.setString(8, this.getDireccion());
            parametro.setString(9, this.getTelefono());
            parametro.setString(10, this.getFechanacimiento());
            
            
            int ejecutar = parametro.executeUpdate();
            cn.cerrar_conexion();
            JOptionPane.showMessageDialog(null, Integer.toString(ejecutar)+ " Registro Ingresado","Agregar", JOptionPane.INFORMATION_MESSAGE);
            
        }catch(HeadlessException | SQLException ex){
            System.out.println("Error..."+ex.getMessage());
        }
    } 
    public void actualizar(){
        try{
            PreparedStatement parametro;
            String query = "update docente set nit = ?,cod_docente = ?,nombres = ?,apellidos = ?,direccion = ?,telefono = ?, fecha_nacimiento = ?,salario = ?,f_ingreso_laborar = ?,f_ingreso_registro = ? where id_docente = ?";
            cn = new Conexion();
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, this.getNit());
            parametro.setString(2, this.getCod_docente());
            parametro.setString(3, this.getNombre());
            parametro.setString(4, this.getApellido());
            parametro.setString(5, this.getDireccion());
            parametro.setString(6, this.getTelefono());
            parametro.setString(7, this.getFechanacimiento());
            parametro.setDouble(8, this.getSalario());
            parametro.setString(9, this.getF_ing_lab());
            parametro.setString(10, this.getF_ing_reg());
            parametro.setInt(11, this.getId_docente());
            
            int ejecutar = parametro.executeUpdate();
            cn.cerrar_conexion();
            JOptionPane.showMessageDialog(null, Integer.toString(ejecutar)+ " Registro Actualizado","Agregar", JOptionPane.INFORMATION_MESSAGE);
            
        }catch(HeadlessException | SQLException ex){
            System.out.println("Error..."+ex.getMessage());
        }        
    }
    @Override
    public void borrar(){
        try{
            PreparedStatement parametro;
            String query = "delete from docente where id_docente = ?";
            cn = new Conexion();
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);

            parametro.setInt(1, this.getId_docente());
            
            int ejecutar = parametro.executeUpdate();
            cn.cerrar_conexion();
            JOptionPane.showMessageDialog(null, Integer.toString(ejecutar)+ " Registro Eliminado","Agregar", JOptionPane.INFORMATION_MESSAGE);
            
        }catch(HeadlessException | SQLException ex){
            System.out.println("Error..."+ex.getMessage());
        }
    }
      
}
