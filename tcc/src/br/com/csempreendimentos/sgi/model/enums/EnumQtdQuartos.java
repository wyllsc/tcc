package br.com.csempreendimentos.sgi.model.enums;

public enum EnumQtdQuartos
{
   UM(1, "1 Quarto"), 
   DOIS(2, "2 Quartos"),
   TRES(3, "3 Quartos"),
   QUATRO(4, "4 Quartos"),
   CINCO(5, "5 Quartos"),
   SEIS(6, "6 Quartos"),
   SETE(7, "7 Quartos"),
   OITO(8, "8 Quartos");

   private EnumQtdQuartos(int codigo, String descricao)
   {
      this.codigo = codigo;
      this.descricao = descricao;
   }

   private int codigo;
   private String descricao;

   public String getDescricao()
   {
      return descricao;
   }

   public void setDescricao(String descricao)
   {
      this.descricao = descricao;
   }

   public int getCodigo()
   {
      return codigo;
   }

   public void setCodigo(int codigo)
   {
      this.codigo = codigo;
   }
     
}
