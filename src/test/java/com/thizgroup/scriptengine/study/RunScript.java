package com.thizgroup.scriptengine.study;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * @author gangquan.hu
 * @Package: com.thizgroup.scriptengine.study.RunScript
 * @Description: TODO
 * @date 2019/10/14 16:01
 */
public class RunScript {

  private ScriptEngineManager scriptEngineManager;

  private ScriptEngine scriptEngine;

  private File scriptFile;

  public RunScript(String scriptFileName){
    scriptEngineManager = new ScriptEngineManager();
    scriptEngine = scriptEngineManager.getEngineByName("JavaScript");
    scriptFile = new File(scriptFileName);
  }

  public void setVal(String key,Object value){
    scriptEngine.put(key,value);
  }

  public void start() throws Exception {
    scriptEngine.eval(new FileReader(scriptFile));
  }

}
