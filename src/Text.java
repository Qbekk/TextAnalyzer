import java.util.ArrayList;
public class Text extends Item {
	ArrayList<Item> Sentences=new ArrayList<Item>();
	Text(String content){
		this.content=content;
		makeSentences();
		
	}
	private void makeSentences(){
		String[] sentences=content.split("[.?!]");
		for(String sentence:sentences){
			Sentences.add(new Sentence(sentence));
		}
	}
	
	
	public String getContent(){
		return content;
	}
	public ArrayList<Item> getSentences(){
		return Sentences;
	}
}
