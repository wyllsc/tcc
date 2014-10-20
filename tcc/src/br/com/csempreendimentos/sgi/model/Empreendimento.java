package br.com.csempreendimentos.sgi.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "TB_EMPREENDIMENTO")
public class Empreendimento implements Serializable
{
   private static final long serialVersionUID = -5282994593125323181L;

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "ID_EMPREENDIMENTO", length = 4)
   private Long id;

   @Column(name = "DS_NOME_EMPREENDIMENTO", length = 100)
   private String nomeEmpreendimento;

   @Column(name = "DS_REGIAO", length = 60)
   private String regiao;

   @Transient
   private int contImovelEmpreendimento;

   // @ManyToOne
   // @JoinColumn(name = "ID_CONSTRUTORA", referencedColumnName = "ID_CONSTRUTORA")
   // private Construtora construtora;

   // @OneToMany
   // @Cascade(value = CascadeType.ALL)
   // @JoinColumn(name = "ID_LISTA_IMOVEIS")
   // private List<Imovel> listImoveis;

   // GETTERS E SETTERS

   public Long getId()
   {
      return id;
   }

   public String getNomeEmpreendimento()
   {
      return nomeEmpreendimento;
   }

   public void setNomeEmpreendimento(String nomeEmpreendimento)
   {
      this.nomeEmpreendimento = nomeEmpreendimento;
   }

   // public Construtora getConstrutora()
   // {
   // if (construtora == null)
   // {
   // construtora = new Construtora();
   // }
   // return construtora;
   // }
   //
   // public void setConstrutora(Construtora construtora)
   // {
   // this.construtora = construtora;
   // }

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
      Empreendimento other = (Empreendimento) obj;
      if (id == null)
      {
         if (other.id != null)
            return false;
      }
      else if (!id.equals(other.id))
         return false;
      return true;
   }

   public String getRegiao()
   {
      return regiao;
   }

   public void setRegiao(String regiao)
   {
      this.regiao = regiao;
   }

   public int getContImovelEmpreendimento()
   {
      return contImovelEmpreendimento;
   }

   public void setContImovelEmpreendimento(int contImovelEmpreendimento)
   {
      this.contImovelEmpreendimento = contImovelEmpreendimento;
   }

}