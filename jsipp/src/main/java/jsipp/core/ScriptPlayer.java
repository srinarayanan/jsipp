package jsipp.core;

public class ScriptPlayer {
	
	private String scriptFileName;

	public ScriptPlayer(String scriptFileName) throws Exception {
		
		this.scriptFileName = scriptFileName;
	}

	public void play() throws Exception {

		Script script = new Script(this.scriptFileName);
		script.play();

	}

}
