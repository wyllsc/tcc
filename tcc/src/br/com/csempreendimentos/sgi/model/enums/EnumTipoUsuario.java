package br.com.csempreendimentos.sgi.model.enums;

public enum EnumTipoUsuario
{
   ADM_GERAL("1", "ADM GERAL", "Administrador Geral", (long)1), 
   GERENTE("2", "GERENTE", "Gerente", (long)2), 
   AUXILIAR_ADM("3", "AUX ADM", "Auxiliar Administrativo", (long)3), 
   CORRETOR("4", "CORRETOR", "Corretor", (long)4);

   private EnumTipoUsuario(String codigo, String descricaoBanco, String descricao, Long codBanco)
   {
      this.codigo = codigo;
      this.descricaoBanco = descricaoBanco;
      this.descricao = descricao;
      this.codBanco = codBanco;
   }

   private Long codBanco;
   private String codigo;
   private String descricaoBanco;
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

   public EnumTipoUsuario valueOf(Object codigo)
   {
      for (EnumTipoUsuario tipoUsuario : EnumTipoUsuario.values())
      {
         if (tipoUsuario.getCodigo().equals(codigo))
         {
            return tipoUsuario;
         }
      }
      return null;
   }
   
   public static EnumTipoUsuario recuperaEnum(Object codigo)
   {
      for (EnumTipoUsuario s : EnumTipoUsuario.values())
      {
         if (s.getCodigo().equals(codigo))
         {
            return s;
         }
      }
      return null;
   }

   

   public String getDescricaoBanco()
   {
      return descricaoBanco;
   }

   public void setDescricaoBanco(String descricaoBanco)
   {
      this.descricaoBanco = descricaoBanco;
   }

   public Long getCodBanco()
   {
      return codBanco;
   }

   public void setCodBanco(Long codBanco)
   {
      this.codBanco = codBanco;
   }
}
