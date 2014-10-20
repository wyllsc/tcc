package br.com.csempreendimentos.sgi.model.enums;

public enum EnumStatusVenda
{
   ANALISE("A", "Em Análise"), 
   APROVADO("S", "Aprovado"), 
   RECUSADO("N", "Recusado");

   private EnumStatusVenda(String codigo, String descricao)
   {
      this.codigo = codigo;
      this.descricao = descricao;
   }

   private String codigo;
   private String descricao;

   public String getCodigo()
   {
      return codigo;
   }

   public void setCodigo(String codigo)
   {
      this.codigo = codigo;
   }

   public String getDescricao()
   {
      return descricao;
   }

   public void setDescricao(String descricao)
   {
      this.descricao = descricao;
   }

   public EnumStatusVenda valueOf(Object codigo)
   {
      for (EnumStatusVenda m : EnumStatusVenda.values())
      {
         if (m.getCodigo().equals(codigo))
         {
            return m;
         }
      }
      return null;
   }
   
   public static EnumStatusVenda recuperaEnum(Object codigo)
   {
      for (EnumStatusVenda s : EnumStatusVenda.values())
      {
         if (s.getCodigo().equals(codigo))
         {
            return s;
         }
      }
      return null;
   }


   
   
}
