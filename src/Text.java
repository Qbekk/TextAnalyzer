import java.util.ArrayList;
public class Text extends Item {
	ArrayList<Sentence> Sentences=new ArrayList<Sentence>();
	Text(String content){
		this.content=content;
		makeSentences();
		
	}
	private void makeSentences(){
		String[] sentences=content.split("[.?!]");
		for(String sentence:sentences){
			sentence=sentence.trim();
			//if( sentence.matches("\\w"))
			Sentences.add(new Sentence(sentence));
		}
	}
	
	
	public String getContent(){
		return content;
	}
	public ArrayList<Sentence> getSentences(){
		return Sentences;
	}
}
