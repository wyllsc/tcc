package br.com.csempreendimentos.sgi.model.enums;

public enum EnumSimNao
{
   SIM("SIM", "Sim"), 
   NAO("NAO", "Não");

   private EnumSimNao(String codigo, String descricao)
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

   public EnumSimNao valueOf(Object codigo)
   {
      for (EnumSimNao m : EnumSimNao.values())
      {
         if (m.getCodigo().equals(codigo))
         {
            return m;
         }
      }
      return null;
   }
   
   public static EnumSimNao recuperaEnum(Object codigo)
   {
      for (EnumSimNao s : EnumSimNao.values())
      {
         if (s.getCodigo().equals(codigo))
         {
            return s;
         }
      }
      return null;
   }

}
