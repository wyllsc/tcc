package br.com.csempreendimentos.sgi.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RL_EMPREENDIMENTO_IMOVEL")
public class EmpreendimentoImovel implements Serializable
{
   private static final long serialVersionUID = -5282994593125323181L;

   // @OneToMany
   // @Cascade(value = CascadeType.ALL)
   // @JoinColumn(name = "ID_EMPREENDIMENTO", referencedColumnName="ID_EMPREENDIMENTO")
   // @OneToMany
   // @Cascade(value = CascadeType.ALL)
   // @JoinColumn(name = "ID_IMOVEL", referencedColumnName="ID_IMOVEL")
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "ID")
   private Long id;

   @Column(name = "ID_EMPREENDIMENTO")
   private Long empreendimento;

   @Column(name = "ID_IMOVEL")
   private Long imovel;

   // GETTERS AND SETTERS
   public void setEmpreendimento(Long empreendimento)
   {
      this.empreendimento = empreendimento;
   }

   public void setImovel(Long imovel)
   {
      this.imovel = imovel;
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((empreendimento == null) ? 0 : empreendimento.hashCode());
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      result = prime * result + ((imovel == null) ? 0 : imovel.hashCode());
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
      EmpreendimentoImovel other = (EmpreendimentoImovel) obj;
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
      if (imovel == null)
      {
         if (other.imovel != null)
            return false;
      }
      else if (!imovel.equals(other.imovel))
         return false;
      return true;
   }

}