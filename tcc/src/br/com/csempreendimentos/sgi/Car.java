package br.com.csempreendimentos.sgi;

public class Car
{

   private String id;
   private int ano;
   private String fabricante;
   private String cor;

   public Car(String id, int ano, String fabricante, String cor)
   {
      this.id = id;
      this.ano = ano;
      this.fabricante = fabricante;
      this.cor = cor;
   }

   public String getId()
   {
      return id;
   }

   public void setId(String id)
   {
      this.id = id;
   }

   public int getAno()
   {
      return ano;
   }

   public void setAno(int ano)
   {
      this.ano = ano;
   }

   public String getFabricante()
   {
      return fabricante;
   }

   public void setFabricante(String fabricante)
   {
      this.fabricante = fabricante;
   }

   public String getCor()
   {
      return cor;
   }

   public void setCor(String cor)
   {
      this.cor = cor;
   }
}