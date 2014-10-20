package br.com.csempreendimentos.sgi.model.enums;

public enum EnumPosicao
{
   NASCENTE("1", "Nascente"), 
   POENTE("2", "Poente"),
   PERPENDICULAR("3", "Perpendicular");

   private EnumPosicao(String codigo, String descricao)
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
   
   public static String recuperaPosicao(Object codigo) 
   {
      String retorno = null;
      
      for (EnumPosicao posicao : EnumPosicao.values()) 
      {
         if (posicao.getCodigo().equals(codigo)) 
         {
            retorno = posicao.getDescricao();
            return retorno;
         }
      }
      return retorno;
   }
}
