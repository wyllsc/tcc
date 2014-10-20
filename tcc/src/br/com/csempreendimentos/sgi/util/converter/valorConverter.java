package br.com.csempreendimentos.sgi.util.converter;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import org.apache.commons.lang3.StringUtils;

@FacesConverter("valorConverter")
public class valorConverter implements Converter
{
   public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException
   {
      String valor = value;
    
      if (StringUtils.isNotBlank(value))
      {
         valor = value.replaceAll("[.]", "").replaceAll(",", ".");
      }
      return valor;
   }

   public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException
   {
      String valor;
      
      valor = value.toString();
//      valor = valor.replaceAll("[a-z]", "").replaceAll(":", "").replaceAll("[A-Z]", "").replaceAll("[.]", "");
      
      
      
//      if (StringUtils.isNotBlank(valor) && valor.length() == 11)
//      {
//         try
//         {
//            MaskFormatter mf = new MaskFormatter("###.###.###,##");
//            mf.setValueContainsLiteralCharacters(false);
//            valor = mf.valueToString(valor);
//         }
//         catch (ParseException e)
//         {
//            e.printStackTrace();
//         }
//      }
//      else if (StringUtils.isNotBlank(valor) && valor.length() == 10)
//      {
//         try
//         {
//            MaskFormatter mf = new MaskFormatter("##.###.###,##");
//            mf.setValueContainsLiteralCharacters(false);
//            valor = mf.valueToString(valor);
//         }
//         catch (ParseException e)
//         {
//            e.printStackTrace();
//         }
//      }
//      else if (StringUtils.isNotBlank(valor) && valor.length() == 9)
//      {
//         try
//         {
//            MaskFormatter mf = new MaskFormatter("#.###.###,##");
//            mf.setValueContainsLiteralCharacters(false);
//            valor = mf.valueToString(valor);
//         }
//         catch (ParseException e)
//         {
//            e.printStackTrace();
//         }
//      }
//      else if(StringUtils.isNotBlank(valor) && valor.length() == 8)
//      {
//         try
//         {
//            MaskFormatter mf = new MaskFormatter("###.###,##");
//            mf.setValueContainsLiteralCharacters(false);
//            valor = mf.valueToString(valor);
//         }
//         catch (ParseException e)
//         {
//            e.printStackTrace();
//         }
//      }
//      else if(StringUtils.isNotBlank(valor) && valor.length() == 7)
//      {
//         try
//         {
//            MaskFormatter mf = new MaskFormatter("##.###,##");
//            mf.setValueContainsLiteralCharacters(false);
//            valor = mf.valueToString(valor);
//         }
//         catch (ParseException e)
//         {
//            e.printStackTrace();
//         }
//      }
//      else if(StringUtils.isNotBlank(valor) && valor.length() == 6)
//      {
//         try
//         {
//            MaskFormatter mf = new MaskFormatter("#.###,##");
//            mf.setValueContainsLiteralCharacters(false);
//            valor = mf.valueToString(valor);
//         }
//         catch (ParseException e)
//         {
//            e.printStackTrace();
//         }
//      }
//      else if(StringUtils.isNotBlank(valor) && valor.length() == 5)
//      {
//         try
//         {
//            MaskFormatter mf = new MaskFormatter("###,##");
//            mf.setValueContainsLiteralCharacters(false);
//            valor = mf.valueToString(valor);
//         }
//         catch (ParseException e)
//         {
//            e.printStackTrace();
//         }
//      }
//      else if(StringUtils.isNotBlank(valor) && valor.length() == 4)
//      {
//         try
//         {
//            MaskFormatter mf = new MaskFormatter("##,##");
//            mf.setValueContainsLiteralCharacters(false);
//            valor = mf.valueToString(valor);
//         }
//         catch (ParseException e)
//         {
//            e.printStackTrace();
//         }
//      }
//      else if(StringUtils.isNotBlank(valor) && valor.length() == 3)
//      {
//         try
//         {
//            MaskFormatter mf = new MaskFormatter("#,##");
//            mf.setValueContainsLiteralCharacters(false);
//            valor = mf.valueToString(valor);
//         }
//         catch (ParseException e)
//         {
//            e.printStackTrace();
//         }
//      }
//      else if(StringUtils.isNotBlank(valor) && valor.length() == 2)
//      {
//         try
//         {
//            MaskFormatter mf = new MaskFormatter("##");
//            mf.setValueContainsLiteralCharacters(false);
//            valor = mf.valueToString(valor);
//         }
//         catch (ParseException e)
//         {
//            e.printStackTrace();
//         }
//      }
//      else
//      {
//        valor = ""; 
//      }
//      double valor = 102000.4;  
      
      NumberFormat nf = new DecimalFormat ("#,##0.00", new DecimalFormatSymbols (new Locale ("pt", "BR")));  
      
      valor = (nf.format (new Double(valor))); 
      
      return valor;
   }
}
