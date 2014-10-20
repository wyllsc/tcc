package br.com.csempreendimentos.sgi.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "TB_CONSTRUTORA")
public class Construtora implements Serializable
{
   private static final long serialVersionUID = -5282994593125323181L;

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "ID_CONSTRUTORA", length = 4)
   private Long id;

   @Column(name = "DS_NOME_CONSTRUTORA", length = 60)
   private String nomeConstrutora;

   @Column(name = "DS_NOME_GERENTE", length = 60)
   private String nomeGerente;

   @Column(name = "DS_EMAIL", length = 80)
   private String emailGerente;

   @ManyToOne
   @Cascade(value = CascadeType.ALL)
   @JoinColumn(name = "ID_TELEFONE", referencedColumnName = "ID_TELEFONE")
   private Telefone telefone;

   @ManyToOne
   @Cascade(value = CascadeType.ALL)
   @JoinColumn(name = "ID_ENDERECO", referencedColumnName = "ID_ENDERECO")
   private Endereco endereco;

   @Transient
   private List<Empreendimento> listEmpreendimentos;

   // GETTERS E SETTERS
   public Telefone getTelefone()
   {
      if (telefone == null)
      {
         telefone = new Telefone();
      }
      return telefone;
   }

   public void setTelefone(Telefone telefone)
   {
      this.telefone = telefone;
   }

   public Endereco getEndereco()
   {
      if (endereco == null)
      {
         endereco = new Endereco();
      }
      return endereco;
   }

   public void setEndereco(Endereco endereco)
   {
      this.endereco = endereco;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

   public String getNomeConstrutora()
   {
      return nomeConstrutora;
   }

   public void setNomeConstrutora(String nomeConstrutora)
   {
      this.nomeConstrutora = nomeConstrutora;
   }

   public String getNomeGerente()
   {
      return nomeGerente;
   }

   public void setNomeGerente(String nomeGerente)
   {
      this.nomeGerente = nomeGerente;
   }

   public String getEmailGerente()
   {
      return emailGerente;
   }

   public void setEmailGerente(String emailGerente)
   {
      this.emailGerente = emailGerente;
   }

   public Long getId()
   {
      return id;
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
      Construtora other = (Construtora) obj;
      if (id == null)
      {
         if (other.id != null)
            return false;
      }
      else if (!id.equals(other.id))
         return false;
      return true;
   }

   public List<Empreendimento> getListEmpreendimentos()
   {
      return listEmpreendimentos;
   }

   public void setListEmpreendimentos(List<Empreendimento> listEmpreendimentos)
   {
      this.listEmpreendimentos = listEmpreendimentos;
   }
}