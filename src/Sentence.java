import java.util.List;
import java.util.ArrayList;

public class Sentence extends Item{
	private ArrayList<Word> Words=new ArrayList<Word>();
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
		if(word.matches("\\S+"))
		Words.add(new Word(word));
	}
	
}
public String getContent(){
	return content;
}
public ArrayList<Word> getWords() {
	return Words;
}


}