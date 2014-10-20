package br.com.csempreendimentos.sgi.model.enums;

public enum MenuAdmGeral
{
   GERENTE("1", "Gerente"), 
   AUX_ADM("2", "Auxiliar Administrativo");

   private String codigo;
   private String descricao;

   private MenuAdmGeral(String codigo, String descricao)
   {
      this.codigo = codigo;
      this.descricao = descricao;
   }
   
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

}
