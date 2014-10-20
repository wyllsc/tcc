package br.com.csempreendimentos.sgi.util.converter;

import java.text.ParseException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.swing.text.MaskFormatter;
import org.apache.commons.lang3.StringUtils;

@FacesConverter("dddConverter")
public class DDDConverter implements Converter
{
   public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException
   {
      String ddd = value;
      
      if (StringUtils.isNotBlank(value))
      {
         ddd = value.replaceAll("(", "").replaceAll(")", "");
      }
      
      return ddd;
   }

   public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException
   {
      String ddd = null;
      
      ddd = value.toString();
      
      if (StringUtils.isNotBlank(ddd) && ddd.length() == 2)
      {
         try
         {
            MaskFormatter mf = new MaskFormatter("(##)");
            mf.setValueContainsLiteralCharacters(false);
            ddd = mf.valueToString(ddd);
         }
         catch (ParseException e)
         {
            e.printStackTrace();
         }
      }
      else
      {
        ddd = ""; 
      }
      return ddd;
   }
}
