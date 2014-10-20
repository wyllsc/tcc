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
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import br.com.csempreendimentos.sgi.model.enums.EnumSexo;

@Entity
@Table(name = "TB_CLIENTE")
public class Cliente implements Serializable
{
   private static final long serialVersionUID = -5282994593125323181L;

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "ID_CLIENTE", length = 4)
   private Long id;

   @Column(name = "DS_NOME", length = 100)
   private String nome;

   @Column(name = "DT_NASCIMENTO")
   private Date dataNascimento;

   @Column(name = "ST_ESTADO_CIVIL", length = 20)
   private String statusCivil;

   @Column(name = "DS_SEXO", length = 1)
   private String sexo;

   @Column(name = "DS_CPF", length = 12)
   private String cpf;

   @Column(name = "DS_EMAIL", length = 100)
   private String email;

   @Column(name = "NU_RENDA", length = 8)
   private double renda;

   @Column(name = "NU_RG", length = 20)
   private int rg;

   @Column(name = "DS_NOME_CONJ", length = 100)
   private String nomeConjuge;

   @Column(name = "DT_NASCIMENTO_CONJ")
   private Date dataNascimentoConjuge;

   @Column(name = "DS_SEXO_CONJ", length = 1)
   private String sexoConjuge;

   @Column(name = "DS_CPF_CONJ", length = 20)
   private String cpfConjuge;

   @Column(name = "DS_EMAIL_CONJ", length = 100)
   private String emailConjuge;

   @Column(name = "NU_RENDA_CONJ", length = 8)
   private double rendaConjuge;

   @Column(name = "NU_RG_CONJ", length = 20)
   private int rgConjuge;

   @Column(name = "DS_OBSERVACAO", length = 400)
   private String observacao;

   @Column(name = "ST_ESTADO_CIVIL_CONJ", length = 20)
   private String statusCivilConjuge;

   @Column(name = "ST_FGTS", length = 20)
   private String fgts;

   @Column(name = "ST_FGTS_CONJ", length = 20)
   private String fgtsConj;

   @Column(name = "ST_RESERVA")
   private boolean statusReserva;

   @ManyToOne
   @Cascade(value = CascadeType.ALL)
   @JoinColumn(name = "ID_TELEFONE", referencedColumnName = "ID_TELEFONE")
   private Telefone telefone;

   @ManyToOne
   @Cascade(value = CascadeType.ALL)
   @JoinColumn(name = "ID_TELEFONE_CONJ", referencedColumnName = "ID_TELEFONE")
   private Telefone telefoneConj;

   @ManyToOne
   @Cascade(value = CascadeType.ALL)
   @JoinColumn(name = "ID_ENDERECO", referencedColumnName = "ID_ENDERECO")
   private Endereco endereco;

   @ManyToOne
   @Cascade(value = CascadeType.ALL)
   @JoinColumn(name = "ID_ENDERECO_CONJ", referencedColumnName = "ID_ENDERECO")
   private Endereco enderecoConj;

   @ManyToOne
   @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
   private Usuario usuario;
   
   

   // GETTERS E SETTERS

   public Long getId()
   {
      return id;
   }

   public String getNome()
   {
      return nome;
   }

   public void setNome(String nome)
   {
      this.nome = nome;
   }

   public Date getDataNascimento()
   {
      return dataNascimento;
   }

   public void setDataNascimento(Date dataNascimento)
   {
      this.dataNascimento = dataNascimento;
   }

   public String getStatusCivil()
   {
      return statusCivil;
   }

   public void setStatusCivil(String statusCivil)
   {
      this.statusCivil = statusCivil;
   }

   public String getSexo()
   {
      return sexo;
   }

   public void setSexo(String sexo)
   {
      this.sexo = sexo;
   }

   public String getEmail()
   {
      return email;
   }

   public void setEmail(String email)
   {
      this.email = email;
   }

   public double getRenda()
   {
      return renda;
   }

   public void setRenda(double renda)
   {
      this.renda = renda;
   }

   public int getRg()
   {
      return rg;
   }

   public void setRg(int rg)
   {
      this.rg = rg;
   }

   public String getNomeConjuge()
   {
      return nomeConjuge;
   }

   public void setNomeConjuge(String nomeConjuge)
   {
      this.nomeConjuge = nomeConjuge;
   }

   public Date getDataNascimentoConjuge()
   {
      return dataNascimentoConjuge;
   }

   public void setDataNascimentoConjuge(Date dataNascimentoConjuge)
   {
      this.dataNascimentoConjuge = dataNascimentoConjuge;
   }

   public String getSexoConjuge()
   {
      return sexoConjuge;
   }

   public void setSexoConjuge(String sexoConjuge)
   {
      this.sexoConjuge = sexoConjuge;
   }

   public String getEmailConjuge()
   {
      return emailConjuge;
   }

   public void setEmailConjuge(String emailConjuge)
   {
      this.emailConjuge = emailConjuge;
   }

   public double getRendaConjuge()
   {
      return rendaConjuge;
   }

   public void setRendaConjuge(double rendaConjuge)
   {
      this.rendaConjuge = rendaConjuge;
   }

   public int getRgConjuge()
   {
      return rgConjuge;
   }

   public void setRgConjuge(int rgConjuge)
   {
      this.rgConjuge = rgConjuge;
   }

   public String getObservacao()
   {
      return observacao;
   }

   public void setObservacao(String observacao)
   {
      this.observacao = observacao;
   }

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
      Cliente other = (Cliente) obj;
      if (id == null)
      {
         if (other.id != null)
            return false;
      }
      else if (!id.equals(other.id))
         return false;
      return true;
   }

   public Usuario getUsuario()
   {
      if (usuario == null)
      {
         usuario = new Usuario();
      }
      return usuario;
   }

   public void setUsuario(Usuario usuario)
   {
      this.usuario = usuario;
   }

   public String getStatusCivilConjuge()
   {
      return statusCivilConjuge;
   }

   public void setStatusCivilConjuge(String statusCivilConjuge)
   {
      this.statusCivilConjuge = statusCivilConjuge;
   }

   public String getFgts()
   {
      return fgts;
   }

   public void setFgts(String fgts)
   {
      this.fgts = fgts;
   }

   public String getFgtsConj()
   {
      return fgtsConj;
   }

   public void setFgtsConj(String fgtsConj)
   {
      this.fgtsConj = fgtsConj;
   }

   public String getCpf()
   {
      return cpf;
   }

   public void setCpf(String cpf)
   {
      this.cpf = cpf;
   }

   public String getCpfConjuge()
   {
      return cpfConjuge;
   }

   public void setCpfConjuge(String cpfConjuge)
   {
      this.cpfConjuge = cpfConjuge;
   }

   public Telefone getTelefoneConj()
   {
      if (telefoneConj == null)
      {
         telefoneConj = new Telefone();
      }
      return telefoneConj;
   }

   public void setTelefoneConj(Telefone telefoneConj)
   {
      this.telefoneConj = telefoneConj;
   }

   public Endereco getEnderecoConj()
   {
      if (enderecoConj == null)
      {
         enderecoConj = new Endereco();
      }
      return enderecoConj;
   }

   public void setEnderecoConj(Endereco enderecoConj)
   {
      this.enderecoConj = enderecoConj;
   }

   public boolean isStatusReserva()
   {
      return statusReserva;
   }

   public void setStatusReserva(boolean statusReserva)
   {
      this.statusReserva = statusReserva;
   }
   
   public String getDescricaoStatus()
   {
      return StringUtils.isNotBlank(sexo) ? EnumSexo.recuperaEnum(sexo).getDescricao() : "";
   }
   
   public double getRendaBruta()
   {
      double rb = 0;
      if(rendaConjuge != 0)
      {
         rb = rendaConjuge + renda;
      }else {
         rb = renda;
      }
      
      return rb;
   }
}