package bitoflife.chatterbean.aiml;

import org.xml.sax.Attributes;

import bitoflife.chatterbean.Match;

public class A extends TemplateElement
{
    /*
    Constructors
    */

    public A()
    {
    }

    public A(Attributes attributes)
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
      return "a not implemented";
    }
}