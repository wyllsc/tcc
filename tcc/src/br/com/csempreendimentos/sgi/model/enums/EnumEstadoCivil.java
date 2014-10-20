package br.com.csempreendimentos.sgi.model.enums;

public enum EnumEstadoCivil
{
   SOLTEIRO("S", "Solteiro(a)"), 
   CASADO("C", "Casado(a)"), 
   DIVORCIADO("D", "Divorciado(a)"), 
   VIUVO("V", "Viuvo(a)");

   private EnumEstadoCivil(String codigo, String descricao)
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

   public EnumEstadoCivil valueOf(Object codigo)
   {
      for (EnumEstadoCivil e : EnumEstadoCivil.values())
      {
         if (e.getCodigo().equals(codigo))
         {
            return e;
         }
      }
      return null;
   }
   
   public static EnumEstadoCivil recuperaEnum(Object codigo)
   {
      for (EnumEstadoCivil s : EnumEstadoCivil.values())
      {
         if (s.getCodigo().equals(codigo))
         {
            return s;
         }
      }
      return null;
   }

}
