package br.com.csempreendimentos.sgi.model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import br.com.csempreendimentos.sgi.model.enums.EnumPosicao;

@Entity
@Table(name = "TB_IMOVEL")
public class Imovel
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "ID_IMOVEL", length = 4)
   private Long id;

   @Column(name = "DS_LOCALIZACAO", length = 50)
   private String localizacaoImovel;

   @Column(name = "DS_POSICIONAMENTO", length = 15)
   private String posicionamento;

   @Column(name = "NU_METRO_QUADRADO_TOTAL", length = 8)
   private double areaTotal;

   @Column(name = "NU_METRO_QUADRADO_COMUM", length = 8)
   private double areaComum;

   @Column(name = "NU_PERCENTUAL_EMPRESA", length = 8)
   private double percentualEmpresa;

   @Column(name = "NU_VALOR_IMOVEL", length = 20)
   private double valorImovel;

   @Column(name = "NU_QUARTOS", length = 2)
   private int qtdQuartos;

   // @ManyToOne
   // @JoinColumn(name = "ID_EMPREENDIMENTO", referencedColumnName = "ID_EMPREENDIMENTO")
   // private Empreendimento empreendimento;

   @Transient
   private double valorSinal;

   @Transient
   private String percentualSinal;

   @Transient
   private String posicaoDoImovel;

   // GETTERS E SETTERS

   public Long getId()
   {
      return id;
   }

   public String getLocalizacaoImovel()
   {
      return localizacaoImovel;
   }

   public void setLocalizacaoImovel(String localizacaoImovel)
   {
      this.localizacaoImovel = localizacaoImovel;
   }

   public String getPosicaoDoImovel()
   {
      return EnumPosicao.recuperaPosicao(posicionamento);
   }

   public void setPosicaoDoImovel(String posicaoDoImovel)
   {
      this.posicaoDoImovel = posicaoDoImovel;
   }

   public String getPosicionamento()
   {
      return posicionamento;
   }

   public void setPosicionamento(String posicionamento)
   {
      this.posicionamento = posicionamento;
   }

   public double getPercentualEmpresa()
   {
      return percentualEmpresa;
   }

   public void setPercentualEmpresa(double percentualEmpresa)
   {
      this.percentualEmpresa = percentualEmpresa;
   }

   public double getValorImovel()
   {
      return valorImovel;
   }

   public void setValorImovel(double valorImovel)
   {
      this.valorImovel = valorImovel;
   }

   // public Empreendimento getEmpreendimento()
   // {
   // if (empreendimento == null)
   // {
   // empreendimento = new Empreendimento();
   // }
   // return empreendimento;
   // }
   //
   // public void setEmpreendimento(Empreendimento empreendimento)
   // {
   // this.empreendimento = empreendimento;
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
      Imovel other = (Imovel) obj;
      if (id == null)
      {
         if (other.id != null)
            return false;
      }
      else if (!id.equals(other.id))
         return false;
      return true;
   }

   public double getValorSinal()
   {
      return valorSinal;
   }

   public void setValorSinal(double valorSinal)
   {
      this.valorSinal = valorSinal;
   }

   @SuppressWarnings("static-access")
   public String getPercentualSinal()
   {
      NumberFormat nf = new DecimalFormat ("#,##0.00", new DecimalFormatSymbols (new Locale ("pt", "BR")));  
      
      String st = null;
      valorSinal = valorImovel * (percentualEmpresa / 100);
      st= st.valueOf(valorSinal);
      
      st = (nf.format (new Double(st))); 
      percentualSinal = "" + percentualEmpresa + " % = R$ " + st + "";
      return percentualSinal;
   }

   public void setPercentualSinal(String percentualSinal)
   {
      this.percentualSinal = percentualSinal;
   }

   public double getAreaTotal()
   {
      return areaTotal;
   }

   public void setAreaTotal(double areaTotal)
   {
      this.areaTotal = areaTotal;
   }

   public double getAreaComum()
   {
      return areaComum;
   }

   public void setAreaComum(double areaComum)
   {
      this.areaComum = areaComum;
   }

   public int getQtdQuartos()
   {
      return qtdQuartos;
   }

   public void setQtdQuartos(int qtdQuartos)
   {
      this.qtdQuartos = qtdQuartos;
   }

   public double getFinanciamento()
   {
      double financiamento = 0;
      financiamento = valorImovel - valorSinal;
      return financiamento;
   }
}