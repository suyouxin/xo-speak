package bitoflife.chatterbean;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import android.content.res.AssetManager;
import android.util.Log;
import bitoflife.chatterbean.parser.AliceBotParser;
import bitoflife.chatterbean.parser.ChatterBeanParser;

public class ChatterBean {
    /** The underlying AliceBot used to produce responses to user queries. */
    private AliceBot aliceBot;
    
    /** Logger object used to keep track of this bot's conversations. */
    private Logger logger;
    private AssetManager mAm;
    
    public ChatterBean(AssetManager am) {
        mAm = am;
    }
    
    /*
    Event Section
    */
    private static final InputStream[] INPUT_STREAM_ARRAY = {};
    
    private void beforeConfigure()
    {
      if (getAliceBot() == null)
        setAliceBot(new AliceBot());
    }
    
    private void afterConfigure()
    {
      Context context = aliceBot.getContext();
      // context.property("javascript.interpreter", javascript);
    }

    public void init()
    {
      try
      {
        beforeConfigure();

        AliceBotParser parser = new AliceBotParser();
        parser.parse(getAliceBot(),
          openStream("context.xml"),
          openStream("splitters.xml"),
          openStream("substitutions.xml"),
          openStreams("robot"));

        afterConfigure();
      }
      catch (ChatterBeanException e)
      {
        throw e;
      }
      catch (Exception e)
      {
        throw new ChatterBeanException(e);
      }
    }

    /*
    Method Section
    */
    
    private InputStream openStream(String name)
    {
      return openURLStream(name);
    }
    
    private InputStream[] openStreams(String name)
    {
        return openAIMLStreams(name);
    }

    private InputStream openURLStream(String path)
    {
      try
      {
          /*
          InputStream is =  mAm.open(path);
          BufferedReader reader = new BufferedReader(new InputStreamReader(is));
          for(;;)
          {
            String input = reader.readLine();
            if (input == null ) break;
            Log.d("text", input);
          }
 */
        return mAm.open(path);
      }
      catch (Exception e)
      {
        throw new ChatterBeanException(e);
      }
    }
    
    private InputStream[] openAIMLStreams(String path)
    {
      try
      {
        List<InputStream> streams = new LinkedList<InputStream>();
        String dirList[] = mAm.list(path);
        if (dirList != null) {   
            for (String dirName : dirList) {
                String fileList[] = mAm.list(path + "/" + dirName);
                for (String fileName : fileList) {    
                if (fileName.endsWith("aiml")) {
                    streams.add(openURLStream(path + "/" + dirName + "/" + fileName));
                    Log.d("text", fileName);
                }
                }
            }
        }
        return streams.toArray(INPUT_STREAM_ARRAY);
      }
      catch (Exception e)
      {
        throw new ChatterBeanException(e);
      }
    }

    /**
    Configures this object with a set of properties.
    
    @param path Path of the properties file.
    */
    public void configure(String path)
    {
      try
      {
        beforeConfigure();
        ChatterBeanParser parser = new ChatterBeanParser();
        parser.parse(this, path);
      }
      catch (Exception e)
      {
        throw new ChatterBeanException(e);
      }
    }
    
    public String respond(String request)
    {
      String response = "";
      if(request != null && !"".equals(request.trim())) try
      {
        response = aliceBot.respond(request);
        if (logger != null)
          logger.append(request, response);
      }
      catch (Exception e)
      {
        throw new RuntimeException(e);
      }

      return response;
    }
    
    /*
    Property Section
    */
    
    /**
    Gets the AliceBot encapsulated by this bot.
    
    @return An AliceBot.
    */
    public AliceBot getAliceBot()
    {
      return aliceBot;
    }
    
    /**
    Sets the AliceBot encapsulated by this bot.
    
    @param aliceBot An AliceBot.
    */
    public void setAliceBot(AliceBot aliceBot)
    {
      this.aliceBot = aliceBot;
    }
    
    /**
    Gets the logger object used by this bot.
    
    @return A Logger object.
    */
    public Logger getLogger()
    {
      return logger;
    }
    
    /**
    Sets the logger object used by this bot.
    
    @param logger A Logger object.
    */
    public void setLogger(Logger logger)
    {
      this.logger = logger;
    }

}