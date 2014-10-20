package br.com.csempreendimentos.sgi.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_BAIRRO")
public class Bairro implements Serializable
{
   private static final long serialVersionUID = -8954512304125558470L;

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "ID_BAIRRO", length = 4)
   private Long id;

   @Column(name = "DS_BAIRRO", length = 50)
   private String descricaoBairro;

   // GETTERS E SETTERS

   public Long getId()
   {
      return id;
   }

   public String getDescricaoBairro()
   {
      return descricaoBairro;
   }

   public void setDescricaoBairro(String descricaoBairro)
   {
      this.descricaoBairro = descricaoBairro;
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
      Bairro other = (Bairro) obj;
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