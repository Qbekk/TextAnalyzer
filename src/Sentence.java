import java.util.List;
import java.util.ArrayList;

public class Sentence extends Item{
	private ArrayList<Item> Words=new ArrayList<Item>();
	private int length;
	Sentence (String content){
	this.content=content;
	makeWords();
	}

private void makeWords(){
	String word;
	String[] words=content.split(" ");
	for (int i=0;i<words.length;i++){
		word=words[i];
		word=word.trim();
		if(word.matches("\\w+"))
		Words.add(new Word(word));
	}
	
}
public String getContent(){
	return content;
}
public ArrayList<Item> getWords() {
	return Words;
}

public void setWords(ArrayList<Item> words) {
	Words = words;
}
}