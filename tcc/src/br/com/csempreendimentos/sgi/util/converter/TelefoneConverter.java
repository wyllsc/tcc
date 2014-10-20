package br.com.csempreendimentos.sgi.util.converter;

import java.text.ParseException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.swing.text.MaskFormatter;
import org.apache.commons.lang3.StringUtils;

@FacesConverter("telefoneConverter")
public class TelefoneConverter implements Converter
{
   public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException
   {
      String telefone = value;
    
      if (StringUtils.isNotBlank(value))
      {
         telefone = value.replaceAll("-", "");
      }
      
      return telefone;
   }

   public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException
   {
      String telefone = null;
      
      telefone = value.toString();
      
      if (StringUtils.isNotBlank(telefone) && telefone.length() == 8)
      {
         try
         {
            MaskFormatter mf = new MaskFormatter("####-####");
            mf.setValueContainsLiteralCharacters(false);
            telefone = mf.valueToString(telefone);
         }
         catch (ParseException e)
         {
            e.printStackTrace();
         }
      }
      else
      {
        telefone = ""; 
      }
      return telefone;
   }
}
