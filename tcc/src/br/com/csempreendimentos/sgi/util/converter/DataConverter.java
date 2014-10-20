package br.com.csempreendimentos.sgi.util.converter;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.swing.text.MaskFormatter;
import org.apache.commons.lang3.StringUtils;

@FacesConverter("dataConverter")
public class DataConverter implements Converter
{
   public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException
   {
      // if (StringUtils.isNotBlank(value))
      // {
      // valor = value.replaceAll("/", "");
      // }
      
      String valor = value;
      Date data = null;
      
      DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");  
      try
      {
         data = new Date(formato.parse(valor).getTime());
      }
      catch (ParseException e1)
      {
         e1.printStackTrace();
      }  

      return data;
   }

   public String getAsString(FacesContext context, UIComponent component, Object value)
      throws ConverterException
   {
      String valor, ano, mes, dia;
      
      valor = value.toString();
      ano = valor.substring(0, 4);
      mes = valor.substring(5, 7);
      dia = valor.substring(8, 10);
      valor = dia+mes+ano;
      
      if (StringUtils.isNotBlank(valor) && valor.length() == 8)
      {
         try
         {
            MaskFormatter mf = new MaskFormatter("##/##/####");
            mf.setValueContainsLiteralCharacters(false);
            valor = mf.valueToString(valor);
         }
         catch (ParseException e)
         {
            e.printStackTrace();
         }
      }
      return valor;
   }
}