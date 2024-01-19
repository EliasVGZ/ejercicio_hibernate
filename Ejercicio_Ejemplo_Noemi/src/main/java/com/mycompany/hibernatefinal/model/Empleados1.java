/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hibernatefinal.model;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
/**
 *
 * @author a22alejandrofc
 */
@Entity
@Table(name="empleados",
	   uniqueConstraints={@UniqueConstraint(columnNames={"emp_no"})})

public class Empleados1 {
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//clave primaria
	@Column(name="EMP_NO", nullable=false, unique=true, length=4)
	private int emp_no;
	
	@Column(name="APELLIDO", length=10, nullable=true)
	private String apellido;

	@Column(name="OFICIO", length=10, nullable=true)
	private String oficio;

	@Column(name="DIR", length=10, nullable=true)
	private int dir;

	@Column(name="FECHA_ALT", length=10, nullable=true)
	private LocalDate fecha_alt;
	
	@Column(name="SALARIO", length=20, nullable=true)
	private float salario;

	@Column(name="COMISION", length=20, nullable=true)
	private float comision;

	@Column(name="DEPT_NO", length=2, nullable=true)
	private int dept_no;


	public int getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getOficio() {
		return oficio;
	}

	public void setOficio(String oficio) {
		this.oficio = oficio;
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	public LocalDate getFecha_alt() {
		return fecha_alt;
	}

	public void setFecha_alt(LocalDate fecha_alt) {
		this.fecha_alt = fecha_alt;
	}

	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}

	public float getComision() {
		return comision;
	}

	public void setComision(float comision) {
		this.comision = comision;
	}

	public int getDept_no() {
		return dept_no;
	}

	public void setDept_no(int dept_no) {
		this.dept_no = dept_no;
	}
}
