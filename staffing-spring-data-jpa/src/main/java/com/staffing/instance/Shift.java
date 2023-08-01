package com.staffing.instance;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Shift implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shift_SEQ")
   @SequenceGenerator(name = "shift_SEQ", sequenceName = "shift_seq", allocationSize = 1)
   private Long id;
   private String department;
   private Date shiftStart;
   private Date shiftEnd;
   private Long employeeId;

   public Long getEmployeeId() {
      return employeeId;
   }

   public void setEmployeeId(Long employeeId) {
      this.employeeId = employeeId;
   }

   public Shift() {

   }

   public Shift(String department, Date shiftStart, Date shiftEnd, Long employeeId) {
      this.department = department;
      this.shiftStart = shiftStart;
      this.shiftEnd = shiftEnd;
      this.employeeId = employeeId;
   }

   public Long getId() {
      return id;
   }
   public void setId(Long id) {
      this.id = id;
   }

   public String getDepartment() {
      return department;
   }

   public void setDepartment(String department) {
      this.department = department;
   }

   public Date getShiftStart() {
      return shiftStart;
   }

   public void setShiftStart(Date shiftStart) {
      this.shiftStart = shiftStart;
   }

   public Date getShiftEnd() {
      return shiftEnd;
   }

   public void setShiftEnd(Date shiftEnd) {
      this.shiftEnd = shiftEnd;
   }
}