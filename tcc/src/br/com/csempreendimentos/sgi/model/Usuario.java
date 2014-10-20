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
@Table(name = "TB_USUARIO")
public class Usuario implements Serializable
{
   private static final long serialVersionUID = -5282994593125323181L;

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "ID_USUARIO", length=4)
   private Long id;

   @Column(name = "DS_LOGIN", length=40)
   private String login;

   @Column(name = "DS_CPF", length=12)
   private String cpf;

   @Column(name = "DS_NOME", length=100)
   private String nome;

   @Column(name = "DT_NASCIMENTO")
   private Date dataNascimento;

   @Column(name = "DS_EMAIL", length=60)
   private String email;

   @Column(name = "DS_SEXO", length=1)
   private String sexo;

   @Column(name = "DS_CRECI", length=20)
   private String creci;

   @Column(name = "DS_SENHA", length=30)
   private String senha;

   @Column(name = "DS_IMG_PERFIL", length=20)
   private String img_perfil;

   @ManyToOne
   @JoinColumn(name = "ID_TIPO", referencedColumnName = "ID_TIPO")
   private TipoUsuario tipoUsuario;

   @ManyToOne
   @Cascade(value=CascadeType.ALL)
   @JoinColumn(name = "ID_TELEFONE", referencedColumnName = "ID_TELEFONE")
   private Telefone telefone;

   @ManyToOne
   @Cascade(value=CascadeType.ALL)
   @JoinColumn(name = "ID_ENDERECO", referencedColumnName = "ID_ENDERECO")
   private Endereco endereco;

   // GETTERS E SETTERS

   public Long getId()
   {
      return id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

   public String getNome()
   {
      return nome;
   }

   public void setNome(String nome)
   {
      this.nome = nome;
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
      Usuario other = (Usuario) obj;
      if (id == null)
      {
         if (other.id != null)
            return false;
      }
      else if (!id.equals(other.id))
         return false;
      return true;
   }

   public String getLogin()
   {
      return login;
   }

   public void setLogin(String login)
   {
      this.login = login;
   }

   public String getCpf()
   {
      return cpf;
   }

   public void setCpf(String cpf)
   {
      this.cpf = cpf;
   }

   public Date getDataNascimento()
   {
      return dataNascimento;
   }

   public void setDataNascimento(Date dataNascimento)
   {
      this.dataNascimento = dataNascimento;
   }

   public String getEmail()
   {
      return email;
   }

   public void setEmail(String email)
   {
      this.email = email;
   }

   public String getSexo()
   {
      return sexo;
   }

   public void setSexo(String sexo)
   {
      this.sexo = sexo;
   }

   public String getCreci()
   {
      return creci;
   }

   public void setCreci(String creci)
   {
      this.creci = creci;
   }

   public TipoUsuario getTipoUsuario()
   {
      if (tipoUsuario == null)
      {
         tipoUsuario = new TipoUsuario();
      }
      return tipoUsuario;
   }

   public void setTipoUsuario(TipoUsuario tipoUsuario)
   {
      this.tipoUsuario = tipoUsuario;
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

   public String getImg_perfil()
   {
      if (img_perfil == null)
      {
         return "padrao.png";
      }
      return img_perfil;
   }

   public void setImg_perfil(String img_perfil)
   {
      this.img_perfil = img_perfil;
   }

   public String getDescricaoStatus()
   {
      return StringUtils.isNotBlank(sexo) ? EnumSexo.recuperaEnum(sexo).getDescricao() : "";
   }

   public String getSenha()
   {
      return senha;
   }

   public void setSenha(String senha)
   {
      this.senha = senha;
   }
}