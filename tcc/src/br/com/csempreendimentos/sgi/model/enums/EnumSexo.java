package br.com.csempreendimentos.sgi.model.enums;

public enum EnumSexo
{
   MASCULINO("M", "Masculino"), FEMININO("F", "Feminino");

   private EnumSexo(String codigo, String descricao)
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

   public EnumSexo valueOf(Object codigo)
   {
      for (EnumSexo sexo : EnumSexo.values())
      {
         if (sexo.getCodigo().equals(codigo))
         {
            return sexo;
         }
      }
      return null;
   }

   public static EnumSexo recuperaEnum(Object codigo)
   {
      for (EnumSexo s : EnumSexo.values())
      {
         if (s.getCodigo().equals(codigo))
         {
            return s;
         }
      }
      return null;
   }

}
