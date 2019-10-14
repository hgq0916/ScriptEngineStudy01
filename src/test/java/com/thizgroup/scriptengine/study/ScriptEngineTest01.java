package com.thizgroup.scriptengine.study;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleScriptContext;
import org.junit.Test;

/**
 * @author gangquan.hu
 * @Package: com.thizgroup.scriptengine.study.ScriptEngineTest01
 * @Description: TODO
 * @date 2019/10/14 14:44
 */
public class ScriptEngineTest01 {

  @Test
  public void print() throws ScriptException {
    ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
    ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("JavaScript");
    scriptEngine.eval("print('hello,first java script engine program')");
  }

  @Test
  public void obj() throws Exception {
    ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
    ScriptEngine engine = scriptEngineManager.getEngineByName("JavaScript");
    StringBuffer scriptText = new StringBuffer();
    scriptText.append("var obj = new Object();");
    scriptText.append("obj.hello=function(name){");
    scriptText.append("print('hello,'+name);");
    scriptText.append("}");
    engine.eval(scriptText.toString());
    Invocable invocable = (Invocable) engine;
    Object obj = engine.get("obj");
    invocable.invokeMethod(obj,"hello","张三");
  }

  @Test
  public void file() throws Exception {
    ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
    ScriptEngine engine = scriptEngineManager.getEngineByName("JavaScript");
    engine.eval(new FileReader("F:\\study\\project\\ScriptEngineStudy01\\src\\doc\\hello.js"));
    Invocable invocable = (Invocable) engine;
    Object obj = engine.get("obj");
    invocable.invokeMethod(obj,"hello","王五");
  }

  @Test
  public void scriptVar() throws Exception {
    ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
    ScriptEngine engine = scriptEngineManager.getEngineByName("JavaScript");
    File file = new File("F:\\study\\project\\ScriptEngineStudy01\\src\\doc\\hello.js");
    engine.put("files",file);
    engine.eval("print(files.getName())");
    engine.eval("print(files.getPath())");
  }

  @Test
  public void runnableImpl() throws Exception {
    ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
    ScriptEngine engine = scriptEngineManager.getEngineByName("JavaScript");
    String script = "function run(){print('hello,first java script thread method.');}";
    engine.eval(script);
    Invocable invocable = (Invocable) engine;
    Runnable runnable = invocable.getInterface(Runnable.class);
    // 启动一个线程运行上面的实现了runnable接口的script脚本
    Thread thread = new Thread(runnable);
    thread.start();
  }

  @Test
  public void multiScopes() throws Exception {
    ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
    ScriptEngine nashorn = scriptEngineManager.getEngineByName("nashorn");
    nashorn.put("x","helloworld");
    nashorn.eval("print(x)");
    ScriptContext scriptContext = new SimpleScriptContext();
    Bindings bindings = scriptContext.getBindings(ScriptContext.ENGINE_SCOPE);
    bindings.put("x","haha");
    nashorn.eval("print(x)",bindings);
    nashorn.eval("print(x);");
  }
}
