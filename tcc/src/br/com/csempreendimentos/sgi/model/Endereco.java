package br.com.csempreendimentos.sgi.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "TB_ENDERECO")
public class Endereco implements Serializable
{
   private static final long serialVersionUID = -8954512304125558470L;

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "ID_ENDERECO", length = 4)
   private Long id;

   @Column(name = "DS_ENDERECO", length = 50)
   private String endereco;

   @Column(name = "DS_COMPLEMENTO", length = 50)
   private String complemento;

   @Column(name = "DS_CEP", length = 10)
   private String cep;

   @ManyToOne
   @Cascade(value=CascadeType.ALL)
   @JoinColumn(name = "ID_BAIRRO", referencedColumnName = "ID_BAIRRO")
   private Bairro bairro;

   // GETTERS E SETTERS

   public Long getId()
   {
      return id;
   }

   public String getEndereco()
   {
      return endereco;
   }

   public void setEndereco(String endereco)
   {
      this.endereco = endereco;
   }

   public String getComplemento()
   {
      return complemento;
   }

   public void setComplemento(String complemento)
   {
      this.complemento = complemento;
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
      Endereco other = (Endereco) obj;
      if (id == null)
      {
         if (other.id != null)
            return false;
      }
      else if (!id.equals(other.id))
         return false;
      return true;
   }

   public Bairro getBairro()
   {
      if (bairro == null)
      {
         bairro = new Bairro();
      }
      return bairro;
   }

   public void setBairro(Bairro bairro)
   {
      this.bairro = bairro;
   }

   public String getCep()
   {
      return cep;
   }

   public void setCep(String cep)
   {
      this.cep = cep;
   }
}