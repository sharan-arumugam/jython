package jyy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.file.Paths;
import java.util.Properties;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleScriptContext;

import org.python.util.PythonInterpreter;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, ScriptException {

		PythonInterpreter interpreter = null;
		try {
			Properties p = new Properties();
			p.setProperty("python.home", Paths.get("jython").toString());
			PythonInterpreter.initialize(System.getProperties(), p, new String[] {});
			interpreter = new PythonInterpreter();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		StringWriter writer = new StringWriter(); // ouput will be stored here

		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptContext context = new SimpleScriptContext();

		Reader targetReader = new FileReader(new File("loopy.py"));

		context.setWriter(writer); // configures output redirection
		ScriptEngine engine = new ScriptEngineManager().getEngineFactories().get(1).getScriptEngine();
		System.out.println(engine);
		engine.eval(targetReader, context);
		System.out.println(writer.toString());

	}

}
