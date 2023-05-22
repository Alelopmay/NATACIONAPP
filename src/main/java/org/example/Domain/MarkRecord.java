package org.example.Domain;

import java.time.LocalDate;

public class MarkRecord {
   private Swimmer Cod_Swimmer;
   private TrialSwimmer Id;

   private int ID_Marckrecord;
   private String Time;
   private LocalDate Date;

   public MarkRecord(Swimmer cod_Swimmer, TrialSwimmer id, int ID_Marckrecord, String time, LocalDate date) {
      Cod_Swimmer = cod_Swimmer;
      Id = id;
      this.ID_Marckrecord = ID_Marckrecord;
      Time = time;
      Date = LocalDate.now();
   }

   public MarkRecord() {

    }

   public Swimmer getCod_Swimmer() {
      return Cod_Swimmer;
   }

   public void setCod_Swimmer(Swimmer cod_Swimmer) {
      Cod_Swimmer = cod_Swimmer;
   }

   public TrialSwimmer getId() {
      return Id;
   }

   public void setId(TrialSwimmer id) {
      Id = id;
   }

   public int getID_Marckrecord() {
      return ID_Marckrecord;
   }

   public void setID_Marckrecord(int ID_Marckrecord) {
      this.ID_Marckrecord = ID_Marckrecord;
   }

   public String getTime() {
      return Time;
   }

   public void setTime(String time) {
      Time = time;
   }

   public LocalDate getDate() {
      return Date;
   }

   public void setDate(LocalDate date) {
      Date = date;
   }

   @Override
   public String toString() {
      return "MarkRecord{" +
              "Cod_Swimmer=" + Cod_Swimmer +
              ", Id=" + Id +
              ", ID_Marckrecord=" + ID_Marckrecord +
              ", Time='" + Time + '\'' +
              ", Date=" + Date +
              '}';
   }
}
