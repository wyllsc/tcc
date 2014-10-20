package br.com.csempreendimentos.sgi.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_TELEFONE")
public class Telefone implements Serializable
{
   private static final long serialVersionUID = -8954512304125558470L;

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "ID_TELEFONE", length=4)
   private Long id;

   @Column(name = "NU_DDD_PREFERENCIAL", length=2)
   private int dddPreferencial;

   @Column(name = "NU_TELEFONE_PREFERENCIAL", length=8)
   private int telefonePreferencial;

   @Column(name = "NU_DDD_COORPORATIVO", length=2)
   private int dddCoorporativo;

   @Column(name = "NU_TELEFONE_COORPORATIVO", length=8)
   private int telefoneCoorporativo;

   @Column(name = "NU_DDD_RECADO", length=2)
   private int dddRecado;

   @Column(name = "NU_TELEFONE_RECADO", length=8)
   private int telefoneRecado;

   // GETTERS E SETTERS

   public Long getId()
   {
      return id;
   }

   public int getDddPreferencial()
   {
      return dddPreferencial;
   }

   public void setDddPreferencial(int dddPreferencial)
   {
      this.dddPreferencial = dddPreferencial;
   }

   public int getTelefonePreferencial()
   {
      return telefonePreferencial;
   }

   public void setTelefonePreferencial(int telefonePreferencial)
   {
      this.telefonePreferencial = telefonePreferencial;
   }

   public int getDddCoorporativo()
   {
      return dddCoorporativo;
   }

   public void setDddCoorporativo(int dddCoorporativo)
   {
      this.dddCoorporativo = dddCoorporativo;
   }

   public int getTelefoneCoorporativo()
   {
      return telefoneCoorporativo;
   }

   public void setTelefoneCoorporativo(int telefoneCoorporativo)
   {
      this.telefoneCoorporativo = telefoneCoorporativo;
   }

   public int getDddRecado()
   {
      return dddRecado;
   }

   public void setDddRecado(int dddRecado)
   {
      this.dddRecado = dddRecado;
   }

   public int getTelefoneRecado()
   {
      return telefoneRecado;
   }

   public void setTelefoneRecado(int telefoneRecado)
   {
      this.telefoneRecado = telefoneRecado;
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
      Telefone other = (Telefone) obj;
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