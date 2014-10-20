package br.com.csempreendimentos.sgi.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RL_CONSTRUTORA_EMPREENDIMENTO")
public class ConstrutoraEmpreendimento implements Serializable
{
   private static final long serialVersionUID = -5282994593125323181L;

   // @OneToMany
   // @Cascade(value = CascadeType.ALL)
   // @OneToMany
   // @Cascade(value = CascadeType.ALL)
   // @JoinColumn(name = "ID_EMPREENDIMENTO", referencedColumnName="ID_EMPREENDIMENTO")
   // @JoinColumn(name = "ID_CONSTRUTORA", referencedColumnName="ID_CONSTRUTORA")
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "ID")
   private Long id;

   @Column(name = "ID_CONSTRUTORA")
   private Long construtora;

   @Column(name = "ID_EMPREENDIMENTO")
   private Long empreendimento;

   // GETTERS AND SETTERS

   public Long getConstrutora()
   {
      return construtora;
   }

   public void setConstrutora(Long construtora)
   {
      this.construtora = construtora;
   }

   public Long getEmpreendimento()
   {
      return empreendimento;
   }

   public void setEmpreendimento(Long empreendimento)
   {
      this.empreendimento = empreendimento;
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((construtora == null) ? 0 : construtora.hashCode());
      result = prime * result + ((empreendimento == null) ? 0 : empreendimento.hashCode());
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
      ConstrutoraEmpreendimento other = (ConstrutoraEmpreendimento) obj;
      if (construtora == null)
      {
         if (other.construtora != null)
            return false;
      }
      else if (!construtora.equals(other.construtora))
         return false;
      if (empreendimento == null)
      {
         if (other.empreendimento != null)
            return false;
      }
      else if (!empreendimento.equals(other.empreendimento))
         return false;
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