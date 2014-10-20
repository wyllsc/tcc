package br.com.csempreendimentos.sgi.model;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TB_VENDA")
public class Venda implements Serializable
{
   private static final long serialVersionUID = 584031168533602335L;

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "ID_VENDA", length = 4)
   private Long id;

   @ManyToOne
   @JoinColumn(name = "ID_RESERVA", referencedColumnName = "ID_RESERVA")
   private Reserva reserva;

   @Column(name = "DT_VENDA")
   private Date dtVenda;

   // GETTERS E SETTERS

   public Long getId()
   {
      return id;
   }

   public Reserva getReserva()
   {
      return reserva;
   }

   public void setReserva(Reserva reserva)
   {
      this.reserva = reserva;
   }

   public Date getDtVenda()
   {
      return dtVenda;
   }

   public void setDtVenda(Date dtVenda)
   {
      this.dtVenda = dtVenda;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      Venda other = (Venda) obj;
      if (id == null)
      {
         if (other.id != null)
            return false;
      }
      else if (!id.equals(other.id))
         return false;
      return true;
   }
}