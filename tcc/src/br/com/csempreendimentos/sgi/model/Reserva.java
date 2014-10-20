package br.com.csempreendimentos.sgi.model;

import java.io.Serializable;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.commons.lang3.StringUtils;
import br.com.csempreendimentos.sgi.model.enums.EnumStatusVenda;

@Entity
@Table(name = "TB_RESERVA")
public class Reserva implements Serializable
{
   private static final long serialVersionUID = 584031168533602335L;

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "ID_RESERVA", length = 4)
   private Long id;

   @ManyToOne
   @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_CLIENTE")
   private Cliente cliente;

   @ManyToOne
   @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
   private Usuario usuario;

   @ManyToOne
   @JoinColumn(name = "ID_IMOVEL", referencedColumnName = "ID_IMOVEL")
   private Imovel imovel;

   @Column(name = "DS_COMENTÁRIO", length = 200)
   private String comentario;

   @Column(name = "DT_RESERVA")
   private Date dtReserva;

   @Column(name = "NU_VALOR_IMOVEL")
   private double valorImovel;

   @Column(name = "NU_PERCENTUAL")
   private double valorPercentual;

   @Column(name = "ST_RESERVA", length = 1)
   private String stReserva;

   @Transient
   private Empreendimento empreendimento;

   @Transient
   private Construtora construtora;
   
   @Transient
   private double valorSinal;

   @Transient
   private String percentualSinal;

   // GETTERS E SETTERS

   public Long getId()
   {
      return id;
   }

   public Cliente getCliente()
   {
      return cliente;
   }

   public void setCliente(Cliente cliente)
   {
      this.cliente = cliente;
   }

   public Usuario getUsuario()
   {
      return usuario;
   }

   public void setUsuario(Usuario usuario)
   {
      this.usuario = usuario;
   }

   public Imovel getImovel()
   {
      return imovel;
   }

   public void setImovel(Imovel imovel)
   {
      this.imovel = imovel;
   }

   public String getComentario()
   {
      return comentario;
   }

   public void setComentario(String comentario)
   {
      this.comentario = comentario;
   }

   public Date getDtReserva()
   {
      return dtReserva;
   }

   public void setDtReserva(Date dtReserva)
   {
      this.dtReserva = dtReserva;
   }

   public String getStReserva()
   {
      return StringUtils.isNotBlank(stReserva) ? EnumStatusVenda.recuperaEnum(stReserva).getDescricao() : "";
   }

   public void setStReserva(String stReserva)
   {
      this.stReserva = stReserva;
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
      Reserva other = (Reserva) obj;
      if (id == null)
      {
         if (other.id != null)
            return false;
      }
      else if (!id.equals(other.id))
         return false;
      return true;
   }

   public Empreendimento getEmpreendimento()
   {
      if (empreendimento == null)
      {
         empreendimento = new Empreendimento();
      }
      return empreendimento;
   }

   public void setEmpreendimento(Empreendimento empreendimento)
   {
      this.empreendimento = empreendimento;
   }

   public Construtora getConstrutora()
   {
      if (construtora == null)
      {
         construtora = new Construtora();
      }
      return construtora;
   }

   public void setConstrutora(Construtora construtora)
   {
      this.construtora = construtora;
   }

   public double getValorImovel()
   {
      return valorImovel;
   }

   public void setValorImovel(double valorImovel)
   {
      this.valorImovel = valorImovel;
   }

   public double getValorPercentual()
   {
      return valorPercentual;
   }

   public void setValorPercentual(double valorPercentual)
   {
      this.valorPercentual = valorPercentual;
   }

   @SuppressWarnings("static-access")
   public String getPercentualSinal()
   {
      NumberFormat nf = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));

      String st = null;
      valorSinal = valorImovel * (valorPercentual / 100);
      st = st.valueOf(valorSinal);

      st = (nf.format(new Double(st)));
      percentualSinal = "" + valorPercentual + " % = R$ " + st + "";
      return percentualSinal;
   }

   public double getValorSinal()
   {
      return valorSinal;
   }

   public void setValorSinal(double valorSinal)
   {
      this.valorSinal = valorSinal;
   }

   public void setPercentualSinal(String percentualSinal)
   {
      this.percentualSinal = percentualSinal;
   }
}