package br.com.csempreendimentos.sgi;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import sun.org.mozilla.javascript.internal.regexp.SubString;

public class TestesVariados
{
   
   
   public static void main(String[] args) throws ParseException
   {

      //RETIRAR PONTOS E BARRAS
      
//      String s = new String();
//      String teste = new String(); 
//      s = "...123.123.123..---..";
//      teste = s.replaceAll("[.]", "").replaceAll("-", "");
//      
//      //PERSISTIR NO BANCO DE DADOS UMA DATA
//      String data = "13/10/1983";      
//      SimpleDateFormat formatada = new SimpleDateFormat("dd/MM/yyyy");  
//      Date dataFormatada = new Date(formatada.parse(data).getTime());  
//      
//      
//      System.out.println(teste);
//      System.out.println(dataFormatada);
      
//      List<String> lista1 = new ArrayList<String>();
//      List<String> lista2 = new ArrayList<String>();
//      
//      lista1.add("carro1");
//      lista1.add("carro2");
//      lista1.add("carro3");
//      lista1.add("carro4");
//      lista1.add("carro5");
//      
//      for (String s : lista1)
//      {
//         String t;
//         t = s;
//         
//         lista2.add(t);
//      }
//      
//      lista2.size();
//  
//      String valor = "150.000,00";
//      String resultado = valor;
//      
//      resultado = valor.replaceAll("[.]", "");
//      
//      System.out.println(resultado);
      
      
      
//      String alfanumerico = "asdnfh";
//      String numerico = "123456";
//      String testmax = "adsfadsfasfasf123456";
//      
//      if(StringUtils.isNumeric(numerico))
//      {
//         System.out.println("funciona");
//      }
//
//      if(StringUtils.isNumeric(alfanumerico))
//      {
//         System.out.println("vish");
//      }
//      
//      testmax.replaceAll("[a-z]", "");
//      System.out.println(testmax);
//      
//      
      
//      NumberFormat nf = new DecimalFormat ("#,##0.00", new DecimalFormatSymbols (new Locale ("pt", "BR")));  
//      double valor = 102000.4;  
//      System.out.println (nf.format (valor)); 
      
      String s1, s2, s3, s4;
      
      s1 = "08-01-1991";
      s4 = s1.substring(6, 10);
      s3 = s1.substring(3, 5);
      s2 = s1.substring(0, 2);


      
      
      System.out.println(s2);
      System.out.println(s3);
      System.out.println(s4);
      
      
   }
   
}
