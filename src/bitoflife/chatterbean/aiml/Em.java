package bitoflife.chatterbean.aiml;

import org.xml.sax.Attributes;

import bitoflife.chatterbean.Match;

public class Em extends TemplateElement
{
    /*
    Constructors
    */

    public Em()
    {
    }

    public Em(Attributes attributes)
    {
    }

    /*
    Methods
    */
    
    public boolean equals(Object obj)
    {
      if (!super.equals(obj))
        return false;
      else
        return toString().equals(obj.toString());
    }
    
    public int hashCode()
    {
      return process(null).hashCode();
    }

    public String process(Match match)
    {
      return "Em not implemented";
    }
}